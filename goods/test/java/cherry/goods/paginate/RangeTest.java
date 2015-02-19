/*
 * Copyright 2004,2015 agwlvssainokuni
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

package cherry.goods.paginate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.Test;

public class RangeTest {

	@Test
	public void testRangeNew() {
		try {
			assertNotNull(new Range(1, 0));
		} catch (IllegalStateException ex) {
			fail("Exception must not be thrown");
		}
	}

	@Test
	public void testIterator1to1() {
		long number = 1;
		for (long i : new Range(1, 1)) {
			assertEquals(number++, i);
		}
	}

	@Test
	public void testIterator1to10() {
		int number = 1;
		for (long i : new Range(1, 10)) {
			assertEquals(number++, i);
		}
	}

	@Test
	public void testIteratorOver() {
		Iterator<Long> iter = (new Range(1, 10)).iterator();
		for (long i = 1; i <= 10; i++) {
			assertTrue(iter.hasNext());
			assertEquals(i, iter.next().longValue());
		}

		assertFalse(iter.hasNext());
		try {
			iter.next();
			fail("Exception must be thrown");
		} catch (IllegalStateException ex) {
			// OK
		}
	}

	@Test
	public void testIteratorRemove() {
		Iterator<Long> iter = (new Range(1, 1)).iterator();
		try {
			iter.remove();
		} catch (Exception ex) {
			fail("Exception must not be thrown");
		}
	}

	@Test
	public void testToString() {
		Range range = new Range(1, 10);
		assertEquals("Range[from=1,to=10]", range.toString());
	}

}
