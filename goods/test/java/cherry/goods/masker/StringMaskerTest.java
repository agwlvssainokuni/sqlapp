/*
 * Copyright 2014,2015 agwlvssainokuni
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

package cherry.goods.masker;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StringMaskerTest {

	@Test
	public void testTail() {
		StringMasker masker = StringMasker.tail("*", 2);
		assertThat(masker.mask(null), is(nullValue(String.class)));
		assertThat(masker.mask(""), is(""));
		assertThat(masker.mask("a"), is("a"));
		assertThat(masker.mask("ab"), is("ab"));
		assertThat(masker.mask("abc"), is("ab*"));
		assertThat(masker.mask("abcd"), is("ab**"));
		assertThat(masker.mask("abcde"), is("ab***"));
		assertThat(masker.mask("abcdef"), is("ab****"));
	}

	@Test
	public void testFixedTail() {
		StringMasker masker = StringMasker.fixedTail("*", 2, 4);
		assertThat(masker.mask(null), is(nullValue(String.class)));
		assertThat(masker.mask(""), is("****"));
		assertThat(masker.mask("a"), is("a***"));
		assertThat(masker.mask("ab"), is("ab**"));
		assertThat(masker.mask("abc"), is("ab**"));
		assertThat(masker.mask("abcd"), is("ab**"));
		assertThat(masker.mask("abcde"), is("ab**"));
		assertThat(masker.mask("abcdef"), is("ab**"));
	}

}
