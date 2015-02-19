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
import java.io.StringWriter;

import org.junit.Test;

public class CsvConsumerTest {

	@Test
	public void testWithHeaderWithRecord() throws IOException {
		StringWriter writer = new StringWriter();
		Consumer consumer = new CsvConsumer(writer, true);
		try {
			consumer.begin(createColumn(3));
			consumer.consume(createRecord(0, 3));
			consumer.consume(createRecord(1, 3));
			consumer.consume(createRecord(2, 3));
		} finally {
			consumer.end();
		}
		assertEquals("\"column0\",\"column1\",\"column2\"\r\n" + "\"record_0_0\",\"record_0_1\",\"record_0_2\"\r\n"
				+ "\"record_1_0\",\"record_1_1\",\"record_1_2\"\r\n"
				+ "\"record_2_0\",\"record_2_1\",\"record_2_2\"\r\n", writer.toString());
	}

	@Test
	public void testWithHeaderWithRecordLF() throws IOException {
		StringWriter writer = new StringWriter();
		Consumer consumer = new CsvConsumer(writer, "\n", true);
		try {
			consumer.begin(createColumn(3));
			consumer.consume(createRecord(0, 3));
			consumer.consume(createRecord(1, 3));
			consumer.consume(createRecord(2, 3));
		} finally {
			consumer.end();
		}
		assertEquals("\"column0\",\"column1\",\"column2\"\n" + "\"record_0_0\",\"record_0_1\",\"record_0_2\"\n"
				+ "\"record_1_0\",\"record_1_1\",\"record_1_2\"\n" + "\"record_2_0\",\"record_2_1\",\"record_2_2\"\n",
				writer.toString());
	}

	@Test
	public void testNoHeaderWithRecord() throws IOException {
		StringWriter writer = new StringWriter();
		Consumer consumer = new CsvConsumer(writer, false);
		try {
			consumer.begin(createColumn(3));
			consumer.consume(createRecord(0, 3));
			consumer.consume(createRecord(1, 3));
			consumer.consume(createRecord(2, 3));
		} finally {
			consumer.end();
		}
		assertEquals("\"record_0_0\",\"record_0_1\",\"record_0_2\"\r\n"
				+ "\"record_1_0\",\"record_1_1\",\"record_1_2\"\r\n"
				+ "\"record_2_0\",\"record_2_1\",\"record_2_2\"\r\n", writer.toString());
	}

	@Test
	public void testWithHeaderNoRecord() throws IOException {
		StringWriter writer = new StringWriter();
		Consumer consumer = new CsvConsumer(writer, true);
		try {
			consumer.begin(createColumn(3));
		} finally {
			consumer.end();
		}
		assertEquals("\"column0\",\"column1\",\"column2\"\r\n", writer.toString());
	}

	@Test
	public void testNoHeaderNoRecord() throws IOException {
		StringWriter writer = new StringWriter();
		Consumer consumer = new CsvConsumer(writer, false);
		try {
			consumer.begin(createColumn(3));
		} finally {
			consumer.end();
		}
		assertEquals("", writer.toString());
	}

	@Test
	public void testNullRecord() throws IOException {
		StringWriter writer = new StringWriter();
		Consumer consumer = new CsvConsumer(writer, true);
		try {
			consumer.begin(createColumn(3));
			consumer.consume(new String[3]);
		} finally {
			consumer.end();
		}
		assertEquals("\"column0\",\"column1\",\"column2\"\r\n" + ",,\r\n", writer.toString());
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

}
