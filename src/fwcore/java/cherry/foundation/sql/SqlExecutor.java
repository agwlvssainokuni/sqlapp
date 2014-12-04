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

package cherry.foundation.sql;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.core.io.Resource;

/**
 * SQL実行機能。
 */
public interface SqlExecutor {

	/**
	 * SQLを実行する。
	 * 
	 * @param dataSource
	 *            データソース。
	 * @param resource
	 *            SQL文の読込み元。
	 * @param paramMap
	 *            SQLに受渡すパラメタ。
	 * @param continueOnError
	 *            SQL実行エラーで継続するか否か。
	 * @throws IOException
	 *             SQL文の読込みでエラー。
	 */
	void execute(DataSource dataSource, Resource resource,
			Map<String, ?> paramMap, boolean continueOnError)
			throws IOException;

	/**
	 * SQLを実行する。
	 * 
	 * @param dataSource
	 *            データソース。
	 * @param in
	 *            SQL文の読込み元。
	 * @param paramMap
	 *            SQLに受渡すパラメタ。
	 * @param continueOnError
	 *            SQL実行エラーで継続するか否か。
	 * @throws IOException
	 *             SQL文の読込みでエラー。
	 */
	void execute(DataSource dataSource, InputStream in,
			Map<String, ?> paramMap, boolean continueOnError)
			throws IOException;

	/**
	 * SQLを実行する。
	 * 
	 * @param dataSource
	 *            データソース。
	 * @param reader
	 *            SQL文の読込み元。
	 * @param paramMap
	 *            SQLに受渡すパラメタ。
	 * @param continueOnError
	 *            SQL実行エラーで継続するか否か。
	 * @throws IOException
	 *             SQL文の読込みでエラー。
	 */
	void execute(DataSource dataSource, Reader reader, Map<String, ?> paramMap,
			boolean continueOnError) throws IOException;

}
