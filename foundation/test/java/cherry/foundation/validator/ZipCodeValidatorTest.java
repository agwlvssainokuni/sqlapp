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

package cherry.foundation.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class ZipCodeValidatorTest {

	@Autowired
	private ConversionService conversionService;

	@Autowired
	private Validator validator;

	@Test
	public void testOK() {

		Map<String, String> val = new HashMap<>();
		val.put("zipCode0", null);
		val.put("zipCode1", "");
		val.put("zipCode2", "1234567");
		val.put("zipCode3", null);
		val.put("zipCode4", "");
		val.put("zipCode5", "123-4567");

		TestDto dto = new TestDto();

		WebDataBinder binder = new WebDataBinder(dto);
		binder.setConversionService(conversionService);
		binder.addValidators(validator);
		binder.bind(new MutablePropertyValues(val));
		binder.validate();
		BindingResult result = binder.getBindingResult();
		assertEquals(0, result.getErrorCount());
		assertNull(dto.getZipCode0());
		assertEquals("", dto.getZipCode1());
		assertEquals("1234567", dto.getZipCode2());
		assertNull(dto.getZipCode3());
		assertEquals("", dto.getZipCode4());
		assertEquals("123-4567", dto.getZipCode5());
	}

	@Test
	public void testNG() {

		Map<String, String> val = new HashMap<>();
		val.put("zipCode0", "123456");
		val.put("zipCode1", "12345678");
		val.put("zipCode2", "abcdefg");
		val.put("zipCode3", "123-456");
		val.put("zipCode4", "123-45678");
		val.put("zipCode5", "abc-defg");

		TestDto dto = new TestDto();

		WebDataBinder binder = new WebDataBinder(dto);
		binder.setConversionService(conversionService);
		binder.addValidators(validator);
		binder.bind(new MutablePropertyValues(val));
		binder.validate();
		BindingResult result = binder.getBindingResult();
		assertEquals(6, result.getErrorCount());
	}

	@Getter
	@Setter
	public static class TestDto {
		@ZipCode
		private String zipCode0;
		@ZipCode
		private String zipCode1;
		@ZipCode
		private String zipCode2;
		@ZipCode(hyphen = true)
		private String zipCode3;
		@ZipCode(hyphen = true)
		private String zipCode4;
		@ZipCode(hyphen = true)
		private String zipCode5;
	}

}
