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

package cherry.foundation.type;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FlagCodeTest {

	@Test
	public void test() {
		for (int i = -50; i <= 50; i++) {
			FlagCode flag = FlagCode.valueOf(i);
			if (i == 0) {
				assertThat(flag, is(FlagCode.FALSE));
				assertThat(flag.code(), is(0));
				assertThat(flag.booleanValue(), is(false));
			} else {
				assertThat(flag, is(FlagCode.TRUE));
				assertThat(flag.code(), is(1));
				assertThat(flag.booleanValue(), is(true));
			}
		}
	}

	@Test
	public void testValueOf() {
		assertThat(FlagCode.valueOf(true), is(FlagCode.TRUE));
		assertThat(FlagCode.valueOf("TRUE"), is(FlagCode.TRUE));
		assertThat(FlagCode.valueOf(false), is(FlagCode.FALSE));
		assertThat(FlagCode.valueOf("FALSE"), is(FlagCode.FALSE));
	}

}
