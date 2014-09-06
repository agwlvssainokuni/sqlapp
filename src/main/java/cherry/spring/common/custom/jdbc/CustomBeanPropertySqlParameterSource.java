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

package cherry.spring.common.custom.jdbc;

import java.sql.Timestamp;

import org.joda.time.LocalDateTime;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import cherry.spring.common.custom.SecureType;

public class CustomBeanPropertySqlParameterSource extends
		BeanPropertySqlParameterSource {

	public CustomBeanPropertySqlParameterSource(Object object) {
		super(object);
	}

	@Override
	public Object getValue(String paramName) throws IllegalArgumentException {
		Object object = super.getValue(paramName);
		if (object instanceof LocalDateTime) {
			LocalDateTime dtm = (LocalDateTime) object;
			return new Timestamp(dtm.toDate().getTime());
		}
		if (object instanceof SecureType<?>) {
			SecureType<?> sectype = (SecureType<?>) object;
			return sectype.crypto();
		}
		return object;
	}

}
