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

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

/**
 * {@link CsvCreator}によるCSVデータ作成テスト。
 */
public class CsvCreatorTest {

	/**
	 * 対象: {@link CsvCreator}<br />
	 * 区分: 正常<br />
	 * null
	 */
	@Test
	public void case0001() throws IOException {

		// 準備
		String[] record1 = null;

		// 実行
		StringWriter target = new StringWriter();
		CsvCreator creator = new CsvCreator(target);
		creator.write(record1);
		creator.flush();
		creator.close();

		// 検証
		assertEquals("", target.toString());
	}

	/**
	 * 対象: {@link CsvCreator}<br />
	 * 区分: 正常<br />
	 * 空リスト
	 */
	@Test
	public void case0002() throws IOException {

		// 準備
		String[] record1 = new String[0];

		// 実行
		StringWriter target = new StringWriter();
		CsvCreator creator = new CsvCreator(target);
		creator.write(record1);
		creator.flush();
		creator.close();

		// 検証
		assertEquals("", target.toString());
	}

	/**
	 * 対象: {@link CsvCreator}<br />
	 * 区分: 正常<br />
	 * フィールドがnull
	 */
	@Test
	public void case0003() throws IOException {

		// 準備
		String[] record1 = new String[] { null };

		// 実行
		StringWriter target = new StringWriter();
		CsvCreator creator = new CsvCreator(target);
		creator.write(record1);
		creator.flush();
		creator.close();

		// 検証
		assertEquals("\n", target.toString());
	}

	/**
	 * 対象: {@link CsvCreator}<br />
	 * 区分: 正常<br />
	 * フィールドがnull (2フィールド)
	 */
	@Test
	public void case0004() throws IOException {

		// 準備
		String[] record1 = new String[] { null, null };

		// 実行
		StringWriter target = new StringWriter();
		CsvCreator creator = new CsvCreator(target);
		creator.write(record1);
		creator.flush();
		creator.close();

		// 検証
		assertEquals(",\n", target.toString());
	}

	/**
	 * 対象: {@link CsvCreator}<br />
	 * 区分: 正常<br />
	 * フィールドが空文字 ("")
	 */
	@Test
	public void case0005() throws IOException {

		// 準備
		String[] record1 = new String[] { "" };

		// 実行
		StringWriter target = new StringWriter();
		CsvCreator creator = new CsvCreator(target);
		creator.write(record1);
		creator.flush();
		creator.close();

		// 検証
		assertEquals("\"\"\n", target.toString());
	}

	/**
	 * 対象: {@link CsvCreator}<br />
	 * 区分: 正常<br />
	 * フィールドが空文字("") (2フィールド)
	 */
	@Test
	public void case0006() throws IOException {

		// 準備
		String[] record1 = new String[] { "", "" };

		// 実行
		StringWriter target = new StringWriter();
		CsvCreator creator = new CsvCreator(target);
		creator.write(record1);
		creator.flush();
		creator.close();

		// 検証
		assertEquals("\"\",\"\"\n", target.toString());
	}

	/**
	 * 対象: {@link CsvCreator}<br />
	 * 区分: 正常<br />
	 * フィールドが引用符 ("\"")
	 */
	@Test
	public void case0007() throws IOException {

		// 準備
		String[] record1 = new String[] { "\"" };

		// 実行
		StringWriter target = new StringWriter();
		CsvCreator creator = new CsvCreator(target);
		creator.write(record1);
		creator.flush();
		creator.close();

		// 検証
		assertEquals("\"\"\"\"\n", target.toString());
	}

	/**
	 * 対象: {@link CsvCreator}<br />
	 * 区分: 正常<br />
	 * フィールドが引用符 ("\"") (2フィールド)
	 */
	@Test
	public void case0008() throws IOException {

		// 準備
		String[] record1 = new String[] { "\"", "\"" };

		// 実行
		StringWriter target = new StringWriter();
		CsvCreator creator = new CsvCreator(target);
		creator.write(record1);
		creator.flush();
		creator.close();

		// 検証
		assertEquals("\"\"\"\",\"\"\"\"\n", target.toString());
	}

	/**
	 * 対象: {@link CsvCreator}<br />
	 * 区分: 正常<br />
	 * フィールドが文字列 ("field")
	 */
	@Test
	public void case0009() throws IOException {

		// 準備
		String[] record1 = new String[] { "field" };

		// 実行
		StringWriter target = new StringWriter();
		CsvCreator creator = new CsvCreator(target);
		creator.write(record1);
		creator.flush();
		creator.close();

		// 検証
		assertEquals("\"field\"\n", target.toString());
	}

	/**
	 * 対象: {@link CsvCreator}<br />
	 * 区分: 正常<br />
	 * フィールドが文字列 ("field") (2フィールド)
	 */
	@Test
	public void case0010() throws IOException {

		// 準備
		String[] record1 = new String[] { "field", "field" };

		// 実行
		StringWriter target = new StringWriter();
		CsvCreator creator = new CsvCreator(target);
		creator.write(record1);
		creator.flush();
		creator.close();

		// 検証
		assertEquals("\"field\",\"field\"\n", target.toString());
	}

	/**
	 * 対象: {@link CsvCreator}<br />
	 * 区分: 正常<br />
	 * フィールドが文字列 ("field") (2フィールド) で2レコード
	 */
	@Test
	public void case0011() throws IOException {

		// 準備
		String[] record1 = new String[] { "fieldA", "fieldB" };
		String[] record2 = new String[] { "fieldC", "fieldD" };

		// 実行
		StringWriter target = new StringWriter();
		CsvCreator creator = new CsvCreator(target);
		creator.write(record1);
		creator.write(record2);
		creator.flush();
		creator.close();

		// 検証
		assertEquals("\"fieldA\",\"fieldB\"\n\"fieldC\",\"fieldD\"\n", target.toString());
	}

	/**
	 * 対象: {@link CsvCreator}<br />
	 * 区分: 正常<br />
	 * フィールドが文字列 ("field") (2フィールド) で2レコード。レコードセパレータはCRLF
	 */
	@Test
	public void case0012() throws IOException {

		// 準備
		String[] record1 = new String[] { "fieldA", "fieldB" };
		String[] record2 = new String[] { "fieldC", "fieldD" };

		// 実行
		StringWriter target = new StringWriter();
		CsvCreator creator = new CsvCreator(target, "\r\n");
		creator.write(record1);
		creator.write(record2);
		creator.flush();
		creator.close();

		// 検証
		assertEquals("\"fieldA\",\"fieldB\"\r\n\"fieldC\",\"fieldD\"\r\n", target.toString());
	}
}
