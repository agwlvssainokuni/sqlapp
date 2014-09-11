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

package cherry.sqlapp.service.sqltool.exec;

import static java.io.File.createTempFile;
import static java.lang.Integer.parseInt;
import static java.text.MessageFormat.format;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.multipart.MultipartFile;

import cherry.spring.common.helper.async.AsyncProcHelper;
import cherry.spring.common.helper.json.JsonHelper;
import cherry.spring.common.lib.etl.CsvProvider;
import cherry.spring.common.lib.etl.LimiterException;
import cherry.spring.common.lib.etl.LoadResult;
import cherry.spring.common.lib.etl.Loader;
import cherry.spring.common.lib.etl.NoneLimiter;
import cherry.spring.common.log.Log;
import cherry.spring.common.log.LogFactory;
import cherry.sqlapp.service.sqltool.DataSourceDef;

@Component
public class ExecLoadServiceImpl implements ExecLoadService {

	public static final String PROC_ID = "procId";

	public static final String DATABASE_NAME = "databaseName";

	public static final String SQL = "sql";

	public static final String TEMP_FILE = "tempFile";

	public static final String LAUNCHER_ID = "launcherId";

	private final Log log = LogFactory.getLog(getClass());

	@Value("${sqlapp.app.import.tempDir}")
	private File tempDir;

	@Value("${sqlapp.app.import.tempPrefix}")
	private String prefix;

	@Value("${sqlapp.app.import.tempSuffix}")
	private String suffix;

	@Value("${sqlapp.app.import.charset}")
	private Charset charset;

	@Value("${sqlapp.app.import.queue}")
	private String queue;

	@Autowired
	private AsyncProcHelper asyncProcHelper;

	@Autowired
	private JsonHelper jsonHelper;

	@Autowired
	private DataSourceDef dataSourceDef;

	@Autowired
	private Loader loader;

	@Autowired
	private JmsOperations jmsOperations;

	@Transactional
	@Override
	public Map<String, String> launch(String databaseName, String sql,
			MultipartFile file, String launcherId) {
		String name = ExecLoadService.class.getSimpleName();
		Integer procId = asyncProcHelper.createAsyncProc(name, launcherId);
		try {
			File tempFile = createFile(file);

			Map<String, String> message = new HashMap<>();
			message.put(PROC_ID, procId.toString());
			message.put(DATABASE_NAME, databaseName);
			message.put(SQL, sql);
			message.put(TEMP_FILE, tempFile.getAbsolutePath());
			message.put(LAUNCHER_ID, launcherId);
			jmsOperations.convertAndSend(queue, message);

			asyncProcHelper.invokeAsyncProc(procId);

			return message;
		} catch (IOException ex) {
			asyncProcHelper
					.errorAsyncProc(procId, jsonHelper.fromThrowable(ex));
			throw new IllegalStateException(ex);
		}
	}

	@JmsListener(destination = "${sqlapp.app.import.queue}")
	@Transactional
	@Override
	public void handle(Map<String, String> message) {
		Integer procId = parseInt(message.get(PROC_ID));
		String databaseName = message.get(DATABASE_NAME);
		DataSource dataSource = dataSourceDef.getDataSource(databaseName);
		String sql = message.get(SQL);
		File tempFile = new File(message.get(TEMP_FILE));
		try {
			asyncProcHelper.startAsyncProc(procId);

			LoadResult loadResult = loadFile(dataSource, sql, tempFile);

			Map<String, Integer> map = new HashMap<>();
			map.put("total", loadResult.getTotalCount());
			map.put("success", loadResult.getSuccessCount());
			map.put("failed", loadResult.getFailedCount());
			asyncProcHelper.successAsyncProc(procId, jsonHelper.fromMap(map));

		} catch (DataAccessException | LimiterException ex) {
			asyncProcHelper
					.errorAsyncProc(procId, jsonHelper.fromThrowable(ex));
			throw ex;
		} catch (IOException ex) {
			asyncProcHelper
					.errorAsyncProc(procId, jsonHelper.fromThrowable(ex));
			throw new IllegalStateException(ex);
		} finally {
			if (!tempFile.delete()) {
				log.debug("failed to delete a temporary file: {0}",
						tempFile.getAbsolutePath());
			}
		}
	}

	private File createFile(MultipartFile file) throws IOException {
		File tempFile = createTempFile(
				format(prefix, LocalDateTime.now().toDate()), suffix, tempDir);
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
			if (!tempFile.delete()) {
				log.debug("failed to delete a temporary file: {0}",
						tempFile.getAbsolutePath());
			}
			throw ex;
		}
	}

	private LoadResult loadFile(DataSource dataSource, String sql, File file)
			throws IOException {

		PlatformTransactionManager txMgr = new DataSourceTransactionManager(
				dataSource);
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus status = txMgr.getTransaction(txDef);

		try (InputStream in = new FileInputStream(file);
				Reader reader = new InputStreamReader(in, charset)) {

			CsvProvider provider = new CsvProvider(reader, true);
			LoadResult result = loader.load(dataSource, sql, provider,
					new NoneLimiter());

			txMgr.commit(status);

			return result;
		} catch (IOException | RuntimeException ex) {
			txMgr.rollback(status);
			throw ex;
		}
	}

}
