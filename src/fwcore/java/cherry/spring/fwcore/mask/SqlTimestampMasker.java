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

import java.sql.Timestamp;

import org.joda.time.LocalDateTime;

public abstract class SqlTimestampMasker implements Masker<Timestamp> {

	public static SqlTimestampMasker newMasker(LocalDateTime mask,
			boolean year, boolean month, boolean day, boolean hour,
			boolean minute, boolean second) {
		return new MaskerImpl(mask, year, month, day, hour, minute, second);
	}

	static class MaskerImpl extends SqlTimestampMasker {

		private final LocalDateTimeMasker masker;

		public MaskerImpl(LocalDateTime mask, boolean year, boolean month,
				boolean day, boolean hour, boolean minute, boolean second) {
			this.masker = LocalDateTimeMasker.newMasker(mask, year, month, day,
					hour, minute, second);
		}

		@Override
		public Timestamp mask(Timestamp value) {
			if (value == null) {
				return value;
			}
			LocalDateTime masked = masker.mask(new LocalDateTime(value
					.getTime()));
			return new Timestamp(masked.toDate().getTime());
		}
	}

}
