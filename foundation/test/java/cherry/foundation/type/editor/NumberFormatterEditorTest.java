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

package cherry.foundation.type.editor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.format.number.NumberFormatter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class NumberFormatterEditorTest {

	@Value("#,##0")
	private NumberFormatter numberFormatter;

	@Value("#,##0.0")
	private NumberFormatter numberFormatter1;

	@Value("#,##0.00")
	private NumberFormatter numberFormatter2;

	@Value("#,##0.000")
	private NumberFormatter numberFormatter3;

	@Test
	public void testFormat() {
		Locale locale = LocaleContextHolder.getLocale();
		assertEquals("1,234,567", numberFormatter.getNumberFormat(locale).format(1234567));
		assertEquals("1,234,567.0", numberFormatter1.getNumberFormat(locale).format(1234567));
		assertEquals("1,234,567.00", numberFormatter2.getNumberFormat(locale).format(1234567));
		assertEquals("1,234,567.000", numberFormatter3.getNumberFormat(locale).format(1234567));
	}

	@Test
	public void testForNull() {
		NumberFormatterEditor editor = new NumberFormatterEditor();
		editor.setAsText(null);
		assertEquals("", editor.getAsText());
		assertNull(editor.getValue());
	}

	@Test
	public void testForNNN() {
		NumberFormatterEditor editor = new NumberFormatterEditor();
		editor.setAsText("#,##0");
		assertEquals("#,##0", editor.getAsText());
		assertTrue(editor.getValue() instanceof NumberFormatter);
	}

}
