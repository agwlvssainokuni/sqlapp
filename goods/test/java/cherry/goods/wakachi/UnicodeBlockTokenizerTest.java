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

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UnicodeBlockTokenizerTest {

	private UnicodeBlockTokenizer tokenizer;

	@Before
	public void before() {
		tokenizer = new UnicodeBlockTokenizer();
	}

	@Test
	public void testTokenize_EMPTY() {
		List<String> list = tokenizer.tokenize("");
		assertEquals(0, list.size());
	}

	@Test
	public void testTokenize_ASCII() {
		List<String> list = tokenizer.tokenize("abc");
		assertEquals(1, list.size());
		assertEquals("abc", list.get(0));
	}

	@Test
	public void testTokenize_ASCII_HIRA() {
		List<String> list = tokenizer.tokenize("abcあいう");
		assertEquals(2, list.size());
		assertEquals("abc", list.get(0));
		assertEquals("あいう", list.get(1));
	}

	@Test
	public void testTokenize_ASCII_HIRA_KANJI() {
		List<String> list = tokenizer.tokenize("abcあいう漢字");
		assertEquals(3, list.size());
		assertEquals("abc", list.get(0));
		assertEquals("あいう", list.get(1));
		assertEquals("漢字", list.get(2));
	}

}
