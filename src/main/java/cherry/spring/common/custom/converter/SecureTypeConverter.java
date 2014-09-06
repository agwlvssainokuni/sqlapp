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

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import cherry.spring.common.custom.SecureBigDecimal;
import cherry.spring.common.custom.SecureBigInteger;
import cherry.spring.common.custom.SecureInteger;
import cherry.spring.common.custom.SecureLong;
import cherry.spring.common.custom.SecureString;

public class SecureTypeConverter implements GenericConverter {

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		Set<ConvertiblePair> set = new HashSet<>();
		set.add(new ConvertiblePair(String.class, SecureString.class));
		set.add(new ConvertiblePair(String.class, SecureInteger.class));
		set.add(new ConvertiblePair(String.class, SecureLong.class));
		set.add(new ConvertiblePair(String.class, SecureBigInteger.class));
		set.add(new ConvertiblePair(String.class, SecureBigDecimal.class));
		return set;
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType,
			TypeDescriptor targetType) {
		if (targetType.getType() == SecureString.class) {
			return SecureString.cryptoValueOf((String) source);
		} else if (targetType.getType() == SecureInteger.class) {
			return SecureInteger.cryptoValueOf((String) source);
		} else if (targetType.getType() == SecureLong.class) {
			return SecureLong.cryptoValueOf((String) source);
		} else if (targetType.getType() == SecureBigInteger.class) {
			return SecureBigInteger.cryptoValueOf((String) source);
		} else if (targetType.getType() == SecureBigDecimal.class) {
			return SecureBigDecimal.cryptoValueOf((String) source);
		} else {
			throw new IllegalArgumentException("targetType "
					+ targetType.getClass() + " is not supported");
		}
	}

}
