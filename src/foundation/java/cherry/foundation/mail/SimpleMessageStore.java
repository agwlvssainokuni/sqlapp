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

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.joda.time.LocalDateTime;
import org.springframework.mail.SimpleMailMessage;

public class SimpleMessageStore implements MessageStore {

	private AtomicLong nextMessageId = new AtomicLong(0L);

	private Map<Long, MessageRecord> messageRecordMap = Collections
			.synchronizedMap(new LinkedHashMap<Long, MessageRecord>());

	@Override
	public long createMessage(String launcherId, String messageName,
			LocalDateTime scheduledAt, String from, List<String> to,
			List<String> cc, List<String> bcc, String subject, String body) {

		long messageId = nextMessageId.getAndIncrement();

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(toArray(to));
		message.setCc(toArray(cc));
		message.setBcc(toArray(bcc));
		message.setSubject(subject);
		message.setText(body);

		MessageRecord record = new MessageRecord();
		record.setScheduledAt(scheduledAt);
		record.setSimpleMailMessage(message);
		messageRecordMap.put(messageId, record);

		return messageId;
	}

	@Override
	public List<Long> listMessage(LocalDateTime dtm) {
		List<Long> list = new LinkedList<>();
		for (Map.Entry<Long, MessageRecord> entry : messageRecordMap.entrySet()) {
			if (entry.getValue().getScheduledAt().compareTo(dtm) <= 0) {
				list.add(entry.getKey());
			}
		}
		return list;
	}

	@Override
	public SimpleMailMessage getMessage(long messageId) {
		MessageRecord record = messageRecordMap.get(messageId);
		if (record == null) {
			return null;
		}
		return record.getSimpleMailMessage();
	}

	@Override
	public void finishMessage(long messageId) {
		messageRecordMap.remove(messageId);
	}

	private String[] toArray(List<String> list) {
		if (list == null) {
			return null;
		}
		return list.toArray(new String[list.size()]);
	}

	public static class MessageRecord {

		private LocalDateTime scheduledAt;

		private SimpleMailMessage simpleMailMessage;

		public LocalDateTime getScheduledAt() {
			return scheduledAt;
		}

		public void setScheduledAt(LocalDateTime scheduledAt) {
			this.scheduledAt = scheduledAt;
		}

		public SimpleMailMessage getSimpleMailMessage() {
			return simpleMailMessage;
		}

		public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
			this.simpleMailMessage = simpleMailMessage;
		}
	}

}
