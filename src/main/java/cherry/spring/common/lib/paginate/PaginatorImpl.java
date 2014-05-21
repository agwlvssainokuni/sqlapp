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

import java.util.ArrayList;
import java.util.List;

/**
 * ページネーション機能。
 */
public class PaginatorImpl implements Paginator {

	/** ページネーションリンクとして並べるページ番号の範囲の算出方法を定義する。 */
	private PaginateStrategy paginateStrategy;

	/**
	 * ページネーションリンクとして並べるページ番号の範囲の算出方法を設定する。
	 * 
	 * @param paginateStrategy
	 *            ページネーションリンクとして並べるページ番号の範囲の算出方法。
	 */
	public void setPaginateStrategy(PaginateStrategy paginateStrategy) {
		this.paginateStrategy = paginateStrategy;
	}

	/**
	 * ページ数を計算する。
	 * 
	 * @param itemCount
	 *            項目数。
	 * @param pageSize
	 *            ページサイズ。
	 * @return ページ数。
	 */
	@Override
	public int getPageCount(int itemCount, int pageSize) {
		if (itemCount % pageSize == 0) {
			return itemCount / pageSize;
		} else {
			return itemCount / pageSize + 1;
		}
	}

	/**
	 * ページ情報を取得する。
	 * 
	 * @param pageNo
	 *            ページ番号。
	 * @param itemCount
	 *            項目数。
	 * @param pageSize
	 *            ページサイズ。
	 * @return ページ情報。
	 */
	@Override
	public Page getPage(int pageNo, int itemCount, int pageSize) {

		if (itemCount <= 0) {
			Page page = new Page();
			page.setNo(0);
			page.setCount(0);
			page.setFrom(0);
			page.setTo(-1);
			return page;
		}

		int pageCount = getPageCount(itemCount, pageSize);
		int adjusted = adjustPageNo(pageNo, pageCount);
		return createPage(adjusted, pageCount, itemCount, pageSize);
	}

	/**
	 * ページネーションの処理を実行する。
	 * 
	 * @param pageNo
	 *            ページ番号。
	 * @param itemCount
	 *            項目数。
	 * @param pageSize
	 *            ページサイズ。
	 * @return ページネーションの処理結果。
	 */
	@Override
	public PageSet paginate(int pageNo, int itemCount, int pageSize) {

		int pageCount = getPageCount(itemCount, pageSize);
		int curNo = adjustPageNo(pageNo, pageCount);
		int prevNo = adjustPageNo(curNo - 1, pageCount);
		int nextNo = adjustPageNo(curNo + 1, pageCount);
		int firstNo = adjustPageNo(0, pageCount);
		int lastNo = adjustPageNo(pageCount - 1, pageCount);

		PageSet pageSet = new PageSet();
		List<Page> list = new ArrayList<>();
		for (Integer no : paginateStrategy.calculate(curNo, pageCount)) {

			Page page = createPage(no, pageCount, itemCount, pageSize);
			list.add(page);

			if (no == curNo) {
				pageSet.setCurrent(page);
			}
			if (no == prevNo) {
				pageSet.setPrev(page);
			}
			if (no == nextNo) {
				pageSet.setNext(page);
			}
			if (no == firstNo) {
				pageSet.setFirst(page);
			}
			if (no == lastNo) {
				pageSet.setLast(page);
			}
		}
		pageSet.setRange(list);

		if (pageSet.getCurrent() == null) {
			pageSet.setCurrent(createPage(curNo, pageCount, itemCount, pageSize));
		}
		if (pageSet.getPrev() == null) {
			pageSet.setPrev(createPage(prevNo, pageCount, itemCount, pageSize));
		}
		if (pageSet.getNext() == null) {
			pageSet.setNext(createPage(nextNo, pageCount, itemCount, pageSize));
		}
		if (pageSet.getFirst() == null) {
			pageSet.setFirst(createPage(firstNo, pageCount, itemCount, pageSize));
		}
		if (pageSet.getLast() == null) {
			pageSet.setLast(createPage(lastNo, pageCount, itemCount, pageSize));
		}

		return pageSet;
	}

	/**
	 * ページ番号を適正な範囲内に補正する。
	 * 
	 * @param pageNo
	 *            ページ番号。
	 * @param pageCount
	 *            ページ数。
	 * @return 補正したページ番号。
	 */
	private int adjustPageNo(int pageNo, int pageCount) {
		int adjusted = pageNo;
		if (adjusted >= pageCount) {
			adjusted = pageCount - 1;
		}
		if (adjusted < 0) {
			adjusted = 0;
		}
		return adjusted;
	}

	/**
	 * ページ情報を作成する。
	 * 
	 * @param pageNo
	 *            ページ番号。
	 * @param pageCount
	 *            ページ数。
	 * @param itemCount
	 *            項目数。
	 * @param pageSize
	 *            ページサイズ。
	 * @return ページ情報。
	 */
	private Page createPage(int pageNo, int pageCount, int itemCount,
			int pageSize) {
		Page page = new Page();
		page.setNo(pageNo);
		if (itemCount <= 0) {
			page.setCount(0);
		} else if (pageNo < pageCount - 1) {
			page.setCount(pageSize);
		} else if (itemCount % pageSize == 0) {
			page.setCount(pageSize);
		} else {
			page.setCount(itemCount % pageSize);
		}
		page.setFrom(pageSize * pageNo);
		page.setTo(page.getFrom() + page.getCount() - 1);
		return page;
	}

}
