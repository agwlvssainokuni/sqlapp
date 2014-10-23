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

package cherry.spring.fwcore.type.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

import cherry.spring.fwcore.type.DeletedFlag;
import cherry.spring.fwcore.type.FlagCode;

public class CodeSqlConverterRegistrar implements FormatterRegistrar {

	@Override
	public void registerFormatters(FormatterRegistry registry) {
		registry.addConverter(new DeletedFlagConverter());
		registry.addConverter(new FlagCodeConverter());
	}

	static class DeletedFlagConverter implements
			Converter<Integer, DeletedFlag> {
		@Override
		public DeletedFlag convert(Integer source) {
			return new DeletedFlag(source);
		}
	}

	static class FlagCodeConverter extends EnumCodeConverter<Integer, FlagCode> {
		public FlagCodeConverter() {
			super(FlagCode.class, FlagCode.TRUE);
		}
	}

}
