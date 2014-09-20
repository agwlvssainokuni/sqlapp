/*
 * Copyright 2004,2014 agwlvssainokuni
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

package cherry.spring.common.lib.paginate;

/**
 * ページネーションリンクとして並べるページ番号の範囲を算出する。現在のページの前後それぞれにできるだけ表示範囲を広げる。
 */
public class EachSideStrategy implements PaginateStrategy {

	/** 下位に表示するページ数を保持する。 */
	private int lowerSide;

	/** 上位に表示するページ数を保持する。 */
	private int upperSide;

	/** ページ番号の下限設定を保持する。「0 + 設定値」以上に調整する */
	private int lowerTrim = 0;

	/** ページ番号の上限設定を保持する。「最終ページ番号 - 設定値」以下に調整する。 */
	private int upperTrim = 0;

	public void setLowerSide(int lowerSide) {
		this.lowerSide = lowerSide;
	}

	public void setUpperSide(int upperSide) {
		this.upperSide = upperSide;
	}

	public void setLowerTrim(int lowerTrim) {
		this.lowerTrim = lowerTrim;
	}

	public void setUpperTrim(int upperTrim) {
		this.upperTrim = upperTrim;
	}

	/**
	 * ページネーションリンクとして並べるページ番号の範囲を算出する。現在のページの前後それぞれにできるだけ表示範囲を広げる。
	 * 
	 * @param pageNo
	 *            ページ番号。
	 * @param pageCount
	 *            ページ数。
	 * @return ページ番号の範囲。
	 */
	@Override
	public Iterable<Integer> calculate(int pageNo, int pageCount) {
		int from = pageNo - lowerSide;
		if (from <= lowerTrim) {
			from = lowerTrim;
		}
		int to = pageNo + upperSide;
		if (to >= (pageCount - 1) - upperTrim) {
			to = (pageCount - 1) - upperTrim;
		}
		return new Range(from, to);
	}

}
