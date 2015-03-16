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

package cherry.foundation.type;

import static cherry.foundation.type.SecureBigInteger.cryptoValueOf;
import static cherry.foundation.type.SecureBigInteger.plainValueOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.foundation.type.SecureType.Encoder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class SecureBigIntegerTest {

	@Test
	public void testRandomTest() {
		for (int i = 0; i < 100; i++) {
			BigInteger plain = new BigInteger(RandomUtils.nextBytes(1024));
			SecureBigInteger sec0 = plainValueOf(plain);
			SecureBigInteger sec1 = cryptoValueOf(sec0.crypto());
			assertThat(sec1.plain(), is(plain));
		}
	}

	@Test
	public void testToString() {
		SecureBigInteger sec = plainValueOf(BigInteger.ONE);
		assertThat(sec.toString(), is("SecureBigInteger[1]"));
	}

	@Test
	public void testNoneEncoder() {
		Encoder<BigInteger> encoder = new SecureBigInteger.NoneEncoder();
		assertThat(encoder.encode(null), is(nullValue()));
		assertThat(encoder.encode(BigInteger.ONE), is("1"));
		assertThat(encoder.decode(null), is(nullValue()));
		assertThat(encoder.decode("1"), is(BigInteger.ONE));
	}

}
