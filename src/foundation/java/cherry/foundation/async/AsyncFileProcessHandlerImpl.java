/*
 * Copyright 2014 agwlvssainokuni
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cherry.foundation.async;

import static java.io.File.createTempFile;
import static java.text.MessageFormat.format;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDateTime;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.web.multipart.MultipartFile;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.goods.log.Log;
import cherry.goods.log.LogFactory;

import com.google.common.io.ByteStreams;

/**
 * 非同期実行フレームワーク。<br />
 * 非同期にファイル処理を実行する仕組みを提供する。ファイル処理の実体は業務ロジックごとに{@link FileProcessHandler}
 * を実装することとする。
 */
public class AsyncFileProcessHandlerImpl implements AsyncFileProcessHandler,
		ApplicationContextAware {

	private static final String ASYNCID = "asyncId";
	private static final String FILE = "file";
	private static final String NAME = "name";
	private static final String ORIGINAL_FILENAME = "originalFilename";
	private static final String CONTENT_TYPE = "contentType";
	private static final String SIZE = "size";
	private static final String HANDLER_NAME = "handlerName";

	private final Log log = LogFactory.getLog(getClass());

	private BizDateTime bizDateTime;

	private AsyncProcessStore asyncProcessStore;

	private JmsOperations jmsOperations;

	private ApplicationContext applicationContext;

	private File tempDir;

	private String tempPrefix;

	private String tempSuffix;

	private MessagePostProcessor messagePostProcessor;

	public void setBizDateTime(BizDateTime bizDateTime) {
		this.bizDateTime = bizDateTime;
	}

	public void setAsyncProcessStore(AsyncProcessStore asyncProcessStore) {
		this.asyncProcessStore = asyncProcessStore;
	}

	public void setJmsOperations(JmsOperations jmsOperations) {
		this.jmsOperations = jmsOperations;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void setTempDir(File tempDir) {
		this.tempDir = tempDir;
	}

	public void setTempPrefix(String tempPrefix) {
		this.tempPrefix = tempPrefix;
	}

	public void setTempSuffix(String tempSuffix) {
		this.tempSuffix = tempSuffix;
	}

	public void setMessagePostProcessor(
			MessagePostProcessor messagePostProcessor) {
		this.messagePostProcessor = messagePostProcessor;
	}

	/**
	 * 非同期のファイル処理を実行登録する。
	 *
	 * @param launcherId
	 *            非同期処理の実行者のID。
	 * @param description
	 *            内容表記。
	 * @param file
	 *            処理対象のファイル。
	 * @param handlerName
	 *            非同期のファイル処理の処理を実装したBeanの名前。同Beanは{@link FileProcessHandler}
	 *            を実装しなければならない。
	 * @param args
	 *            引数。
	 * @return 非同期実行状況の管理データのID。
	 */
	@Override
	public long launchFileProcess(String launcherId, String description,
			MultipartFile file, String handlerName, String... args) {

		long asyncId = asyncProcessStore.createFileProcess(launcherId,
				bizDateTime.now(), description, file.getName(),
				file.getOriginalFilename(), file.getContentType(),
				file.getSize(), handlerName, args);
		try {

			File tempFile = createFile(file);

			Map<String, String> message = new HashMap<>();
			message.put(ASYNCID, String.valueOf(asyncId));
			message.put(FILE, tempFile.getAbsolutePath());
			message.put(NAME, file.getName());
			message.put(ORIGINAL_FILENAME, file.getOriginalFilename());
			message.put(CONTENT_TYPE, file.getContentType());
			message.put(SIZE, String.valueOf(file.getSize()));
			message.put(HANDLER_NAME, handlerName);
			for (int i = 0; i < args.length; i++) {
				message.put(String.valueOf(i), args[i]);
			}
			jmsOperations.convertAndSend(message, messagePostProcessor);

			asyncProcessStore.updateToLaunched(asyncId, bizDateTime.now());
			return asyncId;
		} catch (IOException ex) {
			asyncProcessStore.finishWithException(asyncId, bizDateTime.now(),
					ex);
			throw new IllegalStateException(ex);
		}
	}

	/**
	 * 実行登録したファイル処理を実行する。<br />
	 * 本メソッドはコンテナが呼出すことを意図するものである。
	 *
	 * @param message
	 *            {@link #launchFileProcess(String, MultipartFile, String)}
	 *            において登録した内容がコンテナから受渡される。
	 */
	@Override
	public void handleMessage(Map<String, String> message) {

		long asyncId = Long.parseLong(message.get(ASYNCID));
		File tempFile = new File(message.get(FILE));
		String name = message.get(NAME);
		String originalFilename = message.get(ORIGINAL_FILENAME);
		String contentType = message.get(CONTENT_TYPE);
		long size = Long.parseLong(message.get(SIZE));
		String handlerName = message.get(HANDLER_NAME);

		List<String> args = new ArrayList<>();
		for (int i = 0;; i++) {
			String v = message.get(String.valueOf(i));
			if (v == null) {
				break;
			}
			args.add(v);
		}

		asyncProcessStore.updateToProcessing(asyncId, bizDateTime.now());
		try {

			FileProcessHandler handler = applicationContext.getBean(
					handlerName, FileProcessHandler.class);
			FileProcessResult result = handler.handleFile(tempFile, name,
					originalFilename, contentType, size, asyncId,
					args.toArray(new String[args.size()]));

			AsyncStatus status;
			if (result.getTotalCount() == result.getOkCount()) {
				status = AsyncStatus.SUCCESS;
			} else if (result.getTotalCount() == result.getNgCount()) {
				status = AsyncStatus.ERROR;
			} else {
				status = AsyncStatus.WARN;
			}

			asyncProcessStore.finishFileProcess(asyncId, bizDateTime.now(),
					status, result);
		} catch (Exception ex) {
			asyncProcessStore.finishWithException(asyncId, bizDateTime.now(),
					ex);
		} finally {
			deleteFile(tempFile);
		}
	}

	private File createFile(MultipartFile file) throws IOException {
		File tempFile = createTempFile(
				format(tempPrefix, LocalDateTime.now().toDate()), tempSuffix,
				tempDir);
		tempFile.deleteOnExit();
		try {
			try (InputStream in = file.getInputStream();
					OutputStream out = new FileOutputStream(tempFile)) {
				ByteStreams.copy(in, out);
				return tempFile;
			}
		} catch (IOException ex) {
			deleteFile(tempFile);
			throw ex;
		}
	}

	private void deleteFile(File file) {
		if (!file.delete()) {
			log.debug("failed to delete a temporary file: {0}",
					file.getAbsolutePath());
		}
	}

}
