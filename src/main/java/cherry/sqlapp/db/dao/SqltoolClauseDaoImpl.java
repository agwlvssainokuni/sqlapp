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

package cherry.sqlapp.db.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;

import cherry.spring.common.helper.sql.SqlLoader;
import cherry.sqlapp.db.dto.SqltoolClause;

@Component
public class SqltoolClauseDaoImpl implements SqltoolClauseDao, InitializingBean {

	@Autowired
	private NamedParameterJdbcOperations namedParameterJdbcOperations;

	@Autowired
	private ConversionService conversionService;

	@Autowired
	private SqlLoader sqlLoader;

	@Value("classpath:cherry/sqlapp/db/dao/SqltoolClauseDaoImpl.sql")
	private Resource sqlResource;

	private String sqlFindById;

	private String sqlCreate;

	private String sqlUpdate;

	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String, String> sqlmap = sqlLoader.load(sqlResource);
		sqlFindById = sqlmap.get("findById");
		sqlCreate = sqlmap.get("create");
		sqlUpdate = sqlmap.get("update");
	}

	@Override
	public SqltoolClause findById(Integer id) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		return namedParameterJdbcOperations.queryForObject(sqlFindById,
				paramMap, new DaoRowMapper<SqltoolClause>(SqltoolClause.class,
						conversionService));
	}

	@Override
	public int create(SqltoolClause record) {
		return namedParameterJdbcOperations.update(sqlCreate,
				new BeanPropertySqlParameterSource(record));
	}

	@Override
	public int update(SqltoolClause record) {
		return namedParameterJdbcOperations.update(sqlUpdate,
				new BeanPropertySqlParameterSource(record));
	}

}
