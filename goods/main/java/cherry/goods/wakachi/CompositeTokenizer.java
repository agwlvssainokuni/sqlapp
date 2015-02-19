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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CompositeTokenizer implements Tokenizer {

	private List<Tokenizer> tokenizers;

	public void setTokenizers(List<Tokenizer> tokenizers) {
		this.tokenizers = tokenizers;
	}

	@Override
	public List<String> tokenize(String text) {
		List<String> list = Arrays.asList(text);
		for (Tokenizer tk : tokenizers) {
			list = doTokenize(tk, list);
		}
		return list;
	}

	private List<String> doTokenize(Tokenizer tk, List<String> src) {
		List<String> list = new LinkedList<>();
		for (String text : src) {
			list.addAll(tk.tokenize(text));
		}
		return list;
	}

}
