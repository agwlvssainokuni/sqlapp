/*
 * Copyright 2015 agwlvssainokuni
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

package cherry.goods.telno;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;

public class SoumuExcelParserTest {

	private SoumuExcelParser parser = new SoumuExcelParser();

	@Before
	public void before() {
		parser.setNumberLabel("番号");
		parser.setAreaCodeLabel("市外局番");
		parser.setLocalCodeLabel("市内局番");
	}

	@Test
	public void testParse1() throws Exception {
		Map<String, Pair<String, String>> map;
		try (InputStream in = getClass().getResourceAsStream("soumu/000124070.xls")) {
			map = parser.parse(in);
		}
		assertEquals(4554, map.size());
		assertEquals(Pair.of("011", "200"), map.get("011200"));
		assertEquals(Pair.of("0198", "79"), map.get("019879"));
	}

	@Test
	public void testParse2() throws Exception {
		Map<String, Pair<String, String>> map;
		try (InputStream in = getClass().getResourceAsStream("soumu/000124071.xls")) {
			map = parser.parse(in);
		}
		assertEquals(5683, map.size());
		assertEquals(Pair.of("022", "200"), map.get("022200"));
		assertEquals(Pair.of("0299", "99"), map.get("029999"));
	}

	@Test
	public void testParse3() throws Exception {
		Map<String, Pair<String, String>> map;
		try (InputStream in = getClass().getResourceAsStream("soumu/000124072.xls")) {
			map = parser.parse(in);
		}
		assertEquals(3400, map.size());
		assertEquals(Pair.of("03", "3000"), map.get("033000"));
		assertEquals(Pair.of("03", "6999"), map.get("036999"));
	}

	@Test
	public void testParse4() throws Exception {
		Map<String, Pair<String, String>> map;
		try (InputStream in = getClass().getResourceAsStream("soumu/000124073.xls")) {
			map = parser.parse(in);
		}
		assertEquals(5343, map.size());
		assertEquals(Pair.of("0422", "20"), map.get("042220"));
		assertEquals(Pair.of("04998", "9"), map.get("049989"));
	}

	@Test
	public void testParse5() throws Exception {
		Map<String, Pair<String, String>> map;
		try (InputStream in = getClass().getResourceAsStream("soumu/000124074.xls")) {
			map = parser.parse(in);
		}
		assertEquals(5307, map.size());
		assertEquals(Pair.of("052", "200"), map.get("052200"));
		assertEquals(Pair.of("0599", "89"), map.get("059989"));
	}

	@Test
	public void testParse6() throws Exception {
		Map<String, Pair<String, String>> map;
		try (InputStream in = getClass().getResourceAsStream("soumu/000124075.xls")) {
			map = parser.parse(in);
		}
		assertEquals(3000, map.size());
		assertEquals(Pair.of("06", "4000"), map.get("064000"));
		assertEquals(Pair.of("06", "7999"), map.get("067999"));
	}

	@Test
	public void testParse7() throws Exception {
		Map<String, Pair<String, String>> map;
		try (InputStream in = getClass().getResourceAsStream("soumu/000124076.xls")) {
			map = parser.parse(in);
		}
		assertEquals(5548, map.size());
		assertEquals(Pair.of("072", "300"), map.get("072300"));
		assertEquals(Pair.of("0799", "89"), map.get("079989"));
	}

	@Test
	public void testParse8() throws Exception {
		Map<String, Pair<String, String>> map;
		try (InputStream in = getClass().getResourceAsStream("soumu/000124077.xls")) {
			map = parser.parse(in);
		}
		assertEquals(4526, map.size());
		assertEquals(Pair.of("082", "200"), map.get("082200"));
		assertEquals(Pair.of("089", "999"), map.get("089999"));
	}

	@Test
	public void testParse9() throws Exception {
		Map<String, Pair<String, String>> map;
		try (InputStream in = getClass().getResourceAsStream("soumu/000124078.xls")) {
			map = parser.parse(in);
		}
		assertEquals(5330, map.size());
		assertEquals(Pair.of("092", "320"), map.get("092320"));
		assertEquals(Pair.of("0997", "79"), map.get("099779"));
	}

}
