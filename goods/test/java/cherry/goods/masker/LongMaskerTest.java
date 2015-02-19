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

public class LongMaskerTest {

	@Test
	public void testLowerDigit() {
		LongMasker masker = LongMasker.lowerDigit(999999L, 2);
		assertThat(masker, is(notNullValue()));
		assertThat(masker.mask(null), is(nullValue(Long.class)));
		assertThat(masker.mask(0L), is(0L));
		assertThat(masker.mask(1L), is(1L));
		assertThat(masker.mask(12L), is(12L));
		assertThat(masker.mask(123L), is(129L));
		assertThat(masker.mask(1234L), is(1299L));
		assertThat(masker.mask(12345L), is(12999L));
		assertThat(masker.mask(123456L), is(129999L));
		assertThat(masker.mask(1234567L), is(1299999L));
		assertThat(masker.mask(12345678L), is(12999999L));
		assertThat(masker.mask(-1L), is(-1L));
		assertThat(masker.mask(-12L), is(-12L));
		assertThat(masker.mask(-123L), is(-129L));
		assertThat(masker.mask(-1234L), is(-1299L));
		assertThat(masker.mask(-12345L), is(-12999L));
		assertThat(masker.mask(-123456L), is(-129999L));
		assertThat(masker.mask(-1234567L), is(-1299999L));
		assertThat(masker.mask(-12345678L), is(-12999999L));
	}

	@Test
	public void testUpperDigit() {
		LongMasker masker = LongMasker.upperDigit(999999L, 2);
		assertThat(masker, is(notNullValue()));
		assertThat(masker.mask(null), is(nullValue(Long.class)));
		assertThat(masker.mask(0L), is(0L));
		assertThat(masker.mask(1L), is(1L));
		assertThat(masker.mask(12L), is(12L));
		assertThat(masker.mask(123L), is(923L));
		assertThat(masker.mask(1234L), is(9934L));
		assertThat(masker.mask(12345L), is(99945L));
		assertThat(masker.mask(123456L), is(999956L));
		assertThat(masker.mask(1234567L), is(999967L));
		assertThat(masker.mask(12345678L), is(999978L));
		assertThat(masker.mask(-1L), is(-1L));
		assertThat(masker.mask(-12L), is(-12L));
		assertThat(masker.mask(-123L), is(-923L));
		assertThat(masker.mask(-1234L), is(-9934L));
		assertThat(masker.mask(-12345L), is(-99945L));
		assertThat(masker.mask(-123456L), is(-999956L));
		assertThat(masker.mask(-1234567L), is(-999967L));
		assertThat(masker.mask(-12345678L), is(-999978L));
	}

	@Test
	public void testFixedUpperDigit() {
		LongMasker masker = LongMasker.fixedUpperDigit(999999L, 2);
		assertThat(masker, is(notNullValue()));
		assertThat(masker.mask(null), is(nullValue(Long.class)));
		assertThat(masker.mask(0L), is(999900L));
		assertThat(masker.mask(1L), is(999901L));
		assertThat(masker.mask(12L), is(999912L));
		assertThat(masker.mask(123L), is(999923L));
		assertThat(masker.mask(1234L), is(999934L));
		assertThat(masker.mask(12345L), is(999945L));
		assertThat(masker.mask(123456L), is(999956L));
		assertThat(masker.mask(1234567L), is(999967L));
		assertThat(masker.mask(12345678L), is(999978L));
		assertThat(masker.mask(-1L), is(-999901L));
		assertThat(masker.mask(-12L), is(-999912L));
		assertThat(masker.mask(-123L), is(-999923L));
		assertThat(masker.mask(-1234L), is(-999934L));
		assertThat(masker.mask(-12345L), is(-999945L));
		assertThat(masker.mask(-123456L), is(-999956L));
		assertThat(masker.mask(-1234567L), is(-999967L));
		assertThat(masker.mask(-12345678L), is(-999978L));
	}

}
