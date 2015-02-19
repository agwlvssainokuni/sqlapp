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

package cherry.goods.telno;

import java.util.List;

/**
 * 電話番号正規化機能。<br />
 * 電話番号の表示形式を正規化する。具体的には下記の2機能を提供する。
 * <ul>
 * <li>{@link #flatten(String)}: ハイフンを除去する。</li>
 * <li>{@link #normalize(String)}: 電話番号を市外局番、市内局番、番号に分割する。</li>
 * </ul>
 */
public interface TelNoNormalizer {

	/**
	 * 電話番号のハイフンを除去する。<br />
	 * 
	 * @param telNo 対象とする電話番号。
	 * @return ハイフンを除去した電話番号。
	 */
	String flatten(String telNo);

	/**
	 * 電話番号 (ハイフンなし) を市外局番、市内局番、番号に分割。<br />
	 * 
	 * @param telNo 対象とする電話番号 (ハイフンなし)。
	 * @return 市外局番、市内局番、番号に分割した電話番号。複数の候補がある場合は複数返却される。
	 */
	List<String[]> normalize(String telNo);

}
