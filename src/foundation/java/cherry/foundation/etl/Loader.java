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

import javax.sql.DataSource;

/**
 * データ取込み機能。
 */
public interface Loader {

	/**
	 * データを取込む。
	 * 
	 * @param dataSource データソース。
	 * @param sql SQL。
	 * @param provider データの取得元。
	 * @param limit データ取込み制限。
	 * @return 取込みの結果。
	 * @throws LimiterException データ取込み制限超過。
	 * @throws IOException データ取得でエラー。
	 */
	LoadResult load(DataSource dataSource, String sql, Provider provider, Limiter limiter) throws LimiterException,
			IOException;

}
