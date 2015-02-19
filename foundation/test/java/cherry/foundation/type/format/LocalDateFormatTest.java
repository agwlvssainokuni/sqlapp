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

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.LocalDate;
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

import cherry.foundation.type.format.CustomDateTimeFormat.Range;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class LocalDateFormatTest {

	@Autowired
	private ConversionService conversionService;

	@Test
	public void testNone() throws BindException {
		String name = "attrNone";
		assertEquals("2014/01/01", parseAndPrint(name, "2014/01/01"));
		assertEquals("2014/01/01", parseAndPrint(name, "2014/01/01"));
	}

	@Test
	public void testNoneReq() throws BindException {
		String name = "attrNoneReq";
		assertEquals("2014/01/01", parseAndPrint(name, "2014/01/01"));
		assertEquals("2014/01/01", parseAndPrint(name, "2014/01/01"));
	}

	@Test
	public void testFrom() throws BindException {
		String name = "attrFrom";
		assertEquals("2014/01/01", parseAndPrint(name, "2014/01/01"));
		assertEquals("2014/01/01", parseAndPrint(name, "2014/01/01"));
	}

	@Test
	public void testFromReq() throws BindException {
		String name = "attrFromReq";
		assertEquals("2014/01/01", parseAndPrint(name, "2014/01/01"));
		assertEquals("2014/01/01", parseAndPrint(name, "2014/01/01"));
	}

	@Test
	public void testTo() throws BindException {
		String name = "attrTo";
		assertEquals("2014/01/01", parseAndPrint(name, "2014/01/01"));
		assertEquals("2014/01/01", parseAndPrint(name, "2014/01/01"));
	}

	@Test
	public void testToReq() throws BindException {
		String name = "attrToReq";
		assertEquals("2014/01/01", parseAndPrint(name, "2014/01/01"));
		assertEquals("2014/01/01", parseAndPrint(name, "2014/01/01"));
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

		@CustomDateTimeFormat(value = Range.NONE)
		private LocalDate attrNone;

		@CustomDateTimeFormat(value = Range.NONE, optional = false)
		private LocalDate attrNoneReq;

		@CustomDateTimeFormat(value = Range.FROM)
		private LocalDate attrFrom;

		@CustomDateTimeFormat(value = Range.FROM, optional = false)
		private LocalDate attrFromReq;

		@CustomDateTimeFormat(value = Range.TO)
		private LocalDate attrTo;

		@CustomDateTimeFormat(value = Range.TO, optional = false)
		private LocalDate attrToReq;
	}

}
