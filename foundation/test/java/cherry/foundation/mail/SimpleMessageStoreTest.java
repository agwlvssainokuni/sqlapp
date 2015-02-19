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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;

public class SimpleMessageStoreTest {

	@Test
	public void testCreateMessage() {
		SimpleMessageStore store = new SimpleMessageStore();
		long id0 = store.createMessage("launcherId", "messageName", LocalDateTime.now(), "from@addr",
				asList("to@addr"), asList("cc@addr"), asList("bcc@addr"), "subject", "body");
		assertEquals(0L, id0);
		long id1 = store.createMessage("launcherId", "messageName", LocalDateTime.now().plusMinutes(1), "from@addr",
				asList("to@addr"), null, null, "subject", "body");
		assertEquals(1L, id1);
	}

	@Test
	public void testListMessage() {

		SimpleMessageStore store = new SimpleMessageStore();
		long id0 = store.createMessage("launcherId", "messageName", LocalDateTime.now(), "from@addr",
				asList("to@addr"), asList("cc@addr"), asList("bcc@addr"), "subject", "body");
		assertEquals(0L, id0);
		long id1 = store.createMessage("launcherId", "messageName", LocalDateTime.now().plusMinutes(1), "from@addr",
				asList("to@addr"), null, null, "subject", "body");
		assertEquals(1L, id1);

		List<Long> list0 = store.listMessage(LocalDateTime.now().plusSeconds(1));
		assertEquals(1, list0.size());
		assertEquals(0L, list0.get(0).longValue());

		List<Long> list1 = store.listMessage(LocalDateTime.now().plusHours(1));
		assertEquals(2, list1.size());
		assertEquals(0L, list1.get(0).longValue());
		assertEquals(1L, list1.get(1).longValue());
	}

	@Test
	public void testGetMessage() {

		SimpleMessageStore store = new SimpleMessageStore();
		long id0 = store.createMessage("launcherId", "messageName", LocalDateTime.now(), "from@addr",
				asList("to@addr"), asList("cc@addr"), asList("bcc@addr"), "subject", "body");
		assertEquals(0L, id0);
		long id1 = store.createMessage("launcherId", "messageName", LocalDateTime.now().plusMinutes(1), "from@addr",
				asList("to@addr"), null, null, "subject", "body");
		assertEquals(1L, id1);

		SimpleMailMessage msg0 = store.getMessage(0L);
		assertNotNull(msg0);
		assertEquals("from@addr", msg0.getFrom());
		assertEquals(1, msg0.getTo().length);
		assertEquals("to@addr", msg0.getTo()[0]);
		assertEquals(1, msg0.getCc().length);
		assertEquals("cc@addr", msg0.getCc()[0]);
		assertEquals(1, msg0.getBcc().length);
		assertEquals("bcc@addr", msg0.getBcc()[0]);
		assertEquals("subject", msg0.getSubject());
		assertEquals("body", msg0.getText());

		assertNull(store.getMessage(10L));
	}

	@Test
	public void testFinishMessage() {

		SimpleMessageStore store = new SimpleMessageStore();
		long id0 = store.createMessage("launcherId", "messageName", LocalDateTime.now(), "from@addr",
				asList("to@addr"), asList("cc@addr"), asList("bcc@addr"), "subject", "body");
		assertEquals(0L, id0);
		long id1 = store.createMessage("launcherId", "messageName", LocalDateTime.now().plusMinutes(1), "from@addr",
				asList("to@addr"), null, null, "subject", "body");
		assertEquals(1L, id1);

		assertNotNull(store.getMessage(0L));
		assertNotNull(store.getMessage(1L));

		store.finishMessage(0L);
		store.finishMessage(1L);

		assertNull(store.getMessage(0L));
		assertNull(store.getMessage(1L));
	}

}
