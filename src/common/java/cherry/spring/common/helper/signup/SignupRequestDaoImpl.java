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

package cherry.spring.common.helper.signup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import cherry.spring.common.helper.sql.SqlLoader;

public class SignupRequestDaoImpl implements SignupRequestDao, InitializingBean {

	@Autowired
	private NamedParameterJdbcOperations namedParameterJdbcOperations;

	@Autowired
	private SqlLoader sqlLoader;

	private String sqlCreateSignupRequest;

	private String sqlValidateMailAddr;

	private String sqlValidateToken;

	@Override
	public void afterPropertiesSet() throws IOException {
		Map<String, String> sqlmap = sqlLoader.load(getClass());
		this.sqlCreateSignupRequest = sqlmap.get("createSignupRequest");
		this.sqlValidateMailAddr = sqlmap.get("validateMailAddr");
		this.sqlValidateToken = sqlmap.get("validateToken");
	}

	@Override
	public Integer createSignupRequest(String mailAddr, String token,
			LocalDateTime appliedAt) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("mailAddr", mailAddr);
		paramMap.put("token", token);
		paramMap.put("appliedAt", appliedAt.toDate());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int count = namedParameterJdbcOperations.update(sqlCreateSignupRequest,
				new MapSqlParameterSource(paramMap), keyHolder);
		if (count != 1) {
			return null;
		}
		return keyHolder.getKey().intValue();
	}

	@Override
	public boolean validateMailAddr(String mailAddr,
			LocalDateTime intervalFrom, LocalDateTime rangeFrom, int numOfReq) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("mailAddr", mailAddr);
		paramMap.put("intervalFrom", intervalFrom.toDate());
		paramMap.put("rangeFrom", rangeFrom.toDate());
		paramMap.put("numOfReq", numOfReq);
		return namedParameterJdbcOperations.queryForObject(sqlValidateMailAddr,
				paramMap, Boolean.class);
	}

	@Override
	public boolean validateToken(String mailAddr, String token,
			LocalDateTime validFrom) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("mailAddr", mailAddr);
		paramMap.put("token", token);
		paramMap.put("validFrom", validFrom.toDate());
		return namedParameterJdbcOperations.queryForObject(sqlValidateToken,
				paramMap, Boolean.class);
	}

}
