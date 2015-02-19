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

package cherry.foundation.navi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.util.InMemoryResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.foundation.navi.Navigator.Node;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class NavigatorImplTest {

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testEmptyDef() throws IOException {
		Navigator navigator = create("{}");
		List<Node> list = navigator.navigate("aa");
		assertTrue(list.isEmpty());
	}

	@Test
	public void testSingleNode() throws IOException {
		Navigator navigator = create("{\"name\":\"1\",\"uri\":\"uri/1\"}");
		List<Node> list = navigator.navigate("1");
		assertEquals(1, list.size());
		int i = 1;
		for (Node node : list) {
			assertEquals(String.valueOf(i), node.getName());
			assertEquals("uri/" + i, node.getUri());
			assertTrue(node.isLast());
			assertEquals("Navigator.Node[name=" + i + ",uri=uri/" + i + ",last=true]", node.toString());
			i += 1;
		}
	}

	@Test
	public void testHierNode() throws IOException {
		Navigator navigator = create("{\"name\":\"1\",\"uri\":\"uri/1\",\"children\":[{\"name\":\"2\",\"uri\":\"uri/2\"}]}");
		List<Node> list = navigator.navigate("2");
		assertEquals(2, list.size());
		int i = 1;
		for (Node node : list) {
			assertEquals(String.valueOf(i), node.getName());
			assertEquals("uri/" + i, node.getUri());
			if (i == 2) {
				assertTrue(node.isLast());
			} else {
				assertFalse(node.isLast());
			}
			i += 1;
		}
	}

	@Test
	public void testMisc() {
		NavigatorImpl.NaviNode node = new NavigatorImpl.NaviNode();
		node.setName("A");
		node.setUri("U");
		node.setChildren(new ArrayList<NavigatorImpl.NaviNode>());
		assertEquals("NavigatorImpl.NaviNode[name=A,uri=U,children=[]]", node.toString());
	}

	private Navigator create(String def) throws IOException {
		NavigatorImpl impl = new NavigatorImpl();
		impl.setObjectMapper(objectMapper);
		impl.setNavigationDef(new InMemoryResource(def));
		impl.afterPropertiesSet();
		return impl;
	}

}
