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
import java.util.Map;

/**
 * データ取込み機能におけるデータ取得元。
 */
public interface Provider {

	/**
	 * データの取得を開始する。
	 * 
	 * @throws IOException データ取得エラー。
	 */
	void begin() throws IOException;

	/**
	 * 1レコードのデータを取得する。
	 * 
	 * @return 1レコードのデータ。 データが存在しない場合はnull。
	 * @throws IOException データ取得エラー。
	 */
	Map<String, ?> provide() throws IOException;

	/**
	 * エータの取得を終了する。
	 * 
	 * @throws IOException データ取得エラー。
	 */
	void end() throws IOException;

}
