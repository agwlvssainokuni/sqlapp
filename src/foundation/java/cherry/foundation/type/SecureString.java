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

public class SecureString extends SecureTypeBase<String> {

	private static final long serialVersionUID = 1L;

	private static Encoder<String> encoder = new Encoder<String>() {

		@Override
		public String encode(String s) {
			return s;
		}

		@Override
		public String decode(String s) {
			return s;
		}
	};

	public static Encoder<String> setEncoder(Encoder<String> e) {
		encoder = e;
		return encoder;
	}

	public static SecureString plainValueOf(String s) {
		return new SecureString(s, null, encoder);
	}

	public static SecureString cryptoValueOf(String s) {
		return new SecureString(null, s, encoder);
	}

	private SecureString(String p, String c, Encoder<String> e) {
		super(p, c, e);
	}

}
