/*
 * Copyright 2014,2015 agwlvssainokuni
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

package cherry.foundation.code;

import java.util.List;
import java.util.Map;

import cherry.foundation.type.Code;

/**
 * 区分値管理機能。<br />
 * 区分値に対して操作する機能を提供する。
 */
public interface CodeManager {

	/**
	 * 区分値の妥当性 (定義されているか) を検証する。
	 * 
	 * @param codeEnum 区分値を識別する列挙型。
	 * @param value 区分値。
	 * @return 定義されているならばtrue、さもなくばfalse。
	 */
	<T extends Code<String>> boolean isValidValue(T codeEnum, String value);

	/**
	 * 区分値の妥当性 (定義されているか) を検証する。
	 * 
	 * @param codeName 区分値を識別する名前。
	 * @param value 区分値。
	 * @return 定義されているならばtrue、さもなくばfalse。
	 */
	boolean isValidValue(String codeName, String value);

	/**
	 * 区分値の定義情報 (値と表示名) を取得する。<br />
	 * 表示名は、区分値を付与する。
	 * 
	 * @param codeEnum 区分値を識別する列挙型。
	 * @param value 区分値。
	 * @return 定義情報 (値と表示名)。
	 */
	<T extends Code<String>> CodeEntry findByValue(T codeEnum, String value);

	/**
	 * 区分値の定義情報 (値と表示名) を取得する。<br />
	 * 素の表示名とするか、区分値を付与した表示名とするかを指定できる。
	 * 
	 * @param codeEnum 区分値を識別する列挙型。
	 * @param value 区分値。
	 * @param plainLabel 表示名を、素の表示名とするか (true)、区分値を付与するか (false) を指定する。
	 * @return 定義情報 (値と表示名)。
	 */
	<T extends Code<String>> CodeEntry findByValue(T codeEnum, String value, boolean plainLabel);

	/**
	 * 区分値の定義情報 (値と表示名) を取得する。<br />
	 * 表示名は、区分値を付与する。
	 * 
	 * @param codeName 区分値を識別する名前。
	 * @param value 区分値。
	 * @return 定義情報 (値と表示名)。
	 */
	CodeEntry findByValue(String codeName, String value);

	/**
	 * 区分値の定義情報 (値と表示名) を取得する。<br />
	 * 素の表示名とするか、区分値を付与した表示名とするかを指定できる。
	 * 
	 * @param codeName 区分値を識別する名前。
	 * @param value 区分値。
	 * @param plainLabel 表示名を、素の表示名とするか (true)、区分値を付与するか (false) を指定する。
	 * @return 定義情報 (値と表示名)。
	 */
	CodeEntry findByValue(String codeName, String value, boolean plainLabel);

	/**
	 * 区分値の定義情報 (値と表示名) のリストを取得する。<br />
	 * 表示名は、区分値を付与する。
	 * 
	 * @param codeEnum 区分値を識別する列挙型。
	 * @return 定義情報 (値と表示名) のリスト。
	 */
	<T extends Code<String>> List<CodeEntry> getCodeList(T codeEnum);

	/**
	 * 区分値の定義情報 (値と表示名) のリストを取得する。<br />
	 * 素の表示名とするか、区分値を付与した表示名とするかを指定できる。
	 * 
	 * @param codeEnum 区分値を識別する列挙型。
	 * @param plainLabel 表示名を、素の表示名とするか (true)、区分値を付与するか (false) を指定する。
	 * @return 定義情報 (値と表示名) のリスト。
	 */
	<T extends Code<String>> List<CodeEntry> getCodeList(T codeEnum, boolean plainLabel);

	/**
	 * 区分値の定義情報 (値と表示名) のリストを取得する。<br />
	 * 表示名は、区分値を付与する。
	 * 
	 * @param codeName 区分値を識別する名前。
	 * @return 定義情報 (値と表示名) のリスト。
	 */
	List<CodeEntry> getCodeList(String codeName);

	/**
	 * 区分値の定義情報 (値と表示名) のリストを取得する。<br />
	 * 表示名は、区分値を付与する。
	 * 
	 * @param codeName 区分値を識別する名前。
	 * @param plainLabel 表示名を、素の表示名とするか (true)、区分値を付与するか (false) を指定する。
	 * @return 定義情報 (値と表示名) のリスト。
	 */
	List<CodeEntry> getCodeList(String codeName, boolean plainLabel);

	/**
	 * 区分値のマップ (区分値から表示名に対するマップ) を取得する。<br />
	 * 表示名は、区分値を付与する。
	 * 
	 * @param codeEnum 区分値を識別する列挙型。
	 * @return 区分値から表示名に対するマップ。
	 */
	<T extends Code<String>> Map<String, String> getCodeMap(T codeEnum);

	/**
	 * 区分値のマップ (区分値から表示名に対するマップ) を取得する。<br />
	 * 素の表示名とするか、区分値を付与した表示名とするかを指定できる。
	 * 
	 * @param codeEnum 区分値を識別する列挙型。
	 * @param plainLabel 表示名を、素の表示名とするか (true)、区分値を付与するか (false) を指定する。
	 * @return 区分値から表示名に対するマップ。
	 */
	<T extends Code<String>> Map<String, String> getCodeMap(T codeEnum, boolean plainLabel);

	/**
	 * 区分値のマップ (区分値から表示名に対するマップ) を取得する。<br />
	 * 表示名は、区分値を付与する。
	 * 
	 * @param codeName 区分値を識別する名前。
	 * @return 区分値から表示名に対するマップ。
	 */
	Map<String, String> getCodeMap(String codeName);

	/**
	 * 区分値のマップ (区分値から表示名に対するマップ) を取得する。<br />
	 * 素の表示名とするか、区分値を付与した表示名とするかを指定できる。
	 * 
	 * @param codeName 区分値を識別する名前。
	 * @param plainLabel 表示名を、素の表示名とするか (true)、区分値を付与するか (false) を指定する。
	 * @return 区分値から表示名に対するマップ。
	 */
	Map<String, String> getCodeMap(String codeName, boolean plainLabel);

}
