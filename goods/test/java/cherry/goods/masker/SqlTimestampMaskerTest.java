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

import java.sql.Timestamp;

import org.joda.time.LocalDateTime;
import org.junit.Test;

public class SqlTimestampMaskerTest {

	@Test
	public void testMaskYear() {
		SqlTimestampMasker masker = SqlTimestampMasker.newMasker(new LocalDateTime(1970, 1, 1, 0, 0, 0), true, false,
				false, false, false, false);
		LocalDateTime v = LocalDateTime.now();
		Timestamp vv = new Timestamp(v.toDate().getTime());
		assertThat(masker.mask(null), is(nullValue()));
		assertThat(masker.mask(vv).getTime(),
				is(new LocalDateTime(1970, v.getMonthOfYear(), v.getDayOfMonth(), v.getHourOfDay(),
						v.getMinuteOfHour(), v.getSecondOfMinute()).toDate().getTime()));
	}

	@Test
	public void testMaskMonth() {
		SqlTimestampMasker masker = SqlTimestampMasker.newMasker(new LocalDateTime(1970, 1, 1, 0, 0, 0), false, true,
				false, false, false, false);
		LocalDateTime v = LocalDateTime.now();
		Timestamp vv = new Timestamp(v.toDate().getTime());
		assertThat(masker.mask(null), is(nullValue()));
		assertThat(masker.mask(vv).getTime(), is(new LocalDateTime(v.getYear(), 1, v.getDayOfMonth(), v.getHourOfDay(),
				v.getMinuteOfHour(), v.getSecondOfMinute()).toDate().getTime()));
	}

	@Test
	public void testMaskDay() {
		SqlTimestampMasker masker = SqlTimestampMasker.newMasker(new LocalDateTime(1970, 1, 1, 0, 0, 0), false, false,
				true, false, false, false);
		LocalDateTime v = LocalDateTime.now();
		Timestamp vv = new Timestamp(v.toDate().getTime());
		assertThat(masker.mask(null), is(nullValue()));
		assertThat(masker.mask(vv).getTime(), is(new LocalDateTime(v.getYear(), v.getMonthOfYear(), 1,
				v.getHourOfDay(), v.getMinuteOfHour(), v.getSecondOfMinute()).toDate().getTime()));
	}

	@Test
	public void testMaskHour() {
		SqlTimestampMasker masker = SqlTimestampMasker.newMasker(new LocalDateTime(1970, 1, 1, 0, 0, 0), false, false,
				false, true, false, false);
		LocalDateTime v = LocalDateTime.now();
		Timestamp vv = new Timestamp(v.toDate().getTime());
		assertThat(masker.mask(null), is(nullValue()));
		assertThat(masker.mask(vv).getTime(), is(new LocalDateTime(v.getYear(), v.getMonthOfYear(), v.getDayOfMonth(),
				0, v.getMinuteOfHour(), v.getSecondOfMinute()).toDate().getTime()));
	}

	@Test
	public void testMaskMinute() {
		SqlTimestampMasker masker = SqlTimestampMasker.newMasker(new LocalDateTime(1970, 1, 1, 0, 0, 0), false, false,
				false, false, true, false);
		LocalDateTime v = LocalDateTime.now();
		Timestamp vv = new Timestamp(v.toDate().getTime());
		assertThat(masker.mask(null), is(nullValue()));
		assertThat(masker.mask(vv).getTime(), is(new LocalDateTime(v.getYear(), v.getMonthOfYear(), v.getDayOfMonth(),
				v.getHourOfDay(), 0, v.getSecondOfMinute()).toDate().getTime()));
	}

	@Test
	public void testMaskSecond() {
		SqlTimestampMasker masker = SqlTimestampMasker.newMasker(new LocalDateTime(1970, 1, 1, 0, 0, 0), false, false,
				false, false, false, true);
		LocalDateTime v = LocalDateTime.now();
		Timestamp vv = new Timestamp(v.toDate().getTime());
		assertThat(masker.mask(null), is(nullValue()));
		assertThat(masker.mask(vv).getTime(), is(new LocalDateTime(v.getYear(), v.getMonthOfYear(), v.getDayOfMonth(),
				v.getHourOfDay(), v.getMinuteOfHour(), 0).toDate().getTime()));
	}

}
