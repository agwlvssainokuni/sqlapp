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
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class SqlUtilTest {

	@Test
	public void testEscapeForLike() {
		assertEquals("abcd", SqlUtil.escapeForLike("abcd"));
		assertEquals("ab\\%cd", SqlUtil.escapeForLike("ab%cd"));
		assertEquals("ab\\_cd", SqlUtil.escapeForLike("ab_cd"));
		assertEquals("ab\\\\cd", SqlUtil.escapeForLike("ab\\cd"));
	}

	@Test
	public void testMisc() {
		assertNotNull(new SqlUtil());
	}

}
