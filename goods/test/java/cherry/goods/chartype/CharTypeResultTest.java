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

package cherry.goods.chartype;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CharTypeResultTest {

	@Test
	public void testDefaultConstructor() {
		CharTypeResult result = new CharTypeResult();
		assertTrue(result.isValid());
		assertEquals(-1, result.getIndex());
		assertEquals(-1, result.getCodePoint());
		assertEquals("CharTypeResult[valid=true,index=-1,codePoint=-1]", result.toString());
	}

	@Test
	public void testConstructor() {
		CharTypeResult result = new CharTypeResult(false, 1, 2);
		assertFalse(result.isValid());
		assertEquals(1, result.getIndex());
		assertEquals(2, result.getCodePoint());
		assertEquals("CharTypeResult[valid=false,index=1,codePoint=2]", result.toString());
	}

}
