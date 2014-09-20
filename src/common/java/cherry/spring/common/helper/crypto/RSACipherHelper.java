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

package cherry.spring.common.helper.crypto;

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

public class RSACipherHelper implements CipherHelper, InitializingBean {

	private String algorithm = "RSA/ECB/PKCS1Padding";

	private Resource publicKey;

	private Resource privateKey;

	private KeyLoader keyLoader;

	private PublicKey pubKey;

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
