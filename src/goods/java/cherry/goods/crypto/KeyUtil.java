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

package cherry.goods.crypto;

import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 暗号の鍵を生成する機能を提供する。
 */
public class KeyUtil {

	/** 鍵インスタンスを生成するためのファクトリを保持する。 */
	private static final KeyFactory rsaKeyFactory;

	static {
		try {
			rsaKeyFactory = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	/**
	 * バイト列からRSA公開鍵を生成する。
	 * 
	 * @param b
	 *            生成元バイト列。
	 * @return RSA公開鍵。
	 * @throws InvalidKeySpecException
	 *             読込んだ鍵データの形式が正しくないことを表す。
	 */
	public static PublicKey createRsaPublicKey(byte[] b)
			throws InvalidKeySpecException {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(b);
		return rsaKeyFactory.generatePublic(keySpec);
	}

	/**
	 * バイト列からRSA秘密鍵を生成する。
	 * 
	 * @param b
	 *            生成バイト列。
	 * @return RSA秘密鍵。
	 * @throws InvalidKeySpecException
	 *             読込んだ鍵データの形式が正しくないことを表す。
	 */
	public static PrivateKey createRsaPrivateKey(byte[] b)
			throws InvalidKeySpecException {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(b);
		return rsaKeyFactory.generatePrivate(keySpec);
	}

	/**
	 * バイト列からAES共通鍵を生成する。
	 * 
	 * @param b
	 *            生成元バイト列。
	 * @return AES共通鍵。
	 */
	public static Key createAesSecretKey(byte[] b) {
		return new SecretKeySpec(b, "AES");
	}

	/**
	 * バイト列からAES初期化ベクタを生成する。
	 * 
	 * @param b
	 *            生成元バイト列。
	 * @return AES初期化ベクタ。
	 */
	public static IvParameterSpec createAesInitVector(byte[] b) {
		return new IvParameterSpec(b);
	}

}
