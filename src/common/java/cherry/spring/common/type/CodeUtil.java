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

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import cherry.spring.common.log.Log;
import cherry.spring.common.log.LogFactory;

public class CodeUtil {

	private static MessageSource messageSource;

	public static MessageSource setMessageSource(MessageSource ms) {
		messageSource = ms;
		return ms;
	}

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
					checkArgument(defaultValue != null,
							"No matching enum %s for %s", type.getSimpleName(),
							code);
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
		checkArgument(type.getEnumConstants() != null,
				"%s does not represent an enum type.", type.getSimpleName());
		Map<C, E> map = new LinkedHashMap<>();
		for (E e : type.getEnumConstants()) {
			map.put(e.code(), e);
		}
		return map;
	}

	public static <C, E extends Code<C>> LabeledCode<C, E> getLabeledCode(
			final E code) {
		return new LabeledCode<C, E>() {

			@Override
			public E getCode() {
				return code;
			}

			@Override
			public C getCodeValue() {
				return code.code();
			}

			@Override
			public String getCodeLabel() {
				String cd = new StringBuffer(code.getClass().getName())
						.append(".").append(code.code()).toString();
				return messageSource.getMessage(cd, null,
						LocaleContextHolder.getLocale());
			}
		};
	}

	public static <C, E extends Code<C>> List<LabeledCode<C, E>> getLabeledCodeList(
			Class<E> type) {
		checkArgument(type.getEnumConstants() != null,
				"%s does not represent an enum type.", type.getSimpleName());
		List<LabeledCode<C, E>> list = new ArrayList<>();
		for (E e : type.getEnumConstants()) {
			list.add(getLabeledCode(e));
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public static <C, E extends Code<C>> List<LabeledCode<C, E>> getLabeledCodeList(
			String typeName) {
		try {
			return getLabeledCodeList((Class<E>) Class.forName(typeName));
		} catch (ClassNotFoundException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

}
