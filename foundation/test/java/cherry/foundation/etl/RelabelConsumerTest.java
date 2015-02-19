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

import java.io.StringWriter;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class RelabelConsumerTest {

	@Test
	public void test() throws Exception {
		try (StringWriter writer = new StringWriter()) {
			Map<String, String> labelMap = new HashMap<>();
			labelMap.put("AA", "ＡＡ");
			Consumer consumer = new RelabelConsumer(new CsvConsumer(writer, true), labelMap);
			consumer.begin(new Column[] { column(Types.VARCHAR, "AA"), column(Types.VARCHAR, "BB") });
			consumer.consume(new String[] { "0A", "0B" });
			consumer.consume(new String[] { "1A", "1B" });
			consumer.end();
			writer.flush();
			assertEquals("\"ＡＡ\",\"BB\"\r\n\"0A\",\"0B\"\r\n\"1A\",\"1B\"\r\n", writer.toString());
		}
	}

	private Column column(int type, String label) {
		Column col = new Column();
		col.setType(type);
		col.setLabel(label);
		return col;
	}

}
