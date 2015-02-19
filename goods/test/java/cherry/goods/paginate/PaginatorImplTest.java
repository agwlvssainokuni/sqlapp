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
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class PaginatorImplTest {

	private Paginator paginator = create();

	private Paginator create() {
		TotalCountStrategy strategy = new TotalCountStrategy();
		strategy.setTotalCount(5);
		strategy.setLowerSideHint(2);
		strategy.setLowerTrim(0);
		strategy.setUpperTrim(0);
		PaginatorImpl p = new PaginatorImpl();
		p.setPaginateStrategy(strategy);
		return p;
	}

	@Test
	public void testNew() {
		assertNotNull(create());
	}

	@Test
	public void testGetPageCount() {
		assertEquals(0, paginator.getPageCount(0, 10));
		assertEquals(1, paginator.getPageCount(10, 10));
		assertEquals(2, paginator.getPageCount(11, 10));
		assertEquals(2, paginator.getPageCount(20, 10));
		assertEquals(3, paginator.getPageCount(21, 10));
	}

	@Test
	public void testGetPage() {

		Page page;

		page = paginator.getPage(0, 0, 10);
		assertEquals(0, page.getNo());
		assertEquals(0, page.getCount());
		assertEquals(0, page.getFrom());
		assertEquals(-1, page.getTo());

		page = paginator.getPage(-1, 21, 10);
		assertEquals(0, page.getNo());
		assertEquals(10, page.getCount());
		assertEquals(0, page.getFrom());
		assertEquals(9, page.getTo());

		page = paginator.getPage(0, 21, 10);
		assertEquals(0, page.getNo());
		assertEquals(10, page.getCount());
		assertEquals(0, page.getFrom());
		assertEquals(9, page.getTo());

		page = paginator.getPage(1, 21, 10);
		assertEquals(1, page.getNo());
		assertEquals(10, page.getCount());
		assertEquals(10, page.getFrom());
		assertEquals(19, page.getTo());

		page = paginator.getPage(2, 21, 10);
		assertEquals(2, page.getNo());
		assertEquals(1, page.getCount());
		assertEquals(20, page.getFrom());
		assertEquals(20, page.getTo());

		page = paginator.getPage(3, 21, 10);
		assertEquals(2, page.getNo());
		assertEquals(1, page.getCount());
		assertEquals(20, page.getFrom());
		assertEquals(20, page.getTo());

		page = paginator.getPage(3, 30, 10);
		assertEquals(2, page.getNo());
		assertEquals(10, page.getCount());
		assertEquals(20, page.getFrom());
		assertEquals(29, page.getTo());
	}

	@Test
	public void testPaginateMain() {
		PageSet pageSet = paginator.paginate(4, 80, 10);

		assertEquals(4, pageSet.getCurrent().getNo());
		assertEquals(10, pageSet.getCurrent().getCount());
		assertEquals(40, pageSet.getCurrent().getFrom());
		assertEquals(49, pageSet.getCurrent().getTo());

		assertEquals(0, pageSet.getFirst().getNo());
		assertEquals(10, pageSet.getFirst().getCount());
		assertEquals(0, pageSet.getFirst().getFrom());
		assertEquals(9, pageSet.getFirst().getTo());

		assertEquals(3, pageSet.getPrev().getNo());
		assertEquals(10, pageSet.getPrev().getCount());
		assertEquals(30, pageSet.getPrev().getFrom());
		assertEquals(39, pageSet.getPrev().getTo());

		assertEquals(5, pageSet.getNext().getNo());
		assertEquals(10, pageSet.getNext().getCount());
		assertEquals(50, pageSet.getNext().getFrom());
		assertEquals(59, pageSet.getNext().getTo());

		assertEquals(7, pageSet.getLast().getNo());
		assertEquals(10, pageSet.getLast().getCount());
		assertEquals(70, pageSet.getLast().getFrom());
		assertEquals(79, pageSet.getLast().getTo());

		assertEquals(5, pageSet.getRange().size());
		int p = 2;
		for (Page page : pageSet.getRange()) {
			assertEquals(p, page.getNo());
			p += 1;
		}
	}

	@Test
	public void testPaginateEmpty() {

		PageSet pageSet = paginator.paginate(0, 0, 10);

		assertEquals(0, pageSet.getFirst().getNo());
		assertEquals(0, pageSet.getFirst().getCount());
		assertEquals(0, pageSet.getFirst().getFrom());
		assertEquals(-1, pageSet.getFirst().getTo());

		assertEquals(0, pageSet.getPrev().getNo());
		assertEquals(0, pageSet.getPrev().getCount());
		assertEquals(0, pageSet.getPrev().getFrom());
		assertEquals(-1, pageSet.getPrev().getTo());

		assertEquals(0, pageSet.getCurrent().getNo());
		assertEquals(0, pageSet.getCurrent().getCount());
		assertEquals(0, pageSet.getCurrent().getFrom());
		assertEquals(-1, pageSet.getCurrent().getTo());

		assertEquals(0, pageSet.getNext().getNo());
		assertEquals(0, pageSet.getNext().getCount());
		assertEquals(0, pageSet.getNext().getFrom());
		assertEquals(-1, pageSet.getNext().getTo());

		assertEquals(0, pageSet.getLast().getNo());
		assertEquals(0, pageSet.getLast().getCount());
		assertEquals(0, pageSet.getLast().getFrom());
		assertEquals(-1, pageSet.getLast().getTo());
	}

	@Test
	public void testPaginateNegative() {
		PageSet pageSet = paginator.paginate(-1, 80, 10);
		assertEquals(0, pageSet.getPrev().getNo());
		assertEquals(0, pageSet.getCurrent().getNo());
		assertEquals(1, pageSet.getNext().getNo());
	}

	@Test
	public void testPaginateFirst() {
		PageSet pageSet = paginator.paginate(0, 80, 10);
		assertEquals(0, pageSet.getPrev().getNo());
		assertEquals(0, pageSet.getCurrent().getNo());
		assertEquals(1, pageSet.getNext().getNo());
	}

	@Test
	public void testPaginate2nd() {
		PageSet pageSet = paginator.paginate(1, 80, 10);
		assertEquals(0, pageSet.getPrev().getNo());
		assertEquals(1, pageSet.getCurrent().getNo());
		assertEquals(2, pageSet.getNext().getNo());
	}

	@Test
	public void testPaginatePreLast() {
		PageSet pageSet = paginator.paginate(6, 80, 10);
		assertEquals(5, pageSet.getPrev().getNo());
		assertEquals(6, pageSet.getCurrent().getNo());
		assertEquals(7, pageSet.getNext().getNo());
	}

	@Test
	public void testPaginateLast() {
		PageSet pageSet = paginator.paginate(7, 80, 10);
		assertEquals(6, pageSet.getPrev().getNo());
		assertEquals(7, pageSet.getCurrent().getNo());
		assertEquals(7, pageSet.getNext().getNo());
	}

	@Test
	public void testPaginateOver() {
		PageSet pageSet = paginator.paginate(8, 80, 10);
		assertEquals(6, pageSet.getPrev().getNo());
		assertEquals(7, pageSet.getCurrent().getNo());
		assertEquals(7, pageSet.getNext().getNo());
	}

}
