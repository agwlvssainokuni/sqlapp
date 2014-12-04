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

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * RSA暗号アルゴリズムによる暗号化/復号化の機能を提供する。
 */
public class RSACrypto implements Crypto {

	/** RSA暗号アルゴリズムを使用するためのアルゴリズム名を保持する。 */
	private String algorithm = "RSA/ECB/PKCS1Padding";

	/** RSA暗号アルゴリズムで使用する公開鍵を保持する。 */
	private PublicKey publicKey;

	/** RSA暗号アルゴリズムで使用する秘密鍵を保持する。 */
	private PrivateKey privateKey;

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public void setPublicKeyBytes(byte[] publicKeyBytes)
			throws InvalidKeySpecException {
		this.publicKey = KeyUtil.createRsaPublicKey(publicKeyBytes);
	}

	public void setPrivateKeyBytes(byte[] privateKeyBytes)
			throws InvalidKeySpecException {
		this.privateKey = KeyUtil.createRsaPrivateKey(privateKeyBytes);
	}

	@Override
	public byte[] encrypt(byte[] in) {
		try {
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
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
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			return cipher.doFinal(in);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException ex) {
			throw new IllegalStateException(ex);
		}
	}

}
