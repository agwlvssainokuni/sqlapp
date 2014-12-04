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

package cherry.foundation.download;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;

import cherry.goods.log.Log;
import cherry.goods.log.LogFactory;

public class DownloadTemplate implements DownloadOperation {

	private final Log log = LogFactory.getLog(getClass());

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
	public void download(HttpServletResponse response, String contentType,
			Charset charset, String filename, LocalDateTime timestamp,
			DownloadAction action) {

		String fname = MessageFormat.format(filename,
				formatter.print(timestamp));

		if (log.isDebugEnabled()) {
			log.debug("Setting response headers: contentType={0}", contentType);
			log.debug("Setting response headers:     charset={0}",
					charset == null ? "" : charset.name());
			log.debug("Setting response headers:  headerName={0}", headerName);
			log.debug("Setting response headers: headerValue={0}", headerValue);
			log.debug("Setting response headers:    filename={0}", fname);
		}

		response.setContentType(contentType);
		if (charset != null) {
			response.setCharacterEncoding(charset.name());
		}
		response.setHeader(headerName, MessageFormat.format(headerValue, fname));

		if (log.isDebugEnabled()) {
			log.debug("Download action starting.");
		}

		try (OutputStream out = response.getOutputStream()) {

			long count = action.doDownload(out);

			if (log.isDebugEnabled()) {
				log.debug("Download action completed: result={0}", count);
			}
		} catch (IOException ex) {
			if (log.isDebugEnabled()) {
				log.debug(ex, "Download action failed.");
			}
			throw new IllegalStateException(ex);
		}
	}

}
