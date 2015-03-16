/*
 * Copyright 2014,2015 agwlvssainokuni
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

package cherry.foundation.batch.etl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import cherry.foundation.batch.ExitStatus;
import cherry.foundation.batch.IBatch;
import cherry.foundation.etl.Consumer;
import cherry.foundation.etl.CsvConsumer;
import cherry.foundation.etl.Extractor;
import cherry.foundation.etl.NoneLimiter;
import cherry.goods.log.Log;
import cherry.goods.log.LogFactory;

public class ExtractorBatch implements IBatch {

	private final Log log = LogFactory.getLog(getClass());

	private String query;

	private File file;

	private Charset charset = StandardCharsets.UTF_8;

	private boolean withHeader = true;

	private Map<String, Object> paramMap = new HashMap<>();

	private DataSource dataSource;

	private Extractor extractor;

	public void setQuery(String query) {
		this.query = query;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	public void setWithHeader(boolean withHeader) {
		this.withHeader = withHeader;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setExtractor(Extractor extractor) {
		this.extractor = extractor;
	}

	@Override
	public ExitStatus execute(String... args) {

		if (log.isDebugEnabled()) {
			log.debug("File    {0}", file.getAbsolutePath());
			log.debug("Charset {0}", charset.name());
			log.debug("Query   {0}", query);
			log.debug("Param   {0}", paramMap.toString());
		}

		try (OutputStream out = new FileOutputStream(file); Writer writer = new OutputStreamWriter(out, charset)) {

			Consumer consumer = new CsvConsumer(writer, withHeader);
			long count = extractor.extract(dataSource, query, paramMap, consumer, new NoneLimiter());

			if (log.isDebugEnabled()) {
				log.debug("Reult   {0}", count);
			}

			return ExitStatus.NORMAL;
		} catch (IOException ex) {
			if (log.isDebugEnabled()) {
				log.debug(ex, "Filed for file {0}", file.getAbsolutePath());
			}
			return ExitStatus.ERROR;
		}
	}

}
