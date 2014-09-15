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
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import cherry.spring.common.custom.Code;
import cherry.spring.common.log.Log;
import cherry.spring.common.log.LogFactory;

public abstract class EnumCodeStringTypeHandler<E extends Code<String>> extends
		BaseTypeHandler<E> {

	private final Log log = LogFactory.getLog(getClass());

	private Class<E> type;

	private Map<String, E> enums;

	private E defaultValue;

	protected EnumCodeStringTypeHandler(Class<E> type, E defaultValue) {
		this.type = type;
		if (this.type.getEnumConstants() == null) {
			throw new IllegalArgumentException(this.type.getSimpleName()
					+ " does not represent an enum type.");
		}
		this.enums = new HashMap<>();
		for (E e : this.type.getEnumConstants()) {
			this.enums.put(e.code(), e);
		}
		this.defaultValue = defaultValue;
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, E parameter,
			JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter.code());
	}

	@Override
	public E getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return getEnumValue(rs.getString(columnName));
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return getEnumValue(rs.getString(columnIndex));
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return getEnumValue(cs.getString(columnIndex));
	}

	private E getEnumValue(String code) {
		if (code == null) {
			return null;
		}
		E e = enums.get(code);
		if (e == null) {
			if (defaultValue == null) {
				throw new IllegalStateException("No matching enum "
						+ type.getSimpleName() + " for " + code);
			}
			if (log.isDebugEnabled()) {
				log.debug("No matching enum {0} for {1}", type.getSimpleName(),
						code);
			}
			return defaultValue;
		}
		return e;
	}

}
