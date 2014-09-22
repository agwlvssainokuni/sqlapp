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

package cherry.spring.common.lib.util;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

public class LocalDateUtil {

	public static LocalDate rangeFrom(LocalDate from) {
		return from;
	}

	public static LocalDate rangeFrom(LocalDateTime from) {
		if (from == null) {
			return null;
		}
		return from.toLocalDate();
	}

	public static LocalDate rangeTo(LocalDate to) {
		if (to == null) {
			return null;
		}
		return to.plusDays(1);
	}

	public static LocalDate rangeTo(LocalDateTime to) {
		if (to == null) {
			return null;
		}
		return to.toLocalDate().plusDays(1);
	}

}
