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

package cherry.foundation.springmvc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.springframework.security.core.context.SecurityContextHolder.createEmptyContext;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;
import static org.springframework.security.core.context.SecurityContextHolder.setContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class OperationLogHandlerInterceptorTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

		SecurityContext context = createEmptyContext();
		context.setAuthentication(new UsernamePasswordAuthenticationToken("user01", "password"));
		setContext(context);
	}

	@After
	public void after() {
		SecurityContextHolder.clearContext();
	}

	@Test
	public void testSimpleGet() throws Exception {
		mockMvc.perform(get("/secure/test").principal(getContext().getAuthentication())).andExpect(status().isOk());
	}

	@Test
	public void testPostWithMultiParam() throws Exception {
		mockMvc.perform(
				post("/secure/test").param("loginId", "u").param("password", "p")
						.principal(getContext().getAuthentication())).andExpect(status().isOk());
	}

	@Test
	public void testRedirect() throws Exception {
		mockMvc.perform(get("/secure/test").param("redirect", "").principal(getContext().getAuthentication()))
				.andExpect(status().is3xxRedirection());
	}

	@Test
	public void testDownload() throws Exception {
		mockMvc.perform(get("/secure/test").param("download", "").principal(getContext().getAuthentication()))
				.andExpect(status().isOk());
	}

	@Test
	public void testNullview() throws Exception {
		mockMvc.perform(get("/secure/test").param("nullview", "").principal(getContext().getAuthentication()))
				.andExpect(status().isOk());
	}

	@Test
	public void testException() throws Exception {
		try {
			mockMvc.perform(get("/secure/test").param("exception", "").principal(getContext().getAuthentication()))
					.andExpect(status().is5xxServerError());
			fail("Exception must be thrown");
		} catch (NestedServletException ex) {
			assertEquals("SecureTestController exception", ex.getCause().getMessage());
		}
	}

}
