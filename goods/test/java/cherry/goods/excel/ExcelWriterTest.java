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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class ExcelWriterTest {

	@Test
	public void testGetNumberOfSheets() throws IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			// 準備
			workbook.createSheet("CREATED 0");
			workbook.createSheet("CREATED 1");
			workbook.createSheet("CREATED 2");

			// 実行＆検証
			try (ExcelWriter writer = new ExcelWriter(workbook)) {
				assertEquals(3, writer.getNumberOfSheets());
			}
		}
	}

	@Test
	public void testGetSheetName() throws IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			// 準備
			workbook.createSheet("CREATED 0");
			workbook.createSheet("CREATED 1");
			workbook.createSheet("CREATED 2");

			// 実行＆検証
			try (ExcelWriter writer = new ExcelWriter(workbook)) {
				assertEquals("CREATED 0", writer.getSheetName(0));
				assertEquals("CREATED 1", writer.getSheetName(1));
				assertEquals("CREATED 2", writer.getSheetName(2));
			}
		}
	}

	@Test
	public void testSetCurrentSheet() throws IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			// 準備
			Sheet sheet0 = workbook.createSheet("CREATED 0");
			Sheet sheet1 = workbook.createSheet("CREATED 1");

			// 実行＆検証
			try (ExcelWriter writer = new ExcelWriter(workbook)) {
				writer.setCurrentSheet(0);
				writer.write("CELL IN 0");
				writer.setCurrentSheet(1);
				writer.write("CELL IN 1");

				assertEquals("CELL IN 0", sheet0.getRow(0).getCell(0).getStringCellValue());
				assertEquals("CELL IN 1", sheet1.getRow(0).getCell(0).getStringCellValue());
			}
		}
	}

	@Test
	public void testWrite_2_COLS_2_ROWS() throws IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			// 準備
			Sheet sheet = workbook.createSheet("CREATED 0");

			// 実行＆検証
			try (ExcelWriter writer = new ExcelWriter(workbook)) {
				writer.write("CELL 00", "CELL 01");
				writer.write("CELL 10", "CELL 11");

				assertEquals("CELL 00", sheet.getRow(0).getCell(0).getStringCellValue());
				assertEquals("CELL 01", sheet.getRow(0).getCell(1).getStringCellValue());
				assertEquals("CELL 10", sheet.getRow(1).getCell(0).getStringCellValue());
				assertEquals("CELL 11", sheet.getRow(1).getCell(1).getStringCellValue());
			}
		}
	}

	@Test
	public void testWrite_2_COLS_2_ROWS_WITH_NULL() throws IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			// 準備
			Sheet sheet = workbook.createSheet("CREATED 0");
			Row row0 = sheet.createRow(0);
			row0.createCell(0);
			row0.createCell(1);

			// 実行＆検証
			try (ExcelWriter writer = new ExcelWriter(workbook)) {
				writer.write("CELL 00", null);
				writer.write(null, "CELL 11");

				assertEquals("CELL 00", sheet.getRow(0).getCell(0).getStringCellValue());
				assertNull(sheet.getRow(0).getCell(1));
				assertNull(sheet.getRow(1).getCell(0));
				assertEquals("CELL 11", sheet.getRow(1).getCell(1).getStringCellValue());
			}
		}
	}

	@Test
	public void testWrite_WITH_OFFSET() throws IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			// 準備
			Sheet sheet = workbook.createSheet("CREATED 0");

			// 実行＆検証
			try (ExcelWriter writer = new ExcelWriter(workbook)) {
				writer.write(2, "CELL 00", "CELL 01");

				assertEquals("CELL 00", sheet.getRow(0).getCell(2).getStringCellValue());
				assertEquals("CELL 01", sheet.getRow(0).getCell(3).getStringCellValue());
			}
		}
	}

	@Test
	public void testSkipRows() throws IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			// 準備
			Sheet sheet = workbook.createSheet("CREATED 0");

			// 実行＆検証
			try (ExcelWriter writer = new ExcelWriter(workbook)) {
				writer.write("CELL 00", "CELL 01");
				writer.skipRows(1);
				writer.write("CELL 20", "CELL 21");

				assertEquals("CELL 00", sheet.getRow(0).getCell(0).getStringCellValue());
				assertEquals("CELL 01", sheet.getRow(0).getCell(1).getStringCellValue());
				assertNull(sheet.getRow(1));
				assertEquals("CELL 20", sheet.getRow(2).getCell(0).getStringCellValue());
				assertEquals("CELL 21", sheet.getRow(2).getCell(1).getStringCellValue());
			}
		}
	}

}
