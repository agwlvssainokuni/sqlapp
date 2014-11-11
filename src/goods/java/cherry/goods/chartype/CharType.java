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

package cherry.goods.chartype;

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

/**
 * 文字の種類を判別する機能を提供する。<br />
 * 判別する種類は下記の通り。
 * <ul>
 * <li>半角文字(半角カナ含まず) ({@link #isBasicLatin(int)})</li>
 * <li>半角文字(半角カナ含む) ({@link #isHalfWidth(int)})</li>
 * <li>全角文字 ({@link #isFullWidth(int)})</li>
 * <li>半角空白 ({@link #isSpace(int)})</li>
 * <li>半角数字 ({@link #isNumeric(int)})</li>
 * <li>半角英字 ({@link #isAlpha(int)})</li>
 * <li>半角英字大文字 ({@link #isUpper(int)})</li>
 * <li>半角英字小文字 ({@link #isLower(int)})</li>
 * <li>全角空白 ({@link #isFullSpace(int)})</li>
 * <li>全角数字 ({@link #isFullNumeric(int)})</li>
 * <li>全角英字 ({@link #isFullAlpha(int)})</li>
 * <li>全角英字大文字 ({@link #isFullUpper(int)})</li>
 * <li>全角英字小文字 ({@link #isFullLower(int)})</li>
 * <li>全角ひらがな ({@link #isFullHiragana(int)})</li>
 * <li>全角カタカナ ({@link #isFullKatakana(int)})</li>
 * <li>半角カタカナ ({@link #isHalfKatakana(int)})</li>
 * </ul>
 */
public class CharType {

	/**
	 * 文字種判別「半角文字(半角カナ含まず)」。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @return 対象文字が「半角文字(半角カナ含まず)」であれば真(true)、さもなくば、偽(false)。
	 */
	public static boolean isBasicLatin(int codePoint) {
		return of(codePoint) == BASIC_LATIN;
	}

	/**
	 * 文字種判別「半角文字(半角カナ含む)」。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @return 対象文字が「半角文字(半角カナ含む)」であれば真(true)、さもなくば、偽(false)。
	 */
	public static boolean isHalfWidth(int codePoint) {
		return isBasicLatin(codePoint) || isHalfKatakana(codePoint);
	}

	/**
	 * 文字種判別「全角文字」。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @return 対象文字が「全角文字」であれば真(true)、さもなくば、偽(false)。
	 */
	public static boolean isFullWidth(int codePoint) {
		return !isHalfWidth(codePoint);
	}

	/**
	 * 文字種判別「半角空白」。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @return 対象文字が「半角空白」であれば真(true)、さもなくば、偽(false)。
	 */
	public static boolean isSpace(int codePoint) {
		return of(codePoint) == BASIC_LATIN && isWhitespace(codePoint);
	}

	/**
	 * 文字種判別「半角数字」。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @return 対象文字が「半角数字」であれば真(true)、さもなくば、偽(false)。
	 */
	public static boolean isNumeric(int codePoint) {
		return of(codePoint) == BASIC_LATIN && isDigit(codePoint);
	}

	/**
	 * 文字種判別「半角英字」。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @return 対象文字が「半角英字」であれば真(true)、さもなくば、偽(false)。
	 */
	public static boolean isAlpha(int codePoint) {
		return of(codePoint) == BASIC_LATIN && isAlphabetic(codePoint);
	}

	/**
	 * 文字種判別「半角英字大文字」。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @return 対象文字が「半角英字大文字」であれば真(true)、さもなくば、偽(false)。
	 */
	public static boolean isUpper(int codePoint) {
		return of(codePoint) == BASIC_LATIN && isUpperCase(codePoint);
	}

	/**
	 * 文字種判別「半角英字小文字」。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @return 対象文字が「半角英字小文字」であれば真(true)、さもなくば、偽(false)。
	 */
	public static boolean isLower(int codePoint) {
		return of(codePoint) == BASIC_LATIN && isLowerCase(codePoint);
	}

	/**
	 * 文字種判別「全角空白」。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @return 対象文字が「全角空白」であれば真(true)、さもなくば、偽(false)。
	 */
	public static boolean isFullSpace(int codePoint) {
		return of(codePoint) != BASIC_LATIN && isWhitespace(codePoint);
	}

	/**
	 * 文字種判別「全角数字」。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @return 対象文字が「全角数字」であれば真(true)、さもなくば、偽(false)。
	 */
	public static boolean isFullNumeric(int codePoint) {
		return of(codePoint) == HALFWIDTH_AND_FULLWIDTH_FORMS
				&& isDigit(codePoint);
	}

	/**
	 * 文字種判別「全角英字」。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @return 対象文字が「全角英字」であれば真(true)、さもなくば、偽(false)。
	 */
	public static boolean isFullAlpha(int codePoint) {
		return of(codePoint) == HALFWIDTH_AND_FULLWIDTH_FORMS
				&& isAlphabetic(codePoint) && !isHalfKatakana(codePoint);
	}

	/**
	 * 文字種判別「全角英字大文字」。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @return 対象文字が「全角英字大文字」であれば真(true)、さもなくば、偽(false)。
	 */
	public static boolean isFullUpper(int codePoint) {
		return of(codePoint) == HALFWIDTH_AND_FULLWIDTH_FORMS
				&& isUpperCase(codePoint) && !isHalfKatakana(codePoint);
	}

	/**
	 * 文字種判別「全角英字小文字」。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @return 対象文字が「全角英字小文字」であれば真(true)、さもなくば、偽(false)。
	 */
	public static boolean isFullLower(int codePoint) {
		return of(codePoint) == HALFWIDTH_AND_FULLWIDTH_FORMS
				&& isLowerCase(codePoint) && !isHalfKatakana(codePoint);
	}

	/**
	 * 文字種判別「全角ひらがな」。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @return 対象文字が「全角ひらがな」であれば真(true)、さもなくば、偽(false)。
	 */
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

	/**
	 * 文字種判別「全角カタカナ」。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @return 対象文字が「全角カタカナ」であれば真(true)、さもなくば、偽(false)。
	 */
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

	/**
	 * 文字種判別「半角カタカナ」。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @return 対象文字が「半角カタカナ」であれば真(true)、さもなくば、偽(false)。
	 */
	public static boolean isHalfKatakana(int codePoint) {
		return '\uFF61' <= codePoint && codePoint <= '\uFF9F';
	}

}
