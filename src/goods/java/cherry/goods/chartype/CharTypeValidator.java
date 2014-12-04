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

import static java.lang.Character.codePointAt;
import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLowSurrogate;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.isWhitespace;
import static java.lang.Character.UnicodeBlock.of;

import java.lang.Character.UnicodeBlock;

/**
 * 文字列を構成する文字の種類を判別する機能を提供する。<br />
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
public class CharTypeValidator {

	/** 文字種指定「半角文字(半角カナ含まず)」。 */
	public static final int BASIC_LATIN;
	/** 文字種指定「半角文字(半角カナ含む)」。 */
	public static final int HALF_WIDTH;
	/** 文字種指定「全角文字」。 */
	public static final int FULL_WIDTH;
	/** 文字種指定「半角空白」。 */
	public static final int SPACE;
	/** 文字種指定「半角数字」。 */
	public static final int NUMERIC;
	/** 文字種指定「半角英字」。 */
	public static final int ALPHA;
	/** 文字種指定「半角英字大文字」。 */
	public static final int UPPER;
	/** 文字種指定「半角英字小文字」。 */
	public static final int LOWER;
	/** 文字種指定「全角空白」。 */
	public static final int FULL_SPACE;
	/** 文字種指定「全角数字」。 */
	public static final int FULL_NUMERIC;
	/** 文字種指定「全角英字」。 */
	public static final int FULL_ALPHA;
	/** 文字種指定「全角英字大文字」。 */
	public static final int FULL_UPPER;
	/** 文字種指定「全角英字大文字」。 */
	public static final int FULL_LOWER;
	/** 文字種指定「全角ひらがな」。 */
	public static final int FULL_HIRAGANA;
	/** 文字種指定「全角カタカナ」。 */
	public static final int FULL_KATAKANA;
	/** 文字種指定「半角カタカナ」。 */
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

	/**
	 * 文字の種類を判別する機能を提供する。<br />
	 * 実態は{@link CharType}が提供するメソッドへの振分けである (ファサードとして位置付ける)。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @param mode
	 *            文字種指定。文字種は複数指定可能であり、下記の組合せ ("|"でマスクを組合せる) で指定することとする。
	 *            <ul>
	 *            <li>{@link #BASIC_LATIN}</li>
	 *            <li>{@link #HALF_WIDTH}</li>
	 *            <li>{@link #FULL_WIDTH}</li>
	 *            <li>{@link #SPACE}</li>
	 *            <li>{@link #NUMERIC}</li>
	 *            <li>{@link #ALPHA}</li>
	 *            <li>{@link #UPPER}</li>
	 *            <li>{@link #LOWER}</li>
	 *            <li>{@link #FULL_SPACE}</li>
	 *            <li>{@link #FULL_NUMERIC}</li>
	 *            <li>{@link #FULL_ALPHA}</li>
	 *            <li>{@link #FULL_UPPER}</li>
	 *            <li>{@link #FULL_LOWER}</li>
	 *            <li>{@link #FULL_HIRAGANA}</li>
	 *            <li>{@link #FULL_KATAKANA}</li>
	 *            <li>{@link #HALF_KATAKANA}</li>
	 *            </ul>
	 * @return 対象文字が文字種指定で指定された文字種のいずれかであれば真(true)、さもなくば、偽(false)。
	 */
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

	/**
	 * 文字の種類を判別する機能を提供する。<br />
	 * 実態は{@link CharType}が提供するメソッドへの振分けである (ファサードとして位置付ける)。<br />
	 * 基本的な機能は{@link #isValid(int, int)}
	 * と同等であり、これに加えて、許容文字として指定した文字も文字種が合致したものとして判定する。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @param mode
	 *            文字種指定。文字種は複数指定可能であり、下記の組合せ ("|"でマスクを組合せる) で指定することとする。
	 *            <ul>
	 *            <li>{@link #BASIC_LATIN}</li>
	 *            <li>{@link #HALF_WIDTH}</li>
	 *            <li>{@link #FULL_WIDTH}</li>
	 *            <li>{@link #SPACE}</li>
	 *            <li>{@link #NUMERIC}</li>
	 *            <li>{@link #ALPHA}</li>
	 *            <li>{@link #UPPER}</li>
	 *            <li>{@link #LOWER}</li>
	 *            <li>{@link #FULL_SPACE}</li>
	 *            <li>{@link #FULL_NUMERIC}</li>
	 *            <li>{@link #FULL_ALPHA}</li>
	 *            <li>{@link #FULL_UPPER}</li>
	 *            <li>{@link #FULL_LOWER}</li>
	 *            <li>{@link #FULL_HIRAGANA}</li>
	 *            <li>{@link #FULL_KATAKANA}</li>
	 *            <li>{@link #HALF_KATAKANA}</li>
	 *            </ul>
	 * @param acceptable
	 *            許容文字。文字種指定で指定した文字種に加えて、当引数に指定した文字も文字種が合致したものとして判定する。
	 * @return 対象文字が、文字種指定で指定された文字種のいずれか、または、許容文字に含まれるならば真(true)、さもなくば、偽(false)。
	 */
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

	/**
	 * 文字列を構成する文字の種類を判別する機能を提供する。<br />
	 * 
	 * @param seq
	 *            対象文字列。
	 * @param mode
	 *            文字種指定。文字種は複数指定可能であり、下記の組合せ ("|"でマスクを組合せる) で指定することとする。
	 *            <ul>
	 *            <li>{@link #BASIC_LATIN}</li>
	 *            <li>{@link #HALF_WIDTH}</li>
	 *            <li>{@link #FULL_WIDTH}</li>
	 *            <li>{@link #SPACE}</li>
	 *            <li>{@link #NUMERIC}</li>
	 *            <li>{@link #ALPHA}</li>
	 *            <li>{@link #UPPER}</li>
	 *            <li>{@link #LOWER}</li>
	 *            <li>{@link #FULL_SPACE}</li>
	 *            <li>{@link #FULL_NUMERIC}</li>
	 *            <li>{@link #FULL_ALPHA}</li>
	 *            <li>{@link #FULL_UPPER}</li>
	 *            <li>{@link #FULL_LOWER}</li>
	 *            <li>{@link #FULL_HIRAGANA}</li>
	 *            <li>{@link #FULL_KATAKANA}</li>
	 *            <li>{@link #HALF_KATAKANA}</li>
	 *            </ul>
	 * @param acceptable
	 *            許容文字。文字種指定で指定した文字種に加えて、当引数に指定した文字も文字種が合致したものとして判定する。
	 * @return 対象文字列を構成する全ての文字が、文字種指定で指定された文字種のいずれか、または、許容文字に含まれるならば真(true)、
	 *         さもなくば、偽 (false )。
	 */
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

	/**
	 * 文字種判別「半角文字(半角カナ含まず)」。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @return 対象文字が「半角文字(半角カナ含まず)」であれば真(true)、さもなくば、偽(false)。
	 */
	public static boolean isBasicLatin(int codePoint) {
		return of(codePoint) == UnicodeBlock.BASIC_LATIN;
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
		return of(codePoint) == UnicodeBlock.BASIC_LATIN
				&& isWhitespace(codePoint);
	}

	/**
	 * 文字種判別「半角数字」。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @return 対象文字が「半角数字」であれば真(true)、さもなくば、偽(false)。
	 */
	public static boolean isNumeric(int codePoint) {
		return of(codePoint) == UnicodeBlock.BASIC_LATIN && isDigit(codePoint);
	}

	/**
	 * 文字種判別「半角英字」。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @return 対象文字が「半角英字」であれば真(true)、さもなくば、偽(false)。
	 */
	public static boolean isAlpha(int codePoint) {
		return of(codePoint) == UnicodeBlock.BASIC_LATIN
				&& isAlphabetic(codePoint);
	}

	/**
	 * 文字種判別「半角英字大文字」。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @return 対象文字が「半角英字大文字」であれば真(true)、さもなくば、偽(false)。
	 */
	public static boolean isUpper(int codePoint) {
		return of(codePoint) == UnicodeBlock.BASIC_LATIN
				&& isUpperCase(codePoint);
	}

	/**
	 * 文字種判別「半角英字小文字」。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @return 対象文字が「半角英字小文字」であれば真(true)、さもなくば、偽(false)。
	 */
	public static boolean isLower(int codePoint) {
		return of(codePoint) == UnicodeBlock.BASIC_LATIN
				&& isLowerCase(codePoint);
	}

	/**
	 * 文字種判別「全角空白」。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @return 対象文字が「全角空白」であれば真(true)、さもなくば、偽(false)。
	 */
	public static boolean isFullSpace(int codePoint) {
		return of(codePoint) != UnicodeBlock.BASIC_LATIN
				&& isWhitespace(codePoint);
	}

	/**
	 * 文字種判別「全角数字」。
	 * 
	 * @param codePoint
	 *            対象文字 (コードポイントで指定すること)。
	 * @return 対象文字が「全角数字」であれば真(true)、さもなくば、偽(false)。
	 */
	public static boolean isFullNumeric(int codePoint) {
		return of(codePoint) == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
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
		return of(codePoint) == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
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
		return of(codePoint) == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
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
		return of(codePoint) == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
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
		return of(codePoint) == UnicodeBlock.HIRAGANA || // \u3040 - \u309F
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
		return of(codePoint) == UnicodeBlock.KATAKANA || // \u30A0 - \u30FF
				of(codePoint) == UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS || // \u31F0-\u31FF
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
