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

import java.util.Arrays;

import org.junit.Test;

public class MailDataTest {

	@Test
	public void testSetterGetter() {

		MailData mailData = new MailData();
		mailData.setFromAddr("from@addr");
		mailData.setToAddr(Arrays.asList("to@addr"));
		mailData.setCcAddr(Arrays.asList("cc@addr"));
		mailData.setBccAddr(Arrays.asList("bcc@addr"));
		mailData.setSubject("subject");
		mailData.setBody("body");

		assertEquals("from@addr", mailData.getFromAddr());
		assertEquals(1, mailData.getToAddr().size());
		assertEquals("to@addr", mailData.getToAddr().get(0));
		assertEquals(1, mailData.getCcAddr().size());
		assertEquals("cc@addr", mailData.getCcAddr().get(0));
		assertEquals(1, mailData.getBccAddr().size());
		assertEquals("bcc@addr", mailData.getBccAddr().get(0));
		assertEquals("subject", mailData.getSubject());
		assertEquals("body", mailData.getBody());
	}

	@Test
	public void testToString() {
		MailData mailData = new MailData();
		assertEquals("MailData[fromAddr=<null>,toAddr=<null>,ccAddr=<null>,bccAddr=<null>,subject=<null>,body=<null>]",
				mailData.toString());
	}

}
