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

package cherry.foundation.telno;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeSet;

import org.apache.commons.collections4.Trie;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import cherry.goods.telno.SoumuExcelParser;

public class AreaCodeTableFactoryTest {

	@Test
	public void test() throws Exception {

		List<Resource> resources = new ArrayList<>(9);
		resources.add(new ClassPathResource("cherry/goods/telno/soumu/000124070.xls"));
		resources.add(new ClassPathResource("cherry/goods/telno/soumu/000124071.xls"));
		resources.add(new ClassPathResource("cherry/goods/telno/soumu/000124072.xls"));
		resources.add(new ClassPathResource("cherry/goods/telno/soumu/000124073.xls"));
		resources.add(new ClassPathResource("cherry/goods/telno/soumu/000124074.xls"));
		resources.add(new ClassPathResource("cherry/goods/telno/soumu/000124075.xls"));
		resources.add(new ClassPathResource("cherry/goods/telno/soumu/000124076.xls"));
		resources.add(new ClassPathResource("cherry/goods/telno/soumu/000124077.xls"));
		resources.add(new ClassPathResource("cherry/goods/telno/soumu/000124078.xls"));
		AreaCodeTableFactory factory = new AreaCodeTableFactory();
		factory.setSoumuExcelParser(new SoumuExcelParser());
		factory.setResources(resources);

		Trie<String, Integer> trie = factory.getObject();
		SortedMap<String, Integer> map = trie.prefixMap("042");
		assertEquals(790, map.size());
		assertEquals(new TreeSet<Integer>(asList(2, 3, 4)), new TreeSet<>(map.values()));

		assertEquals(4554 + 5683 + 3400 + 5343 + 5307 + 3000 + 5548 + 4526 + 5330, trie.size());
		assertEquals(Trie.class, factory.getObjectType());
		assertFalse(factory.isSingleton());
	}
}
