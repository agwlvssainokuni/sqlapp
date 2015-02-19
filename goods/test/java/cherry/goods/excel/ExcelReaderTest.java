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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class ExcelReaderTest {

	@Test
	public void testGetNumberOfSheets() throws IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			// 準備
			workbook.createSheet("CREATED 0");
			workbook.createSheet("CREATED 1");
			workbook.createSheet("CREATED 2");

			// 実行＆検証
			try (ExcelReader reader = new ExcelReader(workbook)) {
				assertEquals(3, reader.getNumberOfSheets());
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
			try (ExcelReader reader = new ExcelReader(workbook)) {
				assertEquals("CREATED 0", reader.getSheetName(0));
				assertEquals("CREATED 1", reader.getSheetName(1));
				assertEquals("CREATED 2", reader.getSheetName(2));
			}
		}
	}

	@Test
	public void testSetCurrentSheet() throws IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			// 準備
			Sheet sheet0 = workbook.createSheet("CREATED 0");
			sheet0.createRow(0).createCell(0).setCellValue("CELL IN 0");
			Sheet sheet1 = workbook.createSheet("CREATED 1");
			sheet1.createRow(0).createCell(0).setCellValue("CELL IN 1");

			// 実行＆検証
			try (ExcelReader reader = new ExcelReader(workbook)) {

				reader.setCurrentSheet(0);
				String[] r0 = reader.read();
				assertNotNull(r0);
				assertEquals(1, r0.length);
				assertEquals("CELL IN 0", r0[0]);
				assertNull(reader.read());

				reader.setCurrentSheet(1);
				String[] r1 = reader.read();
				assertNotNull(r1);
				assertEquals(1, r1.length);
				assertEquals("CELL IN 1", r1[0]);
				assertNull(reader.read());
			}
		}
	}

	@Test
	public void testRead_EMPTY() throws IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			// 準備
			workbook.createSheet();

			// 実行＆検証
			try (ExcelReader reader = new ExcelReader(workbook)) {
				assertNull(reader.read());
			}
		}
	}

	@Test
	public void testRead_2_ROWS() throws IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			// 準備
			Sheet sheet = workbook.createSheet();
			Row row0 = sheet.createRow(0);
			row0.createCell(0).setCellValue("00");
			row0.createCell(1).setCellValue("01");
			Row row1 = sheet.createRow(1);
			row1.createCell(0).setCellValue("10");
			row1.createCell(1).setCellValue("11");
			row1.createCell(2).setCellValue("12");

			// 実行＆検証
			try (ExcelReader reader = new ExcelReader(workbook)) {

				String[] r0 = reader.read();
				assertNotNull(r0);
				assertEquals(2, r0.length);
				assertEquals("00", r0[0]);
				assertEquals("01", r0[1]);

				String[] r1 = reader.read();
				assertNotNull(r1);
				assertEquals(3, r1.length);
				assertEquals("10", r1[0]);
				assertEquals("11", r1[1]);
				assertEquals("12", r1[2]);

				assertNull(reader.read());
			}
		}
	}

	@Test
	public void testRead_3_ROWS_WITH_EMPTY_ROW() throws IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			// 準備
			Sheet sheet = workbook.createSheet();
			Row row0 = sheet.createRow(0);
			row0.createCell(0).setCellValue("00");
			row0.createCell(1).setCellValue("01");
			sheet.createRow(1);
			Row row2 = sheet.createRow(2);
			row2.createCell(0).setCellValue("20");
			row2.createCell(1).setCellValue("21");
			row2.createCell(2).setCellValue("22");

			// 実行＆検証
			try (ExcelReader reader = new ExcelReader(workbook)) {

				String[] r0 = reader.read();
				assertNotNull(r0);
				assertEquals(2, r0.length);
				assertEquals("00", r0[0]);
				assertEquals("01", r0[1]);

				String[] r1 = reader.read();
				assertNotNull(r1);
				assertEquals(0, r1.length);

				String[] r2 = reader.read();
				assertNotNull(r2);
				assertEquals(3, r2.length);
				assertEquals("20", r2[0]);
				assertEquals("21", r2[1]);
				assertEquals("22", r2[2]);

				assertNull(reader.read());
			}
		}
	}

	@Test
	public void testRead_NUMERIC() throws IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			// 準備
			Sheet sheet = workbook.createSheet();
			Row row0 = sheet.createRow(0);
			row0.createCell(0).setCellValue(1234);
			row0.createCell(1).setCellValue(1234.56);

			// 実行＆検証
			try (ExcelReader reader = new ExcelReader(workbook)) {

				String[] r0 = reader.read();
				assertNotNull(r0);
				assertEquals(2, r0.length);
				assertEquals("1234", r0[0]);
				assertEquals("1234.56", r0[1]);

				assertNull(reader.read());
			}
		}
	}

	@Test
	public void testRead_STRING() throws IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			// 準備
			Sheet sheet = workbook.createSheet();
			Row row0 = sheet.createRow(0);
			row0.createCell(0).setCellValue("CELL 00");
			row0.createCell(1).setCellValue("CELL 01");

			// 実行＆検証
			try (ExcelReader reader = new ExcelReader(workbook)) {

				String[] r0 = reader.read();
				assertNotNull(r0);
				assertEquals(2, r0.length);
				assertEquals("CELL 00", r0[0]);
				assertEquals("CELL 01", r0[1]);

				assertNull(reader.read());
			}
		}
	}

	@Test
	public void testRead_BLANK() throws IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			// 準備
			Sheet sheet = workbook.createSheet();
			Row row0 = sheet.createRow(0);
			row0.createCell(0);
			row0.createCell(1);

			// 実行＆検証
			try (ExcelReader reader = new ExcelReader(workbook)) {

				String[] r0 = reader.read();
				assertNotNull(r0);
				assertEquals(2, r0.length);
				assertEquals("", r0[0]);
				assertEquals("", r0[1]);

				assertNull(reader.read());
			}
		}
	}

	@Test
	public void testRead_BOOLEAN() throws IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			// 準備
			Sheet sheet = workbook.createSheet();
			Row row0 = sheet.createRow(0);
			row0.createCell(0).setCellValue(true);
			row0.createCell(1).setCellValue(false);

			// 実行＆検証
			try (ExcelReader reader = new ExcelReader(workbook)) {

				String[] r0 = reader.read();
				assertNotNull(r0);
				assertEquals(2, r0.length);
				assertEquals("true", r0[0]);
				assertEquals("false", r0[1]);

				assertNull(reader.read());
			}
		}
	}

	@Test
	public void testRead_ERROR() throws IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			// 準備
			Sheet sheet = workbook.createSheet();
			Row row0 = sheet.createRow(0);
			row0.createCell(0).setCellErrorValue((byte) 0);
			row0.createCell(1).setCellErrorValue((byte) 0);

			// 実行＆検証
			try (ExcelReader reader = new ExcelReader(workbook)) {

				String[] r0 = reader.read();
				assertNotNull(r0);
				assertEquals(2, r0.length);
				assertNull(r0[0]);
				assertNull(r0[1]);

				assertNull(reader.read());
			}
		}
	}

	@Test
	public void testRead_FORMULA_NUMERIC() throws IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			// 準備
			Sheet sheet = workbook.createSheet();
			Row row0 = sheet.createRow(0);
			row0.createCell(0).setCellFormula("1200+34");
			row0.createCell(1).setCellFormula("1200+34.56");
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			evaluator.evaluateAll();

			// 実行＆検証
			try (ExcelReader reader = new ExcelReader(workbook)) {

				String[] r0 = reader.read();
				assertNotNull(r0);
				assertEquals(2, r0.length);
				assertEquals("1234", r0[0]);
				assertEquals("1234.56", r0[1]);

				assertNull(reader.read());
			}
		}
	}

	@Test
	public void testRead_FORMULA_STRING() throws IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			// 準備
			Sheet sheet = workbook.createSheet();
			Row row0 = sheet.createRow(0);
			row0.createCell(0).setCellFormula("\"CELL\"&\"00\"");
			row0.createCell(1).setCellFormula("\"CELL\"&\"01\"");
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			evaluator.evaluateAll();

			// 実行＆検証
			try (ExcelReader reader = new ExcelReader(workbook)) {

				String[] r0 = reader.read();
				assertNotNull(r0);
				assertEquals(2, r0.length);
				assertEquals("CELL00", r0[0]);
				assertEquals("CELL01", r0[1]);

				assertNull(reader.read());
			}
		}
	}

	@Test
	public void testRead_FORMULA_BOOLEAN() throws IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			// 準備
			Sheet sheet = workbook.createSheet();
			Row row0 = sheet.createRow(0);
			row0.createCell(0).setCellFormula("1=1");
			row0.createCell(1).setCellFormula("1=0");
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			evaluator.evaluateAll();

			// 実行＆検証
			try (ExcelReader reader = new ExcelReader(workbook)) {

				String[] r0 = reader.read();
				assertNotNull(r0);
				assertEquals(2, r0.length);
				assertEquals("true", r0[0]);
				assertEquals("false", r0[1]);

				assertNull(reader.read());
			}
		}
	}

	@Test
	public void testRead_FORMULA_ERROR() throws IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			// 準備
			Sheet sheet = workbook.createSheet();
			Row row0 = sheet.createRow(0);
			row0.createCell(0).setCellErrorValue((byte) 0);
			row0.createCell(1).setCellFormula("A1");
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			evaluator.evaluateAll();

			// 実行＆検証
			try (ExcelReader reader = new ExcelReader(workbook)) {

				String[] r0 = reader.read();
				assertNotNull(r0);
				assertEquals(2, r0.length);
				assertNull(r0[0]);
				assertNull(r0[1]);

				assertNull(reader.read());
			}
		}
	}
}
