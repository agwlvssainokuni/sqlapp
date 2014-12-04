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

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * AES暗号アルゴリズムによる暗号化/復号化の機能を提供する。
 */
public class AESCrypto implements Crypto {

	/** AES暗号アルゴリズムを使用するためのアルゴリズム名を保持する。 */
	private String algorithm = "AES/CBC/PKCS5Padding";

	/** AES暗号アルゴリズムで使用する共通鍵、初期化ベクタを復号化する機能を保持する。 */
	private Crypto keyCrypto = new NullCrypto();

	/** AES暗号アルゴリズムで使用する共通鍵を保持する。 */
	private Key secretKey;

	/** AES暗号アルゴリズムで使用する初期化ベクタを保持する。 */
	private AlgorithmParameterSpec initVector;

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public void setKeyCrypto(Crypto keyCrypto) {
		this.keyCrypto = keyCrypto;
	}

	public void setSecretKeyBytes(byte[] secretKeyBytes) {
		byte[] keyBin = keyCrypto.decrypt(secretKeyBytes);
		this.secretKey = KeyUtil.createAesSecretKey(keyBin);
	}

	public void setInitVectorBytes(byte[] initVectorBytes) {
		byte[] ivBin = keyCrypto.decrypt(initVectorBytes);
		this.initVector = KeyUtil.createAesInitVector(ivBin);
	}

	@Override
	public byte[] encrypt(byte[] in) {
		try {
			Cipher cipher = Cipher.getInstance(algorithm);
			if (initVector == null) {
				cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			} else {
				cipher.init(Cipher.ENCRYPT_MODE, secretKey, initVector);
			}
			return cipher.doFinal(in);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | InvalidKeyException
				| BadPaddingException | IllegalBlockSizeException ex) {
			throw new IllegalStateException(ex);
		}
	}

	@Override
	public byte[] decrypt(byte[] in) {
		try {
			Cipher cipher = Cipher.getInstance(algorithm);
			if (initVector == null) {
				cipher.init(Cipher.DECRYPT_MODE, secretKey);
			} else {
				cipher.init(Cipher.DECRYPT_MODE, secretKey, initVector);
			}
			return cipher.doFinal(in);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | InvalidKeyException
				| BadPaddingException | IllegalBlockSizeException ex) {
			throw new IllegalStateException(ex);
		}
	}

}
