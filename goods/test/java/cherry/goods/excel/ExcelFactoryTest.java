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
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

public class ExcelFactoryTest {

	@Test
	public void testOpen_XLS() throws Exception {
		// 準備
		File file = File.createTempFile(getClass().getName(), ".xls", new File("."));
		file.deleteOnExit();
		try (OutputStream out = new FileOutputStream(file); Workbook workbook = ExcelFactory.createBlankXls("OPEN_XLS")) {
			workbook.write(out);
		}
		// 実行＆検証
		try (InputStream in = new FileInputStream(file); Workbook workbook = ExcelFactory.open(in)) {
			Sheet sheet = workbook.getSheetAt(0);
			assertNotNull(sheet);
			assertEquals("OPEN_XLS", sheet.getSheetName());
		}
	}

	@Test
	public void testOpen_XLSX() throws Exception {
		// 準備
		File file = File.createTempFile(getClass().getName(), ".xlsx", new File("."));
		file.deleteOnExit();
		try (OutputStream out = new FileOutputStream(file);
				Workbook workbook = ExcelFactory.createBlankXlsx("OPEN_XLSX")) {
			workbook.write(out);
		}
		// 実行＆検証
		try (InputStream in = new FileInputStream(file); Workbook workbook = ExcelFactory.open(in)) {
			Sheet sheet = workbook.getSheetAt(0);
			assertNotNull(sheet);
			assertEquals("OPEN_XLSX", sheet.getSheetName());
		}
	}

	@Test
	public void testLoad_XLS() throws Exception {
		// 準備
		File file = File.createTempFile(getClass().getName(), ".xls", new File("."));
		file.deleteOnExit();
		try (OutputStream out = new FileOutputStream(file); Workbook workbook = ExcelFactory.createBlankXls("LOAD_XLS")) {
			workbook.write(out);
		}
		// 実行＆検証
		try (Workbook workbook = ExcelFactory.load(file)) {
			Sheet sheet = workbook.getSheetAt(0);
			assertNotNull(sheet);
			assertEquals("LOAD_XLS", sheet.getSheetName());
		}
	}

	@Test
	public void testLoad_XLSX() throws Exception {
		// 準備
		File file = File.createTempFile(getClass().getName(), ".xlsx", new File("."));
		file.deleteOnExit();
		try (OutputStream out = new FileOutputStream(file);
				Workbook workbook = ExcelFactory.createBlankXlsx("LOAD_XLSX")) {
			workbook.write(out);
		}
		// 実行＆検証
		try (Workbook workbook = ExcelFactory.load(file)) {
			Sheet sheet = workbook.getSheetAt(0);
			assertNotNull(sheet);
			assertEquals("LOAD_XLSX", sheet.getSheetName());
		}
	}

	@Test
	public void testLoad_EXCEPTION() throws Exception {
		// 準備
		File file = File.createTempFile(getClass().getName(), ".xls", new File("."));
		file.deleteOnExit();
		// 実行＆検証
		try (Workbook workbook = ExcelFactory.load(file)) {
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			// OK
		}
	}

	@Test
	public void testCreateBlankXls_NONAME() throws Exception {
		// 準備
		File file = File.createTempFile(getClass().getName(), ".xls", new File("."));
		file.deleteOnExit();
		// 実行
		try (OutputStream out = new FileOutputStream(file); Workbook workbook = ExcelFactory.createBlankXls()) {
			workbook.write(out);
		}
		// 検証
		try (Workbook workbook = ExcelFactory.load(file); ExcelReader reader = new ExcelReader(workbook)) {
			String[] r0 = reader.read();
			assertNull(r0);
		}
	}

	@Test
	public void testCreateBlankXls_WITH_NAME() throws Exception {
		// 準備
		File file = File.createTempFile(getClass().getName(), ".xls", new File("."));
		file.deleteOnExit();
		// 実行
		try (OutputStream out = new FileOutputStream(file);
				Workbook workbook = ExcelFactory.createBlankXls("CREATE_BLANK_XLS")) {
			workbook.write(out);
		}
		// 検証
		try (Workbook workbook = ExcelFactory.load(file); ExcelReader reader = new ExcelReader(workbook)) {
			Sheet sheet = workbook.getSheetAt(0);
			assertNotNull(sheet);
			assertEquals("CREATE_BLANK_XLS", sheet.getSheetName());
			String[] r0 = reader.read();
			assertNull(r0);
		}
	}

	@Test
	public void testCreateBlankXlsx_NONAME() throws Exception {
		// 準備
		File file = File.createTempFile(getClass().getName(), ".xlsx", new File("."));
		file.deleteOnExit();
		// 実行
		try (OutputStream out = new FileOutputStream(file); Workbook workbook = ExcelFactory.createBlankXlsx()) {
			workbook.write(out);
		}
		// 検証
		try (Workbook workbook = ExcelFactory.load(file); ExcelReader reader = new ExcelReader(workbook)) {
			String[] r0 = reader.read();
			assertNull(r0);
		}
	}

	@Test
	public void testCreateBlankXlsx_WITH_NAME() throws Exception {
		// 準備
		File file = File.createTempFile(getClass().getName(), ".xlsx", new File("."));
		file.deleteOnExit();
		// 実行
		try (OutputStream out = new FileOutputStream(file);
				Workbook workbook = ExcelFactory.createBlankXlsx("CREATE_BLANK_XLSX")) {
			workbook.write(out);
		}
		// 検証
		try (Workbook workbook = ExcelFactory.load(file); ExcelReader reader = new ExcelReader(workbook)) {
			Sheet sheet = workbook.getSheetAt(0);
			assertNotNull(sheet);
			assertEquals("CREATE_BLANK_XLSX", sheet.getSheetName());
			String[] r0 = reader.read();
			assertNull(r0);
		}
	}

	@Test
	public void testInstantiate() {
		try {
			new ExcelFactory();
		} catch (Exception ex) {
			fail("Exception must not be thrown");
		}
	}

}
