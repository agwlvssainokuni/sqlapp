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

package cherry.goods.masker;

import org.joda.time.LocalDate;

public abstract class LocalDateMasker implements Masker<LocalDate> {

	public static LocalDateMasker newMasker(LocalDate mask, boolean year,
			boolean month, boolean day) {
		return new MaskerImpl(mask, year, month, day);
	}

	static class MaskerImpl extends LocalDateMasker {

		private final LocalDate mask;

		private final boolean year;

		private final boolean month;

		private final boolean day;

		public MaskerImpl(LocalDate mask, boolean year, boolean month,
				boolean day) {
			this.mask = mask;
			this.year = year;
			this.month = month;
			this.day = day;
		}

		@Override
		public LocalDate mask(LocalDate value) {
			if (value == null) {
				return value;
			}
			int year = (this.year ? mask.getYear() : value.getYear());
			int month = (this.month ? mask.getMonthOfYear() : value
					.getMonthOfYear());
			int day = (this.day ? mask.getDayOfMonth() : value.getDayOfMonth());
			return new LocalDate(year, month, day);
		}
	}

}
