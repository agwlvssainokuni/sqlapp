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

package cherry.foundation.numbering;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class SimpleNumberingStoreTest {

	@Test
	public void testGetDefinition() {
		SimpleNumberingStore store = create();
		assertNotNull(store.getDefinition("TEST00"));
		try {
			store.getDefinition("NONE");
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			assertEquals("NONE must be defined", ex.getMessage());
		}
	}

	@Test
	public void testLoadSave() {
		SimpleNumberingStore store = create();
		for (long i = 0L; i < 100L; i++) {
			long current = store.loadAndLock("TEST00");
			try {
				assertEquals(i, current);
			} finally {
				store.saveAndUnlock("TEST00", current + 1L);
			}
		}
	}

	private SimpleNumberingStore create() {
		NumberingDefinition dto = new NumberingDefinition();
		dto.setTemplate("0000");
		dto.setMinValue(1L);
		dto.setMaxValue(9999L);
		Map<String, NumberingDefinition> map = new HashMap<>();
		map.put("TEST00", dto);
		SimpleNumberingStore store = new SimpleNumberingStore();
		store.setNumberingDefinitionMap(map);
		store.afterPropertiesSet();
		return store;
	}

}
