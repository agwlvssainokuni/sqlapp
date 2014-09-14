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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import cherry.spring.common.custom.Code;

public class EnumCodeTypeConverter<C, E extends Code<C>> implements
		GenericConverter {

	private Class<C> codeType;

	private Class<E> enumType;

	private Map<C, E> enums;

	public EnumCodeTypeConverter(Class<C> codeType, Class<E> enumType) {
		this.codeType = codeType;
		this.enumType = enumType;
		if (this.enumType.getEnumConstants() == null) {
			throw new IllegalArgumentException(this.enumType.getSimpleName()
					+ " does not represent an enum type.");
		}
		this.enums = new HashMap<>();
		for (E e : this.enumType.getEnumConstants()) {
			this.enums.put(e.code(), e);
		}
	}

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		Set<ConvertiblePair> set = new HashSet<>();
		set.add(new ConvertiblePair(codeType, enumType));
		return set;
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType,
			TypeDescriptor targetType) {
		return enums.get(source);
	}

}
