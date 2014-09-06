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

public class AESCipherHelper implements CipherHelper, InitializingBean {

	private String algorithm = "AES/CBC/PKCS5Padding";

	private String keyAlgorithm = "AES";

	private Resource secretKey;

	private Resource initVector;

	private CipherHelper keyCipherHelper = new NullCipherHelper();

	private Key secKey;

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

	@Override
	public void afterPropertiesSet() throws IOException {
		byte[] keyBin = keyCipherHelper.decrypt(load(secretKey));
		secKey = new SecretKeySpec(keyBin, keyAlgorithm);
		if (initVector != null) {
			byte[] ivBin = keyCipherHelper.decrypt(load(initVector));
			initVec = new IvParameterSpec(ivBin);
		}
	}

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
