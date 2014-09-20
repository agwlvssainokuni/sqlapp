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

package cherry.spring.common.helper.mail;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import cherry.spring.common.helper.sql.SqlLoader;

public class MailMessageDaoImpl implements MailMessageDao, InitializingBean {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcOperations;

	@Autowired
	private SqlLoader sqlLoader;

	private String sqlFindTemplate;

	private String sqlFindAddresses;

	@Override
	public void afterPropertiesSet() throws IOException {
		Map<String, String> sqlmap = sqlLoader.load(getClass());
		sqlFindTemplate = sqlmap.get("findTemplate");
		sqlFindAddresses = sqlmap.get("findAddresses");
	}

	@Override
	public MailTemplateDto findTemplate(String name, Locale locale) {

		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("name", name);
		paramMap.put("locale", locale.toString());

		return namedParameterJdbcOperations.queryForObject(sqlFindTemplate,
				paramMap, new BeanPropertyRowMapper<MailTemplateDto>(
						MailTemplateDto.class));
	}

	@Override
	public List<MailTemplateAddressDto> findAddresses(String name) {

		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("name", name);

		return namedParameterJdbcOperations.query(sqlFindAddresses, paramMap,
				new BeanPropertyRowMapper<MailTemplateAddressDto>(
						MailTemplateAddressDto.class));

	}

}
