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

package cherry.spring.fwcore.crypto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

/**
 * AES暗号アルゴリズムによる暗号化/復号化の機能を提供する。
 */
public class AESCipherHelper implements CipherHelper, InitializingBean {

	/** AES暗号アルゴリズムを使用するためのアルゴリズム名を保持する。 */
	private String algorithm = "AES/CBC/PKCS5Padding";

	/** AES暗号アルゴリズムを使用するための鍵アルゴリズム名を保持する。 */
	private String keyAlgorithm = "AES";

	/** AES暗号アルゴリズムで使用する共通鍵が定義されたファイルのパスを保持する。 */
	private Resource secretKey;

	/** AES暗号アルゴリズムで使用する初期化ベクタが定義されたファイルのパスを保持する。 */
	private Resource initVector;

	/** AES暗号アルゴリズムで使用する共通鍵、初期化ベクタを復号化する機能を保持する。 */
	private CipherHelper keyCipherHelper = new NullCipherHelper();

	/** AES暗号アルゴリズムで使用する共通鍵を保持する。 */
	private Key secKey;

	/** AES暗号アルゴリズムで使用する初期化ベクタを保持する。 */
	private AlgorithmParameterSpec initVec;

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public void setKeyAlgorithm(String keyAlgorithm) {
		this.keyAlgorithm = keyAlgorithm;
	}

	public void setSecretKey(Resource secretKey) {
		this.secretKey = secretKey;
	}

	public void setInitVector(Resource initVector) {
		this.initVector = initVector;
	}

	public void setKeyCipherHelper(CipherHelper keyCipherHelper) {
		this.keyCipherHelper = keyCipherHelper;
	}

	/**
	 * AES暗号アルゴリズムによる暗号化/復号化の機能を初期化する。<br />
	 * 具体的には、ファイルのパスとして指定された共通鍵と初期化ベクタを、ファイルから読込み、復号化し、JCE APIで使用する形式で保持する。
	 */
	@Override
	public void afterPropertiesSet() throws IOException {
		byte[] keyBin = keyCipherHelper.decrypt(load(secretKey));
		secKey = new SecretKeySpec(keyBin, keyAlgorithm);
		if (initVector != null) {
			byte[] ivBin = keyCipherHelper.decrypt(load(initVector));
			initVec = new IvParameterSpec(ivBin);
		}
	}

	/**
	 * ファイルの中身をバイト列として取出す。
	 * 
	 * @param res
	 *            ファイルを指定する{@link Resource}。
	 * @return ファイルの中身のバイト列。
	 * @throws IOException
	 *             ファイルの読込みで異常が発生したことを表す。
	 */
	private byte[] load(Resource res) throws IOException {
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				InputStream in = res.getInputStream()) {
			byte[] buff = new byte[4096];
			int len;
			while ((len = in.read(buff)) >= 0) {
				out.write(buff, 0, len);
			}
			out.flush();
			return out.toByteArray();
		}
	}

	@Override
	public byte[] encrypt(byte[] in) {
		try {
			Cipher cipher = Cipher.getInstance(algorithm);
			if (initVec == null) {
				cipher.init(Cipher.ENCRYPT_MODE, secKey);
			} else {
				cipher.init(Cipher.ENCRYPT_MODE, secKey, initVec);
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
			if (initVec == null) {
				cipher.init(Cipher.DECRYPT_MODE, secKey);
			} else {
				cipher.init(Cipher.DECRYPT_MODE, secKey, initVec);
			}
			return cipher.doFinal(in);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | InvalidKeyException
				| BadPaddingException | IllegalBlockSizeException ex) {
			throw new IllegalStateException(ex);
		}
	}

}
