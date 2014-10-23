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

import org.joda.time.LocalTime;

public abstract class LocalTimeMasker implements Masker<LocalTime> {

	public static LocalTimeMasker newMasker(LocalTime mask, boolean hour,
			boolean minute, boolean second) {
		return new MaskerImpl(mask, hour, minute, second);
	}

	static class MaskerImpl extends LocalTimeMasker {

		private final LocalTime mask;

		private final boolean hour;

		private final boolean minute;

		private final boolean second;

		public MaskerImpl(LocalTime mask, boolean hour, boolean minute,
				boolean second) {
			this.mask = mask;
			this.hour = hour;
			this.minute = minute;
			this.second = second;
		}

		@Override
		public LocalTime mask(LocalTime value) {
			if (value == null) {
				return value;
			}
			int hour = (this.hour ? mask.getHourOfDay() : value.getHourOfDay());
			int minute = (this.minute ? mask.getMinuteOfHour() : value
					.getMinuteOfHour());
			int second = (this.second ? mask.getSecondOfMinute() : value
					.getSecondOfMinute());
			return new LocalTime(hour, minute, second);
		}
	}

}
