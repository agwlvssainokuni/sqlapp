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

import java.util.Iterator;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * ページネーションリンクとして並べるページ番号の範囲。連続したページ番号を持つ。
 */
public class Range implements Iterable<Long> {

	/** 開始ページ番号。 */
	private final long from;

	/** 終了ページ番号。 */
	private final long to;

	public Range(long from, long to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public Iterator<Long> iterator() {
		return new Iterator<Long>() {

			private long current = from - 1L;

			@Override
			public boolean hasNext() {
				return current < to;
			}

			@Override
			public Long next() {
				if (current >= to) {
					throw new IllegalStateException();
				}
				current += 1L;
				return current;
			}

			@Override
			public void remove() {
				// 何もしない。
			}
		};
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
