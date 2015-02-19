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
import java.io.StringReader;
import java.util.Map;

import org.junit.Test;

public class CsvProviderTest {

	@Test
	public void testWithHeaderWithRecord() throws IOException {
		StringReader reader = new StringReader("\"column_a\",\"column_b\",\"column_c\"\r\n"
				+ "\"record_0_0\",\"record_0_1\",\"record_0_2\"\r\n"
				+ "\"record_1_0\",\"record_1_1\",\"record_1_2\"\r\n"
				+ "\"record_2_0\",\"record_2_1\",\"record_2_2\"\r\n");
		CsvProvider provider = new CsvProvider(reader, true);
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

	@Test
	public void testNoHeaderWithRecord() throws IOException {
		StringReader reader = new StringReader("\"record_0_0\",\"record_0_1\",\"record_0_2\"\r\n"
				+ "\"record_1_0\",\"record_1_1\",\"record_1_2\"\r\n"
				+ "\"record_2_0\",\"record_2_1\",\"record_2_2\"\r\n");
		CsvProvider provider = new CsvProvider(reader, false);
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

	@Test
	public void testEmptyData() throws IOException {
		StringReader reader = new StringReader("");
		CsvProvider provider = new CsvProvider(reader, true);
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
