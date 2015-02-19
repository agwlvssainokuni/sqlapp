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
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

public class TelNoNormalizerImplTest {

	private SoumuExcelParser parser = new SoumuExcelParser();

	private TelNoNormalizer normalizer = create();

	@Test
	public void testFlatten() {
		assertNull(normalizer.flatten(null));
		assertEquals("", normalizer.flatten(""));
		assertEquals("0112001234", normalizer.flatten("011-200-1234"));
	}

	@Test
	public void testNormalize0000() {

		assertNull(normalizer.normalize(null));

		List<String[]> list;
		list = normalizer.normalize("");
		assertEquals(1, list.size());
		assertEquals(1, list.get(0).length);
		assertEquals("", list.get(0)[0]);

		list = normalizer.normalize("05051115222");
		assertEquals(1, list.size());
		assertEquals(3, list.get(0).length);
		assertEquals("050", list.get(0)[0]);
		assertEquals("5111", list.get(0)[1]);
		assertEquals("5222", list.get(0)[2]);

		list = normalizer.normalize("07071117222");
		assertEquals(1, list.size());
		assertEquals(3, list.get(0).length);
		assertEquals("070", list.get(0)[0]);
		assertEquals("7111", list.get(0)[1]);
		assertEquals("7222", list.get(0)[2]);

		list = normalizer.normalize("08081118222");
		assertEquals(1, list.size());
		assertEquals(3, list.get(0).length);
		assertEquals("080", list.get(0)[0]);
		assertEquals("8111", list.get(0)[1]);
		assertEquals("8222", list.get(0)[2]);

		list = normalizer.normalize("09091119222");
		assertEquals(1, list.size());
		assertEquals(3, list.get(0).length);
		assertEquals("090", list.get(0)[0]);
		assertEquals("9111", list.get(0)[1]);
		assertEquals("9222", list.get(0)[2]);

		list = normalizer.normalize("02021112222");
		assertEquals(1, list.size());
		assertEquals(3, list.get(0).length);
		assertEquals("020", list.get(0)[0]);
		assertEquals("2111", list.get(0)[1]);
		assertEquals("2222", list.get(0)[2]);

		list = normalizer.normalize("0120911922");
		assertEquals(1, list.size());
		assertEquals(3, list.get(0).length);
		assertEquals("0120", list.get(0)[0]);
		assertEquals("911", list.get(0)[1]);
		assertEquals("922", list.get(0)[2]);

		list = normalizer.normalize("08009119222");
		assertEquals(1, list.size());
		assertEquals(3, list.get(0).length);
		assertEquals("0800", list.get(0)[0]);
		assertEquals("911", list.get(0)[1]);
		assertEquals("9222", list.get(0)[2]);

		list = normalizer.normalize("0570911922");
		assertEquals(1, list.size());
		assertEquals(3, list.get(0).length);
		assertEquals("0570", list.get(0)[0]);
		assertEquals("911", list.get(0)[1]);
		assertEquals("922", list.get(0)[2]);

		list = normalizer.normalize("0990919222");
		assertEquals(1, list.size());
		assertEquals(3, list.get(0).length);
		assertEquals("0990", list.get(0)[0]);
		assertEquals("91", list.get(0)[1]);
		assertEquals("9222", list.get(0)[2]);
	}

	@Test
	public void testNormalize0001() {

		List<String[]> list;
		list = normalizer.normalize("04");
		assertEquals(1, list.size());
		assertEquals(1, list.get(0).length);
		assertEquals("04", list.get(0)[0]);

		list = normalizer.normalize("042");
		assertEquals(2, list.size());
		assertEquals(2, list.get(0).length);
		assertEquals("04", list.get(0)[0]);
		assertEquals("2", list.get(0)[1]);
		assertEquals(1, list.get(1).length);
		assertEquals("042", list.get(1)[0]);

		list = normalizer.normalize("0420");
		assertEquals(1, list.size());
		assertEquals(2, list.get(0).length);
		assertEquals("04", list.get(0)[0]);
		assertEquals("20", list.get(0)[1]);

		list = normalizer.normalize("0421");
		assertEquals(1, list.size());
		assertEquals(1, list.get(0).length);
		assertEquals("0421", list.get(0)[0]);

		list = normalizer.normalize("0422");
		assertEquals(2, list.size());
		assertEquals(2, list.get(0).length);
		assertEquals("042", list.get(0)[0]);
		assertEquals("2", list.get(0)[1]);
		assertEquals(1, list.get(1).length);
		assertEquals("0422", list.get(1)[0]);

		list = normalizer.normalize("0423");
		assertEquals(1, list.size());
		assertEquals(2, list.get(0).length);
		assertEquals("042", list.get(0)[0]);
		assertEquals("3", list.get(0)[1]);

		list = normalizer.normalize("0424");
		assertEquals(1, list.size());
		assertEquals(2, list.get(0).length);
		assertEquals("042", list.get(0)[0]);
		assertEquals("4", list.get(0)[1]);

		list = normalizer.normalize("0425");
		assertEquals(1, list.size());
		assertEquals(2, list.get(0).length);
		assertEquals("042", list.get(0)[0]);
		assertEquals("5", list.get(0)[1]);

		list = normalizer.normalize("0426");
		assertEquals(1, list.size());
		assertEquals(2, list.get(0).length);
		assertEquals("042", list.get(0)[0]);
		assertEquals("6", list.get(0)[1]);

		list = normalizer.normalize("0427");
		assertEquals(1, list.size());
		assertEquals(2, list.get(0).length);
		assertEquals("042", list.get(0)[0]);
		assertEquals("7", list.get(0)[1]);

		list = normalizer.normalize("0428");
		assertEquals(2, list.size());
		assertEquals(2, list.get(0).length);
		assertEquals("042", list.get(0)[0]);
		assertEquals("8", list.get(0)[1]);
		assertEquals(1, list.get(1).length);
		assertEquals("0428", list.get(1)[0]);

		list = normalizer.normalize("0429");
		assertEquals(2, list.size());
		assertEquals(2, list.get(0).length);
		assertEquals("04", list.get(0)[0]);
		assertEquals("29", list.get(0)[1]);
		assertEquals(2, list.get(1).length);
		assertEquals("042", list.get(1)[0]);
		assertEquals("9", list.get(1)[1]);

		list = normalizer.normalize("042000");
		assertEquals(1, list.size());
		assertEquals(2, list.get(0).length);
		assertEquals("04", list.get(0)[0]);
		assertEquals("2000", list.get(0)[1]);

		list = normalizer.normalize("0420009");
		assertEquals(1, list.size());
		assertEquals(3, list.get(0).length);
		assertEquals("04", list.get(0)[0]);
		assertEquals("2000", list.get(0)[1]);
		assertEquals("9", list.get(0)[2]);

		list = normalizer.normalize("0420009222");
		assertEquals(1, list.size());
		assertEquals(3, list.get(0).length);
		assertEquals("04", list.get(0)[0]);
		assertEquals("2000", list.get(0)[1]);
		assertEquals("9222", list.get(0)[2]);

		list = normalizer.normalize("0420919222");
		assertEquals(1, list.size());
		assertEquals(1, list.get(0).length);
		assertEquals("0420919222", list.get(0)[0]);
	}

	private TelNoNormalizer create() {
		try {
			Trie<String, Integer> trie = new PatriciaTrie<>();
			trie.putAll(parse("soumu/000124070.xls"));
			trie.putAll(parse("soumu/000124071.xls"));
			trie.putAll(parse("soumu/000124072.xls"));
			trie.putAll(parse("soumu/000124073.xls"));
			trie.putAll(parse("soumu/000124074.xls"));
			trie.putAll(parse("soumu/000124075.xls"));
			trie.putAll(parse("soumu/000124076.xls"));
			trie.putAll(parse("soumu/000124077.xls"));
			TelNoNormalizerImpl impl = new TelNoNormalizerImpl();
			impl.setAreaCodeTable(trie);
			return impl;
		} catch (InvalidFormatException | IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

	private Map<String, Integer> parse(String name) throws InvalidFormatException, IOException {
		try (InputStream in = getClass().getResourceAsStream(name)) {
			Map<String, Integer> map = new TreeMap<>();
			for (Map.Entry<String, Pair<String, String>> entry : parser.parse(in).entrySet()) {
				map.put(entry.getKey(), entry.getValue().getLeft().length());
			}
			return map;
		}
	}

}
