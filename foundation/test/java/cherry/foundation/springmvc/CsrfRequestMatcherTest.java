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

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class CsrfRequestMatcherTest {

	@Test
	public void testMatches() {
		RequestMatcher m = new AntPathRequestMatcher("/logout/**");
		CsrfRequestMatcher matcher = new CsrfRequestMatcher();
		matcher.setAllowedMethods(Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$", Pattern.CASE_INSENSITIVE));
		matcher.setExcludes(asList(m));

		assertFalse(matcher.matches(createRequest("GET", "/login")));
		assertFalse(matcher.matches(createRequest("GET", "/logout")));
		assertTrue(matcher.matches(createRequest("POST", "/login")));
		assertFalse(matcher.matches(createRequest("POST", "/logout")));
	}

	private MockHttpServletRequest createRequest(String method, String pathInfo) {
		MockHttpServletRequest r = new MockHttpServletRequest();
		r.setMethod(method);
		r.setPathInfo(pathInfo);
		return r;
	}

}
