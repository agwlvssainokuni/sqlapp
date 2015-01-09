/*
 * Copyright 2012,2015 agwlvssainokuni
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

package cherry.foundation.etl;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * データ取込みの結果を保持する。
 */
public class LoadResult {

	/** 読込んだデータの件数。 */
	private long totalCount;

	/** 取込みに成功したデータの件数。 */
	private long successCount;

	/** 取込みに失敗したデータの件数。 */
	private long failedCount;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(long successCount) {
		this.successCount = successCount;
	}

	public long getFailedCount() {
		return failedCount;
	}

	public void setFailedCount(long failedCount) {
		this.failedCount = failedCount;
	}

}
