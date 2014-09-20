/*
 * Copyright 2014 agwlvssainokuni
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

package cherry.spring.common.lib.mask;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TEN;
import static java.math.BigInteger.ZERO;

import java.math.BigInteger;

public abstract class BigIntegerMasker implements Masker<BigInteger> {

	public static BigIntegerMasker lowerDigit(BigInteger mask, int count) {
		return new LowerDigitImpl(mask, count);
	}

	public static BigIntegerMasker upperDigit(BigInteger mask, int count) {
		return new UpperDigitImpl(mask, count);
	}

	public static BigIntegerMasker fixedUpperDigit(BigInteger mask, int count) {
		return new FixedUpperDigitImpl(mask, count);
	}

	static class LowerDigitImpl extends BigIntegerMasker {

		private final BigInteger mask;

		private final int count;

		public LowerDigitImpl(BigInteger mask, int count) {
			this.mask = mask;
			this.count = count;
		}

		@Override
		public BigInteger mask(BigInteger value) {
			if (value == null) {
				return value;
			}

			BigInteger digitMask = ONE;
			BigInteger curValue = value.abs();
			for (int i = 0; curValue.signum() > 0; i++, curValue = curValue
					.divide(TEN)) {
				if (i >= count) {
					digitMask = digitMask.multiply(TEN);
				}
			}

			BigInteger maskedValue = value.abs().divide(digitMask)
					.multiply(digitMask).add(mask.remainder(digitMask));

			if (value.signum() < 0) {
				return maskedValue.negate();
			} else {
				return maskedValue;
			}
		}
	}

	static class UpperDigitImpl extends BigIntegerMasker {

		private final BigInteger mask;

		private final int count;

		public UpperDigitImpl(BigInteger mask, int count) {
			this.mask = mask;
			this.count = count;
		}

		@Override
		public BigInteger mask(BigInteger value) {
			if (value == null) {
				return value;
			}

			BigInteger maskedValue = ZERO;
			BigInteger digitMask = ONE;
			BigInteger curValue = value.abs();
			BigInteger curMask = mask;
			for (int i = 0;; i++, digitMask = digitMask.multiply(TEN)) {
				if (i < count) {
					maskedValue = maskedValue.add(curValue.remainder(TEN)
							.multiply(digitMask));
				} else {
					maskedValue = maskedValue.add(curMask.remainder(TEN)
							.multiply(digitMask));
				}
				curValue = curValue.divide(TEN);
				curMask = curMask.divide(TEN);
				if (curValue.signum() == 0) {
					break;
				}
			}

			if (value.signum() < 0) {
				return maskedValue.negate();
			} else {
				return maskedValue;
			}
		}
	}

	static class FixedUpperDigitImpl extends BigIntegerMasker {

		private final BigInteger mask;

		private final int count;

		public FixedUpperDigitImpl(BigInteger mask, int count) {
			this.mask = mask;
			this.count = count;
		}

		@Override
		public BigInteger mask(BigInteger value) {
			if (value == null) {
				return value;
			}

			BigInteger digitMask = ONE;
			for (int i = 0; i < count; i++) {
				digitMask = digitMask.multiply(TEN);
			}

			BigInteger maskedValue = mask.divide(digitMask).multiply(digitMask)
					.add(value.abs().remainder(digitMask));

			if (value.signum() < 0) {
				return maskedValue.negate();
			} else {
				return maskedValue;
			}
		}
	}

}
