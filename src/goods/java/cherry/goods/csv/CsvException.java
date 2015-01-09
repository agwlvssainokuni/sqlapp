/*
 * Copyright 2011,2015 agwlvssainokuni
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

package cherry.goods.csv;

import java.io.IOException;

/**
 * CSV形式データ解析エラー。<br />
 * CSV形式が不正であることを表す。
 */
public class CsvException extends IOException {

	/** シリアルバージョンID。 */
	private static final long serialVersionUID = 1L;

	/**
	 * 例外オブジェクトを生成する。
	 */
	public CsvException(String message) {
		super(message);
	}

}
