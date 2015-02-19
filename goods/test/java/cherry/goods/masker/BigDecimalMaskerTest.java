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

import static java.math.BigDecimal.valueOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class BigDecimalMaskerTest {

	@Test
	public void testLowerDigit() {
		BigDecimalMasker masker = BigDecimalMasker.lowerDigit(new BigDecimal("999999.99"), 2);
		assertThat(masker, is(notNullValue()));
		assertThat(masker.mask(null), is(nullValue(BigDecimal.class)));
		assertThat(masker.mask(valueOf(0L)), is(valOf("0.99")));
		assertThat(masker.mask(valueOf(1L)), is(valOf("1.99")));
		assertThat(masker.mask(valueOf(12L)), is(valOf("12.99")));
		assertThat(masker.mask(valueOf(123L)), is(valOf("129.99")));
		assertThat(masker.mask(valueOf(1234L)), is(valOf("1299.99")));
		assertThat(masker.mask(valueOf(12345L)), is(valOf("12999.99")));
		assertThat(masker.mask(valueOf(123456L)), is(valOf("129999.99")));
		assertThat(masker.mask(valueOf(1234567L)), is(valOf("1299999.99")));
		assertThat(masker.mask(valueOf(12345678L)), is(valOf("12999999.99")));
		assertThat(masker.mask(valueOf(-1L)), is(valOf("-1.99")));
		assertThat(masker.mask(valueOf(-12L)), is(valOf("-12.99")));
		assertThat(masker.mask(valueOf(-123L)), is(valOf("-129.99")));
		assertThat(masker.mask(valueOf(-1234L)), is(valOf("-1299.99")));
		assertThat(masker.mask(valueOf(-12345L)), is(valOf("-12999.99")));
		assertThat(masker.mask(valueOf(-123456L)), is(valOf("-129999.99")));
		assertThat(masker.mask(valueOf(-1234567L)), is(valOf("-1299999.99")));
		assertThat(masker.mask(valueOf(-12345678L)), is(valOf("-12999999.99")));
	}

	@Test
	public void testUpperDigit() {
		BigDecimalMasker masker = BigDecimalMasker.upperDigit(new BigDecimal("999999.99"), 2);
		assertThat(masker, is(notNullValue()));
		assertThat(masker.mask(null), is(nullValue(BigDecimal.class)));
		assertThat(masker.mask(valueOf(0L)), is(valOf("0.99")));
		assertThat(masker.mask(valueOf(1L)), is(valOf("1.99")));
		assertThat(masker.mask(valueOf(12L)), is(valOf("12.99")));
		assertThat(masker.mask(valueOf(123L)), is(valOf("923.99")));
		assertThat(masker.mask(valueOf(1234L)), is(valOf("9934.99")));
		assertThat(masker.mask(valueOf(12345L)), is(valOf("99945.99")));
		assertThat(masker.mask(valueOf(123456L)), is(valOf("999956.99")));
		assertThat(masker.mask(valueOf(1234567L)), is(valOf("999967.99")));
		assertThat(masker.mask(valueOf(12345678L)), is(valOf("999978.99")));
		assertThat(masker.mask(valueOf(-1L)), is(valOf("-1.99")));
		assertThat(masker.mask(valueOf(-12L)), is(valOf("-12.99")));
		assertThat(masker.mask(valueOf(-123L)), is(valOf("-923.99")));
		assertThat(masker.mask(valueOf(-1234L)), is(valOf("-9934.99")));
		assertThat(masker.mask(valueOf(-12345L)), is(valOf("-99945.99")));
		assertThat(masker.mask(valueOf(-123456L)), is(valOf("-999956.99")));
		assertThat(masker.mask(valueOf(-1234567L)), is(valOf("-999967.99")));
		assertThat(masker.mask(valueOf(-12345678L)), is(valOf("-999978.99")));
	}

	@Test
	public void testFixedUpperDigit() {
		BigDecimalMasker masker = BigDecimalMasker.fixedUpperDigit(new BigDecimal("999999.99"), 2);
		assertThat(masker, is(notNullValue()));
		assertThat(masker.mask(null), is(nullValue(BigDecimal.class)));
		assertThat(masker.mask(valueOf(0L)), is(valOf("999900.99")));
		assertThat(masker.mask(valueOf(1L)), is(valOf("999901.99")));
		assertThat(masker.mask(valueOf(12L)), is(valOf("999912.99")));
		assertThat(masker.mask(valueOf(123L)), is(valOf("999923.99")));
		assertThat(masker.mask(valueOf(1234L)), is(valOf("999934.99")));
		assertThat(masker.mask(valueOf(12345L)), is(valOf("999945.99")));
		assertThat(masker.mask(valueOf(123456L)), is(valOf("999956.99")));
		assertThat(masker.mask(valueOf(1234567L)), is(valOf("999967.99")));
		assertThat(masker.mask(valueOf(12345678L)), is(valOf("999978.99")));
		assertThat(masker.mask(valueOf(-1L)), is(valOf("-999901.99")));
		assertThat(masker.mask(valueOf(-12L)), is(valOf("-999912.99")));
		assertThat(masker.mask(valueOf(-123L)), is(valOf("-999923.99")));
		assertThat(masker.mask(valueOf(-1234L)), is(valOf("-999934.99")));
		assertThat(masker.mask(valueOf(-12345L)), is(valOf("-999945.99")));
		assertThat(masker.mask(valueOf(-123456L)), is(valOf("-999956.99")));
		assertThat(masker.mask(valueOf(-1234567L)), is(valOf("-999967.99")));
		assertThat(masker.mask(valueOf(-12345678L)), is(valOf("-999978.99")));
	}

	private BigDecimal valOf(String s) {
		return new BigDecimal(s);
	}

}
