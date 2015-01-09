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

package cherry.goods.paginate;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * ページネーション機能において、画面上に表示するページリンクの情報と一覧データの情報を合わせて保持する。
 */
public class PagedList<T> implements Serializable {

	/** シリアルバージョンID。 */
	private static final long serialVersionUID = 1L;

	/** ページリンクの情報。 */
	private PageSet pageSet;

	/** 一覧データの情報。 */
	private List<T> list;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public PageSet getPageSet() {
		return pageSet;
	}

	public void setPageSet(PageSet pageSet) {
		this.pageSet = pageSet;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
