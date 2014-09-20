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

public abstract class IntegerMasker implements Masker<Integer> {

	public static IntegerMasker lowerDigit(int mask, int count) {
		return new LowerDigitImpl(mask, count);
	}

	public static IntegerMasker upperDigit(int mask, int count) {
		return new UpperDigitImpl(mask, count);
	}

	public static IntegerMasker fixedUpperDigit(int mask, int count) {
		return new FixedUpperDigitImpl(mask, count);
	}

	static class LowerDigitImpl extends IntegerMasker {

		private final int mask;

		private final int count;

		public LowerDigitImpl(int mask, int count) {
			this.mask = mask;
			this.count = count;
		}

		@Override
		public Integer mask(Integer value) {
			if (value == null) {
				return value;
			}

			int digitMask = 1;
			int curValue = Math.abs(value);
			for (int i = 0; curValue > 0; i++, curValue /= 10) {
				if (i >= count) {
					digitMask *= 10;
				}
			}

			int maskedValue = (Math.abs(value) / digitMask) * digitMask
					+ (mask % digitMask);

			if (value < 0) {
				return -maskedValue;
			} else {
				return maskedValue;
			}
		}
	}

	static class UpperDigitImpl extends IntegerMasker {

		private final int mask;

		private final int count;

		public UpperDigitImpl(int mask, int count) {
			this.mask = mask;
			this.count = count;
		}

		@Override
		public Integer mask(Integer value) {
			if (value == null) {
				return value;
			}

			int maskedValue = 0;
			int digitMask = 1;
			int curValue = Math.abs(value);
			int curMask = mask;
			for (int i = 0;; i++, digitMask *= 10) {
				if (i < count) {
					maskedValue += (curValue % 10) * digitMask;
				} else {
					maskedValue += (curMask % 10) * digitMask;
				}
				curValue = curValue / 10;
				curMask = curMask / 10;
				if (curValue == 0) {
					break;
				}
			}

			if (value < 0) {
				return -maskedValue;
			} else {
				return maskedValue;
			}
		}
	}

	static class FixedUpperDigitImpl extends IntegerMasker {

		private final int mask;

		private final int count;

		public FixedUpperDigitImpl(int mask, int count) {
			this.mask = mask;
			this.count = count;
		}

		@Override
		public Integer mask(Integer value) {
			if (value == null) {
				return value;
			}

			int digitMask = 1;
			for (int i = 0; i < count; i++) {
				digitMask *= 10;
			}

			int maskedValue = (mask / digitMask) * digitMask
					+ (Math.abs(value) % digitMask);

			if (value < 0) {
				return -maskedValue;
			} else {
				return maskedValue;
			}
		}
	}

}
