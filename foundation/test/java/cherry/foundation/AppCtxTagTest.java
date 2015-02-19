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

package cherry.foundation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class AppCtxTagTest {

	@Test
	public void testGetBean() {
		ConversionService service = AppCtxTag.getBean("conversionService", ConversionService.class);
		assertNotNull(service);
	}

	@Test
	public void testGetBeanByName() {
		Object service = AppCtxTag.getBeanByName("conversionService");
		assertNotNull(service);
	}

	@Test
	public void testGetBeanByClass() {
		ConversionService service = AppCtxTag.getBeanByClass(ConversionService.class);
		assertNotNull(service);
	}

	@Test
	public void testInstantiate() {
		try {
			new AppCtxTag();
		} catch (Exception ex) {
			fail("Exception must not be thrown");
		}
	}

}
