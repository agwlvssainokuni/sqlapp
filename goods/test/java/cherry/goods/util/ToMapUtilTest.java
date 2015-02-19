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

package cherry.goods.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class ToMapUtilTest {

	@Test
	public void testFromThrowable_00() {

		Exception ex = new Exception("MESSAGE");
		Map<String, Object> map = ToMapUtil.fromThrowable(ex, Integer.MAX_VALUE);

		assertEquals("MESSAGE", map.get("message"));
		assertNotNull(map.get("stackTrace"));
		assertTrue(map.get("stackTrace") instanceof List);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) map.get("stackTrace");
		assertFalse(list.isEmpty());
		assertEquals("cherry.goods.util.ToMapUtilTest.testFromThrowable_00(ToMapUtilTest.java:36)", list.get(0));

		assertNull(map.get("cause"));
	}

	@Test
	public void testFromThrowable_01() {

		Exception ex = new Exception("MESSAGE");
		Map<String, Object> map = ToMapUtil.fromThrowable(ex, 0);

		assertEquals("MESSAGE", map.get("message"));
		assertNotNull(map.get("stackTrace"));
		assertTrue(map.get("stackTrace") instanceof List);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) map.get("stackTrace");
		assertFalse(list.isEmpty());
		assertEquals("...", list.get(0));

		assertNull(map.get("cause"));
	}

	@Test
	public void testFromThrowable_02() {

		Exception cause = new Exception("CAUSE");
		Exception ex = new Exception("MESSAGE", cause);
		Map<String, Object> map = ToMapUtil.fromThrowable(ex, 1);

		assertEquals("MESSAGE", map.get("message"));
		assertNotNull(map.get("stackTrace"));
		assertTrue(map.get("stackTrace") instanceof List);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) map.get("stackTrace");
		assertFalse(list.isEmpty());
		assertEquals("cherry.goods.util.ToMapUtilTest.testFromThrowable_02(ToMapUtilTest.java:71)", list.get(0));

		assertNotNull(map.get("cause"));
		@SuppressWarnings("unchecked")
		Map<String, Object> map2 = (Map<String, Object>) map.get("cause");
		assertEquals("CAUSE", map2.get("message"));
		assertNotNull(map2.get("stackTrace"));
		assertTrue(map2.get("stackTrace") instanceof List);
		@SuppressWarnings("unchecked")
		List<String> list2 = (List<String>) map2.get("stackTrace");
		assertFalse(list2.isEmpty());
		assertEquals("...", list2.get(0));
	}

	@Test
	public void testInstantiate() {
		try {
			new ToMapUtil();
		} catch (Exception ex) {
			fail("Exception must not be thrown");
		}
	}

}
