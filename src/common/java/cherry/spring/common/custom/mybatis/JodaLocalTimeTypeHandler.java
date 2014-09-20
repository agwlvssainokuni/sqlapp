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

package cherry.spring.common.custom.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

@MappedTypes(LocalTime.class)
public class JodaLocalTimeTypeHandler extends BaseTypeHandler<LocalTime> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			LocalTime parameter, JdbcType jdbcType) throws SQLException {
		ps.setTime(i, new Time(parameter.getMillisOfDay()));
	}

	@Override
	public LocalTime getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		Time time = rs.getTime(columnName);
		if (time == null) {
			return null;
		}
		return new LocalTime(time.getTime()
				+ LocalDate.now().toDate().getTime());
	}

	@Override
	public LocalTime getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		Time time = rs.getTime(columnIndex);
		if (time == null) {
			return null;
		}
		return new LocalTime(time.getTime()
				+ LocalDate.now().toDate().getTime());
	}

	@Override
	public LocalTime getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		Time time = cs.getTime(columnIndex);
		if (time == null) {
			return null;
		}
		return new LocalTime(time.getTime()
				+ LocalDate.now().toDate().getTime());
	}

}
