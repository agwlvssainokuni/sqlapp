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

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletResponse;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import cherry.spring.common.helper.bizdate.BizdateHelper;

public class DownloadHelperImpl implements DownloadHelper, InitializingBean {

	@Value("${common.helper.download.charset}")
	private Charset charset;

	@Value("${common.helper.download.headerName}")
	private String headerName;

	@Value("${common.helper.download.headerValue}")
	private String headerValue;

	@Value("${common.helper.download.format}")
	private String format;

	@Autowired
	private BizdateHelper bizdateHelper;

	private DateTimeFormatter formatter;

	@Autowired
	public void afterPropertiesSet() {
		formatter = DateTimeFormat.forPattern(format);
	}

	@Override
	public void download(HttpServletResponse response, String contentType,
			String filename, DownloadAction action) {

		String ts = formatter.print(bizdateHelper.now());

		response.setContentType(contentType);
		response.setCharacterEncoding(charset.name());
		String fname = MessageFormat.format(filename, ts);
		response.setHeader(headerName, MessageFormat.format(headerValue, fname));

		try (OutputStream out = response.getOutputStream();
				Writer writer = new OutputStreamWriter(out, charset)) {
			action.doDownload(writer);
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

}
