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

import static cherry.goods.chartype.CharType.isAlpha;
import static cherry.goods.chartype.CharType.isBasicLatin;
import static cherry.goods.chartype.CharType.isFullAlpha;
import static cherry.goods.chartype.CharType.isFullHiragana;
import static cherry.goods.chartype.CharType.isFullKatakana;
import static cherry.goods.chartype.CharType.isFullLower;
import static cherry.goods.chartype.CharType.isFullNumeric;
import static cherry.goods.chartype.CharType.isFullSpace;
import static cherry.goods.chartype.CharType.isFullUpper;
import static cherry.goods.chartype.CharType.isFullWidth;
import static cherry.goods.chartype.CharType.isHalfKatakana;
import static cherry.goods.chartype.CharType.isHalfWidth;
import static cherry.goods.chartype.CharType.isLower;
import static cherry.goods.chartype.CharType.isNumeric;
import static cherry.goods.chartype.CharType.isSpace;
import static cherry.goods.chartype.CharType.isUpper;
import static java.lang.Character.codePointAt;
import static java.lang.Character.isLowSurrogate;

/**
 * 文字列を構成する文字の種類を判別する機能を提供する。<br />
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

}
