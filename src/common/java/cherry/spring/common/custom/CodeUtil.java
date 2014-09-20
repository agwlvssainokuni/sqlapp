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

package cherry.spring.common.custom;

import java.util.HashMap;
import java.util.Map;

import cherry.spring.common.log.Log;
import cherry.spring.common.log.LogFactory;

public class CodeUtil {

	public interface CodeMap<C, E extends Code<C>> {
		E get(C code);
	}

	public static <C, E extends Code<C>> CodeMap<C, E> getCodeMap(
			final Class<E> type, final E defaultValue) {
		return new CodeMap<C, E>() {

			private Log log = LogFactory.getLog(type);
			private Map<C, E> map = getMap(type);

			@Override
			public E get(C code) {
				E e = map.get(code);
				if (e == null) {
					if (defaultValue == null) {
						throw new IllegalArgumentException("No matching enum "
								+ type.getSimpleName() + " for " + code);
					}
					if (log.isDebugEnabled()) {
						log.debug("No matching enum {0} for {1}",
								type.getSimpleName(), code);
					}
					return defaultValue;
				}
				return e;
			}
		};
	}

	public static <C, E extends Code<C>> Map<C, E> getMap(Class<E> type) {
		if (type.getEnumConstants() == null) {
			throw new IllegalArgumentException(type.getSimpleName()
					+ " does not represent an enum type.");
		}
		Map<C, E> map = new HashMap<>();
		for (E e : type.getEnumConstants()) {
			map.put(e.code(), e);
		}
		return map;
	}

}
