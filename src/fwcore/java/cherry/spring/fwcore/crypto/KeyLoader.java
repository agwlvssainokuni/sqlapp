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

import java.io.IOException;
import java.io.InputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

/**
 * 公開鍵暗号の鍵を読込む機能のインタフェースを規定する。
 */
public interface KeyLoader {

	/**
	 * 公開鍵を読込む。
	 * 
	 * @param in
	 *            読込み元。
	 * @return 公開鍵。
	 * @throws IOException
	 *             読込みで異常が発生したことを表す。
	 * @throws InvalidKeySpecException
	 *             読込んだ鍵データの形式が正しくないことを表す。
	 */
	PublicKey loadPublicKey(InputStream in) throws IOException,
			InvalidKeySpecException;

	/**
	 * 秘密鍵を読込む。
	 * 
	 * @param in
	 *            読込み元。
	 * @return 秘密鍵。
	 * @throws IOException
	 *             読込みで異常が発生したことを表す。
	 * @throws InvalidKeySpecException
	 *             読込んだ鍵データの形式が正しくないことを表す。
	 */
	PrivateKey loadPrivateKey(InputStream in) throws IOException,
			InvalidKeySpecException;

}
