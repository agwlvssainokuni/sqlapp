/*
 * Copyright 2004,2015 agwlvssainokuni
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

public class PageSetTest {

	@Test
	public void testGetterSetter() {

		Page p0 = createPage(0, 0, 0, 1);
		Page p1 = createPage(1, 1, 1, 1);
		Page p2 = createPage(2, 2, 2, 1);
		Page p3 = createPage(3, 3, 3, 1);
		Page p4 = createPage(4, 4, 4, 1);

		PageSet pageSet = new PageSet();
		pageSet.setFirst(p0);
		pageSet.setPrev(p1);
		pageSet.setCurrent(p2);
		pageSet.setNext(p3);
		pageSet.setLast(p4);
		pageSet.setPageSz(1);
		pageSet.setTotalCount(5);
		pageSet.setRange(Arrays.asList(p0, p1, p2, p3, p4));

		assertEquals(p0, pageSet.getFirst());
		assertEquals(p1, pageSet.getPrev());
		assertEquals(p2, pageSet.getCurrent());
		assertEquals(p3, pageSet.getNext());
		assertEquals(p4, pageSet.getLast());
		assertEquals(1L, pageSet.getPageSz());
		assertEquals(5L, pageSet.getTotalCount());
		assertEquals(5, pageSet.getRange().size());
		assertEquals(p0, pageSet.getRange().get(0));
		assertEquals(p1, pageSet.getRange().get(1));
		assertEquals(p2, pageSet.getRange().get(2));
		assertEquals(p3, pageSet.getRange().get(3));
		assertEquals(p4, pageSet.getRange().get(4));
	}

	@Test
	public void testToString() {

		Page p0 = createPage(0, 0, 0, 1);
		Page p1 = createPage(1, 1, 1, 1);
		Page p2 = createPage(2, 2, 2, 1);
		Page p3 = createPage(3, 3, 3, 1);
		Page p4 = createPage(4, 4, 4, 1);

		PageSet pageSet = new PageSet();
		pageSet.setFirst(p0);
		pageSet.setPrev(p1);
		pageSet.setCurrent(p2);
		pageSet.setNext(p3);
		pageSet.setLast(p4);
		pageSet.setPageSz(1);
		pageSet.setTotalCount(5);
		pageSet.setRange(Arrays.asList(p0, p1, p2, p3, p4));

		assertEquals(
				"PageSet[totalCount=5,pageSz=1,current=Page[no=2,count=1,from=2,to=2],prev=Page[no=1,count=1,from=1,to=1],next=Page[no=3,count=1,from=3,to=3],first=Page[no=0,count=1,from=0,to=0],last=Page[no=4,count=1,from=4,to=4],range=[Page[no=0,count=1,from=0,to=0], Page[no=1,count=1,from=1,to=1], Page[no=2,count=1,from=2,to=2], Page[no=3,count=1,from=3,to=3], Page[no=4,count=1,from=4,to=4]]]",
				pageSet.toString());
	}

	private Page createPage(long no, long from, long to, long count) {
		Page p = new Page();
		p.setNo(no);
		p.setFrom(from);
		p.setTo(to);
		p.setCount(count);
		return p;
	}

}
