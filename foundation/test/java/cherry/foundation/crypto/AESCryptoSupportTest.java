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

package cherry.foundation.crypto;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.security.util.InMemoryResource;

public class AESCryptoSupportTest {

	@Test
	public void testDefault() throws Exception {

		AESCryptoSupport crypto = new AESCryptoSupport();
		crypto.setSecretKeyResource(new InMemoryResource(RandomUtils.nextBytes(16)));
		crypto.setInitVectorResource(new InMemoryResource(RandomUtils.nextBytes(16)));
		crypto.afterPropertiesSet();

		for (int i = 0; i < 100; i++) {
			byte[] plain = RandomUtils.nextBytes(1024);
			byte[] enc = crypto.encrypt(plain);
			byte[] dec = crypto.decrypt(enc);
			assertThat(dec, is(plain));
		}
	}

	@Test
	public void testCBC() throws Exception {

		AESCryptoSupport crypto = new AESCryptoSupport();
		crypto.setAlgorithm("AES/CBC/PKCS5Padding");
		crypto.setSecretKeyResource(new InMemoryResource(RandomUtils.nextBytes(16)));
		crypto.setInitVectorResource(new InMemoryResource(RandomUtils.nextBytes(16)));
		crypto.afterPropertiesSet();

		for (int i = 0; i < 100; i++) {
			byte[] plain = RandomUtils.nextBytes(1024);
			byte[] enc = crypto.encrypt(plain);
			byte[] dec = crypto.decrypt(enc);
			assertThat(dec, is(plain));
		}
	}

	@Test
	public void testECB() throws Exception {

		AESCryptoSupport crypto = new AESCryptoSupport();
		crypto.setAlgorithm("AES/ECB/PKCS5Padding");
		crypto.setSecretKeyResource(new InMemoryResource(RandomUtils.nextBytes(16)));
		crypto.afterPropertiesSet();

		for (int i = 0; i < 100; i++) {
			byte[] plain = RandomUtils.nextBytes(1024);
			byte[] enc = crypto.encrypt(plain);
			byte[] dec = crypto.decrypt(enc);
			assertThat(dec, is(plain));
		}
	}

	@Test
	public void testUsingKeyCipherHelper() throws Exception {

		byte[] key = RandomUtils.nextBytes(16);
		byte[] iv = RandomUtils.nextBytes(16);

		AESCryptoSupport crypto0 = new AESCryptoSupport();
		crypto0.setSecretKeyResource(new InMemoryResource(key));
		crypto0.setInitVectorResource(new InMemoryResource(iv));
		crypto0.afterPropertiesSet();

		AESCryptoSupport keyCrypto = new AESCryptoSupport();
		keyCrypto.setSecretKeyResource(new InMemoryResource(RandomUtils.nextBytes(16)));
		keyCrypto.setInitVectorResource(new InMemoryResource(RandomUtils.nextBytes(16)));
		keyCrypto.afterPropertiesSet();

		AESCryptoSupport crypto1 = new AESCryptoSupport();
		crypto1.setKeyCrypto(keyCrypto);
		crypto1.setSecretKeyResource(new InMemoryResource(keyCrypto.encrypt(key)));
		crypto1.setInitVectorResource(new InMemoryResource(keyCrypto.encrypt(iv)));
		crypto1.afterPropertiesSet();

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
