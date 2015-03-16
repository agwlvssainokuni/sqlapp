/*
 * Copyright 2015 agwlvssainokuni
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

package cherry.goods.wakachi;

import static java.lang.Character.UnicodeBlock.BASIC_LATIN;
import static java.lang.Character.UnicodeBlock.of;

import java.util.LinkedList;
import java.util.List;

public class NgramTokenizer implements Tokenizer {

	private boolean applyToAscii;

	private int length;

	public void setApplyToAscii(boolean applyToAscii) {
		this.applyToAscii = applyToAscii;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public List<String> tokenize(String text) {
		List<String> list = new LinkedList<>();
		if (text.isEmpty()) {
			return list;
		}
		if (of(text.charAt(0)) == BASIC_LATIN && !applyToAscii) {
			list.add(text);
			return list;
		}
		for (int i = length; i <= text.length(); i++) {
			list.add(text.substring(i - length, i));
		}
		if (list.isEmpty()) {
			list.add(text);
		}
		return list;
	}

}
