/*
 * Copyright 2004,2015 agwlvssainokuni
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

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * ページネーション機能において、画面上に表示するページリンクの全体を表す。
 */
public class PageSet implements Serializable {

	/** シリアルバージョンID。 */
	private static final long serialVersionUID = 1L;

	/** 全件数。 */
	private long totalCount = 0L;

	/** ページ件数。 */
	private long pageSz = 0L;

	/** 現ページ。 */
	private Page current = null;

	/** 前ページ。 */
	private Page prev = null;

	/** 次ページ。 */
	private Page next = null;

	/** 先頭ページ。 */
	private Page first = null;

	/** 最終ページ。 */
	private Page last = null;

	/** 画面上に表示するページリンク。 */
	private List<Page> range = null;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getPageSz() {
		return pageSz;
	}

	public void setPageSz(long pageSz) {
		this.pageSz = pageSz;
	}

	public Page getCurrent() {
		return current;
	}

	public void setCurrent(Page current) {
		this.current = current;
	}

	public Page getPrev() {
		return prev;
	}

	public void setPrev(Page prev) {
		this.prev = prev;
	}

	public Page getNext() {
		return next;
	}

	public void setNext(Page next) {
		this.next = next;
	}

	public Page getFirst() {
		return first;
	}

	public void setFirst(Page first) {
		this.first = first;
	}

	public Page getLast() {
		return last;
	}

	public void setLast(Page last) {
		this.last = last;
	}

	public List<Page> getRange() {
		return range;
	}

	public void setRange(List<Page> range) {
		this.range = range;
	}

}
