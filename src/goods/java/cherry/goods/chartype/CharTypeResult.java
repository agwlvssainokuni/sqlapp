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

package cherry.goods.chartype;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 文字列を構成する文字の種類の判別結果を保持する。<br />
 * {@link CharTypeValidator#validate(CharSequence, int, int[])}の返却値である。
 */
public class CharTypeResult {

	/** 文字の種類の判別結果を保持する。trueは合致することを意味し、falseは合致しないことを意味する。 */
	private final boolean valid;

	/** 文字の種類が合致しない場合に、合致しない文字の位置(添字)を保持する。 */
	private final int index;

	/** 文字の種類が合致しない場合に、合致しない文字のコードポイントを保持する。 */
	private final int codePoint;

	/**
	 * 文字の種別が合致した場合の判別結果オブジェクトを生成する。<br />
	 * 判別結果はtrue、合致しない文字の位置は-1、合致しない文字のコードポイントは-1で生成する。
	 */
	public CharTypeResult() {
		this(true, -1, -1);
	}

	/**
	 * 文字の種別の判別結果オブジェクトを生成する。
	 * 
	 * @param valid 文字の種別の判別結果。
	 * @param index 合致しない文字の位置(添字)。
	 * @param codePoint 合致しない文字のコードポイント。
	 */
	public CharTypeResult(boolean valid, int index, int codePoint) {
		this.valid = valid;
		this.index = index;
		this.codePoint = codePoint;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * @return 文字の種類の判別結果。trueは合致することを意味し、falseは合致しないことを意味する。
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * @return 文字の種類が合致しない場合に、合致しない文字の位置(添字)。
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @return 文字の種類が合致しない場合に、合致しない文字のコードポイント。
	 */
	public int getCodePoint() {
		return codePoint;
	}

}
