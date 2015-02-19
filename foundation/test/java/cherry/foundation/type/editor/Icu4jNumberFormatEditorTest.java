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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ibm.icu.text.NumberFormat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class Icu4jNumberFormatEditorTest {

	@Value("#,##0")
	private NumberFormat numberFormat;

	@Test
	public void testFormat() {
		assertEquals("1,234,567", numberFormat.format(1234567));
	}

	@Test
	public void testForNull() {
		Icu4jNumberFormatEditor editor = new Icu4jNumberFormatEditor();
		editor.setAsText(null);
		assertEquals("", editor.getAsText());
		assertNull(editor.getValue());
	}

	@Test
	public void testForNNN() {
		Icu4jNumberFormatEditor editor = new Icu4jNumberFormatEditor();
		editor.setAsText("#,##0");
		assertEquals("#,##0", editor.getAsText());
		assertTrue(editor.getValue() instanceof NumberFormat);
	}

}
