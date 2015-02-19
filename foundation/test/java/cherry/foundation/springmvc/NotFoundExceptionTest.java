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

package cherry.foundation.springmvc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Test;

public class NotFoundExceptionTest {

	@Test
	public void testConstructor0() {
		NotFoundException ex = new NotFoundException();
		assertNull(ex.getMessage());
		assertNull(ex.getCause());
	}

	@Test
	public void testConstructor1() {
		NotFoundException ex = new NotFoundException("message");
		assertEquals("message", ex.getMessage());
		assertNull(ex.getCause());
	}

	@Test
	public void testConstructor2() {
		IOException ioex = new IOException();
		NotFoundException ex = new NotFoundException(ioex);
		assertEquals("java.io.IOException", ex.getMessage());
		assertEquals(ioex, ex.getCause());
	}

	@Test
	public void testConstructor3() {
		IOException ioex = new IOException();
		NotFoundException ex = new NotFoundException("message", ioex);
		assertEquals("message", ex.getMessage());
		assertEquals(ioex, ex.getCause());
	}

}
