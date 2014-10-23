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

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class SecureTypeBase<T> implements SecureType<T>, Serializable {

	private T p;

	private String c;

	private Encoder<T> e;

	protected SecureTypeBase(T p, String c, Encoder<T> e) {
		this.p = p;
		this.c = c;
		this.e = e;
	}

	@Override
	public T plain() {
		if (p == null) {
			p = e.decode(c);
		}
		return p;
	}

	@Override
	public String crypto() {
		if (c == null) {
			c = e.encode(p);
		}
		return c;
	}

	@Override
	public String toString() {
		return (new StringBuilder(getClass().getSimpleName())).append("[")
				.append(plain()).append("]").toString();
	}

}
