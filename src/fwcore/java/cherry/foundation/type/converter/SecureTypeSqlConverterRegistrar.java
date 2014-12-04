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
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

import cherry.foundation.type.SecureBigDecimal;
import cherry.foundation.type.SecureBigInteger;
import cherry.foundation.type.SecureInteger;
import cherry.foundation.type.SecureLong;
import cherry.foundation.type.SecureString;

public class SecureTypeSqlConverterRegistrar implements FormatterRegistrar {

	@Override
	public void registerFormatters(FormatterRegistry registry) {
		registry.addConverter(new SecureStringConverter());
		registry.addConverter(new SecureIntegerConverter());
		registry.addConverter(new SecureLongConverter());
		registry.addConverter(new SecureBigIntegerConverter());
		registry.addConverter(new SecureBigDecimalConverter());
	}

	static class SecureStringConverter implements
			Converter<String, SecureString> {
		@Override
		public SecureString convert(String source) {
			return SecureString.cryptoValueOf(source);
		}
	}

	static class SecureIntegerConverter implements
			Converter<String, SecureInteger> {
		@Override
		public SecureInteger convert(String source) {
			return SecureInteger.cryptoValueOf(source);
		}
	}

	static class SecureLongConverter implements Converter<String, SecureLong> {
		@Override
		public SecureLong convert(String source) {
			return SecureLong.cryptoValueOf(source);
		}
	}

	static class SecureBigIntegerConverter implements
			Converter<String, SecureBigInteger> {
		@Override
		public SecureBigInteger convert(String source) {
			return SecureBigInteger.cryptoValueOf(source);
		}
	}

	static class SecureBigDecimalConverter implements
			Converter<String, SecureBigDecimal> {
		@Override
		public SecureBigDecimal convert(String source) {
			return SecureBigDecimal.cryptoValueOf(source);
		}
	}

}
