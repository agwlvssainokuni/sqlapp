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

import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.isWhitespace;
import static java.lang.Character.UnicodeBlock.BASIC_LATIN;
import static java.lang.Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
import static java.lang.Character.UnicodeBlock.HIRAGANA;
import static java.lang.Character.UnicodeBlock.KATAKANA;
import static java.lang.Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS;
import static java.lang.Character.UnicodeBlock.of;

public class CharType {

	public static boolean isBasicLatin(int codePoint) {
		return of(codePoint) == BASIC_LATIN;
	}

	public static boolean isHalfWidth(int codePoint) {
		return isBasicLatin(codePoint) || isHalfKatakana(codePoint);
	}

	public static boolean isFullWidth(int codePoint) {
		return !isHalfWidth(codePoint);
	}

	public static boolean isSpace(int codePoint) {
		return of(codePoint) == BASIC_LATIN && isWhitespace(codePoint);
	}

	public static boolean isNumeric(int codePoint) {
		return of(codePoint) == BASIC_LATIN && isDigit(codePoint);
	}

	public static boolean isAlpha(int codePoint) {
		return of(codePoint) == BASIC_LATIN && isAlphabetic(codePoint);
	}

	public static boolean isUpper(int codePoint) {
		return of(codePoint) == BASIC_LATIN && isUpperCase(codePoint);
	}

	public static boolean isLower(int codePoint) {
		return of(codePoint) == BASIC_LATIN && isLowerCase(codePoint);
	}

	public static boolean isFullSpace(int codePoint) {
		return of(codePoint) != BASIC_LATIN && isWhitespace(codePoint);
	}

	public static boolean isFullNumeric(int codePoint) {
		return of(codePoint) == HALFWIDTH_AND_FULLWIDTH_FORMS
				&& isDigit(codePoint);
	}

	public static boolean isFullAlpha(int codePoint) {
		return of(codePoint) == HALFWIDTH_AND_FULLWIDTH_FORMS
				&& isAlphabetic(codePoint) && !isHalfKatakana(codePoint);
	}

	public static boolean isFullUpper(int codePoint) {
		return of(codePoint) == HALFWIDTH_AND_FULLWIDTH_FORMS
				&& isUpperCase(codePoint) && !isHalfKatakana(codePoint);
	}

	public static boolean isFullLower(int codePoint) {
		return of(codePoint) == HALFWIDTH_AND_FULLWIDTH_FORMS
				&& isLowerCase(codePoint) && !isHalfKatakana(codePoint);
	}

	public static boolean isFullHiragana(int codePoint) {
		// based on Unicode 3.2
		return of(codePoint) == HIRAGANA || // \u3040 - \u309F
				// import from KATAKANA (\u30A0 - \u30FF)
				codePoint == '\u30A0' || // '゠' from KATAKANA (not in Win31J)
				codePoint == '\u30FB' || // '・' from KATAKANA
				codePoint == '\u30FC' || // 'ー' from KATAKANA
				// \u30FD 'ヽ' and \u30FE 'ヾ' if iteration mark for KATAKANA
				codePoint == '\u30FF'; // 'ヿ' from KATAKANA (not in Win31J)
	}

	public static boolean isFullKatakana(int codePoint) {
		// based on Unicode 3.2
		return of(codePoint) == KATAKANA || // \u30A0 - \u30FF
				of(codePoint) == KATAKANA_PHONETIC_EXTENSIONS || // \u31F0-\u31FF
				// import from HIRAGANA (\u3040 - \u309F)
				// \u3040, \u3097, \u3098 is reserved
				codePoint == '\u3099' || // MARK from HIRAGANA (not in Win31J)
				codePoint == '\u309A' || // MARK from HIRAGANA (not in Win31J)
				codePoint == '\u309B' || // '゛' from HIRAGANA
				codePoint == '\u309C' || // '゜' from HIRAGANA
				// \u309D 'ゝ' and \u309E 'ゞ' is iteration mark for HIRAGANA
				codePoint == '\u309F'; // 'ゟ' from HIRAGANA (not in Win31J)
	}

	public static boolean isHalfKatakana(int codePoint) {
		return '\uFF61' <= codePoint && codePoint <= '\uFF9F';
	}

}
