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

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

public class RSACryptoTest {

	@Test
	public void testEncDec() throws Exception {
		RSACrypto helper = createCrypto();
		for (int i = 0; i < 100; i++) {
			byte[] plain = RandomUtils.nextBytes(245);
			byte[] crypto = helper.encrypt(plain);
			assertThat(crypto, is(not(plain)));
			assertThat(helper.decrypt(crypto), is(plain));
		}
	}

	private RSACrypto createCrypto() throws Exception {
		KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
		keygen.initialize(2048);
		KeyPair key = keygen.generateKeyPair();
		RSACrypto helper = new RSACrypto();
		helper.setAlgorithm("RSA/ECB/PKCS1Padding");
		helper.setPublicKeyBytes(key.getPublic().getEncoded());
		helper.setPrivateKeyBytes(key.getPrivate().getEncoded());
		return helper;
	}

}
