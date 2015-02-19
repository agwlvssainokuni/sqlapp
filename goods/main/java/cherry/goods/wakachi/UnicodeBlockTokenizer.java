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

import java.lang.Character.UnicodeBlock;
import java.util.LinkedList;
import java.util.List;

public class UnicodeBlockTokenizer implements Tokenizer {

	@Override
	public List<String> tokenize(String text) {
		int beginIndex = -1;
		UnicodeBlock current = null;
		List<String> list = new LinkedList<>();
		for (int i = 0; i < text.length(); i++) {
			UnicodeBlock block = UnicodeBlock.of(text.charAt(i));
			if (current != block) {
				if (beginIndex >= 0) {
					list.add(text.substring(beginIndex, i));
				}
				beginIndex = i;
				current = block;
			}
		}
		if (beginIndex >= 0) {
			list.add(text.substring(beginIndex));
		}
		return list;
	}

}
