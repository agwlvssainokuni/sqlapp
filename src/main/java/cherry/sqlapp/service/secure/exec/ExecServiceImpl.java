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

package cherry.sqlapp.service.secure.exec;

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

public class ExecServiceImpl implements ExecService {

	@Autowired
	private Extractor extractor;

	@Autowired
	private Paginator paginator;

	@Transactional
	@Override
	public Result exec(DataSource dataSource, SqlBuilder sqlBuilder,
			Map<String, ?> paramMap, int pageNo, int pageSize) {

		int count = count(dataSource, sqlBuilder.buildCount(), paramMap);
		PageSet pageSet = paginator.paginate(pageNo, count, pageSize);

		ExecResult execResult = new ExecResult();
		try {
			int numOfItems = extractor.extract(dataSource,
					sqlBuilder.build(pageSize, pageSet.getCurrent().getFrom()),
					paramMap, execResult, new NoneLimiter());
			if (numOfItems != pageSet.getCurrent().getCount()) {
				throw new IllegalStateException();
			}
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}

		Result result = new Result();
		result.setPageSet(pageSet);
		result.setExecResult(execResult);
		return result;
	}

	private int count(DataSource dataSource, String sql, Map<String, ?> paramMap) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				dataSource);
		return template.queryForObject(sql, paramMap, Integer.class);
	}

}
