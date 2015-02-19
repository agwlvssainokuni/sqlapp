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

/**
 * 区分値管理機能。<br />
 */
public interface CodeStore {

	/**
	 * 区分値の定義情報 (値と表示名) を取得する。
	 * 
	 * @param codeName 区分値を識別する名前。
	 * @param value 区分値。
	 * @return 定義情報 (値と表示名)。
	 */
	CodeEntry findByValue(String codeName, String value);

	/**
	 * 区分値の定義情報 (値と表示名) のリストを取得する。
	 * 
	 * @param codeName 区分値を識別する名前。
	 * @return 定義情報 (値と表示名) のリスト。
	 */
	List<CodeEntry> getCodeList(String codeName);

}
