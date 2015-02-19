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

package cherry.goods.util;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.junit.After;
import org.junit.Test;

public class LocalDateTimeUtilTest {

	@After
	public void after() {
		LocalDateTimeUtil.setUnitOfTime(Period.seconds(1));
	}

	@Test
	public void testRangeFromLocalDate() {
		LocalDate now = LocalDate.now();
		assertThat(LocalDateTimeUtil.rangeFrom((LocalDate) null), is(nullValue()));
		assertThat(LocalDateTimeUtil.rangeFrom(now),
				is(new LocalDateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 0, 0, 0, 0)));
	}

	@Test
	public void testRangeFromLocalDateLocalTime() {
		LocalDate nowD = LocalDate.now();
		LocalTime nowT = LocalTime.now();
		assertThat(LocalDateTimeUtil.rangeFrom((LocalDate) null, (LocalTime) null), is(nullValue()));
		assertThat(LocalDateTimeUtil.rangeFrom(nowD, (LocalTime) null),
				is(new LocalDateTime(nowD.getYear(), nowD.getMonthOfYear(), nowD.getDayOfMonth(), 0, 0, 0, 0)));
		assertThat(LocalDateTimeUtil.rangeFrom(nowD, nowT),
				is(new LocalDateTime(nowD.getYear(), nowD.getMonthOfYear(), nowD.getDayOfMonth(), nowT.getHourOfDay(),
						nowT.getMinuteOfHour(), nowT.getSecondOfMinute(), nowT.getMillisOfSecond())));
	}

	@Test
	public void testRangeFromLocalDateTime() {
		LocalDateTime now = LocalDateTime.now();
		assertThat(LocalDateTimeUtil.rangeFrom((LocalDateTime) null), is(nullValue()));
		assertThat(
				LocalDateTimeUtil.rangeFrom(now),
				is(new LocalDateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), now.getHourOfDay(), now
						.getMinuteOfHour(), now.getSecondOfMinute(), now.getMillisOfSecond())));
	}

	@Test
	public void testRangeToLocalDate() {
		LocalDate now = LocalDate.now();
		assertThat(LocalDateTimeUtil.rangeTo((LocalDate) null), is(nullValue()));
		assertThat(LocalDateTimeUtil.rangeTo(now),
				is(new LocalDateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 0, 0, 0, 0).plusDays(1)));
	}

	@Test
	public void testRangeToLocalDateLocalTime() {
		LocalDate nowD = LocalDate.now();
		LocalTime nowT = LocalTime.now();
		assertThat(LocalDateTimeUtil.rangeTo((LocalDate) null, (LocalTime) null), is(nullValue()));
		assertThat(LocalDateTimeUtil.rangeTo(nowD, (LocalTime) null),
				is(new LocalDateTime(nowD.getYear(), nowD.getMonthOfYear(), nowD.getDayOfMonth(), 0, 0, 0, 0)
						.plusDays(1)));
		assertThat(LocalDateTimeUtil.rangeTo(nowD, nowT),
				is(new LocalDateTime(nowD.getYear(), nowD.getMonthOfYear(), nowD.getDayOfMonth(), nowT.getHourOfDay(),
						nowT.getMinuteOfHour(), nowT.getSecondOfMinute(), nowT.getMillisOfSecond()).plusSeconds(1)));
	}

	@Test
	public void testSetUnitOfTimeAndRangeToLocalDateLocalTime() {
		LocalDateTimeUtil.setUnitOfTime(Period.minutes(1));

		LocalDate nowD = LocalDate.now();
		LocalTime nowT = LocalTime.now();
		assertThat(LocalDateTimeUtil.rangeTo(nowD, nowT),
				is(new LocalDateTime(nowD.getYear(), nowD.getMonthOfYear(), nowD.getDayOfMonth(), nowT.getHourOfDay(),
						nowT.getMinuteOfHour(), nowT.getSecondOfMinute(), nowT.getMillisOfSecond()).plusMinutes(1)));
	}

	@Test
	public void testRangeToLocalDateTime() {
		LocalDateTime now = LocalDateTime.now();
		assertThat(LocalDateTimeUtil.rangeTo((LocalDateTime) null), is(nullValue()));
		assertThat(
				LocalDateTimeUtil.rangeTo(now),
				is(new LocalDateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), now.getHourOfDay(), now
						.getMinuteOfHour(), now.getSecondOfMinute(), now.getMillisOfSecond()).plusSeconds(1)));
	}

	@Test
	public void testSetUnitOfTimeAndRangeToLocalDateTime() {
		LocalDateTimeUtil.setUnitOfTime(Period.minutes(1));

		LocalDateTime now = LocalDateTime.now();
		assertThat(
				LocalDateTimeUtil.rangeTo(now),
				is(new LocalDateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), now.getHourOfDay(), now
						.getMinuteOfHour(), now.getSecondOfMinute(), now.getMillisOfSecond()).plusMinutes(1)));
	}

	@Test
	public void testMisc() {
		assertThat(new LocalDateTimeUtil(), is(notNullValue()));
	}

}
