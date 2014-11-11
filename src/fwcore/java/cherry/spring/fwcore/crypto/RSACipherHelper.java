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

import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

/**
 * RSA暗号アルゴリズムによる暗号化/復号化の機能を提供する。
 */
public class RSACipherHelper implements CipherHelper, InitializingBean {

	/** RSA暗号アルゴリズムを使用するためのアルゴリズム名を保持する。 */
	private String algorithm = "RSA/ECB/PKCS1Padding";

	/** RSA暗号アルゴリズムで使用する公開鍵が定義されたファイルのパスを保持する。 */
	private Resource publicKey;

	/** RSA暗号アルゴリズムで使用する秘密鍵が定義されたファイルのパスを保持する。 */
	private Resource privateKey;

	/** 鍵を読込む機能を保持する。 */
	private KeyLoader keyLoader;

	/** RSA暗号アルゴリズムで使用する公開鍵を保持する。 */
	private PublicKey pubKey;

	/** RSA暗号アルゴリズムで使用する秘密鍵を保持する。 */
	private PrivateKey privKey;

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public void setPublicKey(Resource publicKey) {
		this.publicKey = publicKey;
	}

	public void setPrivateKey(Resource privateKey) {
		this.privateKey = privateKey;
	}

	public void setKeyLoader(KeyLoader keyLoader) {
		this.keyLoader = keyLoader;
	}

	/**
	 * RSA暗号アルゴリズムによる暗号化/復号化の機能を初期化する。<br />
	 * 具体的には、ファイルのパスとして指定された公開鍵と秘密鍵を、ファイルから読込み、復号化し、JCE APIで使用する形式で保持する。
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		try (InputStream in = publicKey.getInputStream()) {
			pubKey = keyLoader.loadPublicKey(in);
		}
		try (InputStream in = privateKey.getInputStream()) {
			privKey = keyLoader.loadPrivateKey(in);
		}
	}

	@Override
	public byte[] encrypt(byte[] in) {
		try {
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			return cipher.doFinal(in);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException ex) {
			throw new IllegalStateException(ex);
		}
	}

	@Override
	public byte[] decrypt(byte[] in) {
		try {
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.DECRYPT_MODE, privKey);
			return cipher.doFinal(in);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException ex) {
			throw new IllegalStateException(ex);
		}
	}

}
