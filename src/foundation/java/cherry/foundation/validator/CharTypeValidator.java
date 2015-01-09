/*
 * Copyright 2014,2015 agwlvssainokuni
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

package cherry.foundation.validator;

import static cherry.goods.chartype.CharTypeValidator.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import cherry.foundation.validator.CharType.Mode;
import cherry.goods.chartype.CharTypeResult;

public class CharTypeValidator implements ConstraintValidator<CharType, String> {

	private int mode;

	private int[] acceptable;

	@Override
	public void initialize(CharType annotation) {
		this.mode = createMode(annotation.value());
		this.acceptable = createAcceptable(annotation.acceptable());
	}

	private int createMode(Mode[] mode) {
		int result = 0;
		for (Mode m : mode) {
			result |= m.mode();
		}
		return result;
	}

	private int[] createAcceptable(String acceptable) {
		int[] result = new int[acceptable.codePointCount(0, acceptable.length())];
		for (int i = 0, j = 0; i < acceptable.length(); i++) {
			if (Character.isLowSurrogate(acceptable.charAt(i))) {
				continue;
			}
			result[j++] = Character.codePointAt(acceptable, i);
		}
		return result;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isEmpty(value)) {
			return true;
		}
		CharTypeResult result = validate(value, mode, acceptable);
		return result.isValid();
	}

}
