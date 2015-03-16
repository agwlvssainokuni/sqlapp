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

import java.io.IOException;
import java.util.List;

import org.springframework.data.jdbc.query.QueryDslJdbcOperations;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import cherry.foundation.etl.Consumer;
import cherry.foundation.etl.ExtractorResultSetExtractor;
import cherry.foundation.etl.Limiter;
import cherry.foundation.etl.LimiterException;
import cherry.foundation.etl.NoneLimiter;
import cherry.goods.paginate.PageSet;
import cherry.goods.paginate.PagedList;
import cherry.goods.paginate.Paginator;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.Expression;

public class QueryDslSupportImpl implements QueryDslSupport {

	private QueryDslJdbcOperations queryDslJdbcOperations;

	private Paginator paginator;

	public void setQueryDslJdbcOperations(QueryDslJdbcOperations queryDslJdbcOperations) {
		this.queryDslJdbcOperations = queryDslJdbcOperations;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

	@Override
	public <T> PagedList<T> search(QueryConfigurer commonClause, QueryConfigurer orderByClause, long pageNo,
			long pageSz, final RowMapper<T> rowMapper, final Expression<?>... expressions) {
		Predicate<Long> cancelPredicate = Predicates.alwaysFalse();
		return search(commonClause, orderByClause, pageNo, pageSz, cancelPredicate, rowMapper, expressions);
	}

	@Override
	public <T> PagedList<T> search(QueryConfigurer commonClause, QueryConfigurer orderByClause, long pageNo,
			long pageSz, Predicate<Long> cancelPredicate, final RowMapper<T> rowMapper,
			final Expression<?>... expressions) {
		Function<SQLQuery, List<T>> searchFunction = new Function<SQLQuery, List<T>>() {
			@Override
			public List<T> apply(SQLQuery query) {
				return queryDslJdbcOperations.query(query, rowMapper, expressions);
			}
		};
		return searchMain(commonClause, orderByClause, pageNo, pageSz, cancelPredicate, searchFunction);
	}

	@Override
	public <T> PagedList<T> search(QueryConfigurer commonClause, QueryConfigurer orderByClause, long pageNo,
			long pageSz, final Expression<T> expression) {
		Predicate<Long> cancelPredicate = Predicates.alwaysFalse();
		return search(commonClause, orderByClause, pageNo, pageSz, cancelPredicate, expression);
	}

	@Override
	public <T> PagedList<T> search(QueryConfigurer commonClause, QueryConfigurer orderByClause, long pageNo,
			long pageSz, Predicate<Long> cancelPredicate, final Expression<T> expression) {
		Function<SQLQuery, List<T>> searchFunction = new Function<SQLQuery, List<T>>() {
			@Override
			public List<T> apply(SQLQuery query) {
				return queryDslJdbcOperations.query(query, expression);
			}
		};
		return searchMain(commonClause, orderByClause, pageNo, pageSz, cancelPredicate, searchFunction);
	}

	@Override
	public long download(QueryConfigurer commonClause, QueryConfigurer orderByClause, Consumer consumer,
			Expression<?>... expressions) throws IOException {
		return downloadMain(commonClause, orderByClause, consumer, new NoneLimiter(), expressions);
	}

	@Override
	public long download(QueryConfigurer commonClause, QueryConfigurer orderByClause, Consumer consumer,
			Limiter limiter, Expression<?>... expressions) throws LimiterException, IOException {
		return downloadMain(commonClause, orderByClause, consumer, limiter, expressions);
	}

	private <T> PagedList<T> searchMain(QueryConfigurer commonClause, QueryConfigurer orderByClause, long pageNo,
			long pageSz, Predicate<Long> cancelPredicate, Function<SQLQuery, List<T>> searchFunction) {

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query = commonClause.configure(query);
		long count = queryDslJdbcOperations.count(query);

		PageSet pageSet = paginator.paginate(pageNo, count, pageSz);
		if (cancelPredicate.apply(count)) {
			PagedList<T> result = new PagedList<>();
			result.setPageSet(pageSet);
			return result;
		}

		query.limit(pageSz).offset(pageSet.getCurrent().getFrom());
		query = orderByClause.configure(query);
		List<T> list = searchFunction.apply(query);

		PagedList<T> result = new PagedList<>();
		result.setPageSet(pageSet);
		result.setList(list);
		return result;
	}

	private long downloadMain(QueryConfigurer commonClause, QueryConfigurer orderByClause, Consumer consumer,
			Limiter limiter, Expression<?>... expressions) throws LimiterException, IOException {

		ResultSetExtractor<Long> extractor = new ExtractorResultSetExtractor(consumer, limiter);

		limiter.start();
		try {

			SQLQuery query = queryDslJdbcOperations.newSqlQuery();
			query = commonClause.configure(query);
			query = orderByClause.configure(query);

			return queryDslJdbcOperations.queryForObject(query, extractor, expressions);
		} catch (IllegalStateException ex) {
			throw (IOException) ex.getCause();
		} finally {
			limiter.stop();
		}
	}

}
