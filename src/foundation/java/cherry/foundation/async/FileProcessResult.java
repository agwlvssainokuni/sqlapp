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
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 非同期実行フレームワーク。<br />
 * 非同期で実行したファイル処理の結果を保持する。内訳は下記の通り。
 * <ul>
 * <li>{@link #totalCount}: ファイルから読込んだレコードの総件数 (== {@link #okCount} + {@link #ngCount} とすること)。</li>
 * <li>{@link #okCount}: ファイルから読込んだレコードのうち正常に処理したレコードの件数 (== {@link #totalCount} - {@link #ngCount} とすること)。</li>
 * <li>{@link #ngCount}: ファイルから読込んだレコードのうち正常に処理しなかったレコードの件数 (== {@link #totalCount} - {@link #okCount} とすること)。</li>
 * <li>{@link #ngRecordInfoList}: ファイルから読込んだレコードのうち正常に処理しなかったレコードの情報 (size() == {@link #ngCount} とすること)。</li>
 * </ul>
 */
public class FileProcessResult implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ファイルから読込んだレコードの総件数 (== okCount + ngCount とすること)。 */
	private long totalCount;

	/** ファイルから読込んだレコードのうち正常に処理したレコードの件数 (== totalCount - ngCount とすること)。 */
	private long okCount;

	/** ファイルから読込んだレコードのうち正常に処理しなかったレコードの件数 (== totalCount - okCount とすること)。 */
	private long ngCount;

	/** ファイルから読込んだレコードのうち正常に処理しなかったレコードの情報 (size() == ngCount とすること)。 */
	private List<FileRecordInfo> ngRecordInfoList;

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

	public long getOkCount() {
		return okCount;
	}

	public void setOkCount(long okCount) {
		this.okCount = okCount;
	}

	public long getNgCount() {
		return ngCount;
	}

	public void setNgCount(long ngCount) {
		this.ngCount = ngCount;
	}

	public List<FileRecordInfo> getNgRecordInfoList() {
		return ngRecordInfoList;
	}

	public void setNgRecordInfoList(List<FileRecordInfo> ngRecordInfoList) {
		this.ngRecordInfoList = ngRecordInfoList;
	}

}
