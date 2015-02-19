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

package cherry.foundation.mail;

import java.io.File;

import javax.activation.DataSource;
import javax.mail.MessagingException;

import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.MimeMessageHelper;

public class Attachment {

	private MimeMessageHelper mimeMessageHelper;

	public Attachment(MimeMessageHelper mimeMessageHelper) {
		this.mimeMessageHelper = mimeMessageHelper;
	}

	public void add(String filename, DataSource dataSource) throws MessagingException {
		mimeMessageHelper.addAttachment(filename, dataSource);
	}

	public void add(String filename, File file) throws MessagingException {
		mimeMessageHelper.addAttachment(filename, file);
	}

	public void add(String filename, InputStreamSource source) throws MessagingException {
		mimeMessageHelper.addAttachment(filename, source);
	}

	public void add(String filename, InputStreamSource source, String contentType) throws MessagingException {
		mimeMessageHelper.addAttachment(filename, source, contentType);
	}

}
