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

/**
 * ページネーション機能。
 */
public interface Paginator {

	/**
	 * ページ数を計算する。
	 * 
	 * @param itemCount 項目数。
	 * @param pageSize ページサイズ。
	 * @return ページ数。
	 */
	long getPageCount(long itemCount, long pageSize);

	/**
	 * ページ情報を取得する。
	 * 
	 * @param pageNo ページ番号。
	 * @param itemCount 項目数。
	 * @param pageSize ページサイズ。
	 * @return ページ情報。
	 */
	Page getPage(long pageNo, long itemCount, long pageSize);

	/**
	 * ページネーション情報を取得する。
	 * 
	 * @param pageNo ページ番号。
	 * @param itemCount 項目数。
	 * @param pageSize ページサイズ。
	 * @return ページネーション情報。
	 */
	PageSet paginate(long pageNo, long itemCount, long pageSize);

}
