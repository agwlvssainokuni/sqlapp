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
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class LoaderImplTest {

	@Autowired
	private DataSource dataSource;

	@Test
	public void testWithHeaderWithRecord() throws IOException {
		StringReader reader = new StringReader("\"NAME\",\"ADDRESS\"\r\n" + "\"name_0\",\"address_0\"\r\n"
				+ "\"name_1\",\"address_1\"\r\n" + "\"name_2\",\"address_2\"\r\n");
		CsvProvider provider = new CsvProvider(reader, true);
		try {
			LoaderImpl impl = new LoaderImpl();
			impl.setBatchCount(0);
			impl.setAllowedFailCount(0);
			LoadResult loadResult = impl.load(dataSource,
					"INSERT INTO etl_extr_ldr_test (name, address) VALUES (:name, :address)", provider,
					new NoneLimiter());

			assertEquals(3, loadResult.getTotalCount());
			assertEquals(3, loadResult.getSuccessCount());
			assertEquals(0, loadResult.getFailedCount());

			assertEquals("address_0", getAddressByName("name_0"));
			assertEquals("address_1", getAddressByName("name_1"));
			assertEquals("address_2", getAddressByName("name_2"));

		} finally {
			cleanupData();
		}
	}

	@Test
	public void testWithHeaderWithRecordBatch3() throws IOException {
		StringReader reader = new StringReader("\"NAME\",\"ADDRESS\"\r\n" + "\"name_0\",\"address_0\"\r\n"
				+ "\"name_1\",\"address_1\"\r\n" + "\"name_2\",\"address_2\"\r\n");
		CsvProvider provider = new CsvProvider(reader, true);
		try {
			LoaderImpl impl = new LoaderImpl();
			impl.setBatchCount(3);
			impl.setAllowedFailCount(0);
			LoadResult loadResult = impl.load(dataSource,
					"INSERT INTO etl_extr_ldr_test (name, address) VALUES (:name, :address)", provider,
					new NoneLimiter());

			assertEquals(3, loadResult.getTotalCount());
			assertEquals(3, loadResult.getSuccessCount());
			assertEquals(0, loadResult.getFailedCount());

			assertEquals("address_0", getAddressByName("name_0"));
			assertEquals("address_1", getAddressByName("name_1"));
			assertEquals("address_2", getAddressByName("name_2"));

		} finally {
			cleanupData();
		}
	}

	@Test
	public void testWithHeaderWithRecordBatch1() throws IOException {
		StringReader reader = new StringReader("\"NAME\",\"ADDRESS\"\r\n" + "\"name_0\",\"address_0\"\r\n"
				+ "\"name_1\",\"address_1\"\r\n" + "\"name_2\",\"address_2\"\r\n");
		CsvProvider provider = new CsvProvider(reader, true);
		try {
			LoaderImpl impl = new LoaderImpl();
			impl.setBatchCount(1);
			impl.setAllowedFailCount(0);
			LoadResult loadResult = impl.load(dataSource,
					"INSERT INTO etl_extr_ldr_test (name, address) VALUES (:name, :address)", provider,
					new NoneLimiter());

			assertEquals(1, loadResult.getTotalCount());
			assertEquals(1, loadResult.getSuccessCount());
			assertEquals(0, loadResult.getFailedCount());

			assertEquals("address_0", getAddressByName("name_0"));
			try {
				getAddressByName("name_1");
				fail("Exception must be thrown");
			} catch (DataAccessException ex) {
				// NOTHING
			}
			try {
				getAddressByName("name_2");
				fail("Exception must be thrown");
			} catch (DataAccessException ex) {
				// NOTHING
			}

		} finally {
			cleanupData();
		}
	}

	@Test
	public void testWithHeaderWithRecordFailCount3() throws IOException {
		StringReader reader = new StringReader("\"NAME\",\"ADDRESS\"\r\n"
				+ "\"name_0_01234567890123456789012345\",\"address_0\"\r\n"
				+ "\"name_1_01234567890123456789012345\",\"address_1\"\r\n"
				+ "\"name_2_01234567890123456789012345\",\"address_2\"\r\n");
		CsvProvider provider = new CsvProvider(reader, true);
		try {
			LoaderImpl impl = new LoaderImpl();
			impl.setBatchCount(0);
			impl.setAllowedFailCount(3);
			LoadResult loadResult = impl.load(dataSource,
					"INSERT INTO etl_extr_ldr_test (name, address) VALUES (:name, :address)", provider,
					new NoneLimiter());

			assertEquals(3, loadResult.getTotalCount());
			assertEquals(0, loadResult.getSuccessCount());
			assertEquals(3, loadResult.getFailedCount());

		} finally {
			cleanupData();
		}
	}

	@Test
	public void testWithHeaderWithRecordFailCount1() throws IOException {
		StringReader reader = new StringReader("\"NAME\",\"ADDRESS\"\r\n"
				+ "\"name_0_01234567890123456789012345\",\"address_0\"\r\n"
				+ "\"name_1_01234567890123456789012345\",\"address_1\"\r\n"
				+ "\"name_2_01234567890123456789012345\",\"address_2\"\r\n");
		CsvProvider provider = new CsvProvider(reader, true);
		try {
			LoaderImpl impl = new LoaderImpl();
			impl.setBatchCount(0);
			impl.setAllowedFailCount(1);
			try {
				impl.load(dataSource, "INSERT INTO etl_extr_ldr_test (name, address) VALUES (:name, :address)",
						provider, new NoneLimiter());
				fail("Exception must be thrown");
			} catch (DataAccessException ex) {
				// NOTHING
			}
		} finally {
			cleanupData();
		}
	}

	@Test
	public void testWithHeaderWithRecordFailCount0() throws IOException {
		StringReader reader = new StringReader("\"NAME\",\"ADDRESS\"\r\n"
				+ "\"name_0_01234567890123456789012345\",\"address_0\"\r\n"
				+ "\"name_1_01234567890123456789012345\",\"address_1\"\r\n"
				+ "\"name_2_01234567890123456789012345\",\"address_2\"\r\n");
		CsvProvider provider = new CsvProvider(reader, true);
		try {
			LoaderImpl impl = new LoaderImpl();
			impl.setBatchCount(0);
			impl.setAllowedFailCount(0);
			try {
				impl.load(dataSource, "INSERT INTO etl_extr_ldr_test (name, address) VALUES (:name, :address)",
						provider, new NoneLimiter());
				fail("Exception must be thrown");
			} catch (DataAccessException ex) {
				// NOTHING
			}
		} finally {
			cleanupData();
		}
	}

	private String getAddressByName(String name) {
		NamedParameterJdbcTemplate template = getTemplate();
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("name", name);
		return template.queryForObject("SELECT address FROM etl_extr_ldr_test WHERE name = :name", paramMap,
				String.class);
	}

	private void cleanupData() {
		NamedParameterJdbcTemplate template = getTemplate();
		Map<String, String> paramMap = new HashMap<>();
		template.update("DELETE FROM etl_extr_ldr_test", paramMap);
	}

	private NamedParameterJdbcTemplate getTemplate() {
		return new NamedParameterJdbcTemplate(dataSource);
	}

}
