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

package cherry.spring.common.type.jdbc;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import cherry.spring.common.type.Code;
import cherry.spring.common.type.SecureType;

public class CustomBeanPropertySqlParameterSource extends
		BeanPropertySqlParameterSource {

	public CustomBeanPropertySqlParameterSource(Object object) {
		super(object);
	}

	@Override
	public Object getValue(String paramName) throws IllegalArgumentException {
		Object object = super.getValue(paramName);
		if (object instanceof LocalDateTime) {
			LocalDateTime ldt = (LocalDateTime) object;
			return new Timestamp(ldt.toDate().getTime());
		}
		if (object instanceof LocalDate) {
			LocalDate ld = (LocalDate) object;
			return new Date(ld.toDate().getTime());
		}
		if (object instanceof LocalTime) {
			LocalTime lt = (LocalTime) object;
			return new Time((long) lt.getMillisOfDay());
		}
		if (object instanceof SecureType<?>) {
			SecureType<?> st = (SecureType<?>) object;
			return st.crypto();
		}
		if (object instanceof Code<?>) {
			Code<?> ce = (Code<?>) object;
			return ce.code();
		}
		return object;
	}

}
