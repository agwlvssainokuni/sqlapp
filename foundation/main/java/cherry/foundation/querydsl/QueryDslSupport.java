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

package cherry.foundation.querydsl;

import java.io.IOException;

import org.springframework.jdbc.core.RowMapper;

import cherry.foundation.etl.Consumer;
import cherry.foundation.etl.Limiter;
import cherry.foundation.etl.LimiterException;
import cherry.goods.paginate.PagedList;

import com.google.common.base.Predicate;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.Expression;

/**
 * Querydslサポート機能。<br />
 * 検索およびダウンロードにおけるQuerydslの呼出しパターンを共通化する。
 */
public interface QueryDslSupport {

	/**
	 * 検索におけるQuerydslの呼出しパターンを共通化する。
	 * 
	 * @param commonClause {@link SQLQuery}の共通部位 (FROM句、WHERE句、GROUP BY句、HAVING句) を構成する。
	 * @param orderByClause {@link SQLQuery}のORDER BY句を構成する。
	 * @param pageNo 抽出対象のページ番号。
	 * @param pageSz 抽出対象のページサイズ。
	 * @param rowMapper 検索結果をDTOに変換する。
	 * @param expressions SELECT句を指定する。
	 * @return 検索結果。
	 */
	<T> PagedList<T> search(QueryConfigurer commonClause, QueryConfigurer orderByClause, long pageNo, long pageSz,
			RowMapper<T> rowMapper, Expression<?>... expressions);

	/**
	 * 検索におけるQuerydslの呼出しパターンを共通化する。
	 * 
	 * @param commonClause {@link SQLQuery}の共通部位 (FROM句、WHERE句、GROUP BY句、HAVING句) を構成する。
	 * @param orderByClause {@link SQLQuery}のORDER BY句を構成する。
	 * @param pageNo 抽出対象のページ番号。
	 * @param pageSz 抽出対象のページサイズ。
	 * @param cancelPredicate 検索の打切り判定方式を指定する。
	 * @param rowMapper 検索結果をDTOに変換する。
	 * @param expressions SELECT句を指定する。
	 * @return 検索結果。
	 */
	<T> PagedList<T> search(QueryConfigurer commonClause, QueryConfigurer orderByClause, long pageNo, long pageSz,
			Predicate<Long> cancelPredicate, RowMapper<T> rowMapper, Expression<?>... expressions);

	/**
	 * 検索におけるQuerydslの呼出しパターンを共通化する。
	 * 
	 * @param commonClause {@link SQLQuery}の共通部位 (FROM句、WHERE句、GROUP BY句、HAVING句) を構成する。
	 * @param orderByClause {@link SQLQuery}のORDER BY句を構成する。
	 * @param pageNo 抽出対象のページ番号。
	 * @param pageSz 抽出対象のページサイズ。
	 * @param expression SELECT句を指定する。
	 * @return 検索結果。
	 */
	<T> PagedList<T> search(QueryConfigurer commonClause, QueryConfigurer orderByClause, long pageNo, long pageSz,
			Expression<T> expression);

	/**
	 * 検索におけるQuerydslの呼出しパターンを共通化する。
	 * 
	 * @param commonClause {@link SQLQuery}の共通部位 (FROM句、WHERE句、GROUP BY句、HAVING句) を構成する。
	 * @param orderByClause {@link SQLQuery}のORDER BY句を構成する。
	 * @param pageNo 抽出対象のページ番号。
	 * @param pageSz 抽出対象のページサイズ。
	 * @param cancelPredicate 検索の打切り判定方式を指定する。
	 * @param expression SELECT句を指定する。
	 * @return 検索結果。
	 */
	<T> PagedList<T> search(QueryConfigurer commonClause, QueryConfigurer orderByClause, long pageNo, long pageSz,
			Predicate<Long> cancelPredicate, Expression<T> expression);

	/**
	 * ダウンロードにおけるQuerydslの呼出しパターンを共通化する。
	 * 
	 * @param commonClause {@link SQLQuery}の共通部位 (FROM句、WHERE句、GROUP BY句、HAVING句) を構成する。
	 * @param orderByClause {@link SQLQuery}のORDER BY句を構成する。
	 * @param consumer ダウンロード形式への変換方式を指定する。
	 * @param expressions SELECT句を指定する。
	 * @return ダウンロードした件数。
	 * @throws IOException 入出力異常。
	 */
	long download(QueryConfigurer commonClause, QueryConfigurer orderByClause, Consumer consumer,
			Expression<?>... expressions) throws IOException;

	/**
	 * ダウンロードにおけるQuerydslの呼出しパターンを共通化する。
	 * 
	 * @param commonClause {@link SQLQuery}の共通部位 (FROM句、WHERE句、GROUP BY句、HAVING句) を構成する。
	 * @param orderByClause {@link SQLQuery}のORDER BY句を構成する。
	 * @param consumer ダウンロード形式への変換方式を指定する。
	 * @param limiter ダウンロードの打切り判定方式を指定する。
	 * @param expressions SELECT句を指定する。
	 * @return ダウンロードした件数。
	 * @throws LimiterException ダウンロード打切り。
	 * @throws IOException 入出力異常。
	 */
	long download(QueryConfigurer commonClause, QueryConfigurer orderByClause, Consumer consumer, Limiter limiter,
			Expression<?>... expressions) throws LimiterException, IOException;

}
