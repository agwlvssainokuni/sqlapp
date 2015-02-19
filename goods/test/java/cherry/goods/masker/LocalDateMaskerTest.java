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
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.joda.time.LocalDate;
import org.junit.Test;

public class LocalDateMaskerTest {

	@Test
	public void testMaskYear() {
		LocalDateMasker masker = LocalDateMasker.newMasker(new LocalDate(1970, 1, 1), true, false, false);
		LocalDate v = LocalDate.now();
		assertThat(masker.mask(null), is(nullValue()));
		assertThat(masker.mask(v), is(new LocalDate(1970, v.getMonthOfYear(), v.getDayOfMonth())));
	}

	@Test
	public void testMaskMonth() {
		LocalDateMasker masker = LocalDateMasker.newMasker(new LocalDate(1970, 1, 1), false, true, false);
		LocalDate v = LocalDate.now();
		assertThat(masker.mask(null), is(nullValue()));
		assertThat(masker.mask(v), is(new LocalDate(v.getYear(), 1, v.getDayOfMonth())));
	}

	@Test
	public void testMaskDay() {
		LocalDateMasker masker = LocalDateMasker.newMasker(new LocalDate(1970, 1, 1), false, false, true);
		LocalDate v = LocalDate.now();
		assertThat(masker.mask(null), is(nullValue()));
		assertThat(masker.mask(v), is(new LocalDate(v.getYear(), v.getMonthOfYear(), 1)));
	}

}
