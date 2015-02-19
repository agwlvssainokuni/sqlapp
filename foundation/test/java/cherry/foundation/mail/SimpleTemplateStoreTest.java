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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class SimpleTemplateStoreTest {

	@Test
	public void testGetTemplate() {

		TemplateStore templateStore = create("name", "from@addr", "to@addr", "cc@addr", "bcc@addr", "subject", "body");

		MailData mailData = templateStore.getTemplate("name");
		assertNotNull(mailData);
		assertEquals("from@addr", mailData.getFromAddr());
		assertEquals(1, mailData.getToAddr().size());
		assertEquals("to@addr", mailData.getToAddr().get(0));
		assertEquals(1, mailData.getCcAddr().size());
		assertEquals("cc@addr", mailData.getCcAddr().get(0));
		assertEquals(1, mailData.getBccAddr().size());
		assertEquals("bcc@addr", mailData.getBccAddr().get(0));
		assertEquals("subject", mailData.getSubject());
		assertEquals("body", mailData.getBody());

		assertNull(templateStore.getTemplate("none"));
	}

	@Test
	public void testPutTemplate() {

		TemplateStore templateStore = create("name", "from@addr", "to@addr", "cc@addr", "bcc@addr", "subject", "body");

		MailData mailData = new MailData();
		mailData.setFromAddr("from2@addr");
		mailData.setToAddr(Arrays.asList("to2@addr"));
		mailData.setCcAddr(Arrays.asList("cc2@addr"));
		mailData.setBccAddr(Arrays.asList("bcc2@addr"));
		mailData.setSubject("subject2");
		mailData.setBody("body2");

		templateStore.putTemplate("name2", mailData);

		MailData mailData2 = templateStore.getTemplate("name2");
		assertNotNull(mailData2);
		assertEquals("from2@addr", mailData2.getFromAddr());
		assertEquals(1, mailData2.getToAddr().size());
		assertEquals("to2@addr", mailData2.getToAddr().get(0));
		assertEquals(1, mailData2.getCcAddr().size());
		assertEquals("cc2@addr", mailData2.getCcAddr().get(0));
		assertEquals(1, mailData2.getBccAddr().size());
		assertEquals("bcc2@addr", mailData2.getBccAddr().get(0));
		assertEquals("subject2", mailData2.getSubject());
		assertEquals("body2", mailData2.getBody());
	}

	private TemplateStore create(String name, String fromAddr, String toAddr, String ccAddr, String bccAddr,
			String subject, String body) {

		MailData mailData = new MailData();
		mailData.setFromAddr(fromAddr);
		mailData.setToAddr(Arrays.asList(toAddr));
		mailData.setCcAddr(Arrays.asList(ccAddr));
		mailData.setBccAddr(Arrays.asList(bccAddr));
		mailData.setSubject(subject);
		mailData.setBody(body);

		Map<String, MailData> map = new HashMap<>();
		map.put(name, mailData);

		SimpleTemplateStore store = new SimpleTemplateStore();
		store.setMailDataMap(map);
		return store;
	}

}
