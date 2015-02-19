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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.DecoderException;
import org.junit.Test;
import org.springframework.security.util.InMemoryResource;

import cherry.foundation.type.SecureString;
import cherry.goods.util.RandomUtil;

public class SecureStringEncoderTest {

	@Test
	public void testEncodeDecode() throws Exception {
		SecureStringEncoder encoder = createSecureStringEncoder();
		for (int i = 0; i < 100; i++) {
			String plain = RandomUtil.randomString(1024);
			String crypto = encoder.encode(plain);
			assertThat(crypto, is(not(plain)));
			assertThat(encoder.decode(crypto), is(plain));
		}
	}

	@Test
	public void testSecureString() throws Exception {
		SecureString.setEncoder(createSecureStringEncoder());
		for (int i = 0; i < 100; i++) {

			String plain = RandomUtil.randomString(1024);
			SecureString ss0 = SecureString.plainValueOf(plain);
			assertThat(ss0.plain(), is(plain));
			assertThat(ss0.crypto(), is(not(plain)));

			SecureString ss1 = SecureString.cryptoValueOf(ss0.crypto());
			assertThat(ss1.plain(), is(plain));
			assertThat(ss1.crypto(), is(ss0.crypto()));

			SecureString ss2 = SecureString.plainValueOf(ss1.plain());
			assertThat(ss2.plain(), is(plain));
			assertThat(ss2.crypto(), is(ss0.crypto()));

			SecureString ss3 = SecureString.cryptoValueOf(ss2.crypto());
			assertThat(ss3.plain(), is(plain));
			assertThat(ss3.crypto(), is(ss0.crypto()));
		}
	}

	@Test
	public void testDecoderException() throws Exception {
		SecureStringEncoder encoder = createSecureStringEncoder();
		try {
			encoder.decode("XXXX");
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			assertTrue(ex.getCause() instanceof DecoderException);
		}
	}

	private SecureStringEncoder createSecureStringEncoder() throws Exception {
		AESCryptoSupport crypto = new AESCryptoSupport();
		crypto.setSecretKeyResource(new InMemoryResource(RandomUtil.randomBytes(16)));
		crypto.setInitVectorResource(new InMemoryResource(RandomUtil.randomBytes(16)));
		crypto.afterPropertiesSet();
		SecureStringEncoder encoder = new SecureStringEncoder();
		encoder.setCharset(StandardCharsets.UTF_8);
		encoder.setCrypto(crypto);
		return encoder;
	}

}
