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

package cherry.goods.paginate;

/**
 * ページネーションリンクとして並べるページ番号の範囲を算出する。現在のページの前後それぞれにできるだけ表示範囲を広げる。
 */
public class EachSideStrategy implements PaginateStrategy {

	/** 下位に表示するページ数を保持する。 */
	private long lowerSide;

	/** 上位に表示するページ数を保持する。 */
	private long upperSide;

	/** ページ番号の下限設定を保持する。「0 + 設定値」以上に調整する */
	private long lowerTrim = 0L;

	/** ページ番号の上限設定を保持する。「最終ページ番号 - 設定値」以下に調整する。 */
	private long upperTrim = 0L;

	public void setLowerSide(long lowerSide) {
		this.lowerSide = lowerSide;
	}

	public void setUpperSide(long upperSide) {
		this.upperSide = upperSide;
	}

	public void setLowerTrim(long lowerTrim) {
		this.lowerTrim = lowerTrim;
	}

	public void setUpperTrim(long upperTrim) {
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
	public Iterable<Long> calculate(long pageNo, long pageCount) {
		long from = pageNo - lowerSide;
		if (from <= lowerTrim) {
			from = lowerTrim;
		}
		long to = pageNo + upperSide;
		if (to >= (pageCount - 1L) - upperTrim) {
			to = (pageCount - 1L) - upperTrim;
		}
		return new Range(from, to);
	}

}
