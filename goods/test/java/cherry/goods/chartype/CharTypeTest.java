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

package cherry.goods.chartype;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import cherry.goods.chartype.TableReader.Entry;

public class CharTypeTest {

	private TableReader tableReader = new TableReader();

	@Test
	public void testIsBasicLatin() {
		for (Entry entry : tableReader.getEntries()) {
			int win31j = entry.getWin31j();
			int unicode = entry.getUnicode();
			if (win31j <= 0x007F) {
				assertThat(CharTypeValidator.isBasicLatin(unicode), is(true));
			} else {
				assertThat(CharTypeValidator.isBasicLatin(unicode), is(false));
			}
		}
	}

	@Test
	public void testIsHalfWidth() {
		for (Entry entry : tableReader.getEntries()) {
			int win31j = entry.getWin31j();
			int unicode = entry.getUnicode();
			if (win31j <= 0x007F) {
				assertThat(CharTypeValidator.isHalfWidth(unicode), is(true));
			} else if (win31j >= 0x00A1 && win31j <= 0x00DF) {
				assertThat(CharTypeValidator.isHalfWidth(unicode), is(true));
			} else {
				assertThat(CharTypeValidator.isHalfWidth(unicode), is(false));
			}
		}
	}

	@Test
	public void testIsFullWidth() {
		for (Entry entry : tableReader.getEntries()) {
			int win31j = entry.getWin31j();
			int unicode = entry.getUnicode();
			if (win31j <= 0x007F) {
				assertThat(CharTypeValidator.isFullWidth(unicode), is(false));
			} else if (win31j >= 0x00A1 && win31j <= 0x00DF) {
				assertThat(CharTypeValidator.isFullWidth(unicode), is(false));
			} else {
				assertThat(CharTypeValidator.isFullWidth(unicode), is(true));
			}
		}
	}

	@Test
	public void testIsSpace() {
		for (Entry entry : tableReader.getEntries()) {
			int win31j = entry.getWin31j();
			int unicode = entry.getUnicode();
			switch (win31j) {
			case 0x0009: // HT
			case 0x000A: // LF
			case 0x000B: // VT
			case 0x000C: // NP
			case 0x000D: // CR
			case 0x001C: // FS
			case 0x001D: // GS
			case 0x001E: // RS
			case 0x001F: // US
			case 0x0020: // SPC
				assertThat(CharTypeValidator.isSpace(unicode), is(true));
				break;
			default:
				assertThat(CharTypeValidator.isSpace(unicode), is(false));
				break;
			}
		}
	}

	@Test
	public void testIsNumeric() {
		for (Entry entry : tableReader.getEntries()) {
			int win31j = entry.getWin31j();
			int unicode = entry.getUnicode();
			switch (win31j) {
			case 0x0030: // '0'
			case 0x0031: // '1'
			case 0x0032: // '2'
			case 0x0033: // '3'
			case 0x0034: // '4'
			case 0x0035: // '5'
			case 0x0036: // '6'
			case 0x0037: // '7'
			case 0x0038: // '8'
			case 0x0039: // '9'
				assertThat(CharTypeValidator.isNumeric(unicode), is(true));
				break;
			default:
				assertThat(CharTypeValidator.isNumeric(unicode), is(false));
				break;
			}
		}
	}

	@Test
	public void testIsAlpha() {
		for (Entry entry : tableReader.getEntries()) {
			int win31j = entry.getWin31j();
			int unicode = entry.getUnicode();
			if (0x0041 <= win31j && 0x005A >= win31j) {// A-Z
				assertThat(CharTypeValidator.isAlpha(unicode), is(true));
			} else if (0x0061 <= win31j && 0x007A >= win31j) {// a-z
				assertThat(CharTypeValidator.isAlpha(unicode), is(true));
			} else {
				assertThat(CharTypeValidator.isAlpha(unicode), is(false));
			}
		}
	}

	@Test
	public void testIsUpper() {
		for (Entry entry : tableReader.getEntries()) {
			int win31j = entry.getWin31j();
			int unicode = entry.getUnicode();
			if (0x0041 <= win31j && 0x005A >= win31j) {// A-Z
				assertThat(CharTypeValidator.isUpper(unicode), is(true));
			} else {
				assertThat(CharTypeValidator.isUpper(unicode), is(false));
			}
		}
	}

	@Test
	public void testIsLower() {
		for (Entry entry : tableReader.getEntries()) {
			int win31j = entry.getWin31j();
			int unicode = entry.getUnicode();
			if (0x0061 <= win31j && 0x007A >= win31j) {// a-z
				assertThat(CharTypeValidator.isLower(unicode), is(true));
			} else {
				assertThat(CharTypeValidator.isLower(unicode), is(false));
			}
		}
	}

	@Test
	public void testIsFullSpace() {
		for (Entry entry : tableReader.getEntries()) {
			int win31j = entry.getWin31j();
			int unicode = entry.getUnicode();
			switch (win31j) {
			case 0x8140: // IDEOGRAPHIC SPACE "　"
				assertThat(CharTypeValidator.isFullSpace(unicode), is(true));
				break;
			default:
				assertThat(CharTypeValidator.isFullSpace(unicode), is(false));
				break;
			}
		}
	}

	@Test
	public void testIsFullNumeric() {
		for (Entry entry : tableReader.getEntries()) {
			int win31j = entry.getWin31j();
			int unicode = entry.getUnicode();
			switch (win31j) {
			case 0x824F: // FULLWIDTH DIGIT ZERO "０"
			case 0x8250: // FULLWIDTH DIGIT ZERO "１"
			case 0x8251: // FULLWIDTH DIGIT ZERO "２"
			case 0x8252: // FULLWIDTH DIGIT ZERO "３"
			case 0x8253: // FULLWIDTH DIGIT ZERO "４"
			case 0x8254: // FULLWIDTH DIGIT ZERO "５"
			case 0x8255: // FULLWIDTH DIGIT ZERO "６"
			case 0x8256: // FULLWIDTH DIGIT ZERO "７"
			case 0x8257: // FULLWIDTH DIGIT ZERO "８"
			case 0x8258: // FULLWIDTH DIGIT ZERO "９"
				assertThat(CharTypeValidator.isFullNumeric(unicode), is(true));
				break;
			default:
				assertThat(CharTypeValidator.isFullNumeric(unicode), is(false));
				break;
			}
		}
	}

	@Test
	public void testIsFullAlpha() {
		for (Entry entry : tableReader.getEntries()) {
			int win31j = entry.getWin31j();
			int unicode = entry.getUnicode();
			if (0x8260 <= win31j && 0x8279 >= win31j) {// Ａ-Ｚ
				assertThat(CharTypeValidator.isFullAlpha(unicode), is(true));
			} else if (0x8281 <= win31j && 0x829A >= win31j) {// ａ-ｚ
				assertThat(CharTypeValidator.isFullAlpha(unicode), is(true));
			} else {
				assertThat(CharTypeValidator.isFullAlpha(unicode), is(false));
			}
		}
	}

	@Test
	public void testIsFullUpper() {
		for (Entry entry : tableReader.getEntries()) {
			int win31j = entry.getWin31j();
			int unicode = entry.getUnicode();
			if (0x8260 <= win31j && 0x8279 >= win31j) {// Ａ-Ｚ
				assertThat(CharTypeValidator.isFullUpper(unicode), is(true));
			} else {
				assertThat(CharTypeValidator.isFullUpper(unicode), is(false));
			}
		}
	}

	@Test
	public void testIsFullLower() {
		for (Entry entry : tableReader.getEntries()) {
			int win31j = entry.getWin31j();
			int unicode = entry.getUnicode();
			if (0x8281 <= win31j && 0x829A >= win31j) {// ａ-ｚ
				assertThat(CharTypeValidator.isFullLower(unicode), is(true));
			} else {
				assertThat(CharTypeValidator.isFullLower(unicode), is(false));
			}
		}
	}

	@Test
	public void testIsFullHiragana() {
		for (Entry entry : tableReader.getEntries()) {
			int win31j = entry.getWin31j();
			int unicode = entry.getUnicode();
			if (0x829F <= win31j && 0x82F1 >= win31j) {
				assertThat(CharTypeValidator.isFullHiragana(unicode), is(true));
			} else {
				switch (win31j) {
				case 0x8145: // '・'
				case 0x814A: // '゛'
				case 0x814B: // '゜'
				case 0x8154: // 'ゝ'
				case 0x8155: // 'ゞ'
				case 0x815B: // 'ー'
				case 0x8141: // '、'
				case 0x8142: // '。'
				case 0x8175: // '「'
				case 0x8176: // '」'
				case 0x8177: // '『'
				case 0x8178: // '』'
					assertThat(CharTypeValidator.isFullHiragana(unicode), is(true));
					break;
				default:
					assertThat(CharTypeValidator.isFullHiragana(unicode), is(false));
					break;
				}
			}
		}
	}

	@Test
	public void testIsFullKatakana() {
		for (Entry entry : tableReader.getEntries()) {
			int win31j = entry.getWin31j();
			int unicode = entry.getUnicode();
			if (0x8340 <= win31j && 0x8396 >= win31j) {
				assertThat(CharTypeValidator.isFullKatakana(unicode), is(true));
			} else {
				switch (win31j) {
				case 0x8145: // '・'
				case 0x814A: // '゛'
				case 0x814B: // '゜'
				case 0x8152: // 'ヽ'
				case 0x8153: // 'ヾ'
				case 0x815B: // 'ー'
				case 0x8141: // '、'
				case 0x8142: // '。'
				case 0x8175: // '「'
				case 0x8176: // '」'
				case 0x8177: // '『'
				case 0x8178: // '』'
					assertThat(CharTypeValidator.isFullKatakana(unicode), is(true));
					break;
				default:
					assertThat(CharTypeValidator.isFullKatakana(unicode), is(false));
					break;
				}
			}
		}
	}

	@Test
	public void testIsHalfKatakana() {
		for (Entry entry : tableReader.getEntries()) {
			int win31j = entry.getWin31j();
			int unicode = entry.getUnicode();
			if (0x00A1 <= win31j && 0x00DF >= win31j) {
				assertThat(CharTypeValidator.isHalfKatakana(unicode), is(true));
			} else {
				assertThat(CharTypeValidator.isHalfKatakana(unicode), is(false));
			}
		}
	}

}
