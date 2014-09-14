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

package cherry.spring.common.custom.converter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;

import cherry.spring.common.custom.Code;

public abstract class EnumCodeConverter<C, E extends Code<C>> implements
		Converter<C, E> {

	private Map<C, E> enums;

	protected EnumCodeConverter(Class<E> enumType) {
		if (enumType.getEnumConstants() == null) {
			throw new IllegalArgumentException(enumType.getSimpleName()
					+ " does not represent an enum type.");
		}
		this.enums = new HashMap<>();
		for (E e : enumType.getEnumConstants()) {
			this.enums.put(e.code(), e);
		}
	}

	@Override
	public E convert(C source) {
		return enums.get(source);
	}

}
