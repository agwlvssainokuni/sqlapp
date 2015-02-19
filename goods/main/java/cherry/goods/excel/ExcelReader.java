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

package cherry.goods.excel;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

import java.io.Closeable;
import java.math.BigDecimal;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Microsoft Excelファイル読込み機能。<br />
 */
public class ExcelReader implements Closeable {

	/** 読込み元のMicrosoft Excelブック。 */
	private final Workbook workbook;

	/** 読込み対象のシート。 */
	private Sheet currentSheet;

	/** 読込み対象の行。 */
	private Iterator<Row> rowIterator;

	/**
	 * Microsoft Excelファイル読込み機能を生成する。<br />
	 * アクティブなシートを読込み対象とする。読込み対象のシートを切替える場合は{@link #setCurrentSheet(int)}でシート番号を指定する。
	 * 
	 * @param workbook 読込み元のMicrosoft Excelブック。
	 */
	public ExcelReader(Workbook workbook) {
		this.workbook = workbook;
		setCurrentSheet(this.workbook.getActiveSheetIndex());
	}

	/**
	 * @return 読込み元のMicrosoft Excelブックのシートの数。
	 */
	public int getNumberOfSheets() {
		return workbook.getNumberOfSheets();
	}

	/**
	 * @param sheetIndex シート番号。
	 * @return 指定されたシート番号の名前。
	 */
	public String getSheetName(int sheetIndex) {
		return workbook.getSheetName(sheetIndex);
	}

	/**
	 * 読込み対象のシートを切替える。<br />
	 * 
	 * @param sheetIndex 読込み対象とするシートのシート番号。
	 */
	public void setCurrentSheet(int sheetIndex) {
		currentSheet = workbook.getSheetAt(sheetIndex);
		rowIterator = currentSheet.rowIterator();
	}

	/**
	 * レコード読込み。<br />
	 * 読込み対象のシートから1レコード(1行)読込む。
	 * 
	 * @return 1レコード(1行)。
	 */
	public String[] read() {
		if (!rowIterator.hasNext()) {
			return null;
		}
		Row row = rowIterator.next();
		int lastCellNum = row.getLastCellNum();
		if (lastCellNum < 0) {
			return new String[0];
		}
		String[] record = new String[lastCellNum];
		for (Cell cell : row) {
			record[cell.getColumnIndex()] = getCellValueAsString(cell);
		}
		return record;
	}

	/**
	 * Microsoft Excelファイル読込み機能を閉じる。<br />
	 * 具体的には、何もしない。
	 */
	@Override
	public void close() {
		// NOTHING
	}

	/**
	 * セルの値を文字列として取得する。<br />
	 * 
	 * @param cell 取得対象のセル。
	 * @return セルの値。
	 */
	private String getCellValueAsString(Cell cell) {
		switch (cell.getCellType()) {
		case CELL_TYPE_NUMERIC:
			return getNumericCellValueAsString(cell);
		case CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case CELL_TYPE_FORMULA:
			switch (cell.getCachedFormulaResultType()) {
			case CELL_TYPE_NUMERIC:
				return getNumericCellValueAsString(cell);
			case CELL_TYPE_STRING:
				return cell.getStringCellValue();
			case CELL_TYPE_BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());
			default:
				return null;
			}
		case CELL_TYPE_BLANK:
			return "";
		case CELL_TYPE_BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		default:
			return null;
		}
	}

	/**
	 * 数値セルの値を文字列として取得する。
	 * 
	 * @param cell 取得対象のセル。
	 * @return セルの値。
	 */
	private String getNumericCellValueAsString(Cell cell) {
		BigDecimal value = BigDecimal.valueOf(cell.getNumericCellValue());
		try {
			return value.toBigIntegerExact().toString();
		} catch (ArithmeticException ex) {
			return value.toPlainString();
		}
	}

}
