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

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

public class ExecServiceImpl implements ExecService {

	@Transactional
	@Override
	public int count(DataSource dataSource, String sql, Map<String, ?> paramMap) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				dataSource);
		return template.queryForObject(sql, paramMap, Integer.class);
	}

	@Transactional
	@Override
	public int query(DataSource dataSource, String sql,
			Map<String, ?> paramMap, final Consumer consumer) {

		ResultSetExtractor<Integer> callback = new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException {

				ResultSetMetaData metaData = rs.getMetaData();
				Column[] col = new Column[metaData.getColumnCount()];
				for (int i = 1; i <= col.length; i++) {
					col[i - 1] = new Column();
					col[i - 1].setType(metaData.getColumnType(i));
					col[i - 1].setLabel(metaData.getColumnLabel(i));
				}

				consumer.begin(col);

				int count;
				for (count = 0; rs.next(); count++) {

					Object[] record = new Object[col.length];
					for (int i = 1; i <= record.length; i++) {
						record[i - 1] = rs.getObject(i);
					}

					consumer.consume(record);
				}

				consumer.end();
				return count;
			}
		};

		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				dataSource);
		return template.query(sql, paramMap, callback);
	}

}
