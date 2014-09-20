/*
 * Copyright 2012,2014 agwlvssainokuni
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

importClass(java.util.ArrayList)
importClass(java.util.LinkedHashMap)

/**
 * JavaScriptの値をJavaオブジェクトに変換する.<br>
 * 
 * @param value
 *            JavaScript値
 * @returns Javaオブジェクト
 */
function toJavaObject(value) {

	// [実装メモ]
	// JavaScript値の型チェックに使用できる機能の動作をテストした結果は下記のとおり。
	//
	// typeof 1 = number
	// typeof 'text' = string
	// typeof true = boolean
	// typeof null = object
	// typeof NaN = number
	// typeof undefined = undefined
	// typeof {} = object
	// typeof [] = object
	// typeof function() {} = function
	// Object.prototype.toString.call(1) = [object Number]
	// Object.prototype.toString.call('text') = [object String]
	// Object.prototype.toString.call(true) = [object Boolean]
	// Object.prototype.toString.call(null) = [object Global]
	// Object.prototype.toString.call(NaN) = [object Number]
	// Object.prototype.toString.call(undefined) = [object Global]
	// Object.prototype.toString.call({}) = [object Object]
	// Object.prototype.toString.call([]) = [object Array]
	// Object.prototype.toString.call(function() {}) = [object Function]

	if (value == null) {
		return null;
	}
	if (value == undefined) {
		return null;
	}
	if (value == NaN) {
		return java.lang.Double.NaN;
	}

	switch (Object.prototype.toString.call(value)) {
	case '[object Boolean]':
		return value;
	case '[object Number]':
		return value;
	case '[object String]':
		return value;
	case '[object Array]':
		var list = new java.util.ArrayList();
		for (var i = 0; i < value.length; i++) {
			list.add(toJavaObject(value[i]));
		}
		return list;
	default:
		var map = new java.util.LinkedHashMap();
		for ( var name in value) {
			map.put(name, toJavaObject(value[name]));
		}
		return map
	}
}
