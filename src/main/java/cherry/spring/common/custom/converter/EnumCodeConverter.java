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
import cherry.spring.common.log.Log;
import cherry.spring.common.log.LogFactory;

public abstract class EnumCodeConverter<C, E extends Code<C>> implements
		Converter<C, E> {

	private final Log log = LogFactory.getLog(getClass());

	private Class<E> type;

	private Map<C, E> enums;

	private E defaultValue;

	protected EnumCodeConverter(Class<E> type, E defaultValue) {
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
	public E convert(C source) {
		E e = enums.get(source);
		if (e == null) {
			if (defaultValue == null) {
				throw new IllegalStateException("No matching enum "
						+ type.getSimpleName() + " for " + source);
			}
			if (log.isDebugEnabled()) {
				log.debug("No matching enum {0} for {1}", type.getSimpleName(),
						source);
			}
			return defaultValue;
		}
		return e;
	}
}
