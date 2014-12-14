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

package cherry.foundation.mail;

import java.util.List;

import org.joda.time.LocalDateTime;

import cherry.foundation.bizdtm.BizDateTime;

public class MailFacadeImpl implements MailFacade {

	private BizDateTime bizDateTime;

	private MailDataHandler mailDataHandler;

	private MailSendHandler mailSendHandler;

	public void setBizDateTime(BizDateTime bizDateTime) {
		this.bizDateTime = bizDateTime;
	}

	public void setMailDataHandler(MailDataHandler mailDataHandler) {
		this.mailDataHandler = mailDataHandler;
	}

	public void setMailSendHandler(MailSendHandler mailSendHandler) {
		this.mailSendHandler = mailSendHandler;
	}

	@Override
	public MailData createMailData(String templateName, String to,
			MailModel mailModel) {
		return mailDataHandler.createMailData(templateName, to, mailModel);
	}

	@Override
	public long send(String launcherId, String messageName, String from,
			List<String> to, List<String> cc, List<String> bcc, String subject,
			String body) {
		return mailSendHandler.sendLater(launcherId, messageName, from, to, cc,
				bcc, subject, body, bizDateTime.now());
	}

	@Override
	public long sendLater(String launcherId, String messageName, String from,
			List<String> to, List<String> cc, List<String> bcc, String subject,
			String body, LocalDateTime scheduledAt) {
		return mailSendHandler.sendLater(launcherId, messageName, from, to, cc,
				bcc, subject, body, scheduledAt);
	}

	@Override
	public long sendNow(String launcherId, String messageName, String from,
			List<String> to, List<String> cc, List<String> bcc, String subject,
			String body) {
		return mailSendHandler.sendNow(launcherId, messageName, from, to, cc,
				bcc, subject, body);
	}

}
