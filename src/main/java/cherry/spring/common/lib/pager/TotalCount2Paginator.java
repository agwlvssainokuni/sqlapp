/*
 *   Copyright 2004,2014 agwlvssainokuni
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package cherry.spring.common.lib.pager;

/**
 * ページネーション機能を実現する。ページリンクとして表示するトータルの数を一定にする。
 */
public class TotalCount2Paginator extends Paginator {

	/** 全ページ数を保持する。 */
	private int totalCount;

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * ページの表示範囲を算出する。ページリンクとして表示するトータルの数を一定にする。
	 * 
	 * @param pageNo
	 *            現在のページ番号。
	 * @param pageCount
	 *            全ページ数。
	 * @return ページの表示範囲。
	 */
	@Override
	protected Range calcRange(int pageNo, int pageCount) {
		int from = pageNo - (totalCount - 1) / 2;
		if (from < 1) {
			from = 1;
		}
		int to = from + totalCount - 1;
		if (to > pageCount - 2) {
			to = pageCount - 2;
			from = to - totalCount + 1;
			if (from < 1) {
				from = 1;
			}
		}
		return new Range(from, to);
	}

}
