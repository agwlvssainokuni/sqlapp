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

package cherry.spring.common.custom;

import java.io.Serializable;

public class SecureString implements Serializable {

	private static final long serialVersionUID = 1L;

	public interface Encoder {

		String encode(String s);

		String decode(String s);
	}

	private static Encoder encoder = new Encoder() {

		@Override
		public String encode(String s) {
			return s;
		}

		@Override
		public String decode(String s) {
			return s;
		}
	};

	public static Encoder setEncoder(Encoder e) {
		encoder = e;
		return encoder;
	}

	public static SecureString plainValueOf(String s) {
		return new SecureString(s, null);
	}

	public static SecureString cryptoValueOf(String s) {
		return new SecureString(null, s);
	}

	private String p;

	private String c;

	private SecureString(String p, String c) {
		this.p = p;
		this.c = c;
	}

	public String plain() {
		if (p == null) {
			p = encoder.decode(c);
		}
		return p;
	}

	public String crypto() {
		if (c == null) {
			c = encoder.encode(p);
		}
		return c;
	}

	@Override
	public String toString() {
		return (new StringBuilder("SecureString[")).append(plain()).append("]")
				.toString();
	}

}
