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

public abstract class LongMasker implements Masker<Long> {

	public static LongMasker lowerDigit(long mask, int count) {
		return new LowerDigitImpl(mask, count);
	}

	public static LongMasker upperDigit(long mask, int count) {
		return new UpperDigitImpl(mask, count);
	}

	public static LongMasker fixedUpperDigit(long mask, int count) {
		return new FixedUpperDigitImpl(mask, count);
	}

	static class LowerDigitImpl extends LongMasker {

		private final long mask;

		private final int count;

		public LowerDigitImpl(long mask, int count) {
			this.mask = mask;
			this.count = count;
		}

		@Override
		public Long mask(Long value) {
			if (value == null) {
				return value;
			}

			long digitMask = 1;
			long curValue = Math.abs(value);
			for (int i = 0; curValue > 0; i++, curValue /= 10) {
				if (i >= count) {
					digitMask *= 10;
				}
			}

			long maskedValue = (Math.abs(value) / digitMask) * digitMask
					+ (mask % digitMask);

			if (value < 0) {
				return -maskedValue;
			} else {
				return maskedValue;
			}
		}
	}

	static class UpperDigitImpl extends LongMasker {

		private final long mask;

		private final int count;

		public UpperDigitImpl(long mask, int count) {
			this.mask = mask;
			this.count = count;
		}

		@Override
		public Long mask(Long value) {
			if (value == null) {
				return value;
			}

			long maskedValue = 0;
			long digitMask = 1;
			long curValue = Math.abs(value);
			long curMask = mask;
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

	static class FixedUpperDigitImpl extends LongMasker {

		private final long mask;

		private final int count;

		public FixedUpperDigitImpl(long mask, int count) {
			this.mask = mask;
			this.count = count;
		}

		@Override
		public Long mask(Long value) {
			if (value == null) {
				return value;
			}

			long digitMask = 1;
			for (int i = 0; i < count; i++) {
				digitMask *= 10;
			}

			long maskedValue = (mask / digitMask) * digitMask
					+ (Math.abs(value) % digitMask);

			if (value < 0) {
				return -maskedValue;
			} else {
				return maskedValue;
			}
		}
	}

}
