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

package cherry.foundation.type;

import static cherry.foundation.type.EnumUtil.getLabeledEnum;
import static cherry.foundation.type.EnumUtil.getLabeledEnumList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class EnumUtilTest {

	@Autowired
	private MessageSource messageSource;

	@Before
	public void before() {
		EnumUtil.setMessageSource(createMessageSource());
	}

	@After
	public void after() {
		EnumUtil.setMessageSource(messageSource);
	}

	@Test
	public void testGetLabeledEnum() {
		assertThat(getLabeledEnum(FlagCode.FALSE).getEnum(), is(FlagCode.FALSE));
		assertThat(getLabeledEnum(FlagCode.FALSE).getEnumName(), is("FALSE"));
		assertThat(getLabeledEnum(FlagCode.FALSE).getEnumLabel(), is("FLAG_CODE_FALSE"));
		assertThat(getLabeledEnum(FlagCode.TRUE).getEnum(), is(FlagCode.TRUE));
		assertThat(getLabeledEnum(FlagCode.TRUE).getEnumName(), is("TRUE"));
		assertThat(getLabeledEnum(FlagCode.TRUE).getEnumLabel(), is("FLAG_CODE_TRUE"));
	}

	@Test
	public void testGetLabeledEnumList() {
		List<LabeledEnum<FlagCode>> list = getLabeledEnumList(FlagCode.class);
		assertThat(list.size(), is(2));
		assertThat(list.get(0).getEnum(), is(FlagCode.FALSE));
		assertThat(list.get(1).getEnum(), is(FlagCode.TRUE));
	}

	@Test
	public void testGetLabeledEnumListForString() {
		List<LabeledEnum<FlagCode>> list = getLabeledEnumList(FlagCode.class.getName());
		assertThat(list.size(), is(2));
		assertThat(list.get(0).getEnum(), is(FlagCode.FALSE));
		assertThat(list.get(1).getEnum(), is(FlagCode.TRUE));
	}

	@Test
	public void testGetLabeledEnumListForStringThrowsException() {
		try {
			getLabeledEnumList("NOTEXIST");
			fail("Exception must be thrown.");
		} catch (IllegalArgumentException ex) {
			// OK
		}
	}

	@Test
	public void testInstantiate() {
		try {
			new EnumUtil();
		} catch (Exception ex) {
			fail("Exception must not be thrown");
		}
	}

	private MessageSource createMessageSource() {
		final Map<String, String> map = new HashMap<>();
		map.put("cherry.foundation.type.FlagCode.FALSE", "FLAG_CODE_FALSE");
		map.put("cherry.foundation.type.FlagCode.TRUE", "FLAG_CODE_TRUE");
		return new MessageSource() {

			@Override
			public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
				return null;
			}

			@Override
			public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
				return map.get(code);
			}

			@Override
			public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
				return null;
			}
		};
	}

}
