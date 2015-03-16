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

import org.joda.time.LocalDate;

import cherry.foundation.type.Code;

/**
 * 営業日管理機能。<br />
 * 以下の2つの機能を提供する。
 * <ul>
 * <li>営業日数算出機能: 所定のカレンダーに基づき、起点日から終点日までの営業日数を算出する。営業日数には起点日を含む。即ち、起点と終点に同じ日付を指定すると、(当日が営業日ならば)「1」が得られる。</li>
 * <li>N営業日後算出機能: 所定のカレンダーに基づき、起点日から指定の営業日数後の営業日を算出する。営業日数には起点日を含む。即ち、営業に数に「1」を指定すると、(当日が営業日ならば)起点日と同じ日付が得られる。</li>
 * </ul>
 */
public interface WorkdayManager {

	/**
	 * 営業日数算出機能。<br />
	 * 標準のカレンダーに基づき、今日(業務日付)から指定した終点日までの営業日数を算出する。
	 * 
	 * @param to 終点日。
	 * @return 営業日数。
	 */
	int getNumberOfWorkday(LocalDate to);

	/**
	 * 営業日数算出機能。<br />
	 * カレンダーを指定して、今日(業務日付)から指定した終点日までの営業日数を算出する。
	 * 
	 * @param code カレンダーの識別名を保持する区分値。
	 * @param to 終点日。
	 * @return 営業日数。
	 */
	<T extends Code<String>> int getNumberOfWorkday(T code, LocalDate to);

	/**
	 * 営業日数算出機能。<br />
	 * カレンダーを指定して、今日(業務日付)から指定した終点日までの営業日数を算出する。
	 * 
	 * @param name カレンダーの識別名。
	 * @param to 終点日。
	 * @return 営業日数。
	 */
	int getNumberOfWorkday(String name, LocalDate to);

	/**
	 * 営業日数算出機能。<br />
	 * 標準のカレンダーに基づき、指定した起点日から終点日までの営業日数を算出する。
	 * 
	 * @param from 起点日。
	 * @param to 終点日。
	 * @return 営業日数。
	 */
	int getNumberOfWorkday(LocalDate from, LocalDate to);

	/**
	 * 営業日数算出機能。<br />
	 * カレンダーを指定して、指定した起点日から終点日までの営業日数を算出する。
	 * 
	 * @param code カレンダーの識別名を保持する区分値。
	 * @param from 起点日。
	 * @param to 終点日。
	 * @return 営業日数。
	 */
	<T extends Code<String>> int getNumberOfWorkday(T code, LocalDate from, LocalDate to);

	/**
	 * 営業日数算出機能。<br />
	 * カレンダーを指定して、指定した起点日から終点日までの営業日数を算出する。
	 * 
	 * @param name カレンダーの識別名。
	 * @param from 起点日。
	 * @param to 終点日。
	 * @return 営業日数。
	 */
	int getNumberOfWorkday(String name, LocalDate from, LocalDate to);

	/**
	 * N営業日後算出機能。<br />
	 * 標準のカレンダーに基づき、今日(業務日付)から指定の営業日後の営業日を算出する。
	 * 
	 * @param numberOfWorkday 営業日数。
	 * @return <code>numberOfWorkday</code>営業日後の営業日。
	 */
	LocalDate getNextWorkday(int numberOfWorkday);

	/**
	 * N営業日後算出機能。<br />
	 * カレンダーを指定して、今日(業務日付)から指定の営業日後の営業日を算出する。
	 * 
	 * @param code カレンダーの識別名を保持する区分値。
	 * @param numberOfWorkday 営業日数。
	 * @return <code>numberOfWorkday</code>営業日後の営業日。
	 */
	<T extends Code<String>> LocalDate getNextWorkday(T code, int numberOfWorkday);

	/**
	 * N営業日後算出機能。<br />
	 * カレンダーを指定して、今日(業務日付)から指定の営業日後の営業日を算出する。
	 * 
	 * @param name カレンダーの識別名。
	 * @param numberOfWorkday 営業日数。
	 * @return <code>numberOfWorkday</code>営業日後の営業日。
	 */
	LocalDate getNextWorkday(String name, int numberOfWorkday);

	/**
	 * N営業日後算出機能。<br />
	 * 標準のカレンダーに基づき、指定した起点日から指定の営業日後の営業日を算出する。
	 * 
	 * @param from 起点日。
	 * @param numberOfWorkday 営業日数。
	 * @return <code>numberOfWorkday</code>営業日後の営業日。
	 */
	LocalDate getNextWorkday(LocalDate from, int numberOfWorkday);

	/**
	 * N営業日後算出機能。<br />
	 * カレンダーを指定して、指定した起点日から指定の営業日後の営業日を算出する。
	 * 
	 * @param code カレンダーの識別名を保持する区分値。
	 * @param from 起点日。
	 * @param numberOfWorkday 営業日数。
	 * @return <code>numberOfWorkday</code>営業日後の営業日。
	 */
	<T extends Code<String>> LocalDate getNextWorkday(T code, LocalDate from, int numberOfWorkday);

	/**
	 * N営業日後算出機能。<br />
	 * カレンダーを指定して、指定した起点日から指定の営業日後の営業日を算出する。
	 * 
	 * @param name カレンダーの識別名。
	 * @param from 起点日。
	 * @param numberOfWorkday 営業日数。
	 * @return <code>numberOfWorkday</code>営業日後の営業日。
	 */
	LocalDate getNextWorkday(String name, LocalDate from, int numberOfWorkday);

}
