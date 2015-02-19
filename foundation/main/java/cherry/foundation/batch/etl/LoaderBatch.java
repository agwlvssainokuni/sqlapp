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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.sql.DataSource;

import cherry.foundation.batch.ExitStatus;
import cherry.foundation.batch.IBatch;
import cherry.foundation.etl.CsvProvider;
import cherry.foundation.etl.LoadResult;
import cherry.foundation.etl.Loader;
import cherry.foundation.etl.NoneLimiter;
import cherry.foundation.etl.Provider;
import cherry.goods.log.Log;
import cherry.goods.log.LogFactory;

public class LoaderBatch implements IBatch {

	private final Log log = LogFactory.getLog(getClass());

	private String query;

	private File file;

	private Charset charset = StandardCharsets.UTF_8;

	private boolean paramByHeader = true;

	private DataSource dataSource;

	private Loader loader;

	public void setQuery(String query) {
		this.query = query;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	public void setParamByHeader(boolean paramByHeader) {
		this.paramByHeader = paramByHeader;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setLoader(Loader loader) {
		this.loader = loader;
	}

	@Override
	public ExitStatus execute(String... args) {

		if (log.isDebugEnabled()) {
			log.debug("File    {0}", file.getAbsolutePath());
			log.debug("Charset {0}", charset.name());
			log.debug("Query   {0}", query);
		}

		try (InputStream in = new FileInputStream(file); Reader reader = new InputStreamReader(in, charset)) {

			Provider provider = new CsvProvider(reader, paramByHeader);
			LoadResult result = loader.load(dataSource, query, provider, new NoneLimiter());

			if (log.isDebugEnabled()) {
				log.debug("Result  {0}", result.toString());
			}

			if (result.getTotalCount() <= 0) {
				return ExitStatus.NORMAL;
			} else if (result.getTotalCount() <= result.getSuccessCount()) {
				return ExitStatus.NORMAL;
			} else if (result.getTotalCount() <= result.getFailedCount()) {
				return ExitStatus.ERROR;
			} else {
				return ExitStatus.WARN;
			}
		} catch (IOException ex) {
			if (log.isDebugEnabled()) {
				log.debug(ex, "Failed for file: {0}", file.getAbsolutePath());
			}
			return ExitStatus.ERROR;
		}
	}

}
