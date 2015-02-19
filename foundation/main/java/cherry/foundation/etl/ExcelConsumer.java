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

package cherry.foundation.etl;

import java.io.IOException;

import cherry.goods.excel.ExcelWriter;

/**
 * データ抽出機能でMicrosoft Excelファイルをデータ格納先とする。
 */
public class ExcelConsumer implements Consumer {

	/** Excelファイル書込み機能。 */
	private ExcelWriter writer;

	/** ヘッダを出力するか否か。 */
	private boolean withHeader;

	/**
	 * Excelファイル格納機能を生成する。
	 * 
	 * @param writer データ書込み先。
	 * @param withHeader ヘッダを出力するか否か。
	 */
	public ExcelConsumer(ExcelWriter writer, boolean withHeader) {
		this.writer = writer;
		this.withHeader = withHeader;
	}

	/**
	 * データの格納を開始する。
	 * 
	 * @param col カラムの情報。
	 */
	@Override
	public void begin(Column[] col) {

		if (!withHeader) {
			return;
		}

		String[] list = new String[col.length];
		for (int i = 0; i < col.length; i++) {
			list[i] = col[i].getLabel();
		}

		writer.write(list);
	}

	/**
	 * 1レコードのデータを格納する。
	 * 
	 * @param record 1レコードのデータ。
	 */
	@Override
	public void consume(Object[] record) {

		String[] list = new String[record.length];
		for (int i = 0; i < record.length; i++) {
			if (record[i] == null) {
				list[i] = null;
			} else {
				list[i] = record[i].toString();
			}
		}

		writer.write(list);
	}

	/**
	 * データの格納を終了する。
	 * 
	 * @throws IOException データ格納エラー。
	 */
	@Override
	public void end() throws IOException {
		writer.close();
	}

}
