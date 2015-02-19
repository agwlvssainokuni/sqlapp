/*
 * Copyright 2014,2015 agwlvssainokuni
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

/**
 * 暗号化/復号化の機能のインタフェースを規定する。
 */
public interface Crypto {

	/**
	 * 暗号化する。
	 * 
	 * @param in 平文。
	 * @return 暗号文。
	 */
	byte[] encrypt(byte[] in);

	/**
	 * 復号化する。
	 * 
	 * @param in 暗号文。
	 * @return 平文。
	 */
	byte[] decrypt(byte[] in);

}
