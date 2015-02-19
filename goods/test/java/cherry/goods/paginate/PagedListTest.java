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

package cherry.goods.paginate;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class PagedListTest {

	@Test
	public void testSetterGetter() {

		PageSet pageSet = new PageSet();
		PagedList<Long> pagedList = new PagedList<>();
		pagedList.setPageSet(pageSet);
		pagedList.setList(Arrays.asList(1L, 2L, 3L));

		assertEquals(pageSet, pagedList.getPageSet());
		assertEquals(3, pagedList.getList().size());
		assertEquals(1L, pagedList.getList().get(0).longValue());
		assertEquals(2L, pagedList.getList().get(1).longValue());
		assertEquals(3L, pagedList.getList().get(2).longValue());
	}

	@Test
	public void testToString() {

		PageSet pageSet = new PageSet();
		PagedList<Long> pagedList = new PagedList<>();
		pagedList.setPageSet(pageSet);
		pagedList.setList(Arrays.asList(1L, 2L, 3L));

		assertEquals(
				"PagedList[pageSet=PageSet[totalCount=0,pageSz=0,current=<null>,prev=<null>,next=<null>,first=<null>,last=<null>,range=<null>],list=[1, 2, 3]]",
				pagedList.toString());
	}

}
