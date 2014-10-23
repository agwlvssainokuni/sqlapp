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

package cherry.spring.fwcore.batch;

public enum ExitStatus {
	/** 正常終了 (0) */
	NORMAL(0),
	/** 警告終了 (50) */
	WARN(50),
	/** 異常修了 (100) */
	ERROR(100),
	/** 致命的異常 (150) */
	FATAL(150);

	private final int code;

	private ExitStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
