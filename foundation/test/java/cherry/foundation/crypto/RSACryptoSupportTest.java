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
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.security.util.InMemoryResource;

public class RSACryptoSupportTest {

	@Test
	public void testEncDec() throws Exception {
		RSACryptoSupport crypto = createCrypto();
		for (int i = 0; i < 100; i++) {
			byte[] p = RandomUtils.nextBytes(245);
			byte[] c = crypto.encrypt(p);
			assertThat(c, is(not(p)));
			assertThat(crypto.decrypt(c), is(p));
		}
	}

	private RSACryptoSupport createCrypto() throws Exception {
		KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
		keygen.initialize(2048);
		KeyPair key = keygen.generateKeyPair();
		RSACryptoSupport crypto = new RSACryptoSupport();
		crypto.setAlgorithm("RSA/ECB/PKCS1Padding");
		crypto.setPublicKeyResource(new InMemoryResource(key.getPublic().getEncoded()));
		crypto.setPrivateKeyResource(new InMemoryResource(key.getPrivate().getEncoded()));
		crypto.afterPropertiesSet();
		return crypto;
	}

}
