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

package cherry.foundation.async;

import cherry.foundation.type.Code;

/**
 * 非同期実行フレームワーク。<br />
 * 非同期処理の種類を表す。
 */
public enum AsyncType implements Code<String> {
	/** 非同期ファイル処理。 */
	FILE("FIL"),
	/** 非同期コマンド実行。 */
	COMMAND("CMD");

	/** 非同期処理の種類。 */
	private final String code;

	private AsyncType(String code) {
		this.code = code;
	}

	/**
	 * @return 非同期処理の種類。
	 * */
	@Override
	public String code() {
		return this.code;
	}

}
