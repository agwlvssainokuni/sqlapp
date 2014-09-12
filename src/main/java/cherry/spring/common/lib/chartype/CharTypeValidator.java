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

package cherry.spring.common.lib.chartype;

import static cherry.spring.common.lib.chartype.CharType.isAlpha;
import static cherry.spring.common.lib.chartype.CharType.isBasicLatin;
import static cherry.spring.common.lib.chartype.CharType.isFullAlpha;
import static cherry.spring.common.lib.chartype.CharType.isFullHiragana;
import static cherry.spring.common.lib.chartype.CharType.isFullKatakana;
import static cherry.spring.common.lib.chartype.CharType.isFullLower;
import static cherry.spring.common.lib.chartype.CharType.isFullNumeric;
import static cherry.spring.common.lib.chartype.CharType.isFullSpace;
import static cherry.spring.common.lib.chartype.CharType.isFullUpper;
import static cherry.spring.common.lib.chartype.CharType.isFullWidth;
import static cherry.spring.common.lib.chartype.CharType.isHalfKatakana;
import static cherry.spring.common.lib.chartype.CharType.isHalfWidth;
import static cherry.spring.common.lib.chartype.CharType.isLower;
import static cherry.spring.common.lib.chartype.CharType.isNumeric;
import static cherry.spring.common.lib.chartype.CharType.isSpace;
import static cherry.spring.common.lib.chartype.CharType.isUpper;
import static java.lang.Character.codePointAt;
import static java.lang.Character.isLowSurrogate;

public class CharTypeValidator {

	public static final int BASIC_LATIN;
	public static final int HALF_WIDTH;
	public static final int FULL_WIDTH;
	public static final int SPACE;
	public static final int NUMERIC;
	public static final int ALPHA;
	public static final int UPPER;
	public static final int LOWER;
	public static final int FULL_SPACE;
	public static final int FULL_NUMERIC;
	public static final int FULL_ALPHA;
	public static final int FULL_UPPER;
	public static final int FULL_LOWER;
	public static final int FULL_HIRAGANA;
	public static final int FULL_KATAKANA;
	public static final int HALF_KATAKANA;

	static {
		int shift = 0;
		BASIC_LATIN = (1 << (shift++));
		HALF_WIDTH = (1 << (shift++));
		FULL_WIDTH = (1 << (shift++));
		SPACE = (1 << (shift++));
		NUMERIC = (1 << (shift++));
		ALPHA = (1 << (shift++));
		UPPER = (1 << (shift++));
		LOWER = (1 << (shift++));
		FULL_SPACE = (1 << (shift++));
		FULL_NUMERIC = (1 << (shift++));
		FULL_ALPHA = (1 << (shift++));
		FULL_UPPER = (1 << (shift++));
		FULL_LOWER = (1 << (shift++));
		FULL_HIRAGANA = (1 << (shift++));
		FULL_KATAKANA = (1 << (shift++));
		HALF_KATAKANA = (1 << (shift++));
	}

	public static boolean isValid(int codePoint, int mode) {
		if ((mode & BASIC_LATIN) != 0 && isBasicLatin(codePoint)) {
			return true;
		}
		if ((mode & HALF_WIDTH) != 0 && isHalfWidth(codePoint)) {
			return true;
		}
		if ((mode & FULL_WIDTH) != 0 && isFullWidth(codePoint)) {
			return true;
		}
		if ((mode & SPACE) != 0 && isSpace(codePoint)) {
			return true;
		}
		if ((mode & NUMERIC) != 0 && isNumeric(codePoint)) {
			return true;
		}
		if ((mode & ALPHA) != 0 && isAlpha(codePoint)) {
			return true;
		}
		if ((mode & UPPER) != 0 && isUpper(codePoint)) {
			return true;
		}
		if ((mode & LOWER) != 0 && isLower(codePoint)) {
			return true;
		}
		if ((mode & FULL_SPACE) != 0 && isFullSpace(codePoint)) {
			return true;
		}
		if ((mode & FULL_NUMERIC) != 0 && isFullNumeric(codePoint)) {
			return true;
		}
		if ((mode & FULL_ALPHA) != 0 && isFullAlpha(codePoint)) {
			return true;
		}
		if ((mode & FULL_UPPER) != 0 && isFullUpper(codePoint)) {
			return true;
		}
		if ((mode & FULL_LOWER) != 0 && isFullLower(codePoint)) {
			return true;
		}
		if ((mode & FULL_HIRAGANA) != 0 && isFullHiragana(codePoint)) {
			return true;
		}
		if ((mode & FULL_KATAKANA) != 0 && isFullKatakana(codePoint)) {
			return true;
		}
		if ((mode & HALF_KATAKANA) != 0 && isHalfKatakana(codePoint)) {
			return true;
		}
		return false;
	}

	public static boolean isValid(int codePoint, int mode, int[] acceptable) {
		if (isValid(codePoint, mode)) {
			return true;
		}
		if (acceptable != null) {
			for (int ch : acceptable) {
				if (codePoint == ch) {
					return true;
				}
			}
		}
		return false;
	}

	public static CharTypeResult validate(CharSequence seq, int mode,
			int[] acceptable) {
		for (int i = 0; i < seq.length(); i++) {
			if (isLowSurrogate(seq.charAt(i))) {
				continue;
			}
			if (isValid(codePointAt(seq, i), mode, acceptable)) {
				continue;
			}
			return new CharTypeResult(false, i, codePointAt(seq, i));
		}
		return new CharTypeResult();
	}

}
