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

public class DeletedFlag implements Code<Integer>, Comparable<DeletedFlag>,
		Serializable {

	private static final long serialVersionUID = 1L;

	public static final DeletedFlag NOT_DELETED = new DeletedFlag(0);

	private final int code;

	public DeletedFlag(int code) {
		this.code = code;
	}

	@Override
	public Integer code() {
		return code;
	}

	public boolean isDeleted() {
		return this.code != 0;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder(getClass().getSimpleName());
		b.append("[").append(isDeleted()).append(",code=").append(code)
				.append("]");
		return b.toString();
	}

	@Override
	public int hashCode() {
		return Integer.valueOf(code).hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof DeletedFlag)) {
			return false;
		}
		return code == ((DeletedFlag) object).code;
	}

	@Override
	public int compareTo(DeletedFlag object) {
		if (object == null) {
			return 1;
		}
		return code - object.code;
	}

}
