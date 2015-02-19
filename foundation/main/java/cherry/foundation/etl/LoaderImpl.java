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

import java.io.IOException;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * データ取込み機能。
 */
public class LoaderImpl implements Loader {

	/** ログ出力。 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/** 読み込み件数。 */
	private long batchCount;

	/** エラーを許容する件数。 */
	private long allowedFailCount;

	/**
	 * 読込み件数 を設定する。
	 * 
	 * @param batchCount 読込み件数。
	 */
	public void setBatchCount(long batchCount) {
		this.batchCount = batchCount;
	}

	/**
	 * エラーを許容する件数 を設定する。
	 * 
	 * @param allowedFailCount エラーを許容する件数。
	 */
	public void setAllowedFailCount(long allowedFailCount) {
		this.allowedFailCount = allowedFailCount;
	}

	/**
	 * データを取込む。
	 * 
	 * @param dataSource データソース。
	 * @param sql SQL。
	 * @param provider データの取得元。
	 * @param limit データ取込み制限。
	 * @return 取込みの結果。
	 * @throws LimiterException データ取込み制限超過。
	 * @throws IOException データ取得でエラー。
	 */
	@Override
	public LoadResult load(DataSource dataSource, String sql, Provider provider, Limiter limiter)
			throws LimiterException, IOException {

		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);

		limiter.start();
		try {

			provider.begin();

			long totalCount = 0;
			long successCount = 0;
			long failedCount = 0;
			Map<String, ?> data;
			while ((data = provider.provide()) != null) {

				totalCount += 1;

				try {
					template.update(sql, data);
					successCount += 1;
				} catch (DataAccessException ex) {
					if (allowedFailCount <= 0) {
						throw ex;
					}

					failedCount += 1;
					logger.warn("SQL failed: count={}, message={}", failedCount, ex.getMessage());
					if (allowedFailCount < failedCount) {
						throw ex;
					}
				}

				if (batchCount > 0 && batchCount <= totalCount) {
					break;
				}

				limiter.tick();
			}

			provider.end();

			LoadResult result = new LoadResult();
			result.setTotalCount(totalCount);
			result.setSuccessCount(successCount);
			result.setFailedCount(failedCount);
			return result;

		} finally {
			limiter.stop();
		}
	}

}
