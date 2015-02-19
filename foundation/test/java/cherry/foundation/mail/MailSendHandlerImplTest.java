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

import static java.util.Arrays.asList;
import static javax.mail.internet.InternetAddress.parse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import cherry.foundation.bizdtm.BizDateTime;

import com.google.common.io.ByteStreams;

public class MailSendHandlerImplTest {

	private JavaMailSender mailSender;

	@Test
	public void testSendLater() {
		LocalDateTime now = LocalDateTime.now();
		MailSendHandler handler = create(now);

		long messageId = handler.sendLater("loginId", "messageName", "from@addr", asList("to@addr"), asList("cc@addr"),
				asList("bcc@addr"), "subject", "body", now);
		assertEquals(0L, messageId);

		List<Long> list = handler.listMessage(now);
		assertEquals(1, list.size());
		assertEquals(0L, list.get(0).longValue());

		ArgumentCaptor<SimpleMailMessage> message = ArgumentCaptor.forClass(SimpleMailMessage.class);
		doNothing().when(mailSender).send(message.capture());
		boolean first = handler.sendMessage(0L);

		assertTrue(first);
		assertEquals("from@addr", message.getValue().getFrom());
		assertEquals(1, message.getValue().getTo().length);
		assertEquals("to@addr", message.getValue().getTo()[0]);
		assertEquals(1, message.getValue().getCc().length);
		assertEquals("cc@addr", message.getValue().getCc()[0]);
		assertEquals(1, message.getValue().getBcc().length);
		assertEquals("bcc@addr", message.getValue().getBcc()[0]);
		assertEquals("subject", message.getValue().getSubject());
		assertEquals("body", message.getValue().getText());

		boolean second = handler.sendMessage(0L);
		assertFalse(second);
	}

	@Test
	public void testSendNow() {
		LocalDateTime now = LocalDateTime.now();
		MailSendHandler handler = create(now);

		ArgumentCaptor<SimpleMailMessage> message = ArgumentCaptor.forClass(SimpleMailMessage.class);
		doNothing().when(mailSender).send(message.capture());

		long messageId = handler.sendNow("loginId", "messageName", "from@addr", asList("to@addr"), asList("cc@addr"),
				asList("bcc@addr"), "subject", "body");
		assertEquals(0L, messageId);

		assertEquals("from@addr", message.getValue().getFrom());
		assertEquals(1, message.getValue().getTo().length);
		assertEquals("to@addr", message.getValue().getTo()[0]);
		assertEquals(1, message.getValue().getCc().length);
		assertEquals("cc@addr", message.getValue().getCc()[0]);
		assertEquals(1, message.getValue().getBcc().length);
		assertEquals("bcc@addr", message.getValue().getBcc()[0]);
		assertEquals("subject", message.getValue().getSubject());
		assertEquals("body", message.getValue().getText());

		boolean result = handler.sendMessage(0L);
		assertFalse(result);
	}

	@Test
	public void testSendNowAttached() throws Exception {
		LocalDateTime now = LocalDateTime.now();
		MailSendHandler handler = create(now);

		ArgumentCaptor<MimeMessagePreparator> preparator = ArgumentCaptor.forClass(MimeMessagePreparator.class);
		doNothing().when(mailSender).send(preparator.capture());

		final File file = File.createTempFile("test_", ".txt", new File("."));
		file.deleteOnExit();
		try {

			try (OutputStream out = new FileOutputStream(file)) {
				out.write("attach2".getBytes());
			}

			final DataSource dataSource = new DataSource() {
				@Override
				public OutputStream getOutputStream() throws IOException {
					return null;
				}

				@Override
				public String getName() {
					return "name3.txt";
				}

				@Override
				public InputStream getInputStream() throws IOException {
					return new ByteArrayInputStream("attach3".getBytes());
				}

				@Override
				public String getContentType() {
					return "text/plain";
				}
			};

			long messageId = handler.sendNow("loginId", "messageName", "from@addr", asList("to@addr"),
					asList("cc@addr"), asList("bcc@addr"), "subject", "body", new AttachmentPreparator() {
						@Override
						public void prepare(Attachment attachment) throws MessagingException {
							attachment.add("name0.txt", new ByteArrayResource("attach0".getBytes()));
							attachment.add("name1.bin", new ByteArrayResource("attach1".getBytes()),
									"application/octet-stream");
							attachment.add("name2.txt", file);
							attachment.add("name3.txt", dataSource);
						}
					});

			Session session = Session.getDefaultInstance(new Properties());
			MimeMessage message = new MimeMessage(session);
			preparator.getValue().prepare(message);

			assertEquals(0L, messageId);
			assertEquals(1, message.getRecipients(RecipientType.TO).length);
			assertEquals(parse("to@addr")[0], message.getRecipients(RecipientType.TO)[0]);
			assertEquals(1, message.getRecipients(RecipientType.CC).length);
			assertEquals(parse("cc@addr")[0], message.getRecipients(RecipientType.CC)[0]);
			assertEquals(1, message.getRecipients(RecipientType.BCC).length);
			assertEquals(parse("bcc@addr")[0], message.getRecipients(RecipientType.BCC)[0]);
			assertEquals(1, message.getFrom().length);
			assertEquals(parse("from@addr")[0], message.getFrom()[0]);
			assertEquals("subject", message.getSubject());

			MimeMultipart mm = (MimeMultipart) message.getContent();
			assertEquals(5, mm.getCount());
			assertEquals("body", ((MimeMultipart) mm.getBodyPart(0).getContent()).getBodyPart(0).getContent());

			assertEquals("name0.txt", mm.getBodyPart(1).getFileName());
			assertEquals("text/plain", mm.getBodyPart(1).getContentType());
			assertEquals("attach0", mm.getBodyPart(1).getContent());

			assertEquals("name1.bin", mm.getBodyPart(2).getDataHandler().getName());
			assertEquals("application/octet-stream", mm.getBodyPart(2).getDataHandler().getContentType());
			assertEquals("attach1",
					new String(ByteStreams.toByteArray((InputStream) mm.getBodyPart(2).getDataHandler().getContent())));

			assertEquals("name2.txt", mm.getBodyPart(3).getFileName());
			assertEquals("text/plain", mm.getBodyPart(3).getContentType());
			assertEquals("attach2", mm.getBodyPart(3).getContent());

			assertEquals("name3.txt", mm.getBodyPart(4).getFileName());
			assertEquals("text/plain", mm.getBodyPart(4).getContentType());
			assertEquals("attach3", mm.getBodyPart(4).getContent());
		} finally {
			file.delete();
		}
	}

	private MailSendHandler create(LocalDateTime now) {
		BizDateTime bizDateTime = mock(BizDateTime.class);
		when(bizDateTime.now()).thenReturn(now);

		MessageStore messageStore = new SimpleMessageStore();

		mailSender = mock(JavaMailSender.class);

		MailSendHandlerImpl impl = new MailSendHandlerImpl();
		impl.setBizDateTime(bizDateTime);
		impl.setMessageStore(messageStore);
		impl.setMailSender(mailSender);
		return impl;
	}

}
