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

package cherry.foundation.etl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class MiscTest {

	@Test
	public void testColumnToString() {
		Column column = new Column();
		column.setType(123);
		column.setLabel("ColumnLabel");
		assertEquals(123, column.getType());
		assertEquals("ColumnLabel", column.getLabel());
		assertEquals("Column[type=123,label=ColumnLabel]", column.toString());
	}

	@Test
	public void testLoadResultToString() {
		LoadResult loadResult = new LoadResult();
		loadResult.setTotalCount(3);
		loadResult.setSuccessCount(2);
		loadResult.setFailedCount(1);
		assertEquals(3, loadResult.getTotalCount());
		assertEquals(2, loadResult.getSuccessCount());
		assertEquals(1, loadResult.getFailedCount());
		assertEquals("LoadResult[totalCount=3,successCount=2,failedCount=1]", loadResult.toString());
	}

	@Test
	public void testLimiterException() {
		try {
			LimiterException cause = new LimiterException();
			assertEquals("message", (new LimiterException("message")).getMessage());
			assertEquals(cause, (new LimiterException(cause)).getCause());
			LimiterException ex = new LimiterException("message", cause);
			assertEquals("message", ex.getMessage());
			assertEquals(cause, ex.getCause());
		} catch (Throwable throwable) {
			fail("Exception must not be thrown");
		}
	}
}
