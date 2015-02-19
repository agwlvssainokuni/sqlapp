/*
 * Copyright 2015 agwlvssainokuni
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

package cherry.foundation.render;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.number.NumberFormatter;

public class ObjectRendererImpl implements ObjectRenderer {

	private ConversionService conversionService;

	private List<NumberFormatter> numberFormatter;

	public void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	public void setNumberFormatter(List<NumberFormatter> numberFormatter) {
		this.numberFormatter = numberFormatter;
	}

	@Override
	public String render(Object value, Integer mode) {
		if (value == null) {
			return "";
		} else if (value instanceof Number) {
			Locale locale = LocaleContextHolder.getLocale();
			if (CollectionUtils.isEmpty(numberFormatter)) {
				return NumberFormat.getInstance(locale).format(value);
			} else {
				return getSafe(numberFormatter, mode).getNumberFormat(locale).format(value);
			}
		} else if (value instanceof LocalDate) {
			return conversionService.convert(value, String.class);
		} else if (value instanceof LocalTime) {
			return conversionService.convert(value, String.class);
		} else if (value instanceof LocalDateTime) {
			return conversionService.convert(value, String.class);
		} else if (value instanceof String) {
			return (String) value;
		} else {
			return value.toString();
		}
	}

	private <T> T getSafe(List<T> list, Integer index) {
		if (index == null) {
			return list.get(0);
		} else if (index.intValue() < 0) {
			return list.get(0);
		} else if (index.intValue() >= list.size()) {
			return list.get(list.size() - 1);
		} else {
			return list.get(index.intValue());
		}
	}

}
