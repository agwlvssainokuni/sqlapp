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
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DeletedFlagTest {

	@Test
	public void test() {
		for (int i = -1024; i <= 1024; i++) {
			DeletedFlag df = new DeletedFlag(i);
			assertThat(df.code(), is(i));
			assertThat(df.compareTo(DeletedFlag.NOT_DELETED), is(i));
			assertThat(df.hashCode(), is(Integer.valueOf(i).hashCode()));
			if (i == 0) {
				assertThat(df, is(DeletedFlag.NOT_DELETED));
				assertThat(df.isDeleted(), is(false));
				assertThat(df.toString(), is("DeletedFlag[false,code=" + i + "]"));
			} else {
				assertThat(df, is(not(DeletedFlag.NOT_DELETED)));
				assertThat(df.isDeleted(), is(true));
				assertThat(df.toString(), is("DeletedFlag[true,code=" + i + "]"));
			}
		}
	}

	@Test
	public void testTypeMismatch() {
		assertThat(DeletedFlag.NOT_DELETED.equals("0"), is(false));
		assertThat(DeletedFlag.NOT_DELETED.compareTo(null), is(1));
	}

}
