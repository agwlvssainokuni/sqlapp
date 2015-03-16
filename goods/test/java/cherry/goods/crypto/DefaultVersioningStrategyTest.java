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

package cherry.goods.crypto;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

import java.security.SecureRandom;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import cherry.goods.crypto.VersionStrategy.VersionedData;

public class DefaultVersioningStrategyTest {

	private SecureRandom random = new SecureRandom();

	@Test
	public void testEncodeAndDecode() {
		DefaultVersionStrategy strategy = new DefaultVersionStrategy();
		for (int i = 0; i < 100; i++) {
			int version = random.nextInt();
			byte[] plain = RandomUtils.nextBytes(1024);
			byte[] encoded = strategy.encode(plain, version);
			assertThat(plain, is(not(encoded)));
			VersionedData<byte[], Integer> vd = strategy.decode(encoded);
			assertThat(vd.getData(), is(plain));
			assertThat(vd.getVersion(), is(Integer.valueOf(version)));
			assertThat(vd.toString(), startsWith("VersionStrategy.VersionedData[data={"));
		}
	}

}
