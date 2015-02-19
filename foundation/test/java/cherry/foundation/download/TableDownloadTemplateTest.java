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

package cherry.foundation.download;

import static com.mysema.query.support.Expressions.constant;
import static com.mysema.query.support.Expressions.constantAs;
import static com.mysema.query.support.Expressions.path;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.foundation.querydsl.QueryConfigurer;
import cherry.goods.excel.ExcelReader;

import com.mysema.query.sql.SQLQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class TableDownloadTemplateTest {

	@Autowired
	private TableDownloadOperation tableDownloadOperation;

	@Test
	public void testDownloadCsvNoHeader() throws IOException {
		// 準備
		MockHttpServletResponse response = new MockHttpServletResponse();
		// 実行
		tableDownloadOperation.downloadCsv(response, StandardCharsets.UTF_8, "test_{0}.csv", new LocalDateTime(2015, 1,
				23, 12, 34, 56), null, createCommonClause(), createOrderByClause(), constant("TEST00"));
		// 検証
		assertEquals("text/csv", response.getContentType());
		assertEquals("UTF-8", response.getCharacterEncoding());
		assertEquals("text/csv;charset=UTF-8", response.getHeader("Content-Type"));
		assertEquals("attachment; filename=\"test_20150123123456.csv\"", response.getHeader("Content-Disposition"));
		assertEquals("\"TEST00\"\r\n", response.getContentAsString());
	}

	@Test
	public void testDownloadCsvWithHeader() throws IOException {
		// 準備
		MockHttpServletResponse response = new MockHttpServletResponse();
		// 実行
		tableDownloadOperation.downloadCsv(response, StandardCharsets.UTF_8, "test_{0}.csv", new LocalDateTime(2015, 1,
				23, 12, 34, 56), asList("HEAD0"), createCommonClause(), createOrderByClause(), constant("TEST00"));
		// 検証
		assertEquals("text/csv", response.getContentType());
		assertEquals("UTF-8", response.getCharacterEncoding());
		assertEquals("text/csv;charset=UTF-8", response.getHeader("Content-Type"));
		assertEquals("attachment; filename=\"test_20150123123456.csv\"", response.getHeader("Content-Disposition"));
		assertEquals("\"HEAD0\"\r\n\"TEST00\"\r\n", response.getContentAsString());
	}

	@Test
	public void testDownloadXlsNoHeader() throws InvalidFormatException, IOException {
		// 準備
		MockHttpServletResponse response = new MockHttpServletResponse();
		// 実行
		tableDownloadOperation.downloadXls(response, "test_{0}.xls", new LocalDateTime(2015, 1, 23, 12, 34, 56), null,
				createCommonClause(), createOrderByClause(), constant("TEST00"));
		// 検証
		assertEquals("application/vnd.ms-excel", response.getContentType());
		assertEquals("application/vnd.ms-excel", response.getHeader("Content-Type"));
		assertEquals("attachment; filename=\"test_20150123123456.xls\"", response.getHeader("Content-Disposition"));
		try (InputStream in = new ByteArrayInputStream(response.getContentAsByteArray());
				Workbook workbook = WorkbookFactory.create(in);
				ExcelReader reader = new ExcelReader(workbook)) {
			String[] record;
			record = reader.read();
			assertEquals(1, record.length);
			assertEquals("TEST00", record[0]);
			assertNull(reader.read());
		}
	}

	@Test
	public void testDownloadXlsWithHeader() throws InvalidFormatException, IOException {
		// 準備
		MockHttpServletResponse response = new MockHttpServletResponse();
		// 実行
		tableDownloadOperation.downloadXls(response, "test_{0}.xls", new LocalDateTime(2015, 1, 23, 12, 34, 56),
				asList("HEAD0"), createCommonClause(), createOrderByClause(), constant("TEST00"));
		// 検証
		assertEquals("application/vnd.ms-excel", response.getContentType());
		assertEquals("application/vnd.ms-excel", response.getHeader("Content-Type"));
		assertEquals("attachment; filename=\"test_20150123123456.xls\"", response.getHeader("Content-Disposition"));
		try (InputStream in = new ByteArrayInputStream(response.getContentAsByteArray());
				Workbook workbook = WorkbookFactory.create(in);
				ExcelReader reader = new ExcelReader(workbook)) {
			String[] record;
			record = reader.read();
			assertEquals(1, record.length);
			assertEquals("HEAD0", record[0]);
			record = reader.read();
			assertEquals(1, record.length);
			assertEquals("TEST00", record[0]);
			assertNull(reader.read());
		}
	}

	@Test
	public void testDownloadXlsxNoHeader() throws InvalidFormatException, IOException {
		// 準備
		MockHttpServletResponse response = new MockHttpServletResponse();
		// 実行
		tableDownloadOperation.downloadXlsx(response, "test_{0}.xlsx", new LocalDateTime(2015, 1, 23, 12, 34, 56),
				null, createCommonClause(), createOrderByClause(), constant("TEST00"));
		// 検証
		assertEquals("application/vnd.ms-excel", response.getContentType());
		assertEquals("application/vnd.ms-excel", response.getHeader("Content-Type"));
		assertEquals("attachment; filename=\"test_20150123123456.xlsx\"", response.getHeader("Content-Disposition"));
		try (InputStream in = new ByteArrayInputStream(response.getContentAsByteArray());
				Workbook workbook = WorkbookFactory.create(in);
				ExcelReader reader = new ExcelReader(workbook)) {
			String[] record;
			record = reader.read();
			assertEquals(1, record.length);
			assertEquals("TEST00", record[0]);
			assertNull(reader.read());
		}
	}

	@Test
	public void testDownloadXlsxWithHeader() throws InvalidFormatException, IOException {
		// 準備
		MockHttpServletResponse response = new MockHttpServletResponse();
		// 実行
		tableDownloadOperation.downloadXls(response, "test_{0}.xlsx", new LocalDateTime(2015, 1, 23, 12, 34, 56),
				asList("HEAD0"), createCommonClause(), createOrderByClause(), constant("TEST00"),
				constantAs("TEST01", path(String.class, "head1")));
		// 検証
		assertEquals("application/vnd.ms-excel", response.getContentType());
		assertEquals("application/vnd.ms-excel", response.getHeader("Content-Type"));
		assertEquals("attachment; filename=\"test_20150123123456.xlsx\"", response.getHeader("Content-Disposition"));
		try (InputStream in = new ByteArrayInputStream(response.getContentAsByteArray());
				Workbook workbook = WorkbookFactory.create(in);
				ExcelReader reader = new ExcelReader(workbook)) {
			String[] record;
			record = reader.read();
			assertEquals(2, record.length);
			assertEquals("HEAD0", record[0]);
			assertEquals("HEAD1", record[1]);
			record = reader.read();
			assertEquals(2, record.length);
			assertEquals("TEST00", record[0]);
			assertEquals("TEST01", record[1]);
			assertNull(reader.read());
		}
	}

	@Test
	public void testInstante() {
		assertNotNull(tableDownloadOperation);
	}

	private QueryConfigurer createCommonClause() {
		return new QueryConfigurer() {
			@Override
			public SQLQuery configure(SQLQuery query) {
				return query;
			}
		};
	}

	private QueryConfigurer createOrderByClause() {
		return new QueryConfigurer() {
			@Override
			public SQLQuery configure(SQLQuery query) {
				return query;
			}
		};
	}

}
