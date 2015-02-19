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

import java.math.BigInteger;
import java.security.SecureRandom;

import org.junit.Test;
import org.springframework.security.util.InMemoryResource;

import cherry.foundation.type.SecureBigInteger;
import cherry.goods.util.RandomUtil;

public class SecureBigIntegerEncoderTest {

	private SecureRandom random = new SecureRandom();

	@Test
	public void testEncodeDecode() throws Exception {
		SecureBigIntegerEncoder encoder = createSecureBigIntegerEncoder();
		for (int i = 0; i < 100; i++) {
			BigInteger plain = BigInteger.valueOf(random.nextLong());
			String crypto = encoder.encode(plain);
			assertThat(crypto, is(not(plain.toString())));
			assertThat(encoder.decode(crypto), is(plain));
		}
	}

	@Test
	public void testSecureBigInteger() throws Exception {
		SecureBigInteger.setEncoder(createSecureBigIntegerEncoder());
		for (int i = 0; i < 100; i++) {

			BigInteger plain = BigInteger.valueOf(random.nextInt());
			SecureBigInteger ss0 = SecureBigInteger.plainValueOf(plain);
			assertThat(ss0.plain(), is(plain));
			assertThat(ss0.crypto(), is(not(plain.toString())));

			SecureBigInteger ss1 = SecureBigInteger.cryptoValueOf(ss0.crypto());
			assertThat(ss1.plain(), is(plain));
			assertThat(ss1.crypto(), is(ss0.crypto()));

			SecureBigInteger ss2 = SecureBigInteger.plainValueOf(ss1.plain());
			assertThat(ss2.plain(), is(plain));
			assertThat(ss2.crypto(), is(ss0.crypto()));

			SecureBigInteger ss3 = SecureBigInteger.cryptoValueOf(ss2.crypto());
			assertThat(ss3.plain(), is(plain));
			assertThat(ss3.crypto(), is(ss0.crypto()));
		}
	}

	private SecureBigIntegerEncoder createSecureBigIntegerEncoder() throws Exception {
		AESCryptoSupport crypto = new AESCryptoSupport();
		crypto.setSecretKeyResource(new InMemoryResource(RandomUtil.randomBytes(16)));
		crypto.setInitVectorResource(new InMemoryResource(RandomUtil.randomBytes(16)));
		crypto.afterPropertiesSet();
		SecureBigIntegerEncoder encoder = new SecureBigIntegerEncoder();
		encoder.setCrypto(crypto);
		return encoder;
	}

}
