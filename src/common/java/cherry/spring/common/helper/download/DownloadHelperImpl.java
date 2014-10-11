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

package cherry.spring.common.helper.download;

import static java.text.MessageFormat.format;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Value;

import cherry.spring.common.log.Log;
import cherry.spring.common.log.LogFactory;

public class DownloadHelperImpl implements DownloadHelper {

	private final Log log = LogFactory.getLog(getClass());

	@Value("${common.helper.download.charset}")
	private Charset charset;

	@Value("${common.helper.download.headerName}")
	private String headerName;

	@Value("${common.helper.download.headerValue}")
	private String headerValue;

	@Value("${common.helper.download.format}")
	private DateTimeFormatter formatter;

	@Override
	public void download(HttpServletResponse response, String contentType,
			String filename, LocalDateTime timestamp, DownloadAction action) {

		String fname = format(filename, formatter.print(timestamp));

		if (log.isDebugEnabled()) {
			log.debug("Setting response headers: contentType={0}", contentType);
			log.debug("Setting response headers:     charset={0}",
					charset.name());
			log.debug("Setting response headers:  headerName={0}", headerName);
			log.debug("Setting response headers: headerValue={0}", headerValue);
			log.debug("Setting response headers:    filename={0}", fname);
		}

		response.setContentType(contentType);
		response.setCharacterEncoding(charset.name());
		response.setHeader(headerName, format(headerValue, fname));

		if (log.isDebugEnabled()) {
			log.debug("Download action starting.");
		}

		try (OutputStream out = response.getOutputStream();
				Writer writer = new OutputStreamWriter(out, charset)) {

			long count = action.doDownload(writer);

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
