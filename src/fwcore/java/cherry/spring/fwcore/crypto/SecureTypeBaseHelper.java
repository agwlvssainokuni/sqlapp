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

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import cherry.spring.fwcore.type.SecureType.Encoder;

public abstract class SecureTypeBaseHelper<T> implements Encoder<T> {

	private CipherHelper cipherHelper;

	public void setCipherHelper(CipherHelper cipherHelper) {
		this.cipherHelper = cipherHelper;
	}

	@Override
	public String encode(T p) {
		byte[] plain = typeToBytes(p);
		byte[] crypto = cipherHelper.encrypt(plain);
		return new String(Hex.encodeHex(crypto));
	}

	@Override
	public T decode(String c) {
		try {
			byte[] crypto = Hex.decodeHex(c.toCharArray());
			byte[] plain = cipherHelper.decrypt(crypto);
			return bytesToType(plain);
		} catch (DecoderException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	protected abstract byte[] typeToBytes(T p);

	protected abstract T bytesToType(byte[] p);

}
