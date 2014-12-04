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

import java.io.InputStream;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import cherry.goods.crypto.RSACrypto;

import com.google.common.io.ByteStreams;

/**
 * RSA暗号アルゴリズムによる暗号化/復号化の機能を提供する。
 */
public class RSACryptoSupport extends RSACrypto implements InitializingBean {

	/** RSA暗号アルゴリズムで使用する公開鍵が定義されたファイルのパスを保持する。 */
	private Resource publicKeyResource;

	/** RSA暗号アルゴリズムで使用する秘密鍵が定義されたファイルのパスを保持する。 */
	private Resource privateKeyResource;

	public void setPublicKeyResource(Resource publicKeyResource) {
		this.publicKeyResource = publicKeyResource;
	}

	public void setPrivateKeyResource(Resource privateKeyResource) {
		this.privateKeyResource = privateKeyResource;
	}

	/**
	 * RSA暗号アルゴリズムによる暗号化/復号化の機能を初期化する。<br />
	 * 具体的には、ファイルのパスとして指定された公開鍵と秘密鍵を、ファイルから読込み、復号化し、JCE APIで使用する形式で保持する。
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		try (InputStream in = publicKeyResource.getInputStream()) {
			setPublicKeyBytes(ByteStreams.toByteArray(in));
		}
		try (InputStream in = privateKeyResource.getInputStream()) {
			setPrivateKeyBytes(ByteStreams.toByteArray(in));
		}
	}

}
