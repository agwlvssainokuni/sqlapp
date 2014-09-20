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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.mysema.query.QueryException;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLTemplates;
import com.mysema.query.types.Expression;

public class SQLQueryTemplate implements SQLQueryOperations {

	private JdbcTemplate jdbcTemplate;

	private SQLTemplates sqlTemplates;

	public SQLQueryTemplate(DataSource dataSource, SQLTemplates sqlTemplates) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.sqlTemplates = sqlTemplates;
	}

	@Override
	public JdbcOperations getJdbcOperations() {
		return jdbcTemplate;
	}

	@Override
	public SQLQuery createSQLQuery() {
		return new SQLQuery(sqlTemplates);
	}

	@Override
	public long count(final SQLQuery sqlQuery) {
		return jdbcTemplate.execute(new ConnectionCallback<Long>() {
			@Override
			public Long doInConnection(Connection con) throws SQLException,
					DataAccessException {
				SQLQuery q = sqlQuery.clone(con);
				try {
					return q.count();
				} catch (QueryException ex) {
					throw translateQueryException(ex, "SQLQuery", q.toString());
				}
			}
		});
	}

	@Override
	public <T> List<T> query(SQLQuery sqlQuery, RowMapper<T> rowMapper,
			Expression<?>... projection) {
		return queryForObject(sqlQuery, new RowMapperResultSetExtractor<T>(
				rowMapper), projection);
	}

	@Override
	public <T> T queryForObject(final SQLQuery sqlQuery,
			final ResultSetExtractor<T> resultSetExtractor,
			final Expression<?>... projection) {
		return jdbcTemplate.execute(new ConnectionCallback<T>() {
			@Override
			public T doInConnection(Connection con) throws SQLException,
					DataAccessException {
				SQLQuery q = sqlQuery.clone(con);
				try (ResultSet resultSet = q.getResults(projection)) {
					return resultSetExtractor.extractData(resultSet);
				} catch (QueryException ex) {
					throw translateQueryException(ex, "SQLQuery", q.toString());
				}
			}
		});
	}

	private RuntimeException translateQueryException(QueryException ex,
			String task, String query) {
		Throwable th = ex.getCause();
		if (th instanceof SQLException) {
			return jdbcTemplate.getExceptionTranslator().translate(task, query,
					(SQLException) th);
		}
		return new UncategorizedQueryException("Error in SQLQuery", ex);
	}

}
