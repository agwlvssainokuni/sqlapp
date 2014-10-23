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

package cherry.spring.fwcore.type;

import java.math.BigDecimal;

public class SecureBigDecimal extends SecureTypeBase<BigDecimal> {

	private static final long serialVersionUID = 1L;

	private static Encoder<BigDecimal> encoder = new Encoder<BigDecimal>() {

		@Override
		public String encode(BigDecimal bd) {
			if (bd == null) {
				return null;
			} else {
				return bd.toPlainString();
			}
		}

		@Override
		public BigDecimal decode(String s) {
			if (s == null) {
				return null;
			} else {
				return new BigDecimal(s);
			}
		}
	};

	public static Encoder<BigDecimal> setEncoder(Encoder<BigDecimal> e) {
		encoder = e;
		return encoder;
	}

	public static SecureBigDecimal plainValueOf(BigDecimal bd) {
		return new SecureBigDecimal(bd, null, encoder);
	}

	public static SecureBigDecimal cryptoValueOf(String s) {
		return new SecureBigDecimal(null, s, encoder);
	}

	private SecureBigDecimal(BigDecimal p, String c, Encoder<BigDecimal> e) {
		super(p, c, e);
	}

}
