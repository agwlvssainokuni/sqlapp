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

import java.util.Iterator;

import org.junit.Test;

public class TotalCountStrategyTest {

	@Test
	public void testCaclulate41() {

		TotalCountStrategy strategy = new TotalCountStrategy();
		strategy.setTotalCount(4);
		strategy.setLowerSideHint(1);
		strategy.setLowerTrim(0);
		strategy.setUpperTrim(0);

		check(new Range(0, 3), strategy.calculate(0, 10));
		check(new Range(0, 3), strategy.calculate(1, 10));
		check(new Range(1, 4), strategy.calculate(2, 10));
		check(new Range(2, 5), strategy.calculate(3, 10));
		check(new Range(3, 6), strategy.calculate(4, 10));
		check(new Range(4, 7), strategy.calculate(5, 10));
		check(new Range(5, 8), strategy.calculate(6, 10));
		check(new Range(6, 9), strategy.calculate(7, 10));
		check(new Range(6, 9), strategy.calculate(8, 10));
		check(new Range(6, 9), strategy.calculate(9, 10));
	}

	@Test
	public void testCaclulate42() {

		TotalCountStrategy strategy = new TotalCountStrategy();
		strategy.setTotalCount(4);
		strategy.setLowerSideHint(2);
		strategy.setLowerTrim(0);
		strategy.setUpperTrim(0);

		check(new Range(0, 3), strategy.calculate(0, 10));
		check(new Range(0, 3), strategy.calculate(1, 10));
		check(new Range(0, 3), strategy.calculate(2, 10));
		check(new Range(1, 4), strategy.calculate(3, 10));
		check(new Range(2, 5), strategy.calculate(4, 10));
		check(new Range(3, 6), strategy.calculate(5, 10));
		check(new Range(4, 7), strategy.calculate(6, 10));
		check(new Range(5, 8), strategy.calculate(7, 10));
		check(new Range(6, 9), strategy.calculate(8, 10));
		check(new Range(6, 9), strategy.calculate(9, 10));
	}

	@Test
	public void testCaclulate52() {

		TotalCountStrategy strategy = new TotalCountStrategy();
		strategy.setTotalCount(5);
		strategy.setLowerSideHint(2);
		strategy.setLowerTrim(0);
		strategy.setUpperTrim(0);

		check(new Range(0, 4), strategy.calculate(0, 10));
		check(new Range(0, 4), strategy.calculate(1, 10));
		check(new Range(0, 4), strategy.calculate(2, 10));
		check(new Range(1, 5), strategy.calculate(3, 10));
		check(new Range(2, 6), strategy.calculate(4, 10));
		check(new Range(3, 7), strategy.calculate(5, 10));
		check(new Range(4, 8), strategy.calculate(6, 10));
		check(new Range(5, 9), strategy.calculate(7, 10));
		check(new Range(5, 9), strategy.calculate(8, 10));
		check(new Range(5, 9), strategy.calculate(9, 10));
	}

	@Test
	public void testCaclulate41Trimmed() {

		TotalCountStrategy strategy = new TotalCountStrategy();
		strategy.setTotalCount(4);
		strategy.setLowerSideHint(1);
		strategy.setLowerTrim(1);
		strategy.setUpperTrim(1);

		check(new Range(1, 4), strategy.calculate(0, 10));
		check(new Range(1, 4), strategy.calculate(1, 10));
		check(new Range(1, 4), strategy.calculate(2, 10));
		check(new Range(2, 5), strategy.calculate(3, 10));
		check(new Range(3, 6), strategy.calculate(4, 10));
		check(new Range(4, 7), strategy.calculate(5, 10));
		check(new Range(5, 8), strategy.calculate(6, 10));
		check(new Range(5, 8), strategy.calculate(7, 10));
		check(new Range(5, 8), strategy.calculate(8, 10));
		check(new Range(5, 8), strategy.calculate(9, 10));
	}

	@Test
	public void testCaclulate42Trimmed() {

		TotalCountStrategy strategy = new TotalCountStrategy();
		strategy.setTotalCount(4);
		strategy.setLowerSideHint(2);
		strategy.setLowerTrim(1);
		strategy.setUpperTrim(1);

		check(new Range(1, 4), strategy.calculate(0, 10));
		check(new Range(1, 4), strategy.calculate(1, 10));
		check(new Range(1, 4), strategy.calculate(2, 10));
		check(new Range(1, 4), strategy.calculate(3, 10));
		check(new Range(2, 5), strategy.calculate(4, 10));
		check(new Range(3, 6), strategy.calculate(5, 10));
		check(new Range(4, 7), strategy.calculate(6, 10));
		check(new Range(5, 8), strategy.calculate(7, 10));
		check(new Range(5, 8), strategy.calculate(8, 10));
		check(new Range(5, 8), strategy.calculate(9, 10));
	}

	@Test
	public void testCaclulate52Trimmed() {

		TotalCountStrategy strategy = new TotalCountStrategy();
		strategy.setTotalCount(5);
		strategy.setLowerSideHint(2);
		strategy.setLowerTrim(1);
		strategy.setUpperTrim(1);

		check(new Range(1, 5), strategy.calculate(0, 10));
		check(new Range(1, 5), strategy.calculate(1, 10));
		check(new Range(1, 5), strategy.calculate(2, 10));
		check(new Range(1, 5), strategy.calculate(3, 10));
		check(new Range(2, 6), strategy.calculate(4, 10));
		check(new Range(3, 7), strategy.calculate(5, 10));
		check(new Range(4, 8), strategy.calculate(6, 10));
		check(new Range(4, 8), strategy.calculate(7, 10));
		check(new Range(4, 8), strategy.calculate(8, 10));
		check(new Range(4, 8), strategy.calculate(9, 10));
	}

	private void check(Iterable<Long> a, Iterable<Long> b) {
		Iterator<Long> ia = a.iterator();
		Iterator<Long> ib = b.iterator();
		while (ia.hasNext() && ib.hasNext()) {
			assertEquals(ia.next(), ib.next());
		}
		if (ia.hasNext()) {
			assertEquals(ia.next(), null);
		}
		if (ib.hasNext()) {
			assertEquals(null, ia.next());
		}
	}

}
