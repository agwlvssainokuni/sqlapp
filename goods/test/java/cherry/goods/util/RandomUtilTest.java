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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

public class RandomUtilTest {

	@Test
	public void testSetBase() {
		RandomUtil.setBase("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray());
	}

	@Test
	public void testRandomBytes() {
		Set<String> set = new HashSet<>();
		for (int i = 0; i < 1024; i++) {
			set.add(Hex.encodeHexString(RandomUtil.randomBytes(1024)));
		}
		assertThat(set.size(), is(1024));
	}

	@Test
	public void testRandomString() {
		Set<String> set = new HashSet<>();
		for (int i = 0; i < 1024; i++) {
			set.add(RandomUtil.randomString(1024));
		}
		assertThat(set.size(), is(1024));
	}

	@Test
	public void testMisc() {
		assertThat(new RandomUtil(), is(notNullValue()));
	}

}
