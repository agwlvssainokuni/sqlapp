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

package cherry.foundation.etl;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cherry.goods.masker.IntegerMasker;
import cherry.goods.masker.Masker;
import cherry.goods.masker.StringMasker;

public class MaskerUtilTest {

	Map<String, Masker<?>> maskerMap;

	@Before
	public void before() {
		maskerMap = new LinkedHashMap<>();
		maskerMap.put("AAA_BBB_CCC", StringMasker.fixedTail("*", 2, 4));
		maskerMap.put("DDD_EEE_FFF", IntegerMasker.fixedUpperDigit(9999, 2));
	}

	@Test
	public void testDecoratorMap() {
		Map<String, Masker<?>> emptyMap = new LinkedHashMap<>();
		assertThat(MaskerUtil.decoratorMap(emptyMap), is(Collections.EMPTY_MAP));
		Map<String, Decorator> map = MaskerUtil.decoratorMap(maskerMap);
		assertThat(map.get("AAA_BBB_CCC"), is(notNullValue()));
		assertThat(map.get("aaaBbbCcc"), is(nullValue(Decorator.class)));
		assertThat((String) map.get("AAA_BBB_CCC").decorate("abcdef"), is("ab**"));
		assertThat(map.get("DDD_EEE_FFF"), is(notNullValue()));
		assertThat(map.get("dddEeeFff"), is(nullValue(Decorator.class)));
		assertThat((Integer) map.get("DDD_EEE_FFF").decorate(1234), is(9934));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testPropertyMap() {
		Map<String, Masker<?>> emptyMap = new LinkedHashMap<>();
		assertThat(MaskerUtil.propertyMap(emptyMap), is(Collections.EMPTY_MAP));
		Map<String, Masker<?>> map = MaskerUtil.propertyMap(maskerMap);
		assertThat(map.get("AAA_BBB_CCC"), is(nullValue(Masker.class)));
		assertThat(map.get("aaaBbbCcc"), is(notNullValue()));
		assertThat(((Masker<String>) map.get("aaaBbbCcc")).mask("abcdef"), is("ab**"));
		assertThat(map.get("DDD_EEE_FFF"), is(nullValue(Masker.class)));
		assertThat(map.get("dddEeeFff"), is(notNullValue()));
		assertThat(((Masker<Integer>) map.get("dddEeeFff")).mask(1234), is(9934));
	}

	@Test
	public void testInstantiate() {
		try {
			new MaskerUtil();
		} catch (Exception ex) {
			fail("Exception must not be thrown");
		}
	}

}
