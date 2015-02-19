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
public class TelNoValidatorTest {

	@Autowired
	private ConversionService conversionService;

	@Autowired
	private Validator validator;

	@Test
	public void testOK() {

		Map<String, String> val = new HashMap<>();
		val.put("telNo0", null);
		val.put("telNo1", "");
		val.put("telNo2", "01-234-5678");
		val.put("telNo3", "01-2345-6789");
		val.put("telNo4", "012-345-6789");
		val.put("telNo5", "0123-45-6789");
		val.put("telNo6", "01234-5-6789");
		val.put("telNo7", "090-1234-5678");
		val.put("telNo8", "0120-123-456");

		TestDto dto = new TestDto();

		WebDataBinder binder = new WebDataBinder(dto);
		binder.setConversionService(conversionService);
		binder.addValidators(validator);
		binder.bind(new MutablePropertyValues(val));
		binder.validate();
		BindingResult result = binder.getBindingResult();
		assertEquals(0, result.getErrorCount());
		assertNull(dto.getTelNo0());
		assertEquals("", dto.getTelNo1());
		assertEquals("01-234-5678", dto.getTelNo2());
		assertEquals("01-2345-6789", dto.getTelNo3());
		assertEquals("012-345-6789", dto.getTelNo4());
		assertEquals("0123-45-6789", dto.getTelNo5());
		assertEquals("01234-5-6789", dto.getTelNo6());
		assertEquals("090-1234-5678", dto.getTelNo7());
		assertEquals("0120-123-456", dto.getTelNo8());
	}

	@Test
	public void testNG() {

		Map<String, String> val = new HashMap<>();
		val.put("telNo0", "01-234-567");
		val.put("telNo1", "0123-1234-5678");
		val.put("telNo2", "0a-2345-6789");
		val.put("telNo3", "01-a345-6789");
		val.put("telNo4", "01-234a-6789");
		val.put("telNo5", "01-2345-a789");
		val.put("telNo6", "01-2345-678a");

		TestDto dto = new TestDto();

		WebDataBinder binder = new WebDataBinder(dto);
		binder.setConversionService(conversionService);
		binder.addValidators(validator);
		binder.bind(new MutablePropertyValues(val));
		binder.validate();
		BindingResult result = binder.getBindingResult();
		assertEquals(7, result.getErrorCount());
	}

	@Getter
	@Setter
	public static class TestDto {
		@TelNo
		private String telNo0;
		@TelNo
		private String telNo1;
		@TelNo
		private String telNo2;
		@TelNo
		private String telNo3;
		@TelNo
		private String telNo4;
		@TelNo
		private String telNo5;
		@TelNo
		private String telNo6;
		@TelNo
		private String telNo7;
		@TelNo
		private String telNo8;
	}

}
