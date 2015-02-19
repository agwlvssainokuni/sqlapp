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

package cherry.foundation.async;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EnumTest {

	@Test
	public void testAsyncType() {
		assertEquals("FIL", AsyncType.FILE.code());
		assertEquals("CMD", AsyncType.COMMAND.code());
	}

	@Test
	public void testAsyncStatus() {
		assertEquals("0", AsyncStatus.LAUNCHING.code());
		assertEquals("1", AsyncStatus.LAUNCHED.code());
		assertEquals("2", AsyncStatus.PROCESSING.code());
		assertEquals("3", AsyncStatus.SUCCESS.code());
		assertEquals("4", AsyncStatus.WARN.code());
		assertEquals("5", AsyncStatus.ERROR.code());
		assertEquals("9", AsyncStatus.EXCEPTION.code());
	}

	@Test
	public void testMisc() {
		assertEquals(AsyncType.FILE, AsyncType.valueOf("FILE"));
		assertEquals(AsyncStatus.LAUNCHING, AsyncStatus.valueOf("LAUNCHING"));
	}

}
