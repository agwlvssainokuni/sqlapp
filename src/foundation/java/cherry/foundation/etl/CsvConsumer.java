/*
 * Copyright 2012,2015 agwlvssainokuni
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

package cherry.foundation.etl;

import java.io.IOException;
import java.io.Writer;

import cherry.goods.csv.CsvCreator;

/**
 * データ抽出機能でCSVファイルをデータ格納先とする。
 */
public class CsvConsumer implements Consumer {

	/** CSVデータ生成機能。 */
	private CsvCreator creator;

	/** ヘッダを出力するか否か。 */
	private boolean withHeader;

	/**
	 * CSVデータ格納機能を生成する。
	 * 
	 * @param writer データ書込み先。
	 * @param withHeader ヘッダを出力するか否か。
	 */
	public CsvConsumer(Writer writer, boolean withHeader) {
		this(writer, "\r\n", withHeader);
	}

	/**
	 * CSVデータ格納機能を生成する。
	 * 
	 * @param writer データ書込み先。
	 * @param recordSeparator レコードセパレータ。
	 * @param withHeader ヘッダを出力するか否か。
	 */
	public CsvConsumer(Writer writer, String recordSeparator, boolean withHeader) {
		this.creator = new CsvCreator(writer, recordSeparator);
		this.withHeader = withHeader;
	}

	/**
	 * データの格納を開始する。
	 * 
	 * @param col カラムの情報。
	 * @throws IOException データ格納エラー。
	 */
	@Override
	public void begin(Column[] col) throws IOException {

		if (!withHeader) {
			return;
		}

		String[] list = new String[col.length];
		for (int i = 0; i < col.length; i++) {
			list[i] = col[i].getLabel();
		}

		creator.write(list);
	}

	/**
	 * 1レコードのデータを格納する。
	 * 
	 * @param record 1レコードのデータ。
	 * @throws IOException データ格納エラー。
	 */
	@Override
	public void consume(Object[] record) throws IOException {

		String[] list = new String[record.length];
		for (int i = 0; i < record.length; i++) {
			if (record[i] == null) {
				list[i] = null;
			} else {
				list[i] = record[i].toString();
			}
		}

		creator.write(list);
	}

	/**
	 * データの格納を終了する。
	 * 
	 * @throws IOException データ格納エラー。
	 */
	@Override
	public void end() throws IOException {
		creator.close();
	}

}
