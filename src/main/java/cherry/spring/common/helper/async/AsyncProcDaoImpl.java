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

package cherry.spring.common.helper.async;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import cherry.spring.common.helper.sql.SqlLoader;

public class AsyncProcDaoImpl implements AsyncProcDao, InitializingBean {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcOperations;

	@Autowired
	private SqlLoader sqlLoader;

	private String sqlCreateAsyncProc;

	private String sqlInvokeAsyncProc;

	private String sqlStartAsyncProc;

	private String sqlSuccessAsyncProc;

	private String sqlErrorAsyncProc;

	@Override
	public void afterPropertiesSet() throws IOException {
		Map<String, String> sqlmap = sqlLoader.load(getClass());
		sqlCreateAsyncProc = sqlmap.get("createAsyncProc");
		sqlInvokeAsyncProc = sqlmap.get("invokeAsyncProc");
		sqlStartAsyncProc = sqlmap.get("startAsyncProc");
		sqlSuccessAsyncProc = sqlmap.get("successAsyncProc");
		sqlErrorAsyncProc = sqlmap.get("errorAsyncProc");
	}

	@Override
	public Integer createAsyncProc(String name, String launcherId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("name", name);
		paramMap.put("launcherId", launcherId);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int count = namedParameterJdbcOperations.update(sqlCreateAsyncProc,
				new MapSqlParameterSource(paramMap), keyHolder);
		if (count != 1) {
			return null;
		}
		return keyHolder.getKey().intValue();
	}

	@Override
	public int invokeAsyncProc(int id) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		return namedParameterJdbcOperations
				.update(sqlInvokeAsyncProc, paramMap);
	}

	@Override
	public int startAsyncProc(int id) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		return namedParameterJdbcOperations.update(sqlStartAsyncProc, paramMap);
	}

	@Override
	public int successAsyncProc(int id, String result) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("result", result);
		return namedParameterJdbcOperations.update(sqlSuccessAsyncProc,
				paramMap);
	}

	@Override
	public int errorAsyncProc(int id, String result) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("result", result);
		return namedParameterJdbcOperations.update(sqlErrorAsyncProc, paramMap);
	}

}
