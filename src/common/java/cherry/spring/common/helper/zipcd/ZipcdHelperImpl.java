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

package cherry.spring.common.helper.zipcd;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.sql.SqlLoader;
import cherry.foundation.type.jdbc.RowMapperCreator;

@Service
public class ZipcdHelperImpl implements ZipcdHelper, InitializingBean {

	@Autowired
	private NamedParameterJdbcOperations namedParameterJdbcOperations;

	@Autowired
	private RowMapperCreator rowMapperCreator;

	@Autowired
	private SqlLoader sqlLoader;

	private RowMapper<ZipcdAddress> rowMapper;

	private String queryByZipcd;

	public void setQueryByZipcd(String queryByZipcd) {
		this.queryByZipcd = queryByZipcd;
	}

	@Override
	public void afterPropertiesSet() throws IOException {
		BeanWrapper bw = new BeanWrapperImpl(this);
		bw.setPropertyValues(sqlLoader.load(getClass()));
		rowMapper = rowMapperCreator.create(ZipcdAddress.class);
	}

	@Transactional(readOnly = true)
	@Cacheable("zipcd")
	@Override
	public List<ZipcdAddress> search(String zipcd) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("zipcd", zipcd);
		return namedParameterJdbcOperations.query(queryByZipcd, paramMap,
				rowMapper);
	}

}
