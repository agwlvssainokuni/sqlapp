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

package cherry.foundation.logicalerror;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.validation.BindingResult;

public class LogicalErrorUtilTest {

	@Test
	public void testRejectOnOneTimeTokenError() {
		BindingResult binding = mock(BindingResult.class);
		LogicalErrorUtil.rejectOnOneTimeTokenError(binding);
		verify(binding).reject(eq("OneTimeTokenError"), eq(new Object[] {}), eq("OneTimeTokenError"));
	}

	@Test
	public void testRejectOnOptimisticLockError() {
		BindingResult binding = mock(BindingResult.class);
		LogicalErrorUtil.rejectOnOptimisticLockError(binding);
		verify(binding).reject(eq("OptimisticLockError"), eq(new Object[] {}), eq("OptimisticLockError"));
	}

	@Test
	public void testRejectOnSearchresultEmpty() {
		BindingResult binding = mock(BindingResult.class);
		LogicalErrorUtil.rejectOnSearchResultEmpty(binding);
		verify(binding).reject(eq("SearchResultEmpty"), eq(new Object[] {}), eq("SearchResultEmpty"));
	}

	@Test
	public void testReject() {
		BindingResult binding = mock(BindingResult.class);
		LogicalErrorUtil.reject(binding, ErrorCode.TestError0, 1, 2);
		verify(binding).reject(eq("TestError0"), eq(new Object[] { Integer.valueOf(1), Integer.valueOf(2) }),
				eq("TestError0"));
	}

	@Test
	public void testRejectValue() {
		BindingResult binding = mock(BindingResult.class);
		LogicalErrorUtil.rejectValue(binding, "property", ErrorCode.TestError0, 1, 2);
		verify(binding).rejectValue(eq("property"), eq("TestError0"),
				eq(new Object[] { Integer.valueOf(1), Integer.valueOf(2) }), eq("TestError0"));
	}

	@Test
	public void testResolve() {
		MessageSourceResolvable resolvable = LogicalErrorUtil.resolve("property");
		assertNotNull(resolvable.getCodes());
		assertEquals(1, resolvable.getCodes().length);
		assertEquals("property", resolvable.getCodes()[0]);
		assertNotNull(resolvable.getArguments());
		assertEquals(0, resolvable.getArguments().length);
		assertNull(resolvable.getDefaultMessage());
	}

	@Test
	public void testInstantiate() {
		try {
			new LogicalErrorUtil();
		} catch (Exception ex) {
			fail("Exception must not be thrown");
		}
	}

	@Test
	public void testEnum() {
		assertEquals(LogicalError.OneTimeTokenError, LogicalError.valueOf("OneTimeTokenError"));
	}

	public enum ErrorCode implements ILogicalError {
		TestError0;

		@Override
		public String code() {
			return name();
		}
	}

}
