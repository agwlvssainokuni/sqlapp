/*
 * Copyright 2015 agwlvssainokuni
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

package cherry.foundation.render;

import static org.junit.Assert.assertEquals;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class ObjectRendererImplTest {

	@Autowired
	private ObjectRenderer objectRenderer;

	@Test
	public void testNull() {
		assertEquals("", objectRenderer.render(null, null));
	}

	@Test
	public void testNumberWithoutFormat() {
		ObjectRenderer renderer = new ObjectRendererImpl();
		assertEquals("1,234,567", renderer.render(1234567L, null));
		assertEquals("1,234,567", renderer.render(1234567.0, null));
		assertEquals("1,234,567.1", renderer.render(1234567.1, null));
		assertEquals("1,234,567.12", renderer.render(1234567.12, null));
		assertEquals("1,234,567.123", renderer.render(1234567.123, null));
		assertEquals("1,234,567.123", renderer.render(1234567.1234, null));
	}

	@Test
	public void testNumberWithNull() {
		assertEquals("1,234,567", objectRenderer.render(1234567L, null));
		assertEquals("1,234,567", objectRenderer.render(1234567.0, null));
		assertEquals("1,234,567.1", objectRenderer.render(1234567.1, null));
		assertEquals("1,234,567.12", objectRenderer.render(1234567.12, null));
		assertEquals("1,234,567.123", objectRenderer.render(1234567.123, null));
		assertEquals("1,234,567.123", objectRenderer.render(1234567.1234, null));
	}

	@Test
	public void testNumberWithMinus1() {
		assertEquals("1,234,567", objectRenderer.render(1234567L, -1));
		assertEquals("1,234,567", objectRenderer.render(1234567.0, -1));
		assertEquals("1,234,567.1", objectRenderer.render(1234567.1, -1));
		assertEquals("1,234,567.12", objectRenderer.render(1234567.12, -1));
		assertEquals("1,234,567.123", objectRenderer.render(1234567.123, -1));
		assertEquals("1,234,567.123", objectRenderer.render(1234567.1234, -1));
	}

	@Test
	public void testNumberWith0() {
		assertEquals("1,234,567", objectRenderer.render(1234567L, 0));
		assertEquals("1,234,567", objectRenderer.render(1234567.0, 0));
		assertEquals("1,234,567.1", objectRenderer.render(1234567.1, 0));
		assertEquals("1,234,567.12", objectRenderer.render(1234567.12, 0));
		assertEquals("1,234,567.123", objectRenderer.render(1234567.123, 0));
		assertEquals("1,234,567.123", objectRenderer.render(1234567.1234, 0));
	}

	@Test
	public void testNumberWith1() {
		assertEquals("1,234,567.0", objectRenderer.render(1234567L, 1));
		assertEquals("1,234,567.0", objectRenderer.render(1234567.0, 1));
		assertEquals("1,234,567.1", objectRenderer.render(1234567.1, 1));
		assertEquals("1,234,567.1", objectRenderer.render(1234567.12, 1));
		assertEquals("1,234,567.1", objectRenderer.render(1234567.123, 1));
		assertEquals("1,234,567.1", objectRenderer.render(1234567.1234, 1));
	}

	@Test
	public void testNumberWith2() {
		assertEquals("1,234,567.00", objectRenderer.render(1234567L, 2));
		assertEquals("1,234,567.00", objectRenderer.render(1234567.0, 2));
		assertEquals("1,234,567.10", objectRenderer.render(1234567.1, 2));
		assertEquals("1,234,567.12", objectRenderer.render(1234567.12, 2));
		assertEquals("1,234,567.12", objectRenderer.render(1234567.123, 2));
		assertEquals("1,234,567.12", objectRenderer.render(1234567.1234, 2));
	}

	@Test
	public void testNumberWith3() {
		assertEquals("1,234,567.000", objectRenderer.render(1234567L, 3));
		assertEquals("1,234,567.000", objectRenderer.render(1234567.0, 3));
		assertEquals("1,234,567.100", objectRenderer.render(1234567.1, 3));
		assertEquals("1,234,567.120", objectRenderer.render(1234567.12, 3));
		assertEquals("1,234,567.123", objectRenderer.render(1234567.123, 3));
		assertEquals("1,234,567.123", objectRenderer.render(1234567.1234, 3));
	}

	@Test
	public void testNumberWith4() {
		assertEquals("1,234,567.000", objectRenderer.render(1234567L, 4));
		assertEquals("1,234,567.000", objectRenderer.render(1234567.0, 4));
		assertEquals("1,234,567.100", objectRenderer.render(1234567.1, 4));
		assertEquals("1,234,567.120", objectRenderer.render(1234567.12, 4));
		assertEquals("1,234,567.123", objectRenderer.render(1234567.123, 4));
		assertEquals("1,234,567.123", objectRenderer.render(1234567.1234, 4));
	}

	@Test
	public void testLocalDate() {
		assertEquals("2015/01/23", objectRenderer.render(new LocalDate(2015, 1, 23), null));
	}

	@Test
	public void testLocalTime() {
		assertEquals("12:34:56", objectRenderer.render(new LocalTime(12, 34, 56), null));
	}

	@Test
	public void testLocalDateTime() {
		assertEquals("2015/01/23 12:34:56", objectRenderer.render(new LocalDateTime(2015, 1, 23, 12, 34, 56), null));
	}

	@Test
	public void testString() {
		assertEquals("TEST", objectRenderer.render("TEST", null));
	}

	@Test
	public void testDto() {
		Dto dto = new Dto();
		dto.setKey("KEY");
		dto.setValue("VALUE");
		assertEquals("ObjectRendererImplTest.Dto(key=KEY, value=VALUE)", objectRenderer.render(dto, null));
	}

	@Setter
	@Getter
	@ToString
	public static class Dto {
		private String key;
		private String value;
	}

}
