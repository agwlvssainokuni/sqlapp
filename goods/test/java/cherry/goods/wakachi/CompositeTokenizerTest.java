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

package cherry.goods.wakachi;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CompositeTokenizerTest {

	private CompositeTokenizer tokenizer;

	@Before
	public void before() {
		SpaceCharTokenizer tk0 = new SpaceCharTokenizer();
		UnicodeBlockTokenizer tk1 = new UnicodeBlockTokenizer();
		NgramTokenizer tk2 = new NgramTokenizer();
		tk2.setApplyToAscii(false);
		tk2.setLength(2);
		tokenizer = new CompositeTokenizer();
		tokenizer.setTokenizers(asList(tk0, tk1, tk2));
	}

	@Test
	public void testTokenize_EMPTY() {
		List<String> list = tokenizer.tokenize("");
		assertEquals(0, list.size());
	}

	@Test
	public void testTokenize_ASCII_SENTENCE() {
		List<String> list = tokenizer.tokenize("abc def ghi");
		assertEquals(3, list.size());
		assertEquals("abc", list.get(0));
		assertEquals("def", list.get(1));
		assertEquals("ghi", list.get(2));
	}

	@Test
	public void testTokenize_HIRA_KANJI_SENTENCE() {
		List<String> list = tokenizer.tokenize("これは、漢字文字列と、ひらがな文字列の、テストケース");
		assertEquals(21, list.size());
		assertEquals("これ", list.get(0));
		assertEquals("れは", list.get(1));
		assertEquals("、", list.get(2));
		assertEquals("漢字", list.get(3));
		assertEquals("字文", list.get(4));
		assertEquals("文字", list.get(5));
		assertEquals("字列", list.get(6));
		assertEquals("と", list.get(7));
		assertEquals("、", list.get(8));
		assertEquals("ひら", list.get(9));
		assertEquals("らが", list.get(10));
		assertEquals("がな", list.get(11));
		assertEquals("文字", list.get(12));
		assertEquals("字列", list.get(13));
		assertEquals("の", list.get(14));
		assertEquals("、", list.get(15));
		assertEquals("テス", list.get(16));
		assertEquals("スト", list.get(17));
		assertEquals("トケ", list.get(18));
		assertEquals("ケー", list.get(19));
		assertEquals("ース", list.get(20));
	}

}
