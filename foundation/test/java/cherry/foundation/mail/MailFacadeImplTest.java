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
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.joda.time.LocalDateTime;
import org.junit.Test;

import cherry.foundation.bizdtm.BizDateTime;

public class MailFacadeImplTest {

	private BizDateTime bizDateTime;

	private MailDataHandler mailDataHandler;

	private MailSendHandler mailSendHandler;

	@Test
	public void testCreateMailData() {
		LocalDateTime now = LocalDateTime.now();
		MailFacade mailFacade = create(now);

		MailModel model = new MailModel() {
		};
		mailFacade.createMailData("templateName", "to@addr", model);
		verify(mailDataHandler).createMailData(eq("templateName"), eq("to@addr"), eq(model));
	}

	@Test
	public void testSend() {
		LocalDateTime now = LocalDateTime.now();
		MailFacade mailFacade = create(now);

		mailFacade.send("launcherId", "messageName", "from@addr", asList("to@addr"), asList("cc@addr"),
				asList("bcc@addr"), "subject", "body");
		verify(mailSendHandler).sendLater(eq("launcherId"), eq("messageName"), eq("from@addr"), eq(asList("to@addr")),
				eq(asList("cc@addr")), eq(asList("bcc@addr")), eq("subject"), eq("body"), eq(now));
	}

	@Test
	public void testSendLater() {
		LocalDateTime now = LocalDateTime.now();
		MailFacade mailFacade = create(now);

		mailFacade.sendLater("launcherId", "messageName", "from@addr", asList("to@addr"), asList("cc@addr"),
				asList("bcc@addr"), "subject", "body", now);
		verify(mailSendHandler).sendLater(eq("launcherId"), eq("messageName"), eq("from@addr"), eq(asList("to@addr")),
				eq(asList("cc@addr")), eq(asList("bcc@addr")), eq("subject"), eq("body"), eq(now));
	}

	@Test
	public void testSendNow() {
		LocalDateTime now = LocalDateTime.now();
		MailFacade mailFacade = create(now);

		mailFacade.sendNow("launcherId", "messageName", "from@addr", asList("to@addr"), asList("cc@addr"),
				asList("bcc@addr"), "subject", "body");
		verify(mailSendHandler).sendNow(eq("launcherId"), eq("messageName"), eq("from@addr"), eq(asList("to@addr")),
				eq(asList("cc@addr")), eq(asList("bcc@addr")), eq("subject"), eq("body"));
	}

	@Test
	public void testSendNowAttached() {
		LocalDateTime now = LocalDateTime.now();
		MailFacade mailFacade = create(now);

		AttachmentPreparator preparator = mock(AttachmentPreparator.class);
		mailFacade.sendNow("launcherId", "messageName", "from@addr", asList("to@addr"), asList("cc@addr"),
				asList("bcc@addr"), "subject", "body", preparator);
		verify(mailSendHandler).sendNow(eq("launcherId"), eq("messageName"), eq("from@addr"), eq(asList("to@addr")),
				eq(asList("cc@addr")), eq(asList("bcc@addr")), eq("subject"), eq("body"), eq(preparator));
	}

	private MailFacade create(LocalDateTime now) {
		bizDateTime = mock(BizDateTime.class);
		when(bizDateTime.now()).thenReturn(now);
		mailDataHandler = mock(MailDataHandler.class);
		mailSendHandler = mock(MailSendHandler.class);
		MailFacadeImpl impl = new MailFacadeImpl();
		impl.setBizDateTime(bizDateTime);
		impl.setMailDataHandler(mailDataHandler);
		impl.setMailSendHandler(mailSendHandler);
		return impl;
	}

}
