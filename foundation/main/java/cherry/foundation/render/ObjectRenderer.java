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

package cherry.foundation.render;

/**
 * オブジェクト描画 (文字列化) 機能。<br />
 */
public interface ObjectRenderer {

	/**
	 * オブジェクトを描画 (文字列化) する。<br />
	 * 対応するオブジェクトの種類と文字列化の方式は以下の通り。
	 * <ul>
	 * <li>null: 空文字列 ("")</li>
	 * <li>数値: カンマ編集して表記する。</li>
	 * <li>日付: 「yyyy/MM/dd」形式で表記する。</li>
	 * <li>時刻: 「HH:mm:ss」形式で表記する。</li>
	 * <li>日時: 「yyyy/MM/dd HH:mm:ss」形式で表記する。</li>
	 * <li>文字列: そのまま表記する。</li>
	 * <li>それ以外: {@link Object#toString()}の返却値を表記する。</li>
	 * </ul>
	 * 
	 * @param value 描画するオブジェクト。
	 * @param mode 描画方式を指定する。
	 * @return オブジェクトの文字列表記。
	 */
	String render(Object value, Integer mode);

}
