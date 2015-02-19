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

package cherry.goods.masker;

import static java.math.BigInteger.valueOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;

import org.junit.Test;

public class BigIntegerMaskerTest {

	@Test
	public void testLowerDigit() {
		BigIntegerMasker masker = BigIntegerMasker.lowerDigit(valueOf(999999L), 2);
		assertThat(masker, is(notNullValue()));
		assertThat(masker.mask(null), is(nullValue(BigInteger.class)));
		assertThat(masker.mask(valueOf(0L)), is(valueOf(0L)));
		assertThat(masker.mask(valueOf(1L)), is(valueOf(1L)));
		assertThat(masker.mask(valueOf(12L)), is(valueOf(12L)));
		assertThat(masker.mask(valueOf(123L)), is(valueOf(129L)));
		assertThat(masker.mask(valueOf(1234L)), is(valueOf(1299L)));
		assertThat(masker.mask(valueOf(12345L)), is(valueOf(12999L)));
		assertThat(masker.mask(valueOf(123456L)), is(valueOf(129999L)));
		assertThat(masker.mask(valueOf(1234567L)), is(valueOf(1299999L)));
		assertThat(masker.mask(valueOf(12345678L)), is(valueOf(12999999L)));
		assertThat(masker.mask(valueOf(-1L)), is(valueOf(-1L)));
		assertThat(masker.mask(valueOf(-12L)), is(valueOf(-12L)));
		assertThat(masker.mask(valueOf(-123L)), is(valueOf(-129L)));
		assertThat(masker.mask(valueOf(-1234L)), is(valueOf(-1299L)));
		assertThat(masker.mask(valueOf(-12345L)), is(valueOf(-12999L)));
		assertThat(masker.mask(valueOf(-123456L)), is(valueOf(-129999L)));
		assertThat(masker.mask(valueOf(-1234567L)), is(valueOf(-1299999L)));
		assertThat(masker.mask(valueOf(-12345678L)), is(valueOf(-12999999L)));
	}

	@Test
	public void testUpperDigit() {
		BigIntegerMasker masker = BigIntegerMasker.upperDigit(valueOf(999999L), 2);
		assertThat(masker, is(notNullValue()));
		assertThat(masker.mask(null), is(nullValue(BigInteger.class)));
		assertThat(masker.mask(valueOf(0L)), is(valueOf(0L)));
		assertThat(masker.mask(valueOf(1L)), is(valueOf(1L)));
		assertThat(masker.mask(valueOf(12L)), is(valueOf(12L)));
		assertThat(masker.mask(valueOf(123L)), is(valueOf(923L)));
		assertThat(masker.mask(valueOf(1234L)), is(valueOf(9934L)));
		assertThat(masker.mask(valueOf(12345L)), is(valueOf(99945L)));
		assertThat(masker.mask(valueOf(123456L)), is(valueOf(999956L)));
		assertThat(masker.mask(valueOf(1234567L)), is(valueOf(999967L)));
		assertThat(masker.mask(valueOf(12345678L)), is(valueOf(999978L)));
		assertThat(masker.mask(valueOf(-1L)), is(valueOf(-1L)));
		assertThat(masker.mask(valueOf(-12L)), is(valueOf(-12L)));
		assertThat(masker.mask(valueOf(-123L)), is(valueOf(-923L)));
		assertThat(masker.mask(valueOf(-1234L)), is(valueOf(-9934L)));
		assertThat(masker.mask(valueOf(-12345L)), is(valueOf(-99945L)));
		assertThat(masker.mask(valueOf(-123456L)), is(valueOf(-999956L)));
		assertThat(masker.mask(valueOf(-1234567L)), is(valueOf(-999967L)));
		assertThat(masker.mask(valueOf(-12345678L)), is(valueOf(-999978L)));
	}

	@Test
	public void testFixedUpperDigit() {
		BigIntegerMasker masker = BigIntegerMasker.fixedUpperDigit(valueOf(999999L), 2);
		assertThat(masker, is(notNullValue()));
		assertThat(masker.mask(null), is(nullValue(BigInteger.class)));
		assertThat(masker.mask(valueOf(0L)), is(valueOf(999900L)));
		assertThat(masker.mask(valueOf(1L)), is(valueOf(999901L)));
		assertThat(masker.mask(valueOf(12L)), is(valueOf(999912L)));
		assertThat(masker.mask(valueOf(123L)), is(valueOf(999923L)));
		assertThat(masker.mask(valueOf(1234L)), is(valueOf(999934L)));
		assertThat(masker.mask(valueOf(12345L)), is(valueOf(999945L)));
		assertThat(masker.mask(valueOf(123456L)), is(valueOf(999956L)));
		assertThat(masker.mask(valueOf(1234567L)), is(valueOf(999967L)));
		assertThat(masker.mask(valueOf(12345678L)), is(valueOf(999978L)));
		assertThat(masker.mask(valueOf(-1L)), is(valueOf(-999901L)));
		assertThat(masker.mask(valueOf(-12L)), is(valueOf(-999912L)));
		assertThat(masker.mask(valueOf(-123L)), is(valueOf(-999923L)));
		assertThat(masker.mask(valueOf(-1234L)), is(valueOf(-999934L)));
		assertThat(masker.mask(valueOf(-12345L)), is(valueOf(-999945L)));
		assertThat(masker.mask(valueOf(-123456L)), is(valueOf(-999956L)));
		assertThat(masker.mask(valueOf(-1234567L)), is(valueOf(-999967L)));
		assertThat(masker.mask(valueOf(-12345678L)), is(valueOf(-999978L)));
	}

}
