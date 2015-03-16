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
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

public class AESCryptoTest {

	@Test
	public void testDefault() throws Exception {

		AESCrypto crypto = new AESCrypto();
		crypto.setSecretKeyBytes(RandomUtils.nextBytes(16));
		crypto.setInitVectorBytes(RandomUtils.nextBytes(16));

		for (int i = 0; i < 100; i++) {
			byte[] plain = RandomUtils.nextBytes(1024);
			byte[] enc = crypto.encrypt(plain);
			byte[] dec = crypto.decrypt(enc);
			assertThat(dec, is(plain));
		}
	}

	@Test
	public void testCBC() throws Exception {

		AESCrypto crypto = new AESCrypto();
		crypto.setAlgorithm("AES/CBC/PKCS5Padding");
		crypto.setSecretKeyBytes(RandomUtils.nextBytes(16));
		crypto.setInitVectorBytes(RandomUtils.nextBytes(16));

		for (int i = 0; i < 100; i++) {
			byte[] plain = RandomUtils.nextBytes(1024);
			byte[] enc = crypto.encrypt(plain);
			byte[] dec = crypto.decrypt(enc);
			assertThat(dec, is(plain));
		}
	}

	@Test
	public void testECB() throws Exception {

		AESCrypto crypto = new AESCrypto();
		crypto.setAlgorithm("AES/ECB/PKCS5Padding");
		crypto.setSecretKeyBytes(RandomUtils.nextBytes(16));

		for (int i = 0; i < 100; i++) {
			byte[] plain = RandomUtils.nextBytes(1024);
			byte[] enc = crypto.encrypt(plain);
			byte[] dec = crypto.decrypt(enc);
			assertThat(dec, is(plain));
		}
	}

	@Test
	public void testUsingKeyCrypto() throws Exception {

		byte[] key = RandomUtils.nextBytes(16);
		byte[] iv = RandomUtils.nextBytes(16);

		AESCrypto crypto0 = new AESCrypto();
		crypto0.setSecretKeyBytes(key);
		crypto0.setInitVectorBytes(iv);

		AESCrypto keyCrypto = new AESCrypto();
		keyCrypto.setSecretKeyBytes(RandomUtils.nextBytes(16));
		keyCrypto.setInitVectorBytes(RandomUtils.nextBytes(16));

		AESCrypto crypto1 = new AESCrypto();
		crypto1.setKeyCrypto(keyCrypto);
		crypto1.setSecretKeyBytes(keyCrypto.encrypt(key));
		crypto1.setInitVectorBytes(keyCrypto.encrypt(iv));

		for (int i = 0; i < 100; i++) {
			byte[] plain = RandomUtils.nextBytes(1024);
			byte[] enc0 = crypto0.encrypt(plain);
			byte[] enc1 = crypto1.encrypt(plain);
			assertThat(enc1, is(enc0));
			byte[] dec0 = crypto0.decrypt(enc0);
			byte[] dec1 = crypto1.decrypt(enc1);
			assertThat(dec0, is(plain));
			assertThat(dec1, is(plain));
		}
	}

}
