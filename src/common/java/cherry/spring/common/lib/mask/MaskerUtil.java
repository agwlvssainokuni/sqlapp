/*
 * Copyright 2014 agwlvssainokuni
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

package cherry.spring.common.lib.mask;

import java.util.LinkedHashMap;
import java.util.Map;

import cherry.spring.common.lib.etl.Decorator;
import cherry.spring.common.lib.etl.MaskerDecorator;
import cherry.spring.common.lib.util.CamelCaseUtil;

public class MaskerUtil {

	public static Map<String, Decorator> decoratorMap(
			Map<String, Masker<?>> maskerMap) {
		Map<String, Decorator> map = new LinkedHashMap<>();
		for (Map.Entry<String, Masker<?>> entry : maskerMap.entrySet()) {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			Decorator d = new MaskerDecorator(entry.getValue());
			map.put(entry.getKey(), d);
		}
		return map;
	}

	public static Map<String, Masker<?>> propertyMap(
			Map<String, Masker<?>> maskerMap) {
		Map<String, Masker<?>> map = new LinkedHashMap<>();
		for (Map.Entry<String, Masker<?>> entry : maskerMap.entrySet()) {
			String key = CamelCaseUtil.fromUnderscoreDelimited(entry.getKey(),
					false);
			map.put(key, entry.getValue());
		}
		return map;
	}

}
