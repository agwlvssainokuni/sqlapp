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

package cherry.foundation.onetimetoken;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class OneTimeTokenValidatorImplTest {

	@Autowired
	private OneTimeTokenValidator oneTimeTokenValidator;

	@Autowired
	private OneTimeTokenIssuer oneTimeTokenIssuer;

	@Test
	public void testIsValid_WithIssuer() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		OneTimeToken token = oneTimeTokenIssuer.newToken(request);
		request.setParameter(token.getName(), token.getValue());
		assertTrue(oneTimeTokenValidator.isValid(request));
	}

	@Test
	public void testIsValid_NoReqParam() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		assertFalse(oneTimeTokenValidator.isValid(request));
	}

	@Test
	public void testIsValid_NoSession() {
		String tokenValue = UUID.randomUUID().toString();
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("__OneTimeToken__", tokenValue);
		assertFalse(oneTimeTokenValidator.isValid(request));
	}

	@Test
	public void testIsValid_NoSessionAttr() {
		String tokenValue = UUID.randomUUID().toString();
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("__OneTimeToken__", tokenValue);
		request.getSession();
		assertFalse(oneTimeTokenValidator.isValid(request));
	}

	@Test
	public void testIsValid_TokenValueUnmatch() {
		String tokenValue = UUID.randomUUID().toString();
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("__OneTimeToken__", tokenValue);
		request.getSession().setAttribute("__OneTimeToken__", UUID.randomUUID().toString());
		assertFalse(oneTimeTokenValidator.isValid(request));
	}

	@Test
	public void testIsValid_TokenValueMatch() {
		String tokenValue = UUID.randomUUID().toString();
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("__OneTimeToken__", tokenValue);
		request.getSession().setAttribute("__OneTimeToken__", tokenValue);
		assertTrue(oneTimeTokenValidator.isValid(request));
	}

}
