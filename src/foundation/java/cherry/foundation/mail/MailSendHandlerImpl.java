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
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.bizdtm.BizDateTime;

public class MailSendHandlerImpl implements MailSendHandler {

	private BizDateTime bizDateTime;

	private MessageStore messageStore;

	private MailSender mailSender;

	public void setBizDateTime(BizDateTime bizDateTime) {
		this.bizDateTime = bizDateTime;
	}

	public void setMessageStore(MessageStore messageStore) {
		this.messageStore = messageStore;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Transactional
	@Override
	public long sendLater(String launcherId, String messageName, String from,
			List<String> to, List<String> cc, List<String> bcc, String subject,
			String body, LocalDateTime scheduledAt) {
		return messageStore.createMessage(launcherId, messageName, scheduledAt,
				from, to, cc, bcc, subject, body);
	}

	@Transactional
	@Override
	public long sendNow(String launcherId, String messageName, String from,
			List<String> to, List<String> cc, List<String> bcc, String subject,
			String body) {
		LocalDateTime now = bizDateTime.now();
		long messageId = messageStore.createMessage(launcherId, messageName,
				now, from, to, cc, bcc, subject, body);
		SimpleMailMessage msg = messageStore.getMessage(messageId);
		messageStore.finishMessage(messageId);
		mailSender.send(msg);
		return messageId;
	}

	@Transactional
	@Override
	public List<Long> listMessage(LocalDateTime dtm) {
		return messageStore.listMessage(dtm);
	}

	@Transactional
	@Override
	public boolean sendMessage(long messageId) {
		SimpleMailMessage msg = messageStore.getMessage(messageId);
		if (msg == null) {
			return false;
		}
		messageStore.finishMessage(messageId);
		mailSender.send(msg);
		return true;
	}

}
