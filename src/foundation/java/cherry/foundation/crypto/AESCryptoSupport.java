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

package cherry.foundation.crypto;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import cherry.goods.crypto.AESCrypto;

import com.google.common.io.ByteStreams;

/**
 * AES暗号アルゴリズムによる暗号化/復号化の機能を提供する。
 */
public class AESCryptoSupport extends AESCrypto implements InitializingBean {

	/** AES暗号アルゴリズムで使用する共通鍵が定義されたファイルのパスを保持する。 */
	private Resource secretKeyResource;

	/** AES暗号アルゴリズムで使用する初期化ベクタが定義されたファイルのパスを保持する。 */
	private Resource initVectorResource;

	public void setSecretKeyResource(Resource secretKeyResource) {
		this.secretKeyResource = secretKeyResource;
	}

	public void setInitVectorResource(Resource initVectorResource) {
		this.initVectorResource = initVectorResource;
	}

	/**
	 * AES暗号アルゴリズムによる暗号化/復号化の機能を初期化する。<br />
	 * 具体的には、ファイルのパスとして指定された共通鍵と初期化ベクタを、ファイルから読込み、復号化し、JCE APIで使用する形式で保持する。
	 */
	@Override
	public void afterPropertiesSet() throws IOException {
		try (InputStream in = secretKeyResource.getInputStream()) {
			setSecretKeyBytes(ByteStreams.toByteArray(in));
		}
		if (initVectorResource != null) {
			try (InputStream in = initVectorResource.getInputStream()) {
				setInitVectorBytes(ByteStreams.toByteArray(in));
			}
		}
	}

}
