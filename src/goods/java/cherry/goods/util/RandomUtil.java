/*
 * Copyright 2014 agwlvssainokuni
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

package cherry.goods.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 乱数処理ユーティリティ。<br />
 * 下記の機能を提供する。
 * <ul>
 * <li>ランダムバイト列 ({@link #randomBytes(int)})</li>
 * <li>ランダム文字列 ({@link #randomString(int)})</li>
 * </ul>
 */
public class RandomUtil {

	private static Random random = new SecureRandom();

	private static char[] base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
			.toCharArray();

	public static void setBase(char[] bs) {
		base = bs;
	}

	/**
	 * ランダムなバイト列を生成する。
	 * 
	 * @param length
	 *            生成するバイト列の長さ。
	 * @return ランダムバイト列。
	 */
	public static byte[] randomBytes(int length) {
		byte[] value = new byte[length];
		random.nextBytes(value);
		return value;
	}

	/**
	 * ランダムな文字列を生成する。
	 * 
	 * @param length
	 *            文字列の長さ。
	 * @return ランダム文字列。
	 */
	public static String randomString(int length) {
		char[] value = new char[length];
		for (int i = 0; i < length; i++) {
			value[i] = base[random.nextInt(base.length)];
		}
		return new String(value);
	}

}
