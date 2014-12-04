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

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import cherry.foundation.type.SecureType.Encoder;
import cherry.goods.crypto.Crypto;

public abstract class SecureTypeEncoder<T> implements Encoder<T> {

	private Crypto crypto;

	public void setCrypto(Crypto crypto) {
		this.crypto = crypto;
	}

	@Override
	public String encode(T p) {
		byte[] pln = typeToBytes(p);
		byte[] crp = crypto.encrypt(pln);
		return new String(Hex.encodeHex(crp));
	}

	@Override
	public T decode(String c) {
		try {
			byte[] crp = Hex.decodeHex(c.toCharArray());
			byte[] pln = crypto.decrypt(crp);
			return bytesToType(pln);
		} catch (DecoderException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	protected abstract byte[] typeToBytes(T p);

	protected abstract T bytesToType(byte[] p);

}
