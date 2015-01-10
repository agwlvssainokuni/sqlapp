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

package cherry.foundation.querydsl;

import com.mysema.query.sql.SQLQuery;

/**
 * Querydslサポート機能。<br />
 * {@link SQLQuery}を構成する。
 */
public interface QueryConfigurer {

	/**
	 * {@link SQLQuery}を構成する。
	 * 
	 * @param query 構成対象の{@link SQLQuery}。
	 * @return 構成した{@link SQLQuery}。
	 */
	SQLQuery configure(SQLQuery query);

}
