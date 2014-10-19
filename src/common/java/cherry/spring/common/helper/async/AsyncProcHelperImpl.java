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

import static com.google.common.base.Preconditions.checkState;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDateTime;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.common.helper.sql.SqlLoader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AsyncProcHelperImpl implements AsyncProcHelper, InitializingBean {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcOperations;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SqlLoader sqlLoader;

	private String createAsyncProc;

	private String invokeAsyncProc;

	private String startAsyncProc;

	private String successAsyncProc;

	private String errorAsyncProc;

	public void setCreateAsyncProc(String createAsyncProc) {
		this.createAsyncProc = createAsyncProc;
	}

	public void setInvokeAsyncProc(String invokeAsyncProc) {
		this.invokeAsyncProc = invokeAsyncProc;
	}

	public void setStartAsyncProc(String startAsyncProc) {
		this.startAsyncProc = startAsyncProc;
	}

	public void setSuccessAsyncProc(String successAsyncProc) {
		this.successAsyncProc = successAsyncProc;
	}

	public void setErrorAsyncProc(String errorAsyncProc) {
		this.errorAsyncProc = errorAsyncProc;
	}

	@Override
	public void afterPropertiesSet() throws IOException {
		BeanWrapper bw = new BeanWrapperImpl(this);
		bw.setPropertyValues(sqlLoader.load(getClass()));
	}

	@Transactional(value = "jtaTransactionManager", propagation = REQUIRES_NEW)
	@Override
	public int createAsyncProc(String name, String launcherId, LocalDateTime dtm) {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("name", name);
		paramMap.put("launcherId", launcherId);
		paramMap.put("registeredAt", dtm.toDate());

		KeyHolder keyHolder = new GeneratedKeyHolder();
		int count = namedParameterJdbcOperations.update(createAsyncProc,
				new MapSqlParameterSource(paramMap), keyHolder);
		checkState(
				count == 1,
				"failed to create async_proc: name={0}, launcherId={1}, registeredAt={2}, count={3}",
				name, launcherId, dtm, count);

		return keyHolder.getKey().intValue();
	}

	@Transactional("jtaTransactionManager")
	@Override
	public void invokeAsyncProc(int id, LocalDateTime dtm) {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("invokedAt", dtm.toDate());

		int count = namedParameterJdbcOperations.update(invokeAsyncProc,
				paramMap);
		checkState(
				count == 1,
				"failed to update async_proc: id={0}, invokedAt={1}, count={2}",
				id, dtm, count);
	}

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public void startAsyncProc(int id, LocalDateTime dtm) {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("startedAt", dtm.toDate());

		int count = namedParameterJdbcOperations.update(startAsyncProc,
				paramMap);
		checkState(
				count == 1,
				"failed to update async_proc: id={0}, startedAt={1}, count={2}",
				id, dtm, count);
	}

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public void successAsyncProc(int id, LocalDateTime dtm, Map<?, ?> result) {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("finishedAt", dtm.toDate());
		paramMap.put("result", mapToString(result));

		int count = namedParameterJdbcOperations.update(successAsyncProc,
				paramMap);
		checkState(
				count == 1,
				"failed to update async_proc: id={0}, finishedAt={1}, count={2}",
				id, dtm, count);
	}

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public void errorAsyncProc(int id, LocalDateTime dtm, Map<?, ?> result) {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("finishedAt", dtm.toDate());
		paramMap.put("result", mapToString(result));

		int count = namedParameterJdbcOperations.update(errorAsyncProc,
				paramMap);
		checkState(
				count == 1,
				"failed to update async_proc: id={0}, finishedAt={1}, count={2}",
				id, dtm, count);
	}

	private String mapToString(Map<?, ?> map) {
		try {
			return objectMapper.writeValueAsString(map);
		} catch (JsonProcessingException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

}
