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

import lombok.Setter;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;

import cherry.foundation.sql.SqlLoader;
import cherry.foundation.type.jdbc.RowMapperCreator;
import cherry.foundation.type.jdbc.SqlParameterSourceCreator;
import cherry.sqlapp.db.dto.SqltoolLoad;

@Setter
@Component
public class SqltoolLoadDaoImpl implements SqltoolLoadDao, InitializingBean {

	@Autowired
	private NamedParameterJdbcOperations namedParameterJdbcOperations;

	@Autowired
	private RowMapperCreator rowMapperCreator;

	@Autowired
	private SqlParameterSourceCreator sqlParameterSourceCreator;

	@Autowired
	private SqlLoader sqlLoader;

	private RowMapper<SqltoolLoad> rowMapper;

	private String findById;

	private String create;

	private String update;

	@Override
	public void afterPropertiesSet() throws Exception {
		BeanWrapper bw = new BeanWrapperImpl(this);
		bw.setPropertyValues(sqlLoader.load(getClass()));
		rowMapper = rowMapperCreator.create(SqltoolLoad.class);
	}

	@Cacheable(value = "SqltoolLoad", key = "#id")
	@Override
	public SqltoolLoad findById(int id) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		return namedParameterJdbcOperations.queryForObject(findById, paramMap,
				rowMapper);
	}

	@Override
	public int create(SqltoolLoad record) {
		return namedParameterJdbcOperations.update(create,
				sqlParameterSourceCreator.create(record));
	}

	@CacheEvict(value = "SqltoolLoad", key = "#record.id")
	@Override
	public int update(SqltoolLoad record) {
		return namedParameterJdbcOperations.update(update,
				sqlParameterSourceCreator.create(record));
	}

}
