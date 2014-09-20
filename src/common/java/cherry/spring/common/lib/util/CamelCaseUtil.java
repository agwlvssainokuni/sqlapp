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

package cherry.spring.common.lib.util;

public class CamelCaseUtil {

	public static String fromUnderscoreDelimited(String text, boolean capitalize) {
		return camelCase(text.split("_"), capitalize);
	}

	public static String fromWhitespaceDelimited(String text, boolean capitalize) {
		return camelCase(text.split(" "), capitalize);
	}

	public static String camelCase(String[] text, boolean capitalize) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < text.length; i++) {
			for (int j = 0; j < text[i].length(); j++) {
				char ch = text[i].charAt(j);
				if (j == 0) {
					if (capitalize) {
						builder.append(Character.toUpperCase(ch));
					} else if (i > 0) {
						builder.append(Character.toUpperCase(ch));
					} else {
						builder.append(Character.toLowerCase(ch));
					}
				} else {
					builder.append(Character.toLowerCase(ch));
				}
			}
		}
		return builder.toString();
	}

}
