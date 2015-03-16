/*
 * Copyright 2015 agwlvssainokuni
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

package cherry.foundation.type.querydsl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import org.joda.time.LocalDateTime;

import com.mysema.query.sql.types.AbstractDateTimeType;

public class LocalDateTimeType extends AbstractDateTimeType<LocalDateTime> {

	public LocalDateTimeType() {
		super(Types.TIMESTAMP);
	}

	public LocalDateTimeType(int type) {
		super(type);
	}

	@Override
	public String getLiteral(LocalDateTime value) {
		return dateTimeFormatter.print(value);
	}

	@Override
	public Class<LocalDateTime> getReturnedClass() {
		return LocalDateTime.class;
	}

	@Override
	public LocalDateTime getValue(ResultSet rs, int startIndex) throws SQLException {
		Timestamp timestamp = rs.getTimestamp(startIndex);
		if (timestamp == null) {
			return null;
		}
		return new LocalDateTime(timestamp.getTime());
	}

	@Override
	public void setValue(PreparedStatement st, int startIndex, LocalDateTime value) throws SQLException {
		st.setTimestamp(startIndex, new Timestamp(value.toDate().getTime()));
	}

}
