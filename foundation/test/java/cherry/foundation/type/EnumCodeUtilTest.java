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

import static cherry.foundation.type.EnumCodeUtil.getCodeMap;
import static cherry.foundation.type.EnumCodeUtil.getLabeledCode;
import static cherry.foundation.type.EnumCodeUtil.getLabeledCodeList;
import static cherry.foundation.type.EnumCodeUtil.getMap;
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

import cherry.foundation.type.EnumCodeUtil.CodeMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class EnumCodeUtilTest {

	@Autowired
	private MessageSource messageSource;

	@Before
	public void before() {
		EnumCodeUtil.setMessageSource(createMessageSource());
	}

	@After
	public void after() {
		EnumCodeUtil.setMessageSource(messageSource);
	}

	@Test
	public void testGetCodeMapNoDefault() {
		CodeMap<Integer, FlagCode> codeMap = getCodeMap(FlagCode.class, null);
		for (int i = -1024; i <= 1024; i++) {
			switch (i) {
			case 0:
				assertThat(codeMap.get(i), is(FlagCode.FALSE));
				break;
			case 1:
				assertThat(codeMap.get(i), is(FlagCode.TRUE));
				break;
			default:
				try {
					codeMap.get(i);
					fail("Exception must be thrown");
				} catch (IllegalArgumentException ex) {
					// OK
				}
				break;
			}
		}
	}

	@Test
	public void testGetCodeMapDefaultFALSE() {
		CodeMap<Integer, FlagCode> codeMap = getCodeMap(FlagCode.class, FlagCode.FALSE);
		for (int i = -1024; i <= 1024; i++) {
			switch (i) {
			case 1:
				assertThat(codeMap.get(i), is(FlagCode.TRUE));
				break;
			default:
				assertThat(codeMap.get(i), is(FlagCode.FALSE));
				break;
			}
		}
	}

	@Test
	public void testGetLabeledCode() {
		assertThat(getLabeledCode(FlagCode.FALSE).getCode(), is(FlagCode.FALSE));
		assertThat(getLabeledCode(FlagCode.FALSE).getCodeValue(), is(0));
		assertThat(getLabeledCode(FlagCode.FALSE).getCodeLabel(), is("FLAG_CODE_FALSE"));
		assertThat(getLabeledCode(FlagCode.TRUE).getCode(), is(FlagCode.TRUE));
		assertThat(getLabeledCode(FlagCode.TRUE).getCodeValue(), is(1));
		assertThat(getLabeledCode(FlagCode.TRUE).getCodeLabel(), is("FLAG_CODE_TRUE"));
	}

	@Test
	public void testGetLabeledCodeList() {
		List<LabeledCode<Integer, FlagCode>> list = getLabeledCodeList(FlagCode.class);
		assertThat(list.size(), is(2));
		assertThat(list.get(0).getCode(), is(FlagCode.FALSE));
		assertThat(list.get(1).getCode(), is(FlagCode.TRUE));
	}

	@Test
	public void testGetLabeledCodeListForString() {
		List<LabeledCode<Integer, FlagCode>> list = getLabeledCodeList(FlagCode.class.getName());
		assertThat(list.size(), is(2));
		assertThat(list.get(0).getCode(), is(FlagCode.FALSE));
		assertThat(list.get(1).getCode(), is(FlagCode.TRUE));
	}

	@Test
	public void testGetMapThrowsException() {
		try {
			getMap(DeletedFlag.class);
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			// OK
		}
	}

	@Test
	public void testGetLabeledCodeListThrowsException() {
		try {
			getLabeledCodeList(DeletedFlag.class);
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			// OK
		}
	}

	@Test
	public void testGetLabeledCodeListForStringThrowsException() {
		try {
			getLabeledCodeList("NOTEXIST");
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			// OK
		}
	}

	@Test
	public void testInstantiate() {
		try {
			new EnumCodeUtil();
		} catch (Exception ex) {
			fail("Exception must not be thrown");
		}
	}

	private MessageSource createMessageSource() {
		final Map<String, String> map = new HashMap<>();
		map.put("cherry.foundation.type.FlagCode.0", "FLAG_CODE_FALSE");
		map.put("cherry.foundation.type.FlagCode.1", "FLAG_CODE_TRUE");
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
