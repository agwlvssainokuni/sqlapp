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

package cherry.spring.common.type;

import cherry.spring.common.type.CodeUtil.CodeMap;

public enum FlagCode implements Code<Integer> {
	FALSE(0), TRUE(1);

	private int code;

	private FlagCode(int code) {
		this.code = code;
	}

	@Override
	public Integer code() {
		return this.code;
	}

	public boolean booleanValue() {
		return this == TRUE;
	}

	private static CodeMap<Integer, FlagCode> codeMap = CodeUtil.getCodeMap(
			FlagCode.class, TRUE);

	public static FlagCode valueOf(int i) {
		return codeMap.get(i);
	}

	public static FlagCode valueOf(boolean b) {
		return b ? TRUE : FALSE;
	}

}
