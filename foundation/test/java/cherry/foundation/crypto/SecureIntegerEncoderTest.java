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

import java.security.SecureRandom;

import org.junit.Test;
import org.springframework.security.util.InMemoryResource;

import cherry.foundation.type.SecureInteger;
import cherry.goods.util.RandomUtil;

public class SecureIntegerEncoderTest {

	private SecureRandom random = new SecureRandom();

	@Test
	public void testEncodeDecode() throws Exception {
		SecureIntegerEncoder encoder = createSecureIntegerEncoder();
		for (int i = 0; i < 100; i++) {
			Integer plain = Integer.valueOf(random.nextInt());
			String crypto = encoder.encode(plain);
			assertThat(crypto, is(not(plain.toString())));
			assertThat(encoder.decode(crypto), is(plain));
		}
	}

	@Test
	public void testSecureInteger() throws Exception {
		SecureInteger.setEncoder(createSecureIntegerEncoder());
		for (int i = 0; i < 100; i++) {

			Integer plain = Integer.valueOf(random.nextInt());
			SecureInteger ss0 = SecureInteger.plainValueOf(plain);
			assertThat(ss0.plain(), is(plain));
			assertThat(ss0.crypto(), is(not(plain.toString())));

			SecureInteger ss1 = SecureInteger.cryptoValueOf(ss0.crypto());
			assertThat(ss1.plain(), is(plain));
			assertThat(ss1.crypto(), is(ss0.crypto()));

			SecureInteger ss2 = SecureInteger.plainValueOf(ss1.plain());
			assertThat(ss2.plain(), is(plain));
			assertThat(ss2.crypto(), is(ss0.crypto()));

			SecureInteger ss3 = SecureInteger.cryptoValueOf(ss2.crypto());
			assertThat(ss3.plain(), is(plain));
			assertThat(ss3.crypto(), is(ss0.crypto()));
		}
	}

	private SecureIntegerEncoder createSecureIntegerEncoder() throws Exception {
		AESCryptoSupport crypto = new AESCryptoSupport();
		crypto.setSecretKeyResource(new InMemoryResource(RandomUtil.randomBytes(16)));
		crypto.setInitVectorResource(new InMemoryResource(RandomUtil.randomBytes(16)));
		crypto.afterPropertiesSet();
		SecureIntegerEncoder encoder = new SecureIntegerEncoder();
		encoder.setCrypto(crypto);
		return encoder;
	}

}
