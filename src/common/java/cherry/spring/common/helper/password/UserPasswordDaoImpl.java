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

package cherry.spring.common.helper.password;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import cherry.spring.common.helper.sql.SqlLoader;

public class UserPasswordDaoImpl implements UserPasswordDao, InitializingBean {

	@Autowired
	private NamedParameterJdbcOperations namedParameterJdbcOperations;

	@Autowired
	private SqlLoader sqlLoader;

	private String sqlUpdatePassword;

	private String sqlChangePassword;

	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String, String> sqlmap = sqlLoader.load(getClass());
		sqlUpdatePassword = sqlmap.get("updatePassword");
		sqlChangePassword = sqlmap.get("changePassword");
	}

	@Override
	public int updatePassword(int id, String password) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("password", password);
		return namedParameterJdbcOperations.update(sqlUpdatePassword, paramMap);
	}

	@Override
	public int changePassword(String loginId, String password) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("loginId", loginId);
		paramMap.put("password", password);
		return namedParameterJdbcOperations.update(sqlChangePassword, paramMap);
	}

}
