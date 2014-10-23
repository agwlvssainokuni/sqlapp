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

package cherry.spring.fwcore.type.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import cherry.spring.fwcore.type.Code;
import cherry.spring.fwcore.type.CodeUtil;
import cherry.spring.fwcore.type.CodeUtil.CodeMap;

public abstract class EnumCodeIntegerTypeHandler<E extends Code<Integer>>
		extends BaseTypeHandler<E> {

	private CodeMap<Integer, E> codeMap;

	protected EnumCodeIntegerTypeHandler(Class<E> type, E defaultValue) {
		this.codeMap = CodeUtil.getCodeMap(type, defaultValue);
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, E parameter,
			JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.code());
	}

	@Override
	public E getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		int code = rs.getInt(columnName);
		if (rs.wasNull()) {
			return null;
		}
		return codeMap.get(code);
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		int code = rs.getInt(columnIndex);
		if (rs.wasNull()) {
			return null;
		}
		return codeMap.get(code);
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		int code = cs.getInt(columnIndex);
		if (cs.wasNull()) {
			return null;
		}
		return codeMap.get(code);
	}

}
