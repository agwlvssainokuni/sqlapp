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

package cherry.foundation.type.format;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class CustomDateTimeFormatMiscTest {

	@Test
	public void testGetPrinter() {
		CustomDateTimeFormatAnnotationFormatterFactory factory = new CustomDateTimeFormatAnnotationFormatterFactory();
		try {
			factory.getPrinter(null, Object.class);
			fail("Exception must be thrown");
		} catch (IllegalStateException ex) {
			// OK
		}
	}

	@Test
	public void testGetParser() {
		CustomDateTimeFormatAnnotationFormatterFactory factory = new CustomDateTimeFormatAnnotationFormatterFactory();
		try {
			factory.getParser(null, Object.class);
			fail("Exception must be thrown");
		} catch (IllegalStateException ex) {
			// OK
		}
	}

	@Test
	public void testEnum() {
		assertEquals(CustomDateTimeFormat.Range.NONE, CustomDateTimeFormat.Range.valueOf("NONE"));
	}

}
