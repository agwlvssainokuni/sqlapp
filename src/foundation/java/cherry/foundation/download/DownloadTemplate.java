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

package cherry.foundation.download;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadTemplate implements DownloadOperation {

	public static final String OPER_DOWNLOAD = "operation.DOWNLOAD";

	private final Logger loggerOper = LoggerFactory.getLogger(OPER_DOWNLOAD);

	private String headerName;

	private String headerValue;

	private DateTimeFormatter formatter;

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}

	public void setFormatter(DateTimeFormatter formatter) {
		this.formatter = formatter;
	}

	@Override
	public void download(HttpServletResponse response, String contentType, Charset charset, String filename,
			LocalDateTime timestamp, DownloadAction action) {

		String fname = MessageFormat.format(filename, formatter.print(timestamp));

		response.setContentType(contentType);
		if (charset != null) {
			response.setCharacterEncoding(charset.name());
		}
		String value = MessageFormat.format(headerValue, fname);
		response.setHeader(headerName, value);

		loggerOper.info("STARTING: Content-Type={}, charset={}, {}={}", contentType,
				charset == null ? "" : charset.name(), headerName, value);

		try (OutputStream out = response.getOutputStream()) {

			long count = action.doDownload(out);

			loggerOper.info("COMPLETED: {} items", count);

		} catch (IOException ex) {
			loggerOper.warn("FAILED WITH I/O EXCEPTION", ex);
			throw new IllegalStateException(ex);
		}
	}

}
