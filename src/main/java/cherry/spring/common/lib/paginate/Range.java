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

import java.util.Iterator;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * ページネーションリンクとして並べるページ番号の範囲。連続したページ番号を持つ。
 */
public class Range implements Iterable<Integer> {

	/** 開始ページ番号。 */
	private final int from;

	/** 終了ページ番号。 */
	private final int to;

	public Range(int from, int to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {

			private int current = from - 1;

			@Override
			public boolean hasNext() {
				return current < to;
			}

			@Override
			public Integer next() {
				if (current >= to) {
					throw new IllegalStateException();
				}
				current += 1;
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
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
