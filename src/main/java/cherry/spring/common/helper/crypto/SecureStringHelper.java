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

import java.nio.charset.Charset;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import cherry.spring.common.custom.SecureString.Encoder;

public class SecureStringHelper implements Encoder {

	private Charset charset;

	private CipherHelper cipherHelper;

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	public void setCipherHelper(CipherHelper cipherHelper) {
		this.cipherHelper = cipherHelper;
	}

	@Override
	public String encode(String s) {
		byte[] plain = s.getBytes(charset);
		byte[] crypto = cipherHelper.encrypt(plain);
		return new String(Hex.encodeHex(crypto));
	}

	@Override
	public String decode(String s) {
		try {
			byte[] crypto = Hex.decodeHex(s.toCharArray());
			byte[] plain = cipherHelper.decrypt(crypto);
			return new String(plain, charset);
		} catch (DecoderException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

}
