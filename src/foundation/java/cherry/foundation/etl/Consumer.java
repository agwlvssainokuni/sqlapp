/*
 * Copyright 2012,2015 agwlvssainokuni
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

package cherry.foundation.etl;

import java.io.IOException;

/**
 * データ抽出機能におけるデータ格納先。
 */
public interface Consumer {

	/**
	 * データの格納を開始する。
	 * 
	 * @param col カラムの情報。
	 * @throws IOException データ格納エラー。
	 */
	void begin(Column[] col) throws IOException;

	/**
	 * 1レコードのデータを格納する。
	 * 
	 * @param record 1レコードのデータ。
	 * @throws IOException データ格納エラー。
	 */
	void consume(Object[] record) throws IOException;

	/**
	 * データの格納を終了する。
	 * 
	 * @throws IOException データ格納エラー。
	 */
	void end() throws IOException;

}
