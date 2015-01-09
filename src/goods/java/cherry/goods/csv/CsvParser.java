/*
 * Copyright 2011,2015 agwlvssainokuni
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

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * CSVパーサ。<br />
 * CSV形式データを解析して、レコード (文字列のリスト) 単位で取得する。サポートするCSV形式は RFC 4180 (http://www.ietf.org/rfc/rfc4180.txt) を基本とする。ただし、下記の点が RFC
 * 4180 と異なる (RFC 4180 の上位互換)。
 * <ul>
 * <li>文字データ (TEXTDATA) の範囲はUnicode (Javaが文字として扱うもの) とする。(RFC 4180 はASCIIの範囲に限定している)</li>
 * <li>引用無しフィールド (non-escaped) は、データ内に引用符 (DQUOTE) を含んでもエラーとしない。引用符も文字データの1文字と同じように扱う。</li>
 * <li>LF, CRLF, CRCRLF, ... を一つの改行として扱う。(RFC 4180 はCRLFを改行とする)</li>
 * <li>データの最後 (end of file) はLFが無くてもエラーとはしない。(引用データ (escaped) 中を除く)</li>
 * </ul>
 */
public class CsvParser implements Closeable {

	/** データ読取り元。 */
	private final Reader reader;

	/**
	 * CSVパーサを生成する。<br />
	 * 
	 * @param r データ読取り元。
	 */
	public CsvParser(final Reader r) {
		reader = r;
	}

	/**
	 * CSVレコード読取り。<br />
	 * データ読取り元からCSVデータを1レコード読取る。
	 * 
	 * @return CSVデータの1レコード。
	 * @throws CsvException CSV形式不正。
	 * @throws IOException データ読取りエラー。
	 */
	public String[] read() throws CsvException, IOException {

		List<String> record = null;
		StringBuilder field = new StringBuilder();
		StringBuilder line = new StringBuilder();

		State state = RECORD_BEGIN;
		while (state != RECORD_END) {

			int ch = reader.read();
			Trans trans = state.trans(ch);

			switch (trans.action) {
			case APPEND:
				field.append((char) ch);
				break;
			case FLUSH:
				if (record == null) {
					record = new ArrayList<String>();
				}
				record.add(field.toString());
				field = new StringBuilder();
				break;
			case ERROR:
				if (ch < 0) {
					line.append("<EOF>");
				} else {
					line.append((char) ch);
				}
				throw new CsvException("Invalid format: " + line.toString());
			default:
				// 何もしない。
				break;
			}

			state = trans.state;
			line.append((char) ch);
		}

		if (record == null) {
			return null;
		} else {
			return record.toArray(new String[record.size()]);
		}
	}

	/**
	 * データ読取り元をクローズする。<br />
	 * 
	 * @throws IOException データ読取り元のクローズエラー。
	 */
	@Override
	public void close() throws IOException {
		reader.close();
	}

	// [管理するデータ]
	// レコード := CSV解析結果。初期状態は [] (空リスト)、終了状態は [フィールド, フィールド, ...] (文字列のリスト)
	// フィールド := レコードの要素
	//
	// [状態遷移の基本構成]
	// 初期状態 := RECORD_BEGIN
	// 途中状態 := FIELD_BEGIN, NONESCAPED, ESCAPED, DQUOTE, CR
	// 終了状態 := RECORD_END
	// イベント := 文字入力 (TEXTDATA, COMMA, DQUOTE, CR, LF, EOF)
	// アクション := APPEND (フィールドに文字追加), FLUSH (フィールドを確定して新フィールドを開始), NONE, ERROR
	//
	// [RECORD_BEGIN]
	// TEXTDATA -> APPEND / NONESCAPED
	// COMMA -> FLUSH / FIELD_BEGIN
	// DQUOTE -> NONE / ESCAPED
	// CR -> FLUSH / CR
	// LF -> FLUSH / RECORD_END
	// EOF -> NONE / RECORD_END
	//
	// [FIELD_BEGIN]
	// TEXTDATA -> APPEND / NONESCAPED
	// COMMA -> FLUSH / FIELD_BEGIN
	// DQUOTE -> NONE / ESCAPED
	// CR -> FLUSH / CR
	// LF -> FLUSH / RECORD_END
	// EOF -> FLUSH / RECORD_END
	//
	// [NONESCAPED]
	// TEXTDATA -> APPEND / NONESCAPED
	// COMMA -> FLUSH / FIELD_BEGIN
	// DQUOTE -> APPEND / NONESCAPED
	// CR -> FLUSH / CR
	// LF -> FLUSH / RECORD_END
	// EOF -> FLUSH / RECORD_END
	//
	// [ESCAPED]
	// TEXTDATA -> APPEND / ESCAPED
	// COMMA -> APPEND / ESCAPED
	// DQUOTE -> NONE / DQUOTE
	// CR -> APPEND / ESCAPED
	// LF -> APPEND / ESCAPED
	// EOF -> ERROR / -
	//
	// [DQUOTE]
	// TEXTDATA -> ERROR / -
	// COMMA -> FLUSH / FIELD_BEGIN
	// DQUOTE -> APPEND / ESCAPED
	// CR -> FLUSH / CR
	// LF -> FLUSH / RECORD_END
	// EOF -> FLUSH / RECORD_END
	//
	// [CR]
	// TEXTDATA -> ERROR / -
	// COMMA -> ERROR / -
	// DQUOTE -> ERROR / -
	// CR -> NONE / CR
	// LF -> NONE / RECORD_END
	// EOF -> NONE / RECORD_END
	//
	// [RECORD_END]
	// ※遷移なし。

	/**
	 * 状態遷移機械における「状態」を表す。
	 */
	private interface State {
		/** イベント (文字入力) に対する応答を決定する。 */
		Trans trans(int ch);
	}

	/**
	 * 状態遷移機械における「アクション」を表す。
	 */
	private static enum Action {
		NONE, APPEND, FLUSH, ERROR
	}

	/**
	 * 状態繊維機械においてイベント (文字入力) に対する応答 (「アクション」と遷移先の「状態」) を表す。
	 */
	private static class Trans {
		/** イベント (文字入力) に対する「アクション」。 */
		public final Action action;
		/** イベント (文字入力) に対する遷移先の「状態」。 */
		public final State state;

		/** イベント (文字入力) に対する応答を生成する。 */
		public Trans(final Action a, final State s) {
			action = a;
			state = s;
		}
	}

	/** 状態: RECORD_BEGIN */
	private final State RECORD_BEGIN = new State() {
		public Trans trans(int ch) {
			switch (ch) {
			case (int) ',': // COMMA
				return new Trans(Action.FLUSH, FIELD_BEGIN);
			case (int) '"': // DQUOTE
				return new Trans(Action.NONE, ESCAPED);
			case (int) '\r': // CR
				return new Trans(Action.FLUSH, CR);
			case (int) '\n': // LF
				return new Trans(Action.FLUSH, RECORD_END);
			case -1: // EOF
				return new Trans(Action.NONE, RECORD_END);
			default: // TEXTDATA
				return new Trans(Action.APPEND, NONESCAPED);
			}
		}
	};

	/** 状態: FIELD_BEGIN */
	private final State FIELD_BEGIN = new State() {
		public Trans trans(int ch) {
			switch (ch) {
			case (int) ',': // COMMA
				return new Trans(Action.FLUSH, FIELD_BEGIN);
			case (int) '"': // DQUOTE
				return new Trans(Action.NONE, ESCAPED);
			case (int) '\r': // CR
				return new Trans(Action.FLUSH, CR);
			case (int) '\n': // LF
				return new Trans(Action.FLUSH, RECORD_END);
			case -1: // EOF
				return new Trans(Action.FLUSH, RECORD_END);
			default: // TEXTDATA
				return new Trans(Action.APPEND, NONESCAPED);
			}
		}
	};

	/** 状態: NONESCAPED */
	private final State NONESCAPED = new State() {
		public Trans trans(int ch) {
			switch (ch) {
			case (int) ',': // COMMA
				return new Trans(Action.FLUSH, FIELD_BEGIN);
			case (int) '"': // DQUOTE
				return new Trans(Action.APPEND, NONESCAPED);
			case (int) '\r': // CR
				return new Trans(Action.FLUSH, CR);
			case (int) '\n': // LF
				return new Trans(Action.FLUSH, RECORD_END);
			case -1: // EOF
				return new Trans(Action.FLUSH, RECORD_END);
			default: // TEXTDATA
				return new Trans(Action.APPEND, NONESCAPED);
			}
		}
	};

	/** 状態: ESCAPED */
	private final State ESCAPED = new State() {
		public Trans trans(int ch) {
			switch (ch) {
			case (int) ',': // COMMA
				return new Trans(Action.APPEND, ESCAPED);
			case (int) '"': // DQUOTE
				return new Trans(Action.NONE, DQUOTE);
			case (int) '\r': // CR
				return new Trans(Action.APPEND, ESCAPED);
			case (int) '\n': // LF
				return new Trans(Action.APPEND, ESCAPED);
			case -1: // EOF
				return new Trans(Action.ERROR, null);
			default: // TEXTDATA
				return new Trans(Action.APPEND, ESCAPED);
			}
		}
	};

	/** 状態: DQUOTE */
	private final State DQUOTE = new State() {
		public Trans trans(int ch) {
			switch (ch) {
			case (int) ',': // COMMA
				return new Trans(Action.FLUSH, FIELD_BEGIN);
			case (int) '"': // DQUOTE
				return new Trans(Action.APPEND, ESCAPED);
			case (int) '\r': // CR
				return new Trans(Action.FLUSH, CR);
			case (int) '\n': // LF
				return new Trans(Action.FLUSH, RECORD_END);
			case -1: // EOF
				return new Trans(Action.FLUSH, RECORD_END);
			default: // TEXTDATA
				return new Trans(Action.ERROR, null);
			}
		}
	};

	/** 状態: CR */
	private final State CR = new State() {
		public Trans trans(int ch) {
			switch (ch) {
			case (int) ',': // COMMA
				return new Trans(Action.ERROR, null);
			case (int) '"': // DQUOTE
				return new Trans(Action.ERROR, null);
			case (int) '\r': // CR
				return new Trans(Action.NONE, CR);
			case (int) '\n': // LF
				return new Trans(Action.NONE, RECORD_END);
			case -1: // EOF
				return new Trans(Action.NONE, RECORD_END);
			default: // TEXTDATA
				return new Trans(Action.ERROR, null);
			}
		}
	};

	/** 状態: RECORD_END */
	private final State RECORD_END = new State() {
		public Trans trans(int ch) {
			return null;
		}
	};

}
