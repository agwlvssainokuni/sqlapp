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

package cherry.foundation.type.editor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ibm.icu.text.Transliterator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class Icu4jTransliteratorEditorTest {

	@Value("Fullwidth-Halfwidth")
	private Transliterator fullHalf;

	@Value("Halfwidth-Fullwidth")
	private Transliterator halfFull;

	@Value("Hiragana-Katakana")
	private Transliterator hiraKata;

	@Test
	public void testTransliterate() {
		assertEquals("ABCDEFG", fullHalf.transliterate("ＡＢＣＤＥＦＧ"));
		assertEquals("abcdefg", fullHalf.transliterate("ａｂｃｄｅｆｇ"));
		assertEquals("ｱｲｳｴｵ", fullHalf.transliterate("アイウエオ"));
		assertEquals("ＡＢＣＤＥＦＧ", halfFull.transliterate("ABCDEFG"));
		assertEquals("ａｂｃｄｅｆｇ", halfFull.transliterate("abcdefg"));
		assertEquals("アイウエオ", halfFull.transliterate("ｱｲｳｴｵ"));
		assertEquals("アイウエオ", hiraKata.transliterate("あいうえお"));
	}

	@Test
	public void testForNull() {
		Icu4jTransliteratorEditor editor = new Icu4jTransliteratorEditor();
		editor.setAsText(null);
		assertEquals("", editor.getAsText());
		assertNull(editor.getValue());
	}

	@Test
	public void testForFullHalf() {
		Icu4jTransliteratorEditor editor = new Icu4jTransliteratorEditor();
		editor.setAsText("Fullwidth-Halfwidth");
		assertEquals("Fullwidth-Halfwidth", editor.getAsText());
		assertTrue(editor.getValue() instanceof Transliterator);
	}

}
