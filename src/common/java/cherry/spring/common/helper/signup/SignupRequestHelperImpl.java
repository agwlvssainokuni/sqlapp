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

import static com.google.common.base.Preconditions.checkState;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDateTime;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.fwcore.sql.SqlLoader;

public class SignupRequestHelperImpl implements SignupRequestHelper,
		InitializingBean {

	@Autowired
	private NamedParameterJdbcOperations namedParameterJdbcOperations;

	@Autowired
	private SqlLoader sqlLoader;

	private String createSignupRequest;

	private String validateMailAddr;

	private String validateToken;

	public void setCreateSignupRequest(String createSignupRequest) {
		this.createSignupRequest = createSignupRequest;
	}

	public void setValidateMailAddr(String validateMailAddr) {
		this.validateMailAddr = validateMailAddr;
	}

	public void setValidateToken(String validateToken) {
		this.validateToken = validateToken;
	}

	@Override
	public void afterPropertiesSet() throws IOException {
		BeanWrapper bw = new BeanWrapperImpl(this);
		bw.setPropertyValues(sqlLoader.load(getClass()));
	}

	@Transactional
	@Override
	public int createSignupRequest(String mailAddr, String token,
			LocalDateTime appliedAt) {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("mailAddr", mailAddr);
		paramMap.put("token", token);
		paramMap.put("appliedAt", appliedAt.toDate());

		KeyHolder keyHolder = new GeneratedKeyHolder();
		int count = namedParameterJdbcOperations.update(createSignupRequest,
				new MapSqlParameterSource(paramMap), keyHolder);
		checkState(
				count == 1,
				"failed to create signup_request: mailAddr={0}, token={1}, appliedAt={2}, count={3}",
				mailAddr, token, appliedAt, count);
		return keyHolder.getKey().intValue();
	}

	@Transactional(readOnly = true)
	@Override
	public boolean validateMailAddr(String mailAddr,
			LocalDateTime intervalFrom, LocalDateTime rangeFrom, int numOfReq) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("mailAddr", mailAddr);
		paramMap.put("intervalFrom", intervalFrom.toDate());
		paramMap.put("rangeFrom", rangeFrom.toDate());
		paramMap.put("numOfReq", numOfReq);
		return namedParameterJdbcOperations.queryForObject(validateMailAddr,
				paramMap, Boolean.class);
	}

	@Transactional(readOnly = true)
	@Override
	public boolean validateToken(String mailAddr, String token,
			LocalDateTime validFrom) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("mailAddr", mailAddr);
		paramMap.put("token", token);
		paramMap.put("validFrom", validFrom.toDate());
		return namedParameterJdbcOperations.queryForObject(validateToken,
				paramMap, Boolean.class);
	}

}
