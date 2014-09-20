/*
 * Copyright 2011,2014 agwlvssainokuni
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

package cherry.spring.common.lib.ipaddr;

import java.math.BigInteger;

/**
 * IPアドレス操作ユーティリティ.<br>
 * IPアドレスの文字列表記を操作するためのユーティリティです。下記の機能を提供します。
 * <ul>
 * <li>IPアドレス形式判定: IPv4 ({@link #isIpv4Addr(String)})、IPv6 (
 * {@link #isIpv6Addr(String)})</li>
 * <li>IPv6アドレスの展開表記への変換 ({@link #decompressIpv6Addr(String)})</li>
 * <li>IPv6アドレスの圧縮表記 (RFC 5952) への変換 ({@link #compressIpv6Addr(String)})</li>
 * <li>IPアドレスの文字列表現から数値表現への変換: IPv4 ({@link #getIpv4AddrAsNumber(String)})、IPv6
 * ({@link #getIpv6AddrAsNumber(String)})</li>
 * <li>IPアドレスのマスクの生成: IPv4 ({@link #getIpv4AddrMask(int)})、IPv6 (
 * {@link #getIpv6AddrMask(int)})</li>
 * <li>IPアドレスの数値表現から文字列表現への変換: IPv4 ({@link #getIpv4AddrFromNumber(BigInteger)}
 * )、IPv6 ({@link #getIpv6AddrFromNumber(BigInteger)})</li>
 * </ul>
 */
public class IpAddrUtil {

	/** IPv4アドレス形式の正規表現. */
	public static final String IPV4_PATTERN = "^"
			+ "(25[0-5]|2[0-4][0-9]|[01]?[0-9]{1,2})(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9]{1,2})){3}"
			+ "$";

	/** IPv6アドレス形式の正規表現. */
	public static final String IPV6_PATTERN = "^"
			// [正規表現のオプション] 大文字小文字を区別しない
			+ "(?i)"
			+ "("
			// (1) IPv4混在なし
			+ "("
			// ・省略なし
			+ "[0-9a-f]{1,4}(:[0-9a-f]{1,4}){7}"
			// ・全省略
			+ "|::"
			// ・前省略
			+ "|:(:[0-9a-f]{1,4}){1,7}"
			// ・後省略
			+ "|([0-9a-f]{1,4}:){1,7}:"
			// ・中省略
			+ "|([0-9a-f]{1,4}:){1}(:[0-9a-f]{1,4}){1,6}"
			+ "|([0-9a-f]{1,4}:){2}(:[0-9a-f]{1,4}){1,5}"
			+ "|([0-9a-f]{1,4}:){3}(:[0-9a-f]{1,4}){1,4}"
			+ "|([0-9a-f]{1,4}:){4}(:[0-9a-f]{1,4}){1,3}"
			+ "|([0-9a-f]{1,4}:){5}(:[0-9a-f]{1,4}){1,2}"
			+ "|([0-9a-f]{1,4}:){6}(:[0-9a-f]{1,4}){1}"
			+ ")"
			// (2) IPv4混在あり]
			+ "|("
			// ・省略なし
			+ "[0-9a-f]{1,4}(:[0-9a-f]{1,4}){5}"
			// ・全省略
			+ "|:"
			// ・前省略
			+ "|:(:[0-9a-f]{1,4}){1,5}"
			// ・後省略
			+ "|([0-9a-f]{1,4}:){1,5}"
			// ・中省略
			+ "|([0-9a-f]{1,4}:){1}(:[0-9a-f]{1,4}){1,4}"
			+ "|([0-9a-f]{1,4}:){2}(:[0-9a-f]{1,4}){1,3}"
			+ "|([0-9a-f]{1,4}:){3}(:[0-9a-f]{1,4}){1,2}"
			+ "|([0-9a-f]{1,4}:){4}(:[0-9a-f]{1,4}){1}"
			// ・共通末尾 (IPv4部)
			+ "):(25[0-5]|2[0-4][0-9]|[01]?[0-9]{1,2})(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9]{1,2})){3}"
			+ ")"
			//
			+ "$";

	/**
	 * IPアドレス形式判定 (IPv4).<br>
	 * 文字列がIPv4アドレス形式であるか否か判定する。
	 * 
	 * @param addr
	 *            判定対象の文字列。
	 * @return IPv4アドレス形式ならばtrue、さもなくばfalse。
	 */
	public static boolean isIpv4Addr(String addr) {
		if (addr == null) {
			return false;
		}
		return addr.matches(IPV4_PATTERN);
	}

	/**
	 * IPアドレス形式判定 (IPv6).<br>
	 * 文字列がIPv6アドレス形式であるか否かを判定する。
	 * 
	 * @param addr
	 *            判定対象の文字列。
	 * @return IPv6アドレス形式ならばtrue、さもなくばfalse。
	 */
	public static boolean isIpv6Addr(String addr) {
		if (addr == null) {
			return false;
		}
		return addr.matches(IPV6_PATTERN);
	}

	/**
	 * IPv6アドレス展開表現.<br>
	 * IPv6アドレスの文字列表現を展開表現に変換する。
	 * 
	 * @param addr
	 *            IPv6アドレスの文字列表現。
	 * @return IPv6アドレスの展開表現。
	 */
	public static String decompressIpv6Addr(String addr) {

		Ipv6Fragment ipv6frag = makeIpv6Fragment(addr);

		StringBuilder builder = new StringBuilder();

		constructDecompressed(builder, ipv6frag.v6);

		if (ipv6frag.v4 != null) {
			builder.append(":");
			builder.append(ipv6frag.v4);
		}

		return builder.toString();
	}

	/**
	 * IPv6アドレス圧縮表現.<br>
	 * IPv6アドレスの文字列表現を圧縮表現に変換する。
	 * 
	 * @param addr
	 *            IPv6アドレスの文字列表現。
	 * @return IPv6アドレスの圧縮表現。
	 */
	public static String compressIpv6Addr(String addr) {

		Ipv6Fragment ipv6frag = makeIpv6Fragment(addr);

		StringBuilder builder = new StringBuilder();

		Range range = computeMaxNullRange(ipv6frag.v6);
		constructCompressed(builder, ipv6frag.v6, range);

		if (ipv6frag.v4 != null) {
			if (builder.charAt(builder.length() - 1) != ':') {
				builder.append(":");
			}
			builder.append(ipv6frag.v4);
		}

		return builder.toString();
	}

	/**
	 * IPv4アドレス数値表現.<br>
	 * IPv4アドレスの文字列表現を数値表現に変換する。
	 * 
	 * @param addr
	 *            IPv4アドレスの文字列表現。
	 * @return IPv4アドレスの数値表現。
	 */
	public static BigInteger getIpv4AddrAsNumber(String addr) {

		BigInteger result = BigInteger.ZERO;

		for (String field : addr.split("\\.")) {
			result = result.shiftLeft(8);
			long value = Long.valueOf(field);
			result = result.add(BigInteger.valueOf(value));
		}

		return result;
	}

	/**
	 * IPv6アドレス数値表現.<br>
	 * IPv6アドレスの文字列表現を数値表現に変換する。
	 * 
	 * @param addr
	 *            IPv6アドレスの文字列表現。
	 * @return IPv6アドレスの数値表現。
	 */
	public static BigInteger getIpv6AddrAsNumber(String addr) {

		Ipv6Fragment ipv6frag = makeIpv6Fragment(addr);

		BigInteger result = BigInteger.ZERO;

		for (String field : ipv6frag.v6) {
			result = result.shiftLeft(16);
			if (!isZero(field)) {
				long value = Long.valueOf(field, 16);
				result = result.add(BigInteger.valueOf(value));
			}
		}

		if (ipv6frag.v4 != null) {
			result = result.shiftLeft(32);
			result = result.add(getIpv4AddrAsNumber(ipv6frag.v4));
		}

		return result;
	}

	/**
	 * IPv4アドレスネットマスク数値表現.<br>
	 * IPv4アドレスのネットマスクの数値表現を取得する。
	 * 
	 * @param maskLength
	 *            ネットマスク長。0から32の範囲で指定する。
	 * @return IPv4アドレスのネットマスクの数値表現。
	 */
	public static BigInteger getIpv4AddrMask(int maskLength) {
		if (maskLength > 32) {
			return getIpAddrMask(32, 32);
		}
		if (maskLength < 0) {
			return getIpAddrMask(32, 0);
		}
		return getIpAddrMask(32, maskLength);
	}

	/**
	 * IPv6アドレスプレフィクスマスク数値表現.<br>
	 * IPv6アドレスのプレフィクスマスクの数値表現を取得する。
	 * 
	 * @param prefixLength
	 *            プレフィクス長。0から128の範囲で指定する。
	 * @return IPv6アドレスのプレフィクスマスクの数値表現。
	 */
	public static BigInteger getIpv6AddrMask(int prefixLength) {
		if (prefixLength > 128) {
			return getIpAddrMask(128, 128);
		}
		if (prefixLength < 0) {
			return getIpAddrMask(128, 0);
		}
		return getIpAddrMask(128, prefixLength);
	}

	/**
	 * IPアドレスマスク数値表現.<br>
	 * IPアドレスのサイズとプレフィクス長からビットマスクの数値表現を取得する。
	 * 
	 * @param size
	 *            IPアドレスのサイズ。
	 * @param length
	 *            プレフィクス長。
	 * @return ビットマスクの数値表現。
	 */
	private static BigInteger getIpAddrMask(int size, int length) {
		// [考え方]
		// -------- <---------------(size)---------------->
		// -------- <-----(length)----> <-(size - length)->
		// --------+-------------------+--------------------
		// base = 1 0 0 0 0 ... 0 0 0 0 0 0 0 0 ... 0 0 0 0
		// mask = . . . . . . . . . . 1 0 0 0 0 ... 0 0 0 0
		// --------+-------------------+--------------------
		// return = 1 1 1 1 ... 1 1 1 1 0 0 0 0 ... 0 0 0 0
		BigInteger base = BigInteger.ONE.shiftLeft(size);
		BigInteger mask = BigInteger.ONE.shiftLeft(size - length);
		return base.subtract(mask);
	}

	/**
	 * IPv4アドレス文字列表現.<br>
	 * IPv4アドレスの数値表現を文字列表現に変換する。
	 * 
	 * @param addr
	 *            IPv4アドレスの数値表現。
	 * @return IPv4アドレスの文字列表現。
	 */
	public static String getIpv4AddrFromNumber(BigInteger addr) {

		String[] field = new String[4];
		BigInteger unit = BigInteger.valueOf(0x100L);

		BigInteger number = addr;
		for (int i = field.length - 1; i >= 0; i--) {
			BigInteger[] div = number.divideAndRemainder(unit);
			number = div[0];
			field[i] = div[1].toString(10);
		}

		StringBuilder builder = new StringBuilder(field[0]);
		for (int i = 1; i < field.length; i++) {
			builder.append(".");
			builder.append(field[i]);
		}
		return builder.toString();
	}

	/**
	 * IPv6アドレス文字列表現.<br>
	 * IPv6アドレスの数値表現を文字列表現に変換する。
	 * 
	 * @param addr
	 *            IPv6アドレスの数値表現。
	 * @return IPv6アドレスの文字列表現。
	 */
	public static String getIpv6AddrFromNumber(BigInteger addr) {

		String[] field = new String[8];
		BigInteger unit = BigInteger.valueOf(0x10000L);

		BigInteger number = addr;
		for (int i = field.length - 1; i >= 0; i--) {
			BigInteger[] div = number.divideAndRemainder(unit);
			number = div[0];
			field[i] = div[1].toString(16);
		}

		StringBuilder builder = new StringBuilder();
		constructDecompressed(builder, field);
		return builder.toString();
	}

	/**
	 * IPv6アドレス解析.<br>
	 * IPv6アドレスの文字列表現を構成要素 (IPv6部、IPv4部) に分解する。
	 * 
	 * @param addr
	 *            IPv6アドレスの文字列表現。
	 * @return 構成要素に分解したIPv6アドレス。
	 */
	private static Ipv6Fragment makeIpv6Fragment(String addr) {

		Ipv6Fragment result = new Ipv6Fragment();

		if (addr.matches("^.*:[0-9]{1,3}(\\.[0-9]{1,3}){3}$")) {

			int colon = addr.lastIndexOf(":");
			result.v4 = addr.substring(colon + 1);

			String addrv6 = addr.substring(0, colon);
			if (addrv6.equals(":")) {
				result.v6 = padding(6, new String[0], new String[0]);
			} else if (addrv6.startsWith("::")) {
				result.v6 = padding(6, new String[0], addrv6.substring(2)
						.split(":"));
			} else if (addrv6.endsWith(":")) {
				result.v6 = padding(6, addrv6.substring(0, addrv6.length() - 1)
						.split(":"), new String[0]);
			} else if (addrv6.indexOf("::") >= 0) {
				String[] part = addrv6.split("::");
				result.v6 = padding(6, part[0].split(":"), part[1].split(":"));
			} else {
				result.v6 = padding(6, addrv6.split(":"), new String[0]);
			}
		} else {

			result.v4 = null;

			String addrv6 = addr;
			if (addrv6.equals("::")) {
				result.v6 = padding(8, new String[0], new String[0]);
			} else if (addrv6.startsWith("::")) {
				result.v6 = padding(8, new String[0], addrv6.substring(2)
						.split(":"));
			} else if (addrv6.endsWith("::")) {
				result.v6 = padding(8, addrv6.substring(0, addrv6.length() - 2)
						.split(":"), new String[0]);
			} else if (addrv6.indexOf("::") >= 0) {
				String[] part = addrv6.split("::");
				result.v6 = padding(8, part[0].split(":"), part[1].split(":"));
			} else {
				result.v6 = padding(8, addrv6.split(":"), new String[0]);
			}
		}

		return result;
	}

	/**
	 * IPv6アドレス解析用配列生成.<br>
	 * サイズを指定して配列を生成する。配列の先頭、末尾には、引数で指定した配列の要素を格納する。
	 * 
	 * @param size
	 *            生成する配列のサイズ。
	 * @param prefix
	 *            先頭に格納する値を保持する配列。
	 * @param suffix
	 *            末尾に格納する値を保持する配列。
	 * @return 生成した配列。
	 */
	private static String[] padding(int size, String[] prefix, String[] suffix) {
		String[] result = new String[size];
		for (int i = 0; i < prefix.length; i++) {
			result[i] = prefix[i];
		}
		for (int i = prefix.length; i < size - suffix.length; i++) {
			result[i] = null;
		}
		for (int i = size - suffix.length; i < size; i++) {
			result[i] = suffix[i - (size - suffix.length)];
		}
		return result;
	}

	/**
	 * IPv6アドレス0値連解析.<br>
	 * 配列の中の最長の0値連 (値が0が連続している範囲) を調べる。
	 * 
	 * @param field
	 *            0値連を調べる対象の配列。
	 * @return 最長の0値連 (開始添字と終了添字)。
	 */
	private static Range computeMaxNullRange(String[] field) {

		int curBegin = -1;
		int curEnd = -1;
		Range range = new Range();

		for (int i = 0; i < field.length; i++) {

			if (!isZero(field[i])) {
				curBegin = -1;
				curEnd = -1;
				continue;
			}

			if (curBegin < 0) {
				curBegin = i;
				curEnd = i;
			} else {
				curEnd = i;
			}

			if (range.begin < 0) {
				range.begin = curBegin;
				range.end = curEnd;
			} else {
				if (range.end - range.begin < curEnd - curBegin) {
					range.begin = curBegin;
					range.end = curEnd;
				}
			}
		}

		return range;
	}

	/**
	 * IPv6アドレス展開表現生成.<br>
	 * IPv6アドレスを項に分解した文字列配列を、展開表現として結合する。
	 * 
	 * @param builder
	 *            文字列結合のためのStringBuilder。
	 * @param field
	 *            IPv6アドレスの項を分解した文字列配列。
	 */
	private static void constructDecompressed(StringBuilder builder,
			String[] field) {

		appendDecompressed(builder, field[0]);
		for (int i = 1; i < field.length; i++) {
			builder.append(":");
			appendDecompressed(builder, field[i]);
		}
	}

	/**
	 * IPv6アドレス圧縮表現生成.<br>
	 * IPv6アドレスを項に分解した文字列配列を、圧縮表現として結合する。
	 * 
	 * @param builder
	 *            文字列結合のためのStringBuilder。
	 * @param field
	 *            IPv6アドレスの項を分解した文字列配列。
	 * @param range
	 *            圧縮表現に結合する際の0値連。
	 */
	private static void constructCompressed(StringBuilder builder,
			String[] field, Range range) {

		if ((range.begin == -1) || (range.begin == range.end)) {
			// 省略なし
			appendCompressed(builder, field[0]);
			for (int i = 1; i < field.length; i++) {
				builder.append(":");
				appendCompressed(builder, field[i]);
			}
		} else if (range.begin == 0) {
			if (range.end == field.length - 1) {
				// 全省略
				builder.append("::");
			} else {
				// 前省略
				builder.append(":");
				for (int i = range.end + 1; i < field.length; i++) {
					builder.append(":");
					appendCompressed(builder, field[i]);
				}
			}
		} else {
			if (range.end == field.length - 1) {
				// 後省略
				for (int i = 0; i < range.begin; i++) {
					appendCompressed(builder, field[i]);
					builder.append(":");
				}
				builder.append(":");
			} else {
				// 中省略
				for (int i = 0; i < range.begin; i++) {
					appendCompressed(builder, field[i]);
					builder.append(":");
				}
				for (int i = range.end + 1; i < field.length; i++) {
					builder.append(":");
					appendCompressed(builder, field[i]);
				}
			}
		}
	}

	/**
	 * IPv6アドレス展開表現生成.<br>
	 * IPv6アドレスの項を展開表現で結合する。
	 * 
	 * @param builder
	 *            文字列結合のためのStringBuilder。
	 * @param text
	 *            結合するIPv6アドレスの項。
	 */
	private static void appendDecompressed(StringBuilder builder, String text) {
		if (isZero(text)) {
			builder.append("0000");
		} else {
			for (int i = text.length(); i < 4; i++) {
				builder.append("0");
			}
			builder.append(text);
		}
	}

	/**
	 * IPv6アドレス圧縮表現生成.<br>
	 * IPv6アドレスの項を圧縮表現で結合する。
	 * 
	 * @param builder
	 *            文字列結合のためのStringBuilder。
	 * @param text
	 *            結合するIPv6アドレスの項。
	 */
	private static void appendCompressed(StringBuilder builder, String text) {
		if (isZero(text)) {
			builder.append("0");
		} else {
			int index = 0;
			for (; index < text.length(); index++) {
				if (text.charAt(index) != '0') {
					break;
				}
			}
			for (; index < text.length(); index++) {
				builder.append(text.charAt(index));
			}
		}
	}

	/**
	 * IPv6アドレス項0値判定.<br>
	 * IPv6アドレスの項が0値か否かを判定する。
	 * 
	 * @param text
	 *            結合するIPv6アドレスの項。
	 * @return 項が0値ならtrue、さもなくばfalse。
	 */
	private static boolean isZero(String text) {
		if (text == null) {
			return true;
		} else {
			return text.matches("^0+$");
		}
	}

	/**
	 * IPv6アドレス構成要素.<br>
	 * 構成要素に分解したIPv6アドレスを保持する。
	 */
	private static class Ipv6Fragment {
		/** IPv6部 (項に分解した配列). */
		public String[] v6 = null;
		/** IPv4部. */
		public String v4 = null;
	}

	/**
	 * IPv6アドレス0値連範囲.<br>
	 * 0値連の範囲 (開始添字と終了添字) を保持する。
	 */
	private static class Range {
		/** 開始添字. */
		public int begin = -1;
		/** 終了添字. */
		public int end = -1;
	}

}
