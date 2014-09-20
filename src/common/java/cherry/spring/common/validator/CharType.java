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

package cherry.spring.common.validator;

import static cherry.spring.common.lib.chartype.CharTypeValidator.ALPHA;
import static cherry.spring.common.lib.chartype.CharTypeValidator.BASIC_LATIN;
import static cherry.spring.common.lib.chartype.CharTypeValidator.FULL_ALPHA;
import static cherry.spring.common.lib.chartype.CharTypeValidator.FULL_HIRAGANA;
import static cherry.spring.common.lib.chartype.CharTypeValidator.FULL_KATAKANA;
import static cherry.spring.common.lib.chartype.CharTypeValidator.FULL_LOWER;
import static cherry.spring.common.lib.chartype.CharTypeValidator.FULL_NUMERIC;
import static cherry.spring.common.lib.chartype.CharTypeValidator.FULL_SPACE;
import static cherry.spring.common.lib.chartype.CharTypeValidator.FULL_UPPER;
import static cherry.spring.common.lib.chartype.CharTypeValidator.FULL_WIDTH;
import static cherry.spring.common.lib.chartype.CharTypeValidator.HALF_KATAKANA;
import static cherry.spring.common.lib.chartype.CharTypeValidator.HALF_WIDTH;
import static cherry.spring.common.lib.chartype.CharTypeValidator.LOWER;
import static cherry.spring.common.lib.chartype.CharTypeValidator.NUMERIC;
import static cherry.spring.common.lib.chartype.CharTypeValidator.SPACE;
import static cherry.spring.common.lib.chartype.CharTypeValidator.UPPER;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = { cherry.spring.common.validator.CharTypeValidator.class })
public @interface CharType {

	enum Mode {
		/** 指定なし */
		None(0),
		/** BASIC_LATIN */
		BasicLatin(BASIC_LATIN),
		/** 半角 (BASIC_LATIN + 半角カナ) */
		HalfWidth(HALF_WIDTH),
		/** 全角 (半角以外) */
		FullWidth(FULL_WIDTH),
		/** 半角空白 */
		Space(SPACE),
		/** 半角数字 */
		Numeric(NUMERIC),
		/** 半角アルファベット */
		Alpha(ALPHA),
		/** 半角アルファベット大文字 */
		Upper(UPPER),
		/** 半角アルファベット小文字 */
		Lower(LOWER),
		/** 全角空白 */
		FullSpace(FULL_SPACE),
		/** 全角数字 */
		FullNumeric(FULL_NUMERIC),
		/** 全角アルファベット */
		FullAlpha(FULL_ALPHA),
		/** 全角アルファベット大文字 */
		FullUpper(FULL_UPPER),
		/** 全角アルファベット小文字 */
		FullLower(FULL_LOWER),
		/** 全角ひらがな */
		FullHiragana(FULL_HIRAGANA),
		/** 全角カタカナ */
		FullKatakana(FULL_KATAKANA),
		/** 半角カタカナ */
		HalfKatakana(HALF_KATAKANA);

		private final int mode;

		private Mode(int mode) {
			this.mode = mode;
		}

		public int mode() {
			return mode;
		}
	}

	Mode[] value() default { Mode.BasicLatin };

	String acceptable() default "";

	String message() default "{cherry.spring.common.validator.CharType.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
	@Retention(RUNTIME)
	public @interface List {
		CharType[] value();
	}

}
