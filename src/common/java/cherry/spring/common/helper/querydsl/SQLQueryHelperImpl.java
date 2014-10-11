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

package cherry.spring.common.helper.querydsl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import cherry.spring.common.lib.etl.Consumer;
import cherry.spring.common.lib.etl.ExtractorResultSetExtractor;
import cherry.spring.common.lib.etl.Limiter;
import cherry.spring.common.lib.etl.LimiterException;
import cherry.spring.common.lib.paginate.PageSet;
import cherry.spring.common.lib.paginate.Paginator;

import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.Expression;

public class SQLQueryHelperImpl implements SQLQueryHelper {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	@Autowired
	private Paginator paginator;

	@Override
	public <T> SearchResult<T> search(QueryConfigurer commonClause,
			QueryConfigurer orderByClause, long pageNo, long pageSz,
			RowMapper<T> rowMapper, Expression<?>... projection) {

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query = commonClause.configure(query);
		long count = queryDslJdbcOperations.count(query);

		PageSet pageSet = paginator.paginate(pageNo, count, pageSz);
		query.limit(pageSz).offset(pageSet.getCurrent().getFrom());
		query = orderByClause.configure(query);
		List<T> list = queryDslJdbcOperations.query(query, rowMapper,
				projection);

		SearchResult<T> result = new SearchResult<>();
		result.setTotalCount(count);
		result.setPageSet(pageSet);
		result.setResultList(list);
		return result;
	}

	@Override
	public long download(QueryConfigurer commonClause,
			QueryConfigurer orderByClause, Consumer consumer, Limiter limiter,
			Expression<?>... projection) throws LimiterException, IOException {

		ResultSetExtractor<Long> extractor = new ExtractorResultSetExtractor(
				consumer, limiter);

		limiter.start();
		try {

			SQLQuery query = queryDslJdbcOperations.newSqlQuery();
			query = commonClause.configure(query);
			query = orderByClause.configure(query);

			return queryDslJdbcOperations.queryForObject(query, extractor,
					projection);
		} catch (IllegalStateException ex) {
			throw (IOException) ex.getCause();
		} finally {
			limiter.stop();
		}
	}

}
