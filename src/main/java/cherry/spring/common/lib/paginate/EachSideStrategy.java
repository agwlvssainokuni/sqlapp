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
public class EachSideStrategy implements PageNumberStrategy {

	/** 下位に表示するページ数を保持する。 */
	private int lowerSide;

	/** 上位に表示するページ数を保持する。 */
	private int upperSide;

	public void setLowerSide(int lowerSide) {
		this.lowerSide = lowerSide;
	}

	public void setUpperSide(int upperSide) {
		this.upperSide = upperSide;
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
		if (from < 0) {
			from = 0;
		}
		int to = pageNo + upperSide;
		if (to >= pageCount) {
			to = pageCount - 1;
		}
		return new Range(from, to);
	}

}
