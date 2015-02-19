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

import static cherry.foundation.type.SecureInteger.cryptoValueOf;
import static cherry.foundation.type.SecureInteger.plainValueOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.security.SecureRandom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.foundation.type.SecureType.Encoder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class SecureIntegerTest {

	private SecureRandom random = new SecureRandom();

	@Test
	public void testRandomTest() {
		for (int i = 0; i < 100; i++) {
			Integer plain = Integer.valueOf(random.nextInt());
			SecureInteger sec0 = plainValueOf(plain);
			SecureInteger sec1 = cryptoValueOf(sec0.crypto());
			assertThat(sec1.plain(), is(plain));
		}
	}

	@Test
	public void testToString() {
		SecureInteger sec = plainValueOf(1);
		assertThat(sec.toString(), is("SecureInteger[1]"));
	}

	@Test
	public void testNoneEncoder() {
		Encoder<Integer> encoder = new SecureInteger.NoneEncoder();
		assertThat(encoder.encode(null), is(nullValue()));
		assertThat(encoder.encode(1), is("1"));
		assertThat(encoder.decode(null), is(nullValue()));
		assertThat(encoder.decode("1"), is(1));
	}

}
