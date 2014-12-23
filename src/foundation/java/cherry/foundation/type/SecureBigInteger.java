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

package cherry.foundation.type;

import java.math.BigInteger;

public class SecureBigInteger extends SecureTypeBase<BigInteger> {

	private static final long serialVersionUID = 1L;

	private static Encoder<BigInteger> encoder;

	public static Encoder<BigInteger> setEncoder(Encoder<BigInteger> e) {
		encoder = e;
		return encoder;
	}

	public static SecureBigInteger plainValueOf(BigInteger bi) {
		return new SecureBigInteger(bi, null, encoder);
	}

	public static SecureBigInteger cryptoValueOf(String s) {
		return new SecureBigInteger(null, s, encoder);
	}

	private SecureBigInteger(BigInteger p, String c, Encoder<BigInteger> e) {
		super(p, c, e);
	}

	public static class NoneEncoder implements Encoder<BigInteger> {

		@Override
		public String encode(BigInteger bi) {
			if (bi == null) {
				return null;
			} else {
				return bi.toString();
			}
		}

		@Override
		public BigInteger decode(String s) {
			if (s == null) {
				return null;
			} else {
				return new BigInteger(s);
			}
		}
	}

}
