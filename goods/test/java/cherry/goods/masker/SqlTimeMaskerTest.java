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

import java.sql.Time;

import org.joda.time.LocalTime;
import org.junit.Test;

public class SqlTimeMaskerTest {

	@Test
	public void testMaskHour() {
		SqlTimeMasker masker = SqlTimeMasker.newMasker(new LocalTime(0, 0, 0), true, false, false);
		LocalTime v = LocalTime.now();
		Time vv = new Time(v.getMillisOfDay());
		assertThat(masker.mask(null), is(nullValue()));
		assertThat((int) masker.mask(vv).getTime(),
				is(new LocalTime(0, v.getMinuteOfHour(), v.getSecondOfMinute()).getMillisOfDay()));
	}

	@Test
	public void testMaskMinute() {
		SqlTimeMasker masker = SqlTimeMasker.newMasker(new LocalTime(0, 0, 0), false, true, false);
		LocalTime v = LocalTime.now();
		Time vv = new Time(v.getMillisOfDay());
		assertThat(masker.mask(null), is(nullValue()));
		assertThat((int) masker.mask(vv).getTime(),
				is(new LocalTime(v.getHourOfDay(), 0, v.getSecondOfMinute()).getMillisOfDay()));
	}

	@Test
	public void testMaskSecond() {
		SqlTimeMasker masker = SqlTimeMasker.newMasker(new LocalTime(0, 0, 0), false, false, true);
		LocalTime v = LocalTime.now();
		Time vv = new Time(v.getMillisOfDay());
		assertThat(masker.mask(null), is(nullValue()));
		assertThat((int) masker.mask(vv).getTime(),
				is(new LocalTime(v.getHourOfDay(), v.getMinuteOfHour(), 0).getMillisOfDay()));
	}

}
