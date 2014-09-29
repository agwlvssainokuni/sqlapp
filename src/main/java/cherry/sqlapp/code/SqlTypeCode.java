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

package cherry.sqlapp.code;

import cherry.spring.common.type.Code;
import cherry.spring.common.type.CodeUtil;
import cherry.spring.common.type.CodeUtil.CodeMap;

public enum SqlTypeCode implements Code<String> {
	CLAUSE("clause"), STATEMENT("statement"), LOAD("load");

	private final String code;

	private SqlTypeCode(String code) {
		this.code = code;
	}

	@Override
	public String code() {
		return code;
	}

	private static final CodeMap<String, SqlTypeCode> codeMap = CodeUtil
			.getCodeMap(SqlTypeCode.class, null);

	public static SqlTypeCode codeValueOf(String code) {
		return codeMap.get(code);
	}

}
