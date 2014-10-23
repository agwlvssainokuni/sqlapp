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

package cherry.spring.fwcore.mask;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TEN;
import static java.math.BigInteger.ZERO;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class BigDecimalMasker implements Masker<BigDecimal> {

	public static BigDecimalMasker lowerDigit(BigDecimal mask, int count) {
		return new LowerDigitImpl(mask, count);
	}

	public static BigDecimalMasker upperDigit(BigDecimal mask, int count) {
		return new UpperDigitImpl(mask, count);
	}

	public static BigDecimalMasker fixedUpperDigit(BigDecimal mask, int count) {
		return new FixedUpperDigitImpl(mask, count);
	}

	static class LowerDigitImpl extends BigDecimalMasker {

		private final BigDecimal mask;

		private final BigInteger maskInt;

		private final BigDecimal maskFrac;

		private final int count;

		public LowerDigitImpl(BigDecimal mask, int count) {
			this.mask = mask;
			this.maskInt = this.mask.toBigInteger();
			this.maskFrac = this.mask.subtract(new BigDecimal(this.maskInt));
			this.count = count;
		}

		@Override
		public BigDecimal mask(BigDecimal value) {
			if (value == null) {
				return value;
			}

			BigInteger biValue = value.toBigInteger().abs();

			BigInteger digitMask = ONE;
			BigInteger curValue = biValue;
			for (int i = 0; curValue.signum() > 0; i++, curValue = curValue
					.divide(TEN)) {
				if (i >= count) {
					digitMask = digitMask.multiply(TEN);
				}
			}

			BigInteger maskedValue = biValue.divide(digitMask)
					.multiply(digitMask).add(maskInt.remainder(digitMask));

			if (value.signum() < 0) {
				return maskFrac.add(new BigDecimal(maskedValue)).negate();
			} else {
				return maskFrac.add(new BigDecimal(maskedValue));
			}
		}
	}

	static class UpperDigitImpl extends BigDecimalMasker {

		private final BigDecimal mask;

		private final BigInteger maskInt;

		private final BigDecimal maskFrac;

		private final int count;

		public UpperDigitImpl(BigDecimal mask, int count) {
			this.mask = mask;
			this.maskInt = this.mask.toBigInteger();
			this.maskFrac = this.mask.subtract(new BigDecimal(this.maskInt));
			this.count = count;
		}

		@Override
		public BigDecimal mask(BigDecimal value) {
			if (value == null) {
				return value;
			}

			BigInteger biValue = value.toBigInteger().abs();

			BigInteger maskedValue = ZERO;
			BigInteger digitMask = ONE;
			BigInteger curValue = biValue;
			BigInteger curMask = maskInt;
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
				return maskFrac.add(new BigDecimal(maskedValue)).negate();
			} else {
				return maskFrac.add(new BigDecimal(maskedValue));
			}
		}
	}

	static class FixedUpperDigitImpl extends BigDecimalMasker {

		private final BigDecimal mask;

		private final BigInteger maskInt;

		private final BigDecimal maskFrac;

		private final int count;

		public FixedUpperDigitImpl(BigDecimal mask, int count) {
			this.mask = mask;
			this.maskInt = this.mask.toBigInteger();
			this.maskFrac = this.mask.subtract(new BigDecimal(this.maskInt));
			this.count = count;
		}

		@Override
		public BigDecimal mask(BigDecimal value) {
			if (value == null) {
				return value;
			}

			BigInteger biValue = value.toBigInteger().abs();

			BigInteger digitMask = ONE;
			for (int i = 0; i < count; i++) {
				digitMask = digitMask.multiply(TEN);
			}

			BigInteger maskedValue = maskInt.divide(digitMask)
					.multiply(digitMask).add(biValue.remainder(digitMask));

			if (value.signum() < 0) {
				return maskFrac.add(new BigDecimal(maskedValue)).negate();
			} else {
				return maskFrac.add(new BigDecimal(maskedValue));
			}
		}
	}

}
