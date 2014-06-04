/*
 * Copyright 2012,2014 agwlvssainokuni
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

package cherry.spring.common.lib.etl;

import java.io.IOException;
import java.io.Reader;

/**
 * SQL操作ユーティリティ。
 */
public class SqlUtil {

	/** SQL文の切出しで使用する解析状態値。 */
	enum State {
		DEFAULT, QUOTED, HYPHEN, COMMENT
	}

	/**
	 * SQL文を切出す。
	 * 
	 * @param reader
	 *            SQL文の読込み元。
	 * @return 切出したSQL文。
	 * @throws IOException
	 *             SQL文の読込みエラー。
	 */
	public static String nextSql(Reader reader) throws IOException {

		StringBuilder builder = new StringBuilder();

		int ch;
		State state = State.DEFAULT;
		while ((ch = reader.read()) >= 0) {
			if (state == State.QUOTED) {
				if (ch == (int) '\'') {
					state = State.DEFAULT;
				}
				builder.append((char) ch);
			} else if (state == State.HYPHEN) {
				if (ch == (int) '-') {
					state = State.COMMENT;
				} else {
					state = State.DEFAULT;
					builder.append('-').append((char) ch);
				}
			} else if (state == State.COMMENT) {
				if (ch == (int) '\r') {
					builder.append((char) ch);
				}
				if (ch == (int) '\n') {
					state = State.DEFAULT;
					builder.append((char) ch);
				}
			} else {
				if (ch == (int) ';') {
					break;
				} else if (ch == (int) '\'') {
					state = State.QUOTED;
					builder.append((char) ch);
				} else if (ch == (int) '-') {
					state = State.HYPHEN;
				} else {
					builder.append((char) ch);
				}
			}
		}

		if (state == State.HYPHEN) {
			builder.append('-');
		}

		if (builder.length() <= 0) {
			return null;
		}

		return builder.toString();
	}

}
