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

package cherry.spring.common.type.format;

import java.util.List;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

public class DelegateFormatterRegistrar implements FormatterRegistrar {

	private List<?> formatters;

	public void setFormatters(List<?> formatters) {
		this.formatters = formatters;
	}

	@Override
	public void registerFormatters(FormatterRegistry registry) {
		for (Object f : formatters) {
			if (f instanceof FormatterRegistrar) {
				FormatterRegistrar registrar = (FormatterRegistrar) f;
				registrar.registerFormatters(registry);
			}
			if (f instanceof AnnotationFormatterFactory) {
				AnnotationFormatterFactory<?> factory = (AnnotationFormatterFactory<?>) f;
				registry.addFormatterForFieldAnnotation(factory);
			}
		}
	}

}
