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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class AsyncProcDaoImpl implements AsyncProcDao, InitializingBean {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcOperations;

	@Value("classpath:cherry/spring/common/helper/async/AsyncProcDaoImpl_createAsyncProc.sql")
	private Resource resourceCreateAsyncProc;

	@Value("classpath:cherry/spring/common/helper/async/AsyncProcDaoImpl_invokeAsyncProc.sql")
	private Resource resourceInvokeAsyncProc;

	@Value("classpath:cherry/spring/common/helper/async/AsyncProcDaoImpl_startAsyncProc.sql")
	private Resource resourceStartAsyncProc;

	@Value("classpath:cherry/spring/common/helper/async/AsyncProcDaoImpl_successAsyncProc.sql")
	private Resource resourceSuccessAsyncProc;

	@Value("classpath:cherry/spring/common/helper/async/AsyncProcDaoImpl_errorAsyncProc.sql")
	private Resource resourceErrorAsyncProc;

	@Value("UTF-8")
	private Charset charset;

	private String sqlCreateAsyncProc;

	private String sqlInvokeAsyncProc;

	private String sqlStartAsyncProc;

	private String sqlSuccessAsyncProc;

	private String sqlErrorAsyncProc;

	@Override
	public void afterPropertiesSet() throws IOException {
		sqlCreateAsyncProc = resToStr(resourceCreateAsyncProc, charset);
		sqlInvokeAsyncProc = resToStr(resourceInvokeAsyncProc, charset);
		sqlStartAsyncProc = resToStr(resourceStartAsyncProc, charset);
		sqlSuccessAsyncProc = resToStr(resourceSuccessAsyncProc, charset);
		sqlErrorAsyncProc = resToStr(resourceErrorAsyncProc, charset);
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

	private String resToStr(Resource res, Charset cs) throws IOException {
		try (StringWriter writer = new StringWriter();
				InputStream in = res.getInputStream();
				Reader reader = new InputStreamReader(in, cs)) {
			int len;
			char[] buff = new char[4096];
			while ((len = reader.read(buff, 0, buff.length)) >= 0) {
				writer.write(buff, 0, len);
			}
			return writer.toString();
		}
	}

}
