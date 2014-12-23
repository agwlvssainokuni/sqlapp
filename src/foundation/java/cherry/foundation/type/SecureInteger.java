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

public class SecureInteger extends SecureTypeBase<Integer> {

	private static final long serialVersionUID = 1L;

	private static Encoder<Integer> encoder;

	public static Encoder<Integer> setEncoder(Encoder<Integer> e) {
		encoder = e;
		return encoder;
	}

	public static SecureInteger plainValueOf(Integer i) {
		return new SecureInteger(i, null, encoder);
	}

	public static SecureInteger cryptoValueOf(String s) {
		return new SecureInteger(null, s, encoder);
	}

	private SecureInteger(Integer p, String c, Encoder<Integer> e) {
		super(p, c, e);
	}

	public static class NoneEncoder implements Encoder<Integer> {

		@Override
		public String encode(Integer i) {
			if (i == null) {
				return null;
			} else {
				return i.toString();
			}
		}

		@Override
		public Integer decode(String s) {
			if (s == null) {
				return null;
			} else {
				return Integer.parseInt(s);
			}
		}
	}

}
