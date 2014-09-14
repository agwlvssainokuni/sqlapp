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

package cherry.spring.common.custom;

public enum FlagCode implements CodeEnum<Integer> {
	// 真偽
	FALSE(0), TRUE(1),
	// スイッチ
	OFF(0), ON(1),
	// 回答
	NO(0), YES(1);

	private Integer code;

	private FlagCode(Integer code) {
		this.code = code;
	}

	@Override
	public Integer code() {
		return this.code;
	}

}
