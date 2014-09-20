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
 * ページネーションリンクとして並べるページ番号の範囲を算出する。ページリンクとして表示するページ番号の総数を一定にする。
 */
public class TotalCountStrategy implements PaginateStrategy {

	/** ページ番号の総数を保持する。 */
	private int totalCount;

	/** 下位に表示するページ数のヒントを保持する。 */
	private int lowerSideHint;

	/** ページ番号の下限設定を保持する。「0 + 設定値」以上に調整する */
	private int lowerTrim = 0;

	/** ページ番号の上限設定を保持する。「最終ページ番号 - 設定値」以下に調整する。 */
	private int upperTrim = 0;

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void setLowerSideHint(int lowerSideHint) {
		this.lowerSideHint = lowerSideHint;
	}

	public void setLowerTrim(int lowerTrim) {
		this.lowerTrim = lowerTrim;
	}

	public void setUpperTrim(int upperTrim) {
		this.upperTrim = upperTrim;
	}

	/**
	 * ページネーションリンクとして並べるページ番号の範囲を算出する。ページリンクとして表示するページ番号の総数を一定にする。
	 * 
	 * @param pageNo
	 *            ページ番号。
	 * @param pageCount
	 *            ページ数。
	 * @return ページ番号の範囲。
	 */
	@Override
	public Iterable<Integer> calculate(int pageNo, int pageCount) {
		int from = pageNo - lowerSideHint;
		if (from <= lowerTrim) {
			from = lowerTrim;
		}
		int to = from + (totalCount - 1);
		if (to >= (pageCount - 1) - upperTrim) {
			to = (pageCount - 1) - upperTrim;
			from = to - (totalCount - 1);
			if (from <= lowerTrim) {
				from = lowerTrim;
			}
		}
		return new Range(from, to);
	}

}
