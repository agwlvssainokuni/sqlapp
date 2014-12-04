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

package cherry.foundation.type.converter;

import org.springframework.core.convert.converter.Converter;

import cherry.foundation.type.Code;
import cherry.foundation.type.CodeUtil;
import cherry.foundation.type.CodeUtil.CodeMap;

public abstract class EnumCodeConverter<C, E extends Code<C>> implements
		Converter<C, E> {

	private CodeMap<C, E> codeMap;

	protected EnumCodeConverter(Class<E> type, E defaultValue) {
		this.codeMap = CodeUtil.getCodeMap(type, defaultValue);
	}

	@Override
	public E convert(C source) {
		return codeMap.get(source);
	}
}
