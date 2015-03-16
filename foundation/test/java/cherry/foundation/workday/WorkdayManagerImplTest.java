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

package cherry.foundation.workday;

import static org.joda.time.LocalDate.now;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cherry.foundation.bizdtm.SimpleBizDateTime;
import cherry.foundation.type.Code;

public class WorkdayManagerImplTest {

	@Test
	public void testGetNumberOfWorkday() {
		WorkdayManager workdayManager = create();
		assertEquals(3, workdayManager.getNumberOfWorkday(now().plusDays(2)));
		assertEquals(4, workdayManager.getNumberOfWorkday("name1", now().plusDays(3)));
		assertEquals(5, workdayManager.getNumberOfWorkday(code("name2"), now().plusDays(4)));
		assertEquals(2, workdayManager.getNumberOfWorkday(now().plusDays(1), now().plusDays(2)));
		assertEquals(3, workdayManager.getNumberOfWorkday("name1", now().plusDays(1), now().plusDays(3)));
		assertEquals(4, workdayManager.getNumberOfWorkday(code("name2"), now().plusDays(1), now().plusDays(4)));
	}

	@Test
	public void testGetNextWorkday() {
		WorkdayManager workdayManager = create();
		assertEquals(now().plusDays(2), workdayManager.getNextWorkday(3));
		assertEquals(now().plusDays(3), workdayManager.getNextWorkday("name1", 4));
		assertEquals(now().plusDays(4), workdayManager.getNextWorkday(code("name2"), 5));
		assertEquals(now().plusDays(2), workdayManager.getNextWorkday(now().plusDays(1), 2));
		assertEquals(now().plusDays(3), workdayManager.getNextWorkday("name1", now().plusDays(1), 3));
		assertEquals(now().plusDays(4), workdayManager.getNextWorkday(code("name2"), now().plusDays(1), 4));
	}

	private WorkdayManager create() {
		WorkdayManagerImpl impl = new WorkdayManagerImpl();
		impl.setWorkdayStore(new SimpleWorkdayStore());
		impl.setDefaultName("standard");
		impl.setNumberOfWorkdayCacheSpec("maximumSize=100,expireAfterAccess=60m");
		impl.setNextWorkdayCacheSpec("maximumSize=100,expireAfterAccess=60m");
		impl.setBizDateTime(new SimpleBizDateTime());
		impl.afterPropertiesSet();
		return impl;
	}

	private Code<String> code(final String name) {
		return new Code<String>() {
			@Override
			public String code() {
				return name;
			}
		};
	}

}
