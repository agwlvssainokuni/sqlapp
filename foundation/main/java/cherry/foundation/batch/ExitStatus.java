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

package cherry.foundation.batch;

/**
 * バッチプログラムの終了ステータスを表す。<br />
 * 具体的には、下記の4種類を定義する。
 * <ul>
 * <li>正常終了 ({@link #NORMAL})</li>
 * <li>警告終了 ({@link #WARN})</li>
 * <li>異常終了 ({@link #ERROR})</li>
 * <li>致命的異常 ({@link #FATAL})</li>
 * </ul>
 */
public enum ExitStatus {
	/** 正常終了 (0) */
	NORMAL(0),
	/** 警告終了 (1) */
	WARN(1),
	/** 異常修了 (-1) */
	ERROR(-1),
	/** 致命的異常 (-2) */
	FATAL(-2);

	/** 終了コード値を保持する。 */
	private final int code;

	/**
	 * 終了ステータスを生成する。
	 * 
	 * @param code 終了コード。
	 */
	private ExitStatus(int code) {
		this.code = code;
	}

	/**
	 * @return 終了コード値。
	 */
	public int getCode() {
		return code;
	}

}
