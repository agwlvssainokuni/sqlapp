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

package cherry.foundation.code;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SimpleCodeStoreTest {

	@Test
	public void testFindByValue() {
		CodeStore codeStore = create();
		assertNull(codeStore.findByValue("NONE", "01"));
		assertNull(codeStore.findByValue("CODE0", "00"));
		CodeEntry entry01 = codeStore.findByValue("CODE0", "01");
		assertEquals("01", entry01.getValue());
		assertEquals("LABEL01", entry01.getLabel());
		assertEquals(0, entry01.getSortOrder());
		CodeEntry entry02 = codeStore.findByValue("CODE0", "02");
		assertEquals("02", entry02.getValue());
		assertEquals("LABEL02", entry02.getLabel());
		assertEquals(0, entry02.getSortOrder());
		assertNull(codeStore.findByValue("CODE0", "03"));
	}

	@Test
	public void testGetCodeList() {
		CodeStore codeStore = create();
		assertNull(codeStore.getCodeList("NONE"));

		List<CodeEntry> list = codeStore.getCodeList("CODE0");
		assertEquals(2, list.size());

		CodeEntry entry01 = list.get(0);
		assertEquals("01", entry01.getValue());
		assertEquals("LABEL01", entry01.getLabel());
		assertEquals(0, entry01.getSortOrder());

		CodeEntry entry02 = list.get(1);
		assertEquals("02", entry02.getValue());
		assertEquals("LABEL02", entry02.getLabel());
		assertEquals(0, entry02.getSortOrder());
	}

	private CodeStore create() {
		Map<String, String> code0 = new LinkedHashMap<>();
		code0.put("01", "LABEL01");
		code0.put("02", "LABEL02");
		Map<String, Map<String, String>> map = new LinkedHashMap<>();
		map.put("CODE0", code0);
		SimpleCodeStore codeStore = new SimpleCodeStore();
		codeStore.setCodeDefMap(map);
		return codeStore;
	}

}
