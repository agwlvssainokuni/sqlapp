/*
 * Copyright 2014 agwlvssainokuni
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

package cherry.spring.fwcore.mask;

import org.joda.time.LocalDateTime;

public abstract class LocalDateTimeMasker implements Masker<LocalDateTime> {

	public static LocalDateTimeMasker newMasker(LocalDateTime mask,
			boolean year, boolean month, boolean day, boolean hour,
			boolean minute, boolean second) {
		return new MaskerImpl(mask, year, month, day, hour, minute, second);
	}

	static class MaskerImpl extends LocalDateTimeMasker {

		private final LocalDateTime mask;

		private final boolean year;

		private final boolean month;

		private final boolean day;

		private final boolean hour;

		private final boolean minute;

		private final boolean second;

		public MaskerImpl(LocalDateTime mask, boolean year, boolean month,
				boolean day, boolean hour, boolean minute, boolean second) {
			this.mask = mask;
			this.year = year;
			this.month = month;
			this.day = day;
			this.hour = hour;
			this.minute = minute;
			this.second = second;
		}

		@Override
		public LocalDateTime mask(LocalDateTime value) {
			if (value == null) {
				return value;
			}
			int year = (this.year ? mask.getYear() : value.getYear());
			int month = (this.month ? mask.getMonthOfYear() : value
					.getMonthOfYear());
			int day = (this.day ? mask.getDayOfMonth() : value.getDayOfMonth());
			int hour = (this.hour ? mask.getHourOfDay() : value.getHourOfDay());
			int minute = (this.minute ? mask.getMinuteOfHour() : value
					.getMinuteOfHour());
			int second = (this.second ? mask.getSecondOfMinute() : value
					.getSecondOfMinute());
			return new LocalDateTime(year, month, day, hour, minute, second);
		}
	}

}
