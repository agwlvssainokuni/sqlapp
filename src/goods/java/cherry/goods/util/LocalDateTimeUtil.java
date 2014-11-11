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

package cherry.goods.util;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Period;

/**
 * 日時操作ユーティリティ。<br />
 * 下記の機能を提供する。
 * <ul>
 * <li>日時範囲(FROM)の条件指定値決定 ({@link #rangeFrom(LocalDate)},
 * {@link #rangeFrom(LocalDate, LocalTime)}, {@link #rangeFrom(LocalDateTime)}</li>
 * <li>日時範囲(TO)の条件指定値決定 ({@link #rangeTo(LocalDate)},
 * {@link #rangeTo(LocalDate, LocalTime)}, {@link #rangeTo(LocalDateTime)}</li>
 * </ul>
 */
public class LocalDateTimeUtil {

	private static Period unitOfTime = Period.seconds(1);

	public static Period setUnitOfTime(Period period) {
		unitOfTime = period;
		return unitOfTime;
	}

	/**
	 * 日時範囲(FROM)の条件指定値を決定する。
	 * 
	 * @param from
	 *            入力された日付値。
	 * @return 入力された日付に対する日時範囲(FROM)の条件指定値。
	 */
	public static LocalDateTime rangeFrom(LocalDate from) {
		if (from == null) {
			return null;
		}
		return from.toLocalDateTime(LocalTime.MIDNIGHT);
	}

	/**
	 * 日時範囲(FROM)の条件指定値を決定する。
	 * 
	 * @param fromD
	 *            入力された日付値。
	 * @param fromT
	 *            　入力された時刻値。
	 * @return 入力された日付と時刻に対する日時範囲(FROM)の条件指定値。
	 */
	public static LocalDateTime rangeFrom(LocalDate fromD, LocalTime fromT) {
		if (fromD == null) {
			return null;
		}
		if (fromT == null) {
			return fromD.toLocalDateTime(LocalTime.MIDNIGHT);
		}
		return fromD.toLocalDateTime(fromT);
	}

	/**
	 * 日時範囲(FROM)の条件指定値を決定する。
	 * 
	 * @param from
	 *            入力された日時値。
	 * @return 入力された日時に対する日時範囲(FROM)の条件指定値。
	 */
	public static LocalDateTime rangeFrom(LocalDateTime from) {
		return from;
	}

	/**
	 * 日時範囲(TO)の条件指定値を決定する。
	 * 
	 * @param to
	 *            入力された日付値。
	 * @return 入力された日付に対する日時範囲(TO)の条件指定値。
	 */
	public static LocalDateTime rangeTo(LocalDate to) {
		if (to == null) {
			return null;
		}
		return to.toLocalDateTime(LocalTime.MIDNIGHT).plusDays(1);
	}

	/**
	 * 日時範囲(TO)の条件指定値を決定する。
	 * 
	 * @param toD
	 *            入力された日付値。
	 * @param toT
	 *            入力された時刻値。
	 * @return 入力された日付と時刻に対する日時範囲(TO)の条件指定値。
	 */
	public static LocalDateTime rangeTo(LocalDate toD, LocalTime toT) {
		if (toD == null) {
			return null;
		}
		if (toT == null) {
			return toD.toLocalDateTime(LocalTime.MIDNIGHT).plusDays(1);
		}
		return toD.toLocalDateTime(toT).plus(unitOfTime);
	}

	/**
	 * 日時範囲(TO)の条件指定値を決定する。
	 * 
	 * @param to
	 *            入力された日時値。
	 * @return 入力された日時に対する日時範囲(TO)の条件指定値。
	 */
	public static LocalDateTime rangeTo(LocalDateTime to) {
		if (to == null) {
			return null;
		}
		return to.plus(unitOfTime);
	}

}
