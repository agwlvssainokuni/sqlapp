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

import static org.junit.Assert.fail;

import org.junit.Test;

public class TimeLimiterTest {

	@Test
	public void testWithinLimit() {
		Limiter limiter = new TimeLimiter(1000L);
		try {
			limiter.start();
			limiter.tick();
		} catch (LimiterException ex) {
			fail("Exception must not be thrown");
		} finally {
			limiter.stop();
		}
	}

	@Test
	public void testOnLimit() {
		Limiter limiter = new TimeLimiter(1100L);
		try {
			limiter.start();
			for (int i = 0; i < 10; i++) {
				Thread.sleep(100L);
				limiter.tick();
			}
		} catch (LimiterException | InterruptedException ex) {
			fail("Exception must not be thrown");
		} finally {
			limiter.stop();
		}
	}

	@Test
	public void testOverLimit() {
		Limiter limiter = new TimeLimiter(1100L);
		try {
			limiter.start();
			for (int i = 0; i < 10; i++) {
				Thread.sleep(100L);
				limiter.tick();
			}
			try {
				Thread.sleep(100L);
				limiter.tick();
				fail("Exception must be thrown");
			} catch (LimiterException ex) {
				// NOTHING
			}
		} catch (LimiterException | InterruptedException ex) {
			fail("Exception must not be thrown");
		} finally {
			limiter.stop();
		}
	}

}
