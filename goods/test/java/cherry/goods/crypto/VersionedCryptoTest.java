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
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cherry.goods.util.RandomUtil;

public class VersionedCryptoTest {

	@Test
	public void simpleEncryptAndDecrypt() throws Exception {

		Map<Integer, Crypto> map = createCryptoMap(1);

		VersionedCrypto helper = new VersionedCrypto();
		helper.setDefaultVersion(0);
		helper.setCryptoMap(map);
		helper.setVersionStrategy(new DefaultVersionStrategy());

		for (int i = 0; i < 100; i++) {
			byte[] plain = RandomUtil.randomBytes(1024);
			byte[] crypto = helper.encrypt(plain);
			assertThat(crypto, is(not(plain)));
			assertThat(helper.decrypt(crypto), is(plain));
		}
	}

	@Test
	public void versionedEncryptAndDecrypt() throws Exception {

		Map<Integer, Crypto> map = createCryptoMap(2);

		VersionedCrypto helper0 = new VersionedCrypto();
		helper0.setDefaultVersion(0);
		helper0.setCryptoMap(map);

		VersionedCrypto helper1 = new VersionedCrypto();
		helper1.setDefaultVersion(1);
		helper1.setCryptoMap(map);

		for (int i = 0; i < 100; i++) {
			byte[] plain = RandomUtil.randomBytes(1024);
			byte[] crypto = helper0.encrypt(plain);
			assertThat(crypto, is(not(plain)));
			assertThat(crypto, is(not(helper1.encrypt(plain))));
			assertThat(helper0.decrypt(crypto), is(plain));
			assertThat(helper1.decrypt(crypto), is(plain));
		}
	}

	@Test
	public void errorNoMatchingCryptoToEncrypt() throws Exception {
		Map<Integer, Crypto> map = createCryptoMap(1);
		VersionedCrypto helper0 = new VersionedCrypto();
		helper0.setDefaultVersion(1);
		helper0.setCryptoMap(map);
		try {
			byte[] plain = RandomUtil.randomBytes(1024);
			helper0.encrypt(plain);
			fail("Exception must be thrown");
		} catch (IllegalStateException ex) {
			// OK
		}
	}

	@Test
	public void errorNoMatchingCryptoToDecrypt() throws Exception {

		Map<Integer, Crypto> map1 = createCryptoMap(2);
		Map<Integer, Crypto> map0 = new HashMap<>();
		map0.put(0, map1.get(0));

		VersionedCrypto crypto0 = new VersionedCrypto();
		crypto0.setDefaultVersion(0);
		crypto0.setCryptoMap(map0);

		VersionedCrypto crypto1 = new VersionedCrypto();
		crypto1.setDefaultVersion(1);
		crypto1.setCryptoMap(map1);

		try {
			byte[] plain = RandomUtil.randomBytes(1024);
			byte[] crypto = crypto1.encrypt(plain);
			assertThat(crypto1.decrypt(crypto), is(plain));

			crypto0.decrypt(crypto);
			fail("Exception must be thrown");
		} catch (IllegalStateException ex) {
			// OK
		}
	}

	private Map<Integer, Crypto> createCryptoMap(int num) throws IOException {
		Map<Integer, Crypto> map = new HashMap<>();
		for (int i = 0; i < num; i++) {
			map.put(i, createAESCrypto());
		}
		return map;
	}

	private Crypto createAESCrypto() throws IOException {
		AESCrypto crypto = new AESCrypto();
		crypto.setSecretKeyBytes(RandomUtil.randomBytes(16));
		crypto.setInitVectorBytes(RandomUtil.randomBytes(16));
		return crypto;
	}

}
