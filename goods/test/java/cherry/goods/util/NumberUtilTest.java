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

package cherry.goods.util;

import static cherry.goods.util.NumberUtil.ceiling1;
import static cherry.goods.util.NumberUtil.ceiling2;
import static cherry.goods.util.NumberUtil.ceiling3;
import static cherry.goods.util.NumberUtil.ceiling4;
import static cherry.goods.util.NumberUtil.floor1;
import static cherry.goods.util.NumberUtil.floor2;
import static cherry.goods.util.NumberUtil.floor3;
import static cherry.goods.util.NumberUtil.floor4;
import static cherry.goods.util.NumberUtil.halfUp1;
import static cherry.goods.util.NumberUtil.halfUp2;
import static cherry.goods.util.NumberUtil.halfUp3;
import static cherry.goods.util.NumberUtil.halfUp4;
import static cherry.goods.util.NumberUtil.roundDown1;
import static cherry.goods.util.NumberUtil.roundDown2;
import static cherry.goods.util.NumberUtil.roundDown3;
import static cherry.goods.util.NumberUtil.roundDown4;
import static cherry.goods.util.NumberUtil.roundUp1;
import static cherry.goods.util.NumberUtil.roundUp2;
import static cherry.goods.util.NumberUtil.roundUp3;
import static cherry.goods.util.NumberUtil.roundUp4;
import static cherry.goods.util.NumberUtil.setScale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import org.junit.Test;

public class NumberUtilTest {

	@Test
	public void testSetScale() {
		assertEquals(new BigDecimal("4444.44"), setScale(new BigDecimal("4444.4444"), 2, RoundingMode.DOWN));
		assertEquals(Double.valueOf(4444.44), setScale(Double.valueOf(4444.4444), 2, RoundingMode.DOWN));
		assertEquals(Double.valueOf(4444.44), setScale(Float.valueOf(4444.4444f), 2, RoundingMode.DOWN));
		assertEquals(Byte.valueOf((byte) 44), setScale(Byte.valueOf((byte) 44), 2, RoundingMode.DOWN));
		assertEquals(Short.valueOf((short) 4444), setScale(Short.valueOf((short) 4444), 2, RoundingMode.DOWN));
		assertEquals(Integer.valueOf(44444), setScale(Integer.valueOf(44444), 2, RoundingMode.DOWN));
		assertEquals(Long.valueOf(44444L), setScale(Long.valueOf(44444L), 2, RoundingMode.DOWN));
		assertEquals(BigInteger.valueOf(44444L), setScale(BigInteger.valueOf(44444L), 2, RoundingMode.DOWN));
	}

	@Test
	public void testHalfUp() {

		assertEquals(new BigDecimal("4"), halfUp1(new BigDecimal("4.4444")));
		assertEquals(new BigDecimal("4.4"), halfUp2(new BigDecimal("4.4444")));
		assertEquals(new BigDecimal("4.44"), halfUp3(new BigDecimal("4.4444")));
		assertEquals(new BigDecimal("4.444"), halfUp4(new BigDecimal("4.4444")));
		assertEquals(new BigDecimal("-4"), halfUp1(new BigDecimal("-4.4444")));
		assertEquals(new BigDecimal("-4.4"), halfUp2(new BigDecimal("-4.4444")));
		assertEquals(new BigDecimal("-4.44"), halfUp3(new BigDecimal("-4.4444")));
		assertEquals(new BigDecimal("-4.444"), halfUp4(new BigDecimal("-4.4444")));

		assertEquals(new BigDecimal("6"), halfUp1(new BigDecimal("5.5555")));
		assertEquals(new BigDecimal("5.6"), halfUp2(new BigDecimal("5.5555")));
		assertEquals(new BigDecimal("5.56"), halfUp3(new BigDecimal("5.5555")));
		assertEquals(new BigDecimal("5.556"), halfUp4(new BigDecimal("5.5555")));
		assertEquals(new BigDecimal("-6"), halfUp1(new BigDecimal("-5.5555")));
		assertEquals(new BigDecimal("-5.6"), halfUp2(new BigDecimal("-5.5555")));
		assertEquals(new BigDecimal("-5.56"), halfUp3(new BigDecimal("-5.5555")));
		assertEquals(new BigDecimal("-5.556"), halfUp4(new BigDecimal("-5.5555")));
	}

	@Test
	public void testRoundUp() {

		assertEquals(new BigDecimal("5"), roundUp1(new BigDecimal("4.4444")));
		assertEquals(new BigDecimal("4.5"), roundUp2(new BigDecimal("4.4444")));
		assertEquals(new BigDecimal("4.45"), roundUp3(new BigDecimal("4.4444")));
		assertEquals(new BigDecimal("4.445"), roundUp4(new BigDecimal("4.4444")));
		assertEquals(new BigDecimal("-5"), roundUp1(new BigDecimal("-4.4444")));
		assertEquals(new BigDecimal("-4.5"), roundUp2(new BigDecimal("-4.4444")));
		assertEquals(new BigDecimal("-4.45"), roundUp3(new BigDecimal("-4.4444")));
		assertEquals(new BigDecimal("-4.445"), roundUp4(new BigDecimal("-4.4444")));

		assertEquals(new BigDecimal("6"), roundUp1(new BigDecimal("5.5555")));
		assertEquals(new BigDecimal("5.6"), roundUp2(new BigDecimal("5.5555")));
		assertEquals(new BigDecimal("5.56"), roundUp3(new BigDecimal("5.5555")));
		assertEquals(new BigDecimal("5.556"), roundUp4(new BigDecimal("5.5555")));
		assertEquals(new BigDecimal("-6"), roundUp1(new BigDecimal("-5.5555")));
		assertEquals(new BigDecimal("-5.6"), roundUp2(new BigDecimal("-5.5555")));
		assertEquals(new BigDecimal("-5.56"), roundUp3(new BigDecimal("-5.5555")));
		assertEquals(new BigDecimal("-5.556"), roundUp4(new BigDecimal("-5.5555")));
	}

	@Test
	public void testRoundDown() {

		assertEquals(new BigDecimal("4"), roundDown1(new BigDecimal("4.4444")));
		assertEquals(new BigDecimal("4.4"), roundDown2(new BigDecimal("4.4444")));
		assertEquals(new BigDecimal("4.44"), roundDown3(new BigDecimal("4.4444")));
		assertEquals(new BigDecimal("4.444"), roundDown4(new BigDecimal("4.4444")));
		assertEquals(new BigDecimal("-4"), roundDown1(new BigDecimal("-4.4444")));
		assertEquals(new BigDecimal("-4.4"), roundDown2(new BigDecimal("-4.4444")));
		assertEquals(new BigDecimal("-4.44"), roundDown3(new BigDecimal("-4.4444")));
		assertEquals(new BigDecimal("-4.444"), roundDown4(new BigDecimal("-4.4444")));

		assertEquals(new BigDecimal("5"), roundDown1(new BigDecimal("5.5555")));
		assertEquals(new BigDecimal("5.5"), roundDown2(new BigDecimal("5.5555")));
		assertEquals(new BigDecimal("5.55"), roundDown3(new BigDecimal("5.5555")));
		assertEquals(new BigDecimal("5.555"), roundDown4(new BigDecimal("5.5555")));
		assertEquals(new BigDecimal("-5"), roundDown1(new BigDecimal("-5.5555")));
		assertEquals(new BigDecimal("-5.5"), roundDown2(new BigDecimal("-5.5555")));
		assertEquals(new BigDecimal("-5.55"), roundDown3(new BigDecimal("-5.5555")));
		assertEquals(new BigDecimal("-5.555"), roundDown4(new BigDecimal("-5.5555")));
	}

	@Test
	public void testCeiling() {

		assertEquals(new BigDecimal("5"), ceiling1(new BigDecimal("4.4444")));
		assertEquals(new BigDecimal("4.5"), ceiling2(new BigDecimal("4.4444")));
		assertEquals(new BigDecimal("4.45"), ceiling3(new BigDecimal("4.4444")));
		assertEquals(new BigDecimal("4.445"), ceiling4(new BigDecimal("4.4444")));
		assertEquals(new BigDecimal("-4"), ceiling1(new BigDecimal("-4.4444")));
		assertEquals(new BigDecimal("-4.4"), ceiling2(new BigDecimal("-4.4444")));
		assertEquals(new BigDecimal("-4.44"), ceiling3(new BigDecimal("-4.4444")));
		assertEquals(new BigDecimal("-4.444"), ceiling4(new BigDecimal("-4.4444")));

		assertEquals(new BigDecimal("6"), ceiling1(new BigDecimal("5.5555")));
		assertEquals(new BigDecimal("5.6"), ceiling2(new BigDecimal("5.5555")));
		assertEquals(new BigDecimal("5.56"), ceiling3(new BigDecimal("5.5555")));
		assertEquals(new BigDecimal("5.556"), ceiling4(new BigDecimal("5.5555")));
		assertEquals(new BigDecimal("-5"), ceiling1(new BigDecimal("-5.5555")));
		assertEquals(new BigDecimal("-5.5"), ceiling2(new BigDecimal("-5.5555")));
		assertEquals(new BigDecimal("-5.55"), ceiling3(new BigDecimal("-5.5555")));
		assertEquals(new BigDecimal("-5.555"), ceiling4(new BigDecimal("-5.5555")));
	}

	@Test
	public void testFloor() {

		assertEquals(new BigDecimal("4"), floor1(new BigDecimal("4.4444")));
		assertEquals(new BigDecimal("4.4"), floor2(new BigDecimal("4.4444")));
		assertEquals(new BigDecimal("4.44"), floor3(new BigDecimal("4.4444")));
		assertEquals(new BigDecimal("4.444"), floor4(new BigDecimal("4.4444")));
		assertEquals(new BigDecimal("-5"), floor1(new BigDecimal("-4.4444")));
		assertEquals(new BigDecimal("-4.5"), floor2(new BigDecimal("-4.4444")));
		assertEquals(new BigDecimal("-4.45"), floor3(new BigDecimal("-4.4444")));
		assertEquals(new BigDecimal("-4.445"), floor4(new BigDecimal("-4.4444")));

		assertEquals(new BigDecimal("5"), floor1(new BigDecimal("5.5555")));
		assertEquals(new BigDecimal("5.5"), floor2(new BigDecimal("5.5555")));
		assertEquals(new BigDecimal("5.55"), floor3(new BigDecimal("5.5555")));
		assertEquals(new BigDecimal("5.555"), floor4(new BigDecimal("5.5555")));
		assertEquals(new BigDecimal("-6"), floor1(new BigDecimal("-5.5555")));
		assertEquals(new BigDecimal("-5.6"), floor2(new BigDecimal("-5.5555")));
		assertEquals(new BigDecimal("-5.56"), floor3(new BigDecimal("-5.5555")));
		assertEquals(new BigDecimal("-5.556"), floor4(new BigDecimal("-5.5555")));
	}

	@Test
	public void testInstantiate() {
		assertNotNull(new NumberUtil());
	}

}
