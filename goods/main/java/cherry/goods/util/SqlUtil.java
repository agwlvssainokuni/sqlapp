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

package cherry.goods.util;

/**
 * SQL関連操作ユーティリティ。<br />
 * 下記の機能を提供する。
 * <ul>
 * <li>LIKE条件用エスケープ ({@link #escapeForLike(String)})</li>
 * </ul>
 */
public class SqlUtil {

	public static String escapeForLike(String text) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			switch (ch) {
			case '%':
				builder.append("\\%");
				break;
			case '_':
				builder.append("\\_");
				break;
			case '\\':
				builder.append("\\\\");
				break;
			default:
				builder.append(ch);
				break;
			}
		}
		return builder.toString();
	}

}
