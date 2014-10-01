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
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class EnumUtil {

	private static MessageSource messageSource;

	public static MessageSource setMessageSource(MessageSource ms) {
		messageSource = ms;
		return ms;
	}

	public static <E extends Enum<E>> LabeledEnum<E> getLabeledEnum(final E e) {
		return new LabeledEnum<E>() {

			@Override
			public E getEnum() {
				return e;
			}

			@Override
			public String getEnumName() {
				return e.name();
			}

			@Override
			public String getEnumLabel() {
				String cd = new StringBuffer(e.getClass().getName())
						.append(".").append(e.name()).toString();
				return messageSource.getMessage(cd, null,
						LocaleContextHolder.getLocale());
			}
		};
	}

	public static <E extends Enum<E>> List<LabeledEnum<E>> getLabeledEnumList(
			Class<E> type) {
		checkArgument(type.getEnumConstants() != null,
				"%s does not represent an enum type.", type.getSimpleName());
		List<LabeledEnum<E>> list = new ArrayList<>();
		for (E e : type.getEnumConstants()) {
			list.add(getLabeledEnum(e));
		}
		return list;
	}

}
