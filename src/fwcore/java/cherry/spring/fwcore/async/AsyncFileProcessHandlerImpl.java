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

package cherry.spring.fwcore.async;

import static java.io.File.createTempFile;
import static java.text.MessageFormat.format;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.web.multipart.MultipartFile;

import cherry.goods.log.Log;
import cherry.goods.log.LogFactory;
import cherry.spring.fwcore.bizdtm.BizDateTime;

/**
 * 非同期実行フレームワーク。<br />
 * 非同期にファイル処理を実行する仕組みを提供する。ファイル処理の実体は業務ロジックごとに{@link FileProcessHandler}
 * を実装することとする。
 */
public class AsyncFileProcessHandlerImpl implements AsyncFileProcessHandler {

	private static final String TYPE_NAME = "type";
	private static final String TYPE_VALUE = "AsyncFileProcessHandler";
	private static final String TYPE_SELECTOR = "type = 'AsyncFileProcessHandler'";

	private static final String ASYNCID = "asyncId";
	private static final String FILE = "file";
	private static final String NAME = "name";
	private static final String ORIGINAL_FILENAME = "originalFilename";
	private static final String CONTENT_TYPE = "contentType";
	private static final String SIZE = "size";
	private static final String HANDLER_NAME = "handlerName";

	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	private BizDateTime bizDateTime;

	@Autowired
	private AsyncStatusStore asyncStatusStore;

	@Autowired
	private JmsOperations jmsOperations;

	@Autowired
	private ApplicationContext applicationContext;

	@Value("${fwcore.async.queue}")
	private String queue;

	@Value("${fwcore.async.file.tempDir}")
	private File tempDir;

	@Value("${fwcore.async.file.tempPrefix}")
	private String tempPrefix;

	@Value("${fwcore.async.file.tempSuffix}")
	private String tempSuffix;

	private MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
		@Override
		public Message postProcessMessage(Message message) throws JMSException {
			message.setStringProperty(TYPE_NAME, TYPE_VALUE);
			return message;
		}
	};

	/**
	 * 非同期のファイル処理を実行登録する。
	 * 
	 * @param launcherId
	 *            非同期処理の実行者のID。
	 * @param file
	 *            処理対象のファイル。
	 * @param handlerName
	 *            非同期のファイル処理の処理を実装したBeanの名前。同Beanは{@link FileProcessHandler}
	 *            を実装しなければならない。
	 * @return 非同期実行状況の管理データのID。
	 */
	@Override
	public long launchFileProcess(String launcherId, MultipartFile file,
			String handlerName) {

		long asyncId = asyncStatusStore.createFileProcess(launcherId,
				bizDateTime.now(), file.getName(), file.getOriginalFilename(),
				file.getContentType(), file.getSize(), handlerName);
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
			jmsOperations.convertAndSend(queue, message, messagePostProcessor);

			asyncStatusStore.updateToLaunched(asyncId, bizDateTime.now());
			return asyncId;
		} catch (IOException ex) {
			asyncStatusStore
					.finishWithException(asyncId, bizDateTime.now(), ex);
			throw new IllegalStateException(ex);
		}
	}

	/**
	 * 実行登録したファイル処理を実行する。<br />
	 * 本メソッドはコンテナが呼出すことを意図するものであり、{@link JmsListener}アノテーションを付与する。
	 * 
	 * @param message
	 *            {@link #launchFileProcess(String, MultipartFile, String)}
	 *            において登録した内容がコンテナから受渡される。
	 */
	@JmsListener(destination = "${fwcore.async.queue}", selector = TYPE_SELECTOR)
	@Override
	public void handleMessage(Map<String, String> message) {

		long asyncId = Long.parseLong(message.get(ASYNCID));
		File tempFile = new File(message.get(FILE));
		String name = message.get(NAME);
		String originalFilename = message.get(ORIGINAL_FILENAME);
		String contentType = message.get(CONTENT_TYPE);
		long size = Long.parseLong(message.get(SIZE));
		String handlerName = message.get(HANDLER_NAME);

		asyncStatusStore.updateToProcessing(asyncId, bizDateTime.now());
		try {

			FileProcessHandler handler = applicationContext.getBean(
					handlerName, FileProcessHandler.class);
			FileProcessResult result = handler.handleFile(tempFile, name,
					originalFilename, contentType, size, asyncId);

			AsyncStatus status;
			if (result.getTotalCount() == result.getOkCount()) {
				status = AsyncStatus.SUCCESS;
			} else if (result.getTotalCount() == result.getNgCount()) {
				status = AsyncStatus.ERROR;
			} else {
				status = AsyncStatus.WARN;
			}

			asyncStatusStore.finishFileProcess(asyncId, bizDateTime.now(),
					status, result);
		} catch (Exception ex) {
			asyncStatusStore
					.finishWithException(asyncId, bizDateTime.now(), ex);
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
				byte[] buff = new byte[4096];
				int size;
				while ((size = in.read(buff, 0, buff.length)) >= 0) {
					out.write(buff, 0, size);
				}
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
