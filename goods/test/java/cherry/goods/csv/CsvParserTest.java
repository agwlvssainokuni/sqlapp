/*
 * Copyright 2004,2015 agwlvssainokuni
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

package cherry.goods.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

/**
 * {@link CsvParser}によるCSVデータ解析テスト。
 */
public class CsvParserTest {

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE001</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 空文字列 ("") を解析する。</dd>
	 * </dl>
	 */
	@Test
	public void testCASE001() {
		String text = "";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNull(record);
			// 2nd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE002</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 改行のみ ([LF]) を解析する。</dd>
	 * </dl>
	 */
	@Test
	public void testCASE002() {
		String text = "\n";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(1, record.length);
			assertEquals("", record[0]);
			// 2nd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE003</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 改行のみ ([CR][LF]) を解析する。</dd>
	 * </dl>
	 */
	@Test
	public void testCASE003() {
		String text = "\r\n";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(1, record.length);
			assertEquals("", record[0]);
			// 2nd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE004</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 改行のみ ([CR]) を解析する。</dd>
	 * </dl>
	 */
	@Test
	public void testCASE004() {
		String text = "\r";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(1, record.length);
			assertEquals("", record[0]);
			// 2nd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE005</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 改行のみ ([CR][CR]) を解析する。</dd>
	 * </dl>
	 */
	@Test
	public void testCASE005() {
		String text = "\r\r";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(1, record.length);
			assertEquals("", record[0]);
			// 2nd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE006</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 改行のみ ([CR][CR][LF]) を解析する。</dd>
	 * </dl>
	 */
	@Test
	public void testCASE006() {
		String text = "\r\r\n";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(1, record.length);
			assertEquals("", record[0]);
			// 2nd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE007</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * ,
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE007() {
		String text = ",";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(2, record.length);
			assertEquals("", record[0]);
			assertEquals("", record[1]);
			// 2nd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE008</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * ,[LF]
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE008() {
		String text = ",\n";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(2, record.length);
			assertEquals("", record[0]);
			assertEquals("", record[1]);
			// 2nd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE009</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * ,[CR][LF]
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE009() {
		String text = ",\r\n";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(2, record.length);
			assertEquals("", record[0]);
			assertEquals("", record[1]);
			// 2nd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE010</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * ,[CR]
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE010() {
		String text = ",\r";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(2, record.length);
			assertEquals("", record[0]);
			assertEquals("", record[1]);
			// 2nd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE011</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * ,[CR][CR]
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE011() {
		String text = ",\r\r";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(2, record.length);
			assertEquals("", record[0]);
			assertEquals("", record[1]);
			// 2nd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE012</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * ,[CR][CR][LF]
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE012() {
		String text = ",\r\r\n";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(2, record.length);
			assertEquals("", record[0]);
			assertEquals("", record[1]);
			// 2nd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE013</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * ,,[CR][LF]
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE013() {
		String text = ",,\r\n";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(3, record.length);
			assertEquals("", record[0]);
			assertEquals("", record[1]);
			assertEquals("", record[2]);
			// 2nd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE014</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * [CR],
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE014() {
		String text = "\r,";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			reader.read();
			fail("Exception must be thrown");
		} catch (IOException ex) {
			// 何もしない
			ex.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE015</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * [CR]"
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE015() {
		String text = "\r\"";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			reader.read();
			fail("Exception must be thrown");
		} catch (IOException ex) {
			// 何もしない
			ex.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE016</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * [CR]a
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE016() {
		String text = "\ra";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			reader.read();
			fail("Exception must be thrown");
		} catch (IOException ex) {
			// 何もしない
			ex.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE100</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * aa,bb
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE100() {
		String text = "aa,bb";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(2, record.length);
			assertEquals("aa", record[0]);
			assertEquals("bb", record[1]);
			// 2nd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE101</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * aa,bb[CR][LF]
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE101() {
		String text = "aa,bb\r\n";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(2, record.length);
			assertEquals("aa", record[0]);
			assertEquals("bb", record[1]);
			// 2nd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE102</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * aa,bb[LF]
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE102() {
		String text = "aa,bb\n";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(2, record.length);
			assertEquals("aa", record[0]);
			assertEquals("bb", record[1]);
			// 2nd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE103</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * aa,bb[CR][LF]
	 * cc,dd
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE103() {
		String text = "aa,bb\r\ncc,dd";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(2, record.length);
			assertEquals("aa", record[0]);
			assertEquals("bb", record[1]);
			// 2nd line
			record = reader.read();
			assertNotNull(record);
			assertEquals(2, record.length);
			assertEquals("cc", record[0]);
			assertEquals("dd", record[1]);
			// 3rd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE104</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * aa,bb[CR][LF]
	 * cc,dd[CR][LF]
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE104() {
		String text = "aa,bb\r\ncc,dd\r\n";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(2, record.length);
			assertEquals("aa", record[0]);
			assertEquals("bb", record[1]);
			// 2nd line
			record = reader.read();
			assertNotNull(record);
			assertEquals(2, record.length);
			assertEquals("cc", record[0]);
			assertEquals("dd", record[1]);
			// 3rd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE105</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * a"a,b""b[CR][LF]
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE105() {
		String text = "a\"a,b\"\"b\r\n";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(2, record.length);
			assertEquals("a\"a", record[0]);
			assertEquals("b\"\"b", record[1]);
			// 2nd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE200</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * "aa","bb"
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE200() {
		String text = "\"aa\",\"bb\"";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(2, record.length);
			assertEquals("aa", record[0]);
			assertEquals("bb", record[1]);
			// 2nd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE201</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * "aa","bb"[CR][LF]
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE201() {
		String text = "\"aa\",\"bb\"\r\n";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(2, record.length);
			assertEquals("aa", record[0]);
			assertEquals("bb", record[1]);
			// 2nd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE202</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * "aa","bb"[LF]
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE202() {
		String text = "\"aa\",\"bb\"\n";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(2, record.length);
			assertEquals("aa", record[0]);
			assertEquals("bb", record[1]);
			// 2nd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE203</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * "aa","bb"[CR][LF]
	 * "cc","dd"
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE203() {
		String text = "\"aa\",\"bb\"\r\n\"cc\",\"dd\"";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(2, record.length);
			assertEquals("aa", record[0]);
			assertEquals("bb", record[1]);
			// 2nd line
			record = reader.read();
			assertNotNull(record);
			assertEquals(2, record.length);
			assertEquals("cc", record[0]);
			assertEquals("dd", record[1]);
			// 3rd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE204</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * "aa","bb"[CR][LF]
	 * "cc","dd"[CR][LF]
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE204() {
		String text = "\"aa\",\"bb\"\r\n\"cc\",\"dd\"\r\n";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(2, record.length);
			assertEquals("aa", record[0]);
			assertEquals("bb", record[1]);
			// 2nd line
			record = reader.read();
			assertNotNull(record);
			assertEquals(2, record.length);
			assertEquals("cc", record[0]);
			assertEquals("dd", record[1]);
			// 3rd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE205</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * "a""a","b,b"[CR][LF]
	 * "c[CR]c","d[LF]d"[CR][LF]
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE205() {
		String text = "\"a\"\"a\",\"b,b\"\r\n\"c\rc\",\"d\nd\"\r\n";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			String[] record;
			// 1st line
			record = reader.read();
			assertNotNull(record);
			assertEquals(2, record.length);
			assertEquals("a\"a", record[0]);
			assertEquals("b,b", record[1]);
			// 2nd line
			record = reader.read();
			assertNotNull(record);
			assertEquals(2, record.length);
			assertEquals("c\rc", record[0]);
			assertEquals("d\nd", record[1]);
			// 3rd line
			record = reader.read();
			assertNull(record);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail("Exception must not be thrown");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE206</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * "a"a"
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE206() {
		String text = "\"a\"a\"";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			reader.read();
			fail("Exception must not be thrown");
		} catch (IOException ex) {
			// 何もしない
			ex.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

	/**
	 * <dl>
	 * <dt>テストID</dt>
	 * <dd>CASE207</dd>
	 * <dt>テスト内容</dt>
	 * <dd>
	 * 
	 * <pre>
	 * "a
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 */
	@Test
	public void testCASE207() {
		String text = "\"a";
		CsvParser reader = new CsvParser(new StringReader(text));
		try {
			reader.read();
			fail("Exception must not be thrown");
		} catch (IOException ex) {
			// 何もしない
			ex.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				fail("Exception must not be thrown");
			}
		}
	}

}
