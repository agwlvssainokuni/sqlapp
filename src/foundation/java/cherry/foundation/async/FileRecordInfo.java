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

package cherry.foundation.async;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 非同期実行フレームワーク。<br />
 * 非同期で実行したファイル処理において、一つのレコードの処理結果を保持する。内訳は下記の通り。
 * <ul>
 * <li>{@link #number}: ファイルの中のレコード番号 (何レコード目か)。</li>
 * <li>{@link #ok}: 正常に処理したか (true: 正常に処理した、false: 正常に処理しなかった)。</li>
 * <li>{@link #description}: 処理結果の追加事項。正常に処理しなかった原因を記述する。</li>
 * </ul>
 */
public class FileRecordInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ファイルの中のレコード番号 (何レコード目か)。 */
	private long number;

	/** 正常に処理したか (true: 正常に処理した、false: 正常に処理しなかった)。 */
	private boolean ok;

	/** 処理結果の追加事項。正常に処理しなかった原因を記述する。 */
	private String description;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
