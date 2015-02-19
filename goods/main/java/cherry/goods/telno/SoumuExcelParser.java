/*
 * Copyright 2015 agwlvssainokuni
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

package cherry.goods.telno;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 電話番号正規化機能。<br />
 * <a href="http://www.soumu.go.jp/main_sosiki/joho_tsusin/top/tel_number/number_shitei.html">総務省のサイト</a>
 * にて公開されている固定電話の局番割当ファイル (Excel形式) を読込む。
 */
public class SoumuExcelParser {

	/** [DI]「局番 (6桁)」列の列名。 */
	private String numberLabel = "番号";

	/** [DI]「市外局番」列の列名。 */
	private String areaCodeLabel = "市外局番";

	/** [DI]「市内局番」列の列名。 */
	private String localCodeLabel = "市内局番";

	public void setNumberLabel(String numberLabel) {
		this.numberLabel = numberLabel;
	}

	public void setAreaCodeLabel(String areaCodeLabel) {
		this.areaCodeLabel = areaCodeLabel;
	}

	public void setLocalCodeLabel(String localCodeLabel) {
		this.localCodeLabel = localCodeLabel;
	}

	/**
	 * 固定電話の局番割当ファイルを読込む。
	 * 
	 * @param in 局番割当ファイルのストリーム。
	 * @return 読込み結果を保持する。キーは局番 (6桁)、値は市外局番と市内局番の組み。「
	 * @throws InvalidFormatException 局番割当ファイルの形式不正。
	 * @throws IOException 局番割当ファイルの読込み異常。
	 */
	public Map<String, Pair<String, String>> parse(InputStream in) throws InvalidFormatException, IOException {
		Map<String, Pair<String, String>> map = new LinkedHashMap<>();
		try (Workbook workbook = WorkbookFactory.create(in)) {
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				Sheet sheet = workbook.getSheetAt(i);
				Integer numberCol = null;
				Integer areaCodeCol = null;
				Integer localCodeCol = null;
				boolean preparing = true;
				for (Row row : sheet) {
					if (preparing) {
						for (Cell cell : row) {
							String value = cell.getStringCellValue();
							if (numberLabel.equals(value)) {
								numberCol = cell.getColumnIndex();
							}
							if (areaCodeLabel.equals(value)) {
								areaCodeCol = cell.getColumnIndex();
							}
							if (localCodeLabel.equals(value)) {
								localCodeCol = cell.getColumnIndex();
							}
						}
						if (numberCol != null && areaCodeCol != null && localCodeCol != null) {
							preparing = false;
						}
					} else {
						String number = getCellValue(row, numberCol.intValue());
						String areaCode = getCellValue(row, areaCodeCol.intValue());
						String localCode = getCellValue(row, localCodeCol.intValue());
						if (isNotEmpty(number) && isNotEmpty(areaCode) && isNotEmpty(localCode)) {
							map.put(number, Pair.of(areaCode, localCode));
						}
					}
				}
			}
		}
		return map;
	}

	/**
	 * セルの値 (文字列) を取得する。
	 * 
	 * @param row 行。
	 * @param col 列番号。
	 * @return セルの値 (文字列)。
	 */
	private String getCellValue(Row row, int col) {
		Cell cell = row.getCell(col);
		if (cell == null) {
			return null;
		}
		return cell.getStringCellValue();
	}

}
