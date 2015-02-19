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

package cherry.foundation.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.util.InMemoryResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * {@link SqlLoaderImpl} のテスト。
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class SqlLoaderImplTest {

	@Autowired
	private SqlLoader sqlLoader;

	@Test
	public void DDLをファイルから読込む() throws IOException {
		Map<String, String> sqlmap = sqlLoader.load(getClass());
		assertEquals("SELECT 1 FROM dual", sqlmap.get("TEST01"));
		assertEquals("SELECT 2 FROM dual", sqlmap.get("TEST02"));
		assertEquals("SELECT 3 FROM dual", sqlmap.get("TEST03"));
		assertEquals("SELECT 4 FROM dual", sqlmap.get("TEST04"));
		assertEquals("SELECT 5 FROM dual", sqlmap.get("TEST05"));
		assertNull(sqlmap.get("TEST06"));
	}

	@Test
	public void DDLをリソースから読込む() throws IOException {
		Resource resource = new InMemoryResource("-- NAME: TEST00\nSELECT 0 FROM dual;");
		Map<String, String> sqlmap = sqlLoader.load(resource);
		assertEquals("SELECT 0 FROM dual", sqlmap.get("TEST00"));
	}

}
