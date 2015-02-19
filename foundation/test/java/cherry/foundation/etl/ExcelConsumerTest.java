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

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import cherry.goods.excel.ExcelFactory;
import cherry.goods.excel.ExcelReader;
import cherry.goods.excel.ExcelWriter;

public class ExcelConsumerTest {

	@Test
	public void testWithHeaderWithRecord() throws IOException {
		try (Workbook workbook = ExcelFactory.createBlankXlsx(); ExcelWriter writer = new ExcelWriter(workbook)) {
			Consumer consumer = new ExcelConsumer(writer, true);
			try {
				consumer.begin(createColumn(3));
				consumer.consume(createRecord(0, 3));
				consumer.consume(createRecord(1, 3));
				consumer.consume(createRecord(2, 3));
			} finally {
				consumer.end();
			}
			List<String[]> expected = new ArrayList<>();
			expected.add(new String[] { "column0", "column1", "column2" });
			expected.add(new String[] { "record_0_0", "record_0_1", "record_0_2" });
			expected.add(new String[] { "record_1_0", "record_1_1", "record_1_2" });
			expected.add(new String[] { "record_2_0", "record_2_1", "record_2_2" });
			assertWorkbook(expected, workbook);
		}
	}

	@Test
	public void testNoHeaderWithRecord() throws IOException {
		try (Workbook workbook = ExcelFactory.createBlankXlsx(); ExcelWriter writer = new ExcelWriter(workbook)) {
			Consumer consumer = new ExcelConsumer(writer, false);
			try {
				consumer.begin(createColumn(3));
				consumer.consume(createRecord(0, 3));
				consumer.consume(createRecord(1, 3));
				consumer.consume(createRecord(2, 3));
			} finally {
				consumer.end();
			}
			List<String[]> expected = new ArrayList<>();
			expected.add(new String[] { "record_0_0", "record_0_1", "record_0_2" });
			expected.add(new String[] { "record_1_0", "record_1_1", "record_1_2" });
			expected.add(new String[] { "record_2_0", "record_2_1", "record_2_2" });
			assertWorkbook(expected, workbook);
		}
	}

	@Test
	public void testWithHeaderNoRecord() throws IOException {
		try (Workbook workbook = ExcelFactory.createBlankXlsx(); ExcelWriter writer = new ExcelWriter(workbook)) {
			Consumer consumer = new ExcelConsumer(writer, true);
			try {
				consumer.begin(createColumn(3));
			} finally {
				consumer.end();
			}
			List<String[]> expected = new ArrayList<>();
			expected.add(new String[] { "column0", "column1", "column2" });
			assertWorkbook(expected, workbook);
		}
	}

	@Test
	public void testNoHeaderNoRecord() throws IOException {
		try (Workbook workbook = ExcelFactory.createBlankXlsx(); ExcelWriter writer = new ExcelWriter(workbook)) {
			Consumer consumer = new ExcelConsumer(writer, false);
			try {
				consumer.begin(createColumn(3));
			} finally {
				consumer.end();
			}
			List<String[]> expected = new ArrayList<>();
			assertWorkbook(expected, workbook);
		}
	}

	@Test
	public void testNullRecord() throws IOException {
		try (Workbook workbook = ExcelFactory.createBlankXlsx(); ExcelWriter writer = new ExcelWriter(workbook)) {
			Consumer consumer = new ExcelConsumer(writer, true);
			try {
				consumer.begin(createColumn(3));
				consumer.consume(new String[3]);
			} finally {
				consumer.end();
			}
			List<String[]> expected = new ArrayList<>();
			expected.add(new String[] { "column0", "column1", "column2" });
			expected.add(new String[0]);
			assertWorkbook(expected, workbook);
		}
	}

	private Column[] createColumn(int num) {
		Column[] col = new Column[num];
		for (int i = 0; i < num; i++) {
			col[i] = new Column();
			col[i].setLabel("column" + i);
			col[i].setType(0);
		}
		return col;
	}

	private String[] createRecord(int row, int num) {
		String[] rec = new String[num];
		for (int i = 0; i < num; i++) {
			rec[i] = "record_" + row + "_" + i;
		}
		return rec;
	}

	private void assertWorkbook(List<String[]> expected, Workbook workbook) throws IOException {
		try (ExcelReader reader = new ExcelReader(workbook)) {

			List<String[]> result = new ArrayList<>();
			String[] record;
			while ((record = reader.read()) != null) {
				result.add(record);
			}

			assertEquals(expected.size(), result.size());
			for (int i = 0; i < expected.size(); i++) {
				String[] e = expected.get(i);
				String[] r = result.get(i);
				assertEquals(e.length, r.length);
				for (int j = 0; j < e.length; j++) {
					assertEquals(e[j], r[j]);
				}
			}
		}
	}

}
