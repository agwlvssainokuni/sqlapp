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

package cherry.foundation.etl;

public class TimeLimiter implements Limiter {

	private final long timeLimit;

	private long limitedTo;

	public TimeLimiter(long timeLimit) {
		this.timeLimit = timeLimit;
	}

	@Override
	public void start() {
		limitedTo = System.currentTimeMillis() + timeLimit;
	}

	@Override
	public void tick() throws LimiterException {
		if (System.currentTimeMillis() > limitedTo) {
			throw new LimiterException();
		}
	}

	@Override
	public void stop() {
		limitedTo = 0L;
	}

}
