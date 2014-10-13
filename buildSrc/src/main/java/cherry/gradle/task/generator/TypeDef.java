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

package cherry.gradle.task.generator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TypeDef extends LinkedHashMap<String, String> {

	private static final long serialVersionUID = 1L;

	public static final String FULLY_QUALIFIED_CLASS_NAME = "fullyQualifiedClassName";

	private String sheetName;

	private List<ItemDef> itemDef = new ArrayList<>();

	public String getFullyQualifiedClassName() {
		return get(FULLY_QUALIFIED_CLASS_NAME);
	}

	public void setFullyQualifiedClassName(String fullyQualifiedClassName) {
		put(FULLY_QUALIFIED_CLASS_NAME, fullyQualifiedClassName);
	}

	public String getClassName(String key) {
		String fqcn = get(key);
		int index = fqcn.lastIndexOf(".");
		if (index < 0) {
			return fqcn;
		} else {
			return fqcn.substring(index + 1);
		}
	}

	public String getPackageName(String key) {
		String fqcn = get(key);
		int index = fqcn.lastIndexOf(".");
		if (index < 0) {
			return "";
		} else {
			return fqcn.substring(0, index);
		}
	}

	public String getFormName(String key) {
		StringBuilder b = new StringBuilder(getClassName(key));
		b.setCharAt(0, Character.toLowerCase(b.charAt(0)));
		return b.toString();
	}

	public String getDirName(String key) {
		return getPackageName(key).replaceAll("\\.", "/");
	}

	public String getClassName() {
		return getClassName(FULLY_QUALIFIED_CLASS_NAME);
	}

	public String getPackageName() {
		return getPackageName(FULLY_QUALIFIED_CLASS_NAME);
	}

	public String getFormName() {
		return getFormName(FULLY_QUALIFIED_CLASS_NAME);
	}

	public String getDirName() {
		return getDirName(FULLY_QUALIFIED_CLASS_NAME);
	}

}
