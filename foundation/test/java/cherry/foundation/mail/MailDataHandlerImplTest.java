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
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.junit.Test;

public class MailDataHandlerImplTest {

	@Test
	public void testFullAddress() {
		MailDataHandler handler = create("name", "from@addr", "other@addr", "cc@addr", "bcc@addr", "subject", "body",
				VelocityMode.NORMAL);
		Model model = new Model();
		model.setParam("PARAM");
		MailData mailData = handler.createMailData("name", "to@addr", model);
		assertNotNull(mailData);
		assertEquals("from@addr", mailData.getFromAddr());
		assertEquals(2, mailData.getToAddr().size());
		assertEquals("to@addr", mailData.getToAddr().get(0));
		assertEquals("other@addr", mailData.getToAddr().get(1));
		assertEquals(1, mailData.getCcAddr().size());
		assertEquals("cc@addr", mailData.getCcAddr().get(0));
		assertEquals(1, mailData.getBccAddr().size());
		assertEquals("bcc@addr", mailData.getBccAddr().get(0));
		assertEquals("subject", mailData.getSubject());
		assertEquals("body", mailData.getBody());
	}

	@Test
	public void testEmptyTemplate() {
		MailDataHandler handler = create("name", "from@addr", null, null, null, "", "", VelocityMode.NORMAL);
		Model model = new Model();
		model.setParam("PARAM");
		MailData mailData = handler.createMailData("name", "to@addr", model);
		assertNotNull(mailData);
		assertEquals("from@addr", mailData.getFromAddr());
		assertEquals(1, mailData.getToAddr().size());
		assertEquals("to@addr", mailData.getToAddr().get(0));
		assertNull(mailData.getCcAddr());
		assertNull(mailData.getBccAddr());
		assertEquals("", mailData.getSubject());
		assertEquals("", mailData.getBody());
	}

	@Test
	public void testTemplateEvaluation() {
		MailDataHandler handler = create("name", "from@addr", null, null, null, "param=${model.param}",
				"param is ${model.param}", VelocityMode.NORMAL);
		Model model = new Model();
		model.setParam("PARAM");
		MailData mailData = handler.createMailData("name", "to@addr", model);
		assertNotNull(mailData);
		assertEquals("from@addr", mailData.getFromAddr());
		assertEquals(1, mailData.getToAddr().size());
		assertEquals("to@addr", mailData.getToAddr().get(0));
		assertNull(mailData.getCcAddr());
		assertNull(mailData.getBccAddr());
		assertEquals("param=PARAM", mailData.getSubject());
		assertEquals("param is PARAM", mailData.getBody());
	}

	@Test
	public void testTemplateEvaluationFalse() {
		MailDataHandler handler = create("name", "from@addr", null, null, null, "param=${model.param}",
				"param is ${model.param}", VelocityMode.MOCK_FALSE);
		Model model = new Model();
		model.setParam("PARAM");
		try {
			handler.createMailData("name", "to@addr", model);
			fail("Exception must be thrown");
		} catch (IllegalStateException ex) {
			assertNull(ex.getCause());
		}
	}

	static enum VelocityMode {
		NORMAL, MOCK_FALSE
	}

	private MailDataHandler create(String name, String fromAddr, String toAddr, String ccAddr, String bccAddr,
			String subject, String body, VelocityMode velocityMode) {

		MailData mailData = new MailData();
		mailData.setFromAddr(fromAddr);
		if (isNotEmpty(toAddr)) {
			mailData.setToAddr(asList(toAddr));
		}
		if (isNotEmpty(ccAddr)) {
			mailData.setCcAddr(asList(ccAddr));
		}
		if (isNotEmpty(bccAddr)) {
			mailData.setBccAddr(asList(bccAddr));
		}
		mailData.setSubject(subject);
		mailData.setBody(body);

		Map<String, MailData> map = new HashMap<>();
		map.put(name, mailData);

		SimpleTemplateStore templateStore = new SimpleTemplateStore();
		templateStore.setMailDataMap(map);

		VelocityEngine velocityEngine;
		switch (velocityMode) {
		case NORMAL:
			velocityEngine = new VelocityEngine();
			velocityEngine.init();
			break;
		default:
			velocityEngine = mock(VelocityEngine.class);
			when(velocityEngine.evaluate((Context) any(), (Writer) any(), anyString(), anyString())).thenReturn(false);
			break;
		}

		MailDataHandlerImpl impl = new MailDataHandlerImpl();
		impl.setTemplateStore(templateStore);
		impl.setVelocityEngine(velocityEngine);
		return impl;
	}

	@Setter
	@Getter
	public static class Model implements MailModel {
		private String param;
	}

}
