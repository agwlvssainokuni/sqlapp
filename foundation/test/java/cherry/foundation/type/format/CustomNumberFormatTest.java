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

package cherry.foundation.type.format;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.validation.DataBinder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class CustomNumberFormatTest {

	@Autowired
	private ConversionService conversionService;

	@Test
	public void testByte() throws BindException {
		String name = "byteValue";
		assertEquals("44", parseAndPrint(name, "44"));
		assertEquals("44", parseAndPrint(name, "44.4444"));
		assertEquals("55", parseAndPrint(name, "55"));
		assertEquals("55", parseAndPrint(name, "55.5555"));
		assertEquals("-44", parseAndPrint(name, "-44"));
		assertEquals("-44", parseAndPrint(name, "-44.4444"));
		assertEquals("-55", parseAndPrint(name, "-55"));
		assertEquals("-55", parseAndPrint(name, "-55.5555"));
		try {
			parseAndPrint(name, "aaa");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			assertEquals(1, ex.getErrorCount());
			assertTrue(ex.hasFieldErrors(name));
			assertEquals("typeMismatch", ex.getFieldError(name).getCode());
		}
	}

	@Test
	public void testShort() throws BindException {
		String name = "shortValue";
		assertEquals("4444", parseAndPrint(name, "4444"));
		assertEquals("4444", parseAndPrint(name, "4444.4444"));
		assertEquals("5555", parseAndPrint(name, "5555"));
		assertEquals("5555", parseAndPrint(name, "5555.5555"));
		assertEquals("-4444", parseAndPrint(name, "-4444"));
		assertEquals("-4444", parseAndPrint(name, "-4444.4444"));
		assertEquals("-5555", parseAndPrint(name, "-5555"));
		assertEquals("-5555", parseAndPrint(name, "-5555.5555"));
		try {
			parseAndPrint(name, "aaa");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			assertEquals(1, ex.getErrorCount());
			assertTrue(ex.hasFieldErrors(name));
			assertEquals("typeMismatch", ex.getFieldError(name).getCode());
		}
	}

	@Test
	public void testInteger() throws BindException {
		String name = "integerValue";
		assertEquals("4444", parseAndPrint(name, "4444"));
		assertEquals("4444", parseAndPrint(name, "4444.4444"));
		assertEquals("5555", parseAndPrint(name, "5555"));
		assertEquals("5555", parseAndPrint(name, "5555.5555"));
		assertEquals("-4444", parseAndPrint(name, "-4444"));
		assertEquals("-4444", parseAndPrint(name, "-4444.4444"));
		assertEquals("-5555", parseAndPrint(name, "-5555"));
		assertEquals("-5555", parseAndPrint(name, "-5555.5555"));
		try {
			parseAndPrint(name, "aaa");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			assertEquals(1, ex.getErrorCount());
			assertTrue(ex.hasFieldErrors(name));
			assertEquals("typeMismatch", ex.getFieldError(name).getCode());
		}
	}

	@Test
	public void testLong() throws BindException {
		String name = "longValue";
		assertEquals("4444", parseAndPrint(name, "4444"));
		assertEquals("4444", parseAndPrint(name, "4444.4444"));
		assertEquals("5555", parseAndPrint(name, "5555"));
		assertEquals("5555", parseAndPrint(name, "5555.5555"));
		assertEquals("-4444", parseAndPrint(name, "-4444"));
		assertEquals("-4444", parseAndPrint(name, "-4444.4444"));
		assertEquals("-5555", parseAndPrint(name, "-5555"));
		assertEquals("-5555", parseAndPrint(name, "-5555.5555"));
		try {
			parseAndPrint(name, "aaa");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			assertEquals(1, ex.getErrorCount());
			assertTrue(ex.hasFieldErrors(name));
			assertEquals("typeMismatch", ex.getFieldError(name).getCode());
		}
	}

	@Test
	public void testBigInteger() throws BindException {
		String name = "bintValue";
		assertEquals("4444", parseAndPrint(name, "4444"));
		assertEquals("4444", parseAndPrint(name, "4444.4444"));
		assertEquals("5555", parseAndPrint(name, "5555"));
		assertEquals("5555", parseAndPrint(name, "5555.5555"));
		assertEquals("-4444", parseAndPrint(name, "-4444"));
		assertEquals("-4444", parseAndPrint(name, "-4444.4444"));
		assertEquals("-5555", parseAndPrint(name, "-5555"));
		assertEquals("-5555", parseAndPrint(name, "-5555.5555"));
		try {
			parseAndPrint(name, "aaa");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			assertEquals(1, ex.getErrorCount());
			assertTrue(ex.hasFieldErrors(name));
			assertEquals("typeMismatch", ex.getFieldError(name).getCode());
		}
	}

	@Test
	public void testFloat() throws BindException {
		String name = "floatValue";
		assertEquals("4444.00", parseAndPrint(name, "4444"));
		assertEquals("4444.44", parseAndPrint(name, "4444.4444"));
		assertEquals("5555.00", parseAndPrint(name, "5555"));
		assertEquals("5555.55", parseAndPrint(name, "5555.5555"));
		assertEquals("-4444.00", parseAndPrint(name, "-4444"));
		assertEquals("-4444.44", parseAndPrint(name, "-4444.4444"));
		assertEquals("-5555.00", parseAndPrint(name, "-5555"));
		assertEquals("-5555.55", parseAndPrint(name, "-5555.5555"));
		try {
			parseAndPrint(name, "aaa");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			assertEquals(1, ex.getErrorCount());
			assertTrue(ex.hasFieldErrors(name));
			assertEquals("typeMismatch", ex.getFieldError(name).getCode());
		}
	}

	@Test
	public void testDouble() throws BindException {
		String name = "doubleValue";
		assertEquals("4444.00", parseAndPrint(name, "4444"));
		assertEquals("4444.44", parseAndPrint(name, "4444.4444"));
		assertEquals("5555.00", parseAndPrint(name, "5555"));
		assertEquals("5555.55", parseAndPrint(name, "5555.5555"));
		assertEquals("-4444.00", parseAndPrint(name, "-4444"));
		assertEquals("-4444.44", parseAndPrint(name, "-4444.4444"));
		assertEquals("-5555.00", parseAndPrint(name, "-5555"));
		assertEquals("-5555.55", parseAndPrint(name, "-5555.5555"));
		try {
			parseAndPrint(name, "aaa");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			assertEquals(1, ex.getErrorCount());
			assertTrue(ex.hasFieldErrors(name));
			assertEquals("typeMismatch", ex.getFieldError(name).getCode());
		}
	}

	@Test
	public void testBigDecimal_1() throws BindException {
		String name = "bdecValue_1";
		assertEquals("4444", parseAndPrint(name, "4444"));
		assertEquals("4444", parseAndPrint(name, "4444.4444"));
		assertEquals("5555", parseAndPrint(name, "5555"));
		assertEquals("5555", parseAndPrint(name, "5555.5555"));
		assertEquals("-4444", parseAndPrint(name, "-4444"));
		assertEquals("-4444", parseAndPrint(name, "-4444.4444"));
		assertEquals("-5555", parseAndPrint(name, "-5555"));
		assertEquals("-5555", parseAndPrint(name, "-5555.5555"));
		try {
			parseAndPrint(name, "aaa");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			assertEquals(1, ex.getErrorCount());
			assertTrue(ex.hasFieldErrors(name));
			assertEquals("typeMismatch", ex.getFieldError(name).getCode());
		}
	}

	@Test
	public void testBigDecimal0() throws BindException {
		String name = "bdecValue0";
		assertEquals("4444", parseAndPrint(name, "4444"));
		assertEquals("4444", parseAndPrint(name, "4444.4444"));
		assertEquals("5555", parseAndPrint(name, "5555"));
		assertEquals("5555", parseAndPrint(name, "5555.5555"));
		assertEquals("-4444", parseAndPrint(name, "-4444"));
		assertEquals("-4444", parseAndPrint(name, "-4444.4444"));
		assertEquals("-5555", parseAndPrint(name, "-5555"));
		assertEquals("-5555", parseAndPrint(name, "-5555.5555"));
		try {
			parseAndPrint(name, "aaa");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			assertEquals(1, ex.getErrorCount());
			assertTrue(ex.hasFieldErrors(name));
			assertEquals("typeMismatch", ex.getFieldError(name).getCode());
		}
	}

	@Test
	public void testBigDecimal1() throws BindException {
		String name = "bdecValue1";
		assertEquals("4444.0", parseAndPrint(name, "4444"));
		assertEquals("4444.4", parseAndPrint(name, "4444.4444"));
		assertEquals("5555.0", parseAndPrint(name, "5555"));
		assertEquals("5555.5", parseAndPrint(name, "5555.5555"));
		assertEquals("-4444.0", parseAndPrint(name, "-4444"));
		assertEquals("-4444.4", parseAndPrint(name, "-4444.4444"));
		assertEquals("-5555.0", parseAndPrint(name, "-5555"));
		assertEquals("-5555.5", parseAndPrint(name, "-5555.5555"));
		try {
			parseAndPrint(name, "aaa");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			assertEquals(1, ex.getErrorCount());
			assertTrue(ex.hasFieldErrors(name));
			assertEquals("typeMismatch", ex.getFieldError(name).getCode());
		}
	}

	@Test
	public void testBigDecimal2() throws BindException {
		String name = "bdecValue2";
		assertEquals("4444.00", parseAndPrint(name, "4444"));
		assertEquals("4444.44", parseAndPrint(name, "4444.4444"));
		assertEquals("5555.00", parseAndPrint(name, "5555"));
		assertEquals("5555.55", parseAndPrint(name, "5555.5555"));
		assertEquals("-4444.00", parseAndPrint(name, "-4444"));
		assertEquals("-4444.44", parseAndPrint(name, "-4444.4444"));
		assertEquals("-5555.00", parseAndPrint(name, "-5555"));
		assertEquals("-5555.55", parseAndPrint(name, "-5555.5555"));
		try {
			parseAndPrint(name, "aaa");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			assertEquals(1, ex.getErrorCount());
			assertTrue(ex.hasFieldErrors(name));
			assertEquals("typeMismatch", ex.getFieldError(name).getCode());
		}
	}

	@Test
	public void testBigDecimal3() throws BindException {
		String name = "bdecValue3";
		assertEquals("4444.000", parseAndPrint(name, "4444"));
		assertEquals("4444.444", parseAndPrint(name, "4444.4444"));
		assertEquals("5555.000", parseAndPrint(name, "5555"));
		assertEquals("5555.555", parseAndPrint(name, "5555.5555"));
		assertEquals("-4444.000", parseAndPrint(name, "-4444"));
		assertEquals("-4444.444", parseAndPrint(name, "-4444.4444"));
		assertEquals("-5555.000", parseAndPrint(name, "-5555"));
		assertEquals("-5555.555", parseAndPrint(name, "-5555.5555"));
		try {
			parseAndPrint(name, "aaa");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			assertEquals(1, ex.getErrorCount());
			assertTrue(ex.hasFieldErrors(name));
			assertEquals("typeMismatch", ex.getFieldError(name).getCode());
		}
	}

	@Test
	public void testBigDecimal4() throws BindException {
		String name = "bdecValue4";
		assertEquals("4444.000", parseAndPrint(name, "4444"));
		assertEquals("4444.444", parseAndPrint(name, "4444.4444"));
		assertEquals("5555.000", parseAndPrint(name, "5555"));
		assertEquals("5555.555", parseAndPrint(name, "5555.5555"));
		assertEquals("-4444.000", parseAndPrint(name, "-4444"));
		assertEquals("-4444.444", parseAndPrint(name, "-4444.4444"));
		assertEquals("-5555.000", parseAndPrint(name, "-5555"));
		assertEquals("-5555.555", parseAndPrint(name, "-5555.5555"));
		try {
			parseAndPrint(name, "aaa");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			assertEquals(1, ex.getErrorCount());
			assertTrue(ex.hasFieldErrors(name));
			assertEquals("typeMismatch", ex.getFieldError(name).getCode());
		}
	}

	@Test
	public void testBigDecimalPatternAndScale() throws BindException {
		String name = "bdecValuePatternAndScale";
		assertEquals("4444.0", parseAndPrint(name, "4444"));
		assertEquals("4444.44", parseAndPrint(name, "4444.4444"));
		assertEquals("5555.0", parseAndPrint(name, "5555"));
		assertEquals("5555.55", parseAndPrint(name, "5555.5555"));
		assertEquals("-4444.0", parseAndPrint(name, "-4444"));
		assertEquals("-4444.44", parseAndPrint(name, "-4444.4444"));
		assertEquals("-5555.0", parseAndPrint(name, "-5555"));
		assertEquals("-5555.55", parseAndPrint(name, "-5555.5555"));
		try {
			parseAndPrint(name, "aaa");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			assertEquals(1, ex.getErrorCount());
			assertTrue(ex.hasFieldErrors(name));
			assertEquals("typeMismatch", ex.getFieldError(name).getCode());
		}
	}

	private String parseAndPrint(String name, String value) throws BindException {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put(name, value);

		Form form = new Form();
		DataBinder binder = new DataBinder(form, "target");
		binder.setConversionService(conversionService);
		binder.bind(new MutablePropertyValues(paramMap));

		BindingResult binding = BindingResultUtils.getBindingResult(binder.close(), "target");
		return (String) binding.getFieldValue(name);
	}

	@Getter
	@Setter
	public static class Form {

		@CustomNumberFormat
		private Byte byteValue;

		@CustomNumberFormat
		private Short shortValue;

		@CustomNumberFormat
		private Integer integerValue;

		@CustomNumberFormat
		private Long longValue;

		@CustomNumberFormat(2)
		private Float floatValue;

		@CustomNumberFormat(2)
		private Double doubleValue;

		@CustomNumberFormat
		private BigInteger bintValue;

		@CustomNumberFormat(-1)
		private BigDecimal bdecValue_1;

		@CustomNumberFormat(0)
		private BigDecimal bdecValue0;

		@CustomNumberFormat(1)
		private BigDecimal bdecValue1;

		@CustomNumberFormat(2)
		private BigDecimal bdecValue2;

		@CustomNumberFormat(3)
		private BigDecimal bdecValue3;

		@CustomNumberFormat(4)
		private BigDecimal bdecValue4;

		@CustomNumberFormat(pattern = "###0.0#", scale = 2)
		private BigDecimal bdecValuePatternAndScale;
	}

}
