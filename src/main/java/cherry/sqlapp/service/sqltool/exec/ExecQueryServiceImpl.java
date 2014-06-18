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

package cherry.sqlapp.service.sqltool.exec;

import java.io.IOException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.common.lib.etl.Extractor;
import cherry.spring.common.lib.etl.NoneLimiter;
import cherry.spring.common.lib.paginate.PageSet;
import cherry.spring.common.lib.paginate.Paginator;
import cherry.sqlapp.service.sqltool.DataSourceDef;

public class ExecQueryServiceImpl implements ExecQueryService {

	@Autowired
	private DataSourceDef dataSourceDef;

	@Autowired
	private Extractor extractor;

	@Autowired
	private Paginator paginator;

	@Transactional
	@Override
	public Result query(String databaseName, String sql, Map<String, ?> paramMap) {

		DataSource dataSource = dataSourceDef.getDataSource(databaseName);

		try {

			ResultSet resultSet = new ResultSet();
			int numOfItems = extractor.extract(dataSource, sql, paramMap,
					resultSet, new NoneLimiter());
			PageSet pageSet = paginator.paginate(0, numOfItems,
					(numOfItems <= 0 ? 1 : numOfItems));

			Result result = new Result();
			result.setPageSet(pageSet);
			result.setResultSet(resultSet);
			return result;
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

	@Transactional
	@Override
	public Result query(String databaseName, QueryBuilder queryBuilder,
			Map<String, ?> paramMap, int pageNo, int pageSz) {

		DataSource dataSource = dataSourceDef.getDataSource(databaseName);

		int count = count(dataSource, queryBuilder.buildCount(), paramMap);
		PageSet pageSet = paginator.paginate(pageNo, count, pageSz);

		ResultSet resultSet = new ResultSet();
		try {
			int numOfItems = extractor.extract(dataSource,
					queryBuilder.build(pageSz, pageSet.getCurrent().getFrom()),
					paramMap, resultSet, new NoneLimiter());
			if (numOfItems != pageSet.getCurrent().getCount()) {
				throw new IllegalStateException();
			}
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}

		Result result = new Result();
		result.setPageSet(pageSet);
		result.setResultSet(resultSet);
		return result;
	}

	private int count(DataSource dataSource, String sql, Map<String, ?> paramMap) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				dataSource);
		return template.queryForObject(sql, paramMap, Integer.class);
	}

}
