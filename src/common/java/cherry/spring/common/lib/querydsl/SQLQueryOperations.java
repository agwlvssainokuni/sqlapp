/*
 * Copyright 2014 agwlvssainokuni
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

package cherry.spring.common.lib.querydsl;

import java.util.List;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.Expression;

public interface SQLQueryOperations {

	JdbcOperations getJdbcOperations();

	SQLQuery createSQLQuery();

	long count(SQLQuery sqlQuery);

	<T> List<T> query(SQLQuery sqlQuery, RowMapper<T> rowMapper,
			Expression<?>... projection);

	<T> T queryForObject(SQLQuery sqlQuery,
			ResultSetExtractor<T> resultSetExtractor,
			Expression<?>... projection);

}
