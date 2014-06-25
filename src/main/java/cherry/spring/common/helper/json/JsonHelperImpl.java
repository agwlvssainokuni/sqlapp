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

package cherry.spring.common.helper.json;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelperImpl implements JsonHelper {

	@Value("${common.helper.json.throwableMaxLength}")
	private Integer throwableMaxlength;

	@Autowired
	private ObjectMapper mapper;

	@Override
	public String fromMap(Map<?, ?> map) {
		try {
			return mapper.writeValueAsString(map);
		} catch (JsonProcessingException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	@Override
	public String fromThrowable(Throwable th) {
		return fromMap(createThrowableData(th))
				.substring(0, throwableMaxlength);
	}

	private Map<String, Object> createThrowableData(Throwable th) {

		Map<String, Object> data = new LinkedHashMap<>();
		data.put("message", th.getMessage());

		List<String> stackTrace = new ArrayList<>();
		for (StackTraceElement ste : th.getStackTrace()) {
			stackTrace.add(ste.toString());
		}
		data.put("stackTrace", stackTrace);

		if (th.getCause() != null) {
			data.put("cause", createThrowableData(th.getCause()));
		}

		return data;
	}

}
