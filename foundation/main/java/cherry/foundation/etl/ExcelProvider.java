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

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_UNDERSCORE;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import cherry.goods.excel.ExcelReader;

/**
 * データ取込み機能でMicrosoft Excelファイルをデータ取得元とする。
 */
public class ExcelProvider implements Provider {

	/** Excelファイル読込み機能。 */
	private ExcelReader reader;

	/** ヘッダが存在するか否か。 */
	private boolean withHeader;

	/** フィールド名。 */
	private String[] fieldName;

	/**
	 * Excelファイル取込み機能を生成する。
	 * 
	 * @param reader データ読取り元。
	 * @param withHeader ヘッダが存在するか否か。
	 */
	public ExcelProvider(ExcelReader reader, boolean withHeader) {
		this.reader = reader;
		this.withHeader = withHeader;
	}

	/**
	 * データの取得を開始する。
	 */
	@Override
	public void begin() {

		if (!withHeader) {
			return;
		}

		String[] header = reader.read();
		if (header == null) {
			return;
		}

		fieldName = new String[header.length];
		for (int i = 0; i < header.length; i++) {
			fieldName[i] = UPPER_UNDERSCORE.to(LOWER_CAMEL, header[i]);
		}
	}

	/**
	 * 1レコードのデータを取得する。
	 * 
	 * @return 1レコードのデータ。データが存在しない場合はnull。
	 */
	@Override
	public Map<String, ?> provide() {

		String[] record = reader.read();
		if (record == null) {
			return null;
		}

		Map<String, String> recordData = new LinkedHashMap<String, String>(record.length);
		for (int i = 0; i < record.length; i++) {
			if (fieldName == null) {
				recordData.put("field" + i, record[i]);
			} else {
				recordData.put(fieldName[i], record[i]);
			}
		}
		return recordData;
	}

	/**
	 * エータの取得を終了する。
	 * 
	 * @throws IOException データ取得エラー。
	 */
	@Override
	public void end() throws IOException {
		reader.close();
	}

}
