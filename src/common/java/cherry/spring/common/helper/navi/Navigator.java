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

package cherry.spring.common.helper.navi;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public interface Navigator {

	List<Node> navigate(String name);

	public static class Node {

		private String name;

		private String uri;

		private boolean last;

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this,
					ToStringStyle.SHORT_PREFIX_STYLE);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUri() {
			return uri;
		}

		public void setUri(String uri) {
			this.uri = uri;
		}

		public boolean isLast() {
			return last;
		}

		public void setLast(boolean last) {
			this.last = last;
		}
	}

}
