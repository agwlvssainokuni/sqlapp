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
 * 非同期処理の実行状況 (ステータス) を表す。
 */
public enum AsyncStatus implements Code<String> {
	/** キュー投入準備中 (初期状態)。 */
	PREPARING("0"),
	/** キュー投入済み。 */
	LAUNCHED("1"),
	/** 非同期処理実行中。 */
	PROCESSING("2"),
	/** 非同期処理正常終了 (全件OK)。 */
	SUCCESS("3"),
	/** 非同期処理警告終了 (NGあり)。 */
	WARN("4"),
	/** 非同期処理異常終了 (全件NG)。 */
	ERROR("5"),
	/** 非同期処理例外終了 (例外発生)。 */
	EXCEPTION("9");

	/** 実行状況の区分値。 */
	private final String code;

	private AsyncStatus(String code) {
		this.code = code;
	}

	/**
	 * @return 実行状況の区分値。
	 * */
	@Override
	public String code() {
		return this.code;
	}

}
