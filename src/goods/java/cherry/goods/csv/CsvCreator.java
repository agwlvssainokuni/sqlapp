/*
 * Copyright 2011,2015 agwlvssainokuni
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

package cherry.goods.csv;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;

/**
 * CSVデータ生成機能。<br />
 */
public class CsvCreator implements Closeable {

	/** データ書込み先。 */
	private final Writer writer;

	/** レコードセパレータ。 */
	private final String recordSeparator;

	/**
	 * CSVデータ生成機能を作成する。
	 * 
	 * @param w データ書込み先。
	 */
	public CsvCreator(Writer w) {
		this(w, "\n");
	}

	/**
	 * CSVデータ生成機能を作成する。
	 * 
	 * @param w データ書込み先。
	 * @param rs レコードセパレータ。
	 */
	public CsvCreator(Writer w, String rs) {
		writer = w;
		recordSeparator = rs;
	}

	/**
	 * CSVレコード書込み。<br />
	 * データ書込み先にCSVデータを1レコード書込む。
	 * 
	 * @param record CSVデータの1レコード。
	 * @throws IOException データ書込みエラー。
	 */
	public void write(String[] record) throws IOException {

		if (record == null || record.length <= 0) {
			return;
		}

		for (int i = 0; i < record.length; i++) {
			if (i > 0) {
				writer.write(',');
			}
			writeField(writer, record[i]);
		}

		writer.write(recordSeparator);
	}

	/**
	 * データ書き込み先をフラッシュする。<br />
	 * 
	 * @throws IOException データ書込み先のフラッシュエラー。
	 */
	public void flush() throws IOException {
		writer.flush();
	}

	/**
	 * データ書込み先をクローズする。<br />
	 * 
	 * @throws IOException データ書込み先のクローズエラー。
	 */
	@Override
	public void close() throws IOException {
		writer.close();
	}

	/**
	 * フィールドデータを書込む。<br />
	 * 
	 * @param writer データ書込み先。
	 * @param field フィールドデータ。
	 * @throws IOException データ書込みエラー。
	 */
	private void writeField(Writer writer, String field) throws IOException {

		if (field == null) {
			return;
		}

		writer.write('"');
		for (char ch : field.toCharArray()) {
			if (ch == '"') {
				writer.write('"');
			}
			writer.write(ch);
		}
		writer.write('"');
	}

}
