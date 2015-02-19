/*
 * Copyright 2015 agwlvssainokuni
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

package cherry.foundation.numbering;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cherry.foundation.type.Code;

import com.google.common.util.concurrent.UncheckedExecutionException;

public class NumberingManagerImplTest {

	enum NumberType implements Code<String> {
		TEST00, TEST01, TEST02;
		@Override
		public String code() {
			return name();
		}
	}

	@Test
	public void testIssueAsString_OK() {
		NumberingManagerImpl impl = create();
		NumberFormat fmt = new DecimalFormat("0000");
		for (int i = 0; i < 100; i++) {
			assertEquals(fmt.format(1 + i), impl.issueAsString("TEST00"));
		}
		for (int i = 0; i < 100; i++) {
			assertEquals(fmt.format(101 + i), impl.issueAsString(NumberType.TEST00));
		}
		String[] n200 = impl.issueAsString("TEST00", 100);
		for (int i = 0; i < 100; i++) {
			assertEquals(fmt.format(201 + i), n200[i]);
		}
		String[] n300 = impl.issueAsString(NumberType.TEST00, 100);
		for (int i = 0; i < 100; i++) {
			assertEquals(fmt.format(301 + i), n300[i]);
		}
	}

	@Test
	public void testIssueAsString_NG() {
		NumberingManagerImpl impl = create();

		try {
			impl.issueAsString((String) null);
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			assertEquals("java.lang.IllegalArgumentException: numberName must not be null", ex.toString());
		}
		try {
			impl.issueAsString(NumberType.TEST01);
			fail("Exception must be thrown");
		} catch (IllegalStateException ex) {
			assertEquals("java.lang.IllegalStateException: TEST01 must be >= 2", ex.toString());
		}
		try {
			impl.issueAsString(NumberType.TEST02);
			fail("Exception must be thrown");
		} catch (IllegalStateException ex) {
			assertEquals("java.lang.IllegalStateException: TEST02 must be <= 0", ex.toString());
		}

		try {
			impl.issueAsString((String) null, 1);
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			assertEquals("java.lang.IllegalArgumentException: numberName must not be null", ex.toString());
		}
		try {
			impl.issueAsString("TEST00", 0);
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			assertEquals("java.lang.IllegalArgumentException: count must be > 0", ex.toString());
		}
		try {
			impl.issueAsString(NumberType.TEST01, 1);
			fail("Exception must be thrown");
		} catch (IllegalStateException ex) {
			assertEquals("java.lang.IllegalStateException: TEST01 must be >= 2", ex.toString());
		}
		try {
			impl.issueAsString(NumberType.TEST02, 1);
			fail("Exception must be thrown");
		} catch (IllegalStateException ex) {
			assertEquals("java.lang.IllegalStateException: TEST02 must be <= 0", ex.toString());
		}
	}

	@Test
	public void testIssueAsLong_OK() {
		NumberingManagerImpl impl = create();
		for (int i = 0; i < 100; i++) {
			assertEquals(1 + i, impl.issueAsLong("TEST00"));
		}
		for (int i = 0; i < 100; i++) {
			assertEquals(101 + i, impl.issueAsLong(NumberType.TEST00));
		}
		long[] n200 = impl.issueAsLong("TEST00", 100);
		for (int i = 0; i < 100; i++) {
			assertEquals(201 + i, n200[i]);
		}
		long[] n300 = impl.issueAsLong(NumberType.TEST00, 100);
		for (int i = 0; i < 100; i++) {
			assertEquals(301 + i, n300[i]);
		}
	}

	@Test
	public void testIssueAsLong_NG() {
		NumberingManagerImpl impl = create();

		try {
			impl.issueAsLong((String) null);
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			assertEquals("java.lang.IllegalArgumentException: numberName must not be null", ex.toString());
		}
		try {
			impl.issueAsLong(NumberType.TEST01);
			fail("Exception must be thrown");
		} catch (IllegalStateException ex) {
			assertEquals("java.lang.IllegalStateException: TEST01 must be >= 2", ex.toString());
		}
		try {
			impl.issueAsLong(NumberType.TEST02);
			fail("Exception must be thrown");
		} catch (IllegalStateException ex) {
			assertEquals("java.lang.IllegalStateException: TEST02 must be <= 0", ex.toString());
		}

		try {
			impl.issueAsLong((String) null, 1);
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			assertEquals("java.lang.IllegalArgumentException: numberName must not be null", ex.toString());
		}
		try {
			impl.issueAsLong("TEST00", 0);
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			assertEquals("java.lang.IllegalArgumentException: count must be > 0", ex.toString());
		}
		try {
			impl.issueAsLong(NumberType.TEST01, 1);
			fail("Exception must be thrown");
		} catch (IllegalStateException ex) {
			assertEquals("java.lang.IllegalStateException: TEST01 must be >= 2", ex.toString());
		}
		try {
			impl.issueAsLong(NumberType.TEST02, 1);
			fail("Exception must be thrown");
		} catch (IllegalStateException ex) {
			assertEquals("java.lang.IllegalStateException: TEST02 must be <= 0", ex.toString());
		}
	}

	@Test
	public void testNoName() {
		NumberingManagerImpl impl = create();
		try {
			impl.issueAsString("NONE");
			fail("Exception must be thrown");
		} catch (UncheckedExecutionException ex) {
			assertEquals(
					"com.google.common.util.concurrent.UncheckedExecutionException: java.lang.IllegalArgumentException: NONE must be defined",
					ex.toString());
		}
	}

	private NumberingManagerImpl create() {

		Map<String, NumberingDefinition> map = new HashMap<>();

		NumberingDefinition dto0 = new NumberingDefinition();
		dto0.setTemplate("{0,number,0000}");
		dto0.setMinValue(1L);
		dto0.setMaxValue(1000L);
		map.put("TEST00", dto0);

		NumberingDefinition dto1 = new NumberingDefinition();
		dto1.setTemplate("{0,number,0000}");
		dto1.setMinValue(2L);
		dto1.setMaxValue(2L);
		map.put("TEST01", dto1);

		NumberingDefinition dto2 = new NumberingDefinition();
		dto2.setTemplate("{0,number,0000}");
		dto2.setMinValue(0L);
		dto2.setMaxValue(0L);
		map.put("TEST02", dto2);

		SimpleNumberingStore store = new SimpleNumberingStore();
		store.setNumberingDefinitionMap(map);
		store.afterPropertiesSet();
		NumberingManagerImpl impl = new NumberingManagerImpl();
		impl.setCacheSpec("maximumSize=10,expireAfterAccess=10s");
		impl.setNumberingStore(store);
		impl.afterPropertiesSet();
		return impl;
	}

}
