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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import cherry.goods.excel.ExcelFactory;
import cherry.goods.excel.ExcelReader;
import cherry.goods.excel.ExcelWriter;

public class ExcelProviderTest {

	@Test
	public void testWithHeaderWithRecord() throws IOException {
		List<String[]> testData = new ArrayList<>();
		testData.add(new String[] { "column_a", "column_b", "column_c" });
		testData.add(new String[] { "record_0_0", "record_0_1", "record_0_2" });
		testData.add(new String[] { "record_1_0", "record_1_1", "record_1_2" });
		testData.add(new String[] { "record_2_0", "record_2_1", "record_2_2" });
		try (Workbook workbook = createWorkbook(testData); ExcelReader reader = new ExcelReader(workbook)) {
			ExcelProvider provider = new ExcelProvider(reader, true);
			try {
				provider.begin();
				Map<String, ?> data;
				data = provider.provide();
				assertNotNull(data);
				assertEquals("record_0_0", data.get("columnA"));
				assertEquals("record_0_1", data.get("columnB"));
				assertEquals("record_0_2", data.get("columnC"));
				data = provider.provide();
				assertNotNull(data);
				assertEquals("record_1_0", data.get("columnA"));
				assertEquals("record_1_1", data.get("columnB"));
				assertEquals("record_1_2", data.get("columnC"));
				data = provider.provide();
				assertNotNull(data);
				assertEquals("record_2_0", data.get("columnA"));
				assertEquals("record_2_1", data.get("columnB"));
				assertEquals("record_2_2", data.get("columnC"));
				data = provider.provide();
				assertNull(data);
			} finally {
				provider.end();
			}
		}
	}

	@Test
	public void testNoHeaderWithRecord() throws IOException {
		List<String[]> testData = new ArrayList<>();
		testData.add(new String[] { "record_0_0", "record_0_1", "record_0_2" });
		testData.add(new String[] { "record_1_0", "record_1_1", "record_1_2" });
		testData.add(new String[] { "record_2_0", "record_2_1", "record_2_2" });
		try (Workbook workbook = createWorkbook(testData); ExcelReader reader = new ExcelReader(workbook)) {
			ExcelProvider provider = new ExcelProvider(reader, false);
			try {
				provider.begin();
				Map<String, ?> data;
				data = provider.provide();
				assertNotNull(data);
				assertEquals("record_0_0", data.get("field0"));
				assertEquals("record_0_1", data.get("field1"));
				assertEquals("record_0_2", data.get("field2"));
				data = provider.provide();
				assertNotNull(data);
				assertEquals("record_1_0", data.get("field0"));
				assertEquals("record_1_1", data.get("field1"));
				assertEquals("record_1_2", data.get("field2"));
				data = provider.provide();
				assertNotNull(data);
				assertEquals("record_2_0", data.get("field0"));
				assertEquals("record_2_1", data.get("field1"));
				assertEquals("record_2_2", data.get("field2"));
				data = provider.provide();
				assertNull(data);
			} finally {
				provider.end();
			}
		}
	}

	@Test
	public void testEmptyData() throws IOException {
		List<String[]> testData = new ArrayList<>();
		try (Workbook workbook = createWorkbook(testData); ExcelReader reader = new ExcelReader(workbook)) {
			ExcelProvider provider = new ExcelProvider(reader, true);
			try {
				provider.begin();
				Map<String, ?> data;
				data = provider.provide();
				assertNull(data);
			} finally {
				provider.end();
			}
		}
	}

	private Workbook createWorkbook(List<String[]> testData) throws IOException {
		try (Workbook workbook = ExcelFactory.createBlankXlsx(); ExcelWriter writer = new ExcelWriter(workbook)) {
			for (String[] record : testData) {
				writer.write(record);
			}
			return workbook;
		}
	}

}
