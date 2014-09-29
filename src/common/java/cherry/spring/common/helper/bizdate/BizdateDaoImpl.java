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

package cherry.spring.common.helper.bizdate;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import cherry.spring.common.helper.sql.SqlLoader;
import cherry.spring.common.type.jdbc.RowMapperCreator;

public class BizdateDaoImpl implements BizdateDao, InitializingBean {

	@Autowired
	private NamedParameterJdbcOperations namedParameterJdbcOperations;

	@Autowired
	private RowMapperCreator rowMapperCreator;

	@Autowired
	private SqlLoader sqlLoader;

	private String sqlSelectFirst;

	private RowMapper<BizdateDto> rowMapper;

	@Override
	public void afterPropertiesSet() throws IOException {
		Map<String, String> sqlmap = sqlLoader.load(getClass());
		sqlSelectFirst = sqlmap.get("selectFirst");
		rowMapper = rowMapperCreator.create(BizdateDto.class);
	}

	@Override
	public List<BizdateDto> selectFirst() {
		return namedParameterJdbcOperations.query(sqlSelectFirst, rowMapper);
	}

}
