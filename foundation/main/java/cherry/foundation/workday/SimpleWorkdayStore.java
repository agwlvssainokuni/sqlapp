/*
 * Copyright 2015 agwlvssainokuni
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

package cherry.foundation.workday;

import org.joda.time.Days;
import org.joda.time.LocalDate;

/**
 * 営業日管理機能。<br />
 */
public class SimpleWorkdayStore implements WorkdayStore {

	@Override
	public int getNumberOfWorkday(String name, LocalDate from, LocalDate to) {
		return Days.daysBetween(from, to).getDays() + 1;
	}

	@Override
	public LocalDate getNextWorkday(String name, LocalDate from, int numberOfWorkday) {
		return from.plusDays(numberOfWorkday - 1);
	}

}
