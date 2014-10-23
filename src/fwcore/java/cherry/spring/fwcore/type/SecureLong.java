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

public class SecureLong extends SecureTypeBase<Long> {

	private static final long serialVersionUID = 1L;

	private static Encoder<Long> encoder = new Encoder<Long>() {

		@Override
		public String encode(Long l) {
			if (l == null) {
				return null;
			} else {
				return l.toString();
			}
		}

		@Override
		public Long decode(String s) {
			if (s == null) {
				return null;
			} else {
				return Long.parseLong(s);
			}
		}
	};

	public static Encoder<Long> setEncoder(Encoder<Long> e) {
		encoder = e;
		return encoder;
	}

	public static SecureLong plainValueOf(Long l) {
		return new SecureLong(l, null, encoder);
	}

	public static SecureLong cryptoValueOf(String s) {
		return new SecureLong(null, s, encoder);
	}

	private SecureLong(Long p, String c, Encoder<Long> e) {
		super(p, c, e);
	}

}
