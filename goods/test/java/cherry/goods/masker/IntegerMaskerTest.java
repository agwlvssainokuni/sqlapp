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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class IntegerMaskerTest {

	@Test
	public void testLowerDigit() {
		IntegerMasker masker = IntegerMasker.lowerDigit(999999, 2);
		assertThat(masker, is(notNullValue()));
		assertThat(masker.mask(null), is(nullValue(Integer.class)));
		assertThat(masker.mask(0), is(0));
		assertThat(masker.mask(1), is(1));
		assertThat(masker.mask(12), is(12));
		assertThat(masker.mask(123), is(129));
		assertThat(masker.mask(1234), is(1299));
		assertThat(masker.mask(12345), is(12999));
		assertThat(masker.mask(123456), is(129999));
		assertThat(masker.mask(1234567), is(1299999));
		assertThat(masker.mask(12345678), is(12999999));
		assertThat(masker.mask(-1), is(-1));
		assertThat(masker.mask(-12), is(-12));
		assertThat(masker.mask(-123), is(-129));
		assertThat(masker.mask(-1234), is(-1299));
		assertThat(masker.mask(-12345), is(-12999));
		assertThat(masker.mask(-123456), is(-129999));
		assertThat(masker.mask(-1234567), is(-1299999));
		assertThat(masker.mask(-12345678), is(-12999999));
	}

	@Test
	public void testUpperDigit() {
		IntegerMasker masker = IntegerMasker.upperDigit(999999, 2);
		assertThat(masker, is(notNullValue()));
		assertThat(masker.mask(null), is(nullValue(Integer.class)));
		assertThat(masker.mask(0), is(0));
		assertThat(masker.mask(1), is(1));
		assertThat(masker.mask(12), is(12));
		assertThat(masker.mask(123), is(923));
		assertThat(masker.mask(1234), is(9934));
		assertThat(masker.mask(12345), is(99945));
		assertThat(masker.mask(123456), is(999956));
		assertThat(masker.mask(1234567), is(999967));
		assertThat(masker.mask(12345678), is(999978));
		assertThat(masker.mask(-1), is(-1));
		assertThat(masker.mask(-12), is(-12));
		assertThat(masker.mask(-123), is(-923));
		assertThat(masker.mask(-1234), is(-9934));
		assertThat(masker.mask(-12345), is(-99945));
		assertThat(masker.mask(-123456), is(-999956));
		assertThat(masker.mask(-1234567), is(-999967));
		assertThat(masker.mask(-12345678), is(-999978));
	}

	@Test
	public void testFixedUpperDigit() {
		IntegerMasker masker = IntegerMasker.fixedUpperDigit(999999, 2);
		assertThat(masker, is(notNullValue()));
		assertThat(masker.mask(null), is(nullValue(Integer.class)));
		assertThat(masker.mask(0), is(999900));
		assertThat(masker.mask(1), is(999901));
		assertThat(masker.mask(12), is(999912));
		assertThat(masker.mask(123), is(999923));
		assertThat(masker.mask(1234), is(999934));
		assertThat(masker.mask(12345), is(999945));
		assertThat(masker.mask(123456), is(999956));
		assertThat(masker.mask(1234567), is(999967));
		assertThat(masker.mask(12345678), is(999978));
		assertThat(masker.mask(-1), is(-999901));
		assertThat(masker.mask(-12), is(-999912));
		assertThat(masker.mask(-123), is(-999923));
		assertThat(masker.mask(-1234), is(-999934));
		assertThat(masker.mask(-12345), is(-999945));
		assertThat(masker.mask(-123456), is(-999956));
		assertThat(masker.mask(-1234567), is(-999967));
		assertThat(masker.mask(-12345678), is(-999978));
	}

}
