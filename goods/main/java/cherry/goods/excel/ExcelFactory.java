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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Microsoft Excelファイル入出力機能。<br />
 */
public class ExcelFactory {

	/**
	 * Excelブックを開く。<br />
	 * 読込み向けに使用することを想定する。
	 * 
	 * @param in 読込み元。
	 * @return Excelブック。
	 * @throws InvalidFormatException 形式異常。
	 * @throws IOException 読込み異常。
	 */
	public static Workbook open(InputStream in) throws InvalidFormatException, IOException {
		return WorkbookFactory.create(in);
	}

	/**
	 * Excelブックを読込む。<br />
	 * ブック作成のテンプレートとして使用することを想定する。
	 * 
	 * @param file 読込み元。
	 * @return Excelブック。
	 * @throws InvalidFormatException 形式異常。
	 * @throws FileNotFoundException 読込み元が存在しない。
	 * @throws IOException 読込み異常。
	 */
	public static Workbook load(File file) throws InvalidFormatException, FileNotFoundException, IOException {
		try (InputStream in = new FileInputStream(file)) {
			return WorkbookFactory.create(in);
		}
	}

	/**
	 * Excelブック (.xls) を新規作成する。<br />
	 * 
	 * @return 作成したExcelブック。
	 */
	public static Workbook createBlankXls() {
		return createSheet(new HSSFWorkbook());
	}

	/**
	 * Excelブック (.xls) を新規作成する。<br />
	 * 
	 * @param sheetname 作成するシートの名前。
	 * @return 作成したExcelブック。
	 */
	public static Workbook createBlankXls(String sheetname) {
		return createSheet(new HSSFWorkbook(), sheetname);
	}

	/**
	 * Excelブック (.xlsx) を新規作成する。<br />
	 * 
	 * @return 作成したExcelブック。
	 */
	public static Workbook createBlankXlsx() {
		return createSheet(new XSSFWorkbook());
	}

	/**
	 * Excelブック (.xlsx) を新規作成する。<br />
	 * 
	 * @param sheetname 作成するシートの名前。
	 * @return 作成したExcelブック。
	 */
	public static Workbook createBlankXlsx(String sheetname) {
		return createSheet(new XSSFWorkbook(), sheetname);
	}

	/**
	 * シートを新規作成する。<br />
	 * 
	 * @param workbook シートを作成するExcelブック。
	 * @return シートを作成したExcelブック。
	 */
	private static Workbook createSheet(Workbook workbook) {
		workbook.createSheet();
		return workbook;
	}

	/**
	 * シートを新規作成する。<br />
	 * 
	 * @param workbook シートを作成するExcelブック。
	 * @param sheetname 作成するシートの名前。
	 * @return シートを作成したExcelブック。
	 */
	private static Workbook createSheet(Workbook workbook, String sheetname) {
		workbook.createSheet(sheetname);
		return workbook;
	}

}
