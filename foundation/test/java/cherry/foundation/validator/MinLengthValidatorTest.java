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
public class MinLengthValidatorTest {

	@Autowired
	private ConversionService conversionService;

	@Autowired
	private Validator validator;

	@Test
	public void testOK() {

		Map<String, String> val = new HashMap<>();
		val.put("val0", null);
		val.put("val1", "");
		val.put("val2", "1");
		val.put("val3", "12345");
		val.put("val4", "123456");

		TestDto dto = new TestDto();

		WebDataBinder binder = new WebDataBinder(dto);
		binder.setConversionService(conversionService);
		binder.addValidators(validator);
		binder.bind(new MutablePropertyValues(val));
		binder.validate();
		BindingResult result = binder.getBindingResult();
		assertEquals(0, result.getErrorCount());
		assertNull(dto.getVal0());
		assertEquals("", dto.getVal1());
		assertEquals("1", dto.getVal2());
		assertEquals("12345", dto.getVal3());
		assertEquals("123456", dto.getVal4());
	}

	@Test
	public void testNG() {

		Map<String, String> val = new HashMap<>();
		val.put("val3", "1234");
		val.put("val4", "123");

		TestDto dto = new TestDto();

		WebDataBinder binder = new WebDataBinder(dto);
		binder.setConversionService(conversionService);
		binder.addValidators(validator);
		binder.bind(new MutablePropertyValues(val));
		binder.validate();
		BindingResult result = binder.getBindingResult();
		assertEquals(2, result.getErrorCount());
	}

	@Getter
	@Setter
	public static class TestDto {
		@MinLength(1)
		private String val0;
		@MinLength(1)
		private String val1;
		@MinLength(1)
		private String val2;
		@MinLength(5)
		private String val3;
		@MinLength(5)
		private String val4;
	}

}
