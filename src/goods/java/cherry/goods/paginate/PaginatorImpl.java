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
	public long getPageCount(long itemCount, long pageSize) {
		if (itemCount % pageSize == 0L) {
			return itemCount / pageSize;
		} else {
			return itemCount / pageSize + 1L;
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
	public Page getPage(long pageNo, long itemCount, long pageSize) {

		if (itemCount <= 0L) {
			Page page = new Page();
			page.setNo(0L);
			page.setCount(0L);
			page.setFrom(0L);
			page.setTo(-1L);
			return page;
		}

		long pageCount = getPageCount(itemCount, pageSize);
		long adjusted = adjustPageNo(pageNo, pageCount);
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
	public PageSet paginate(long pageNo, long itemCount, long pageSize) {

		long pageCount = getPageCount(itemCount, pageSize);
		long curNo = adjustPageNo(pageNo, pageCount);
		long prevNo = adjustPageNo(curNo - 1L, pageCount);
		long nextNo = adjustPageNo(curNo + 1L, pageCount);
		long firstNo = adjustPageNo(0L, pageCount);
		long lastNo = adjustPageNo(pageCount - 1L, pageCount);

		PageSet pageSet = new PageSet();
		pageSet.setTotalCount(itemCount);
		pageSet.setPageSz(pageSize);
		List<Page> list = new ArrayList<>();
		for (Long no : paginateStrategy.calculate(curNo, pageCount)) {

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
	private long adjustPageNo(long pageNo, long pageCount) {
		long adjusted = pageNo;
		if (adjusted >= pageCount) {
			adjusted = pageCount - 1L;
		}
		if (adjusted < 0L) {
			adjusted = 0L;
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
	private Page createPage(long pageNo, long pageCount, long itemCount,
			long pageSize) {
		Page page = new Page();
		page.setNo(pageNo);
		if (itemCount <= 0L) {
			page.setCount(0L);
		} else if (pageNo < pageCount - 1L) {
			page.setCount(pageSize);
		} else if (itemCount % pageSize == 0L) {
			page.setCount(pageSize);
		} else {
			page.setCount(itemCount % pageSize);
		}
		page.setFrom(pageSize * pageNo);
		page.setTo(page.getFrom() + page.getCount() - 1L);
		return page;
	}

}
