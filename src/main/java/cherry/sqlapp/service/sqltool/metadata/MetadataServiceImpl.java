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

package cherry.sqlapp.service.sqltool.metadata;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.common.helper.querydsl.SQLQueryConfigurer;
import cherry.spring.common.helper.querydsl.SQLQueryHelper;
import cherry.spring.common.helper.querydsl.SQLQueryResult;
import cherry.spring.common.lib.util.LocalDateTimeUtil;
import cherry.spring.common.type.DeletedFlag;
import cherry.spring.common.type.FlagCode;
import cherry.spring.common.type.jdbc.RowMapperCreator;
import cherry.sqlapp.code.SqlTypeCode;
import cherry.sqlapp.db.dao.SqltoolMetadataDao;
import cherry.sqlapp.db.dto.SqltoolMetadata;
import cherry.sqlapp.db.gen.query.QSqltoolMetadata;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.Expression;

@Component
public class MetadataServiceImpl implements MetadataService {

	@Autowired
	private SqltoolMetadataDao sqltoolMetadataDao;

	@Autowired
	private SQLQueryHelper sqlQueryHelper;

	@Autowired
	private RowMapperCreator rowMapperCreator;

	@Cacheable(value = "SqltoolMetadata", key = "#id")
	@Transactional
	@Override
	public SqltoolMetadata findById(int id, String loginId) {
		return sqltoolMetadataDao.findById(id, loginId);
	}

	@CacheEvict(value = "SqltoolMetadata", key = "#record.id")
	@Transactional
	@Override
	public boolean update(SqltoolMetadata record) {
		int count = sqltoolMetadataDao.update(record);
		return count == 1;
	}

	@Transactional
	@Override
	public Result search(MetadataCondition cond, int pageNo, int pageSz) {

		QSqltoolMetadata m = new QSqltoolMetadata("m");
		SQLQueryResult<SqltoolMetadata> r = sqlQueryHelper.search(
				getConfigurer(m, cond), pageNo, pageSz,
				rowMapperCreator.create(SqltoolMetadata.class), getColumns(m));

		Result result = new Result();
		result.setPageSet(r.getPageSet());
		result.setMetadataList(r.getResultList());
		return result;
	}

	private Expression<?>[] getColumns(QSqltoolMetadata m) {
		return new Expression<?>[] { m.id, m.sqlType, m.name, m.description,
				m.ownedBy, m.publishedFlg, m.registeredAt, m.updatedAt,
				m.createdAt, m.lockVersion, m.deletedFlg };
	}

	private SQLQueryConfigurer getConfigurer(final QSqltoolMetadata m,
			final MetadataCondition cond) {
		return new SQLQueryConfigurer() {

			@Override
			public SQLQuery configure(SQLQuery query) {

				BooleanBuilder where = new BooleanBuilder();
				if (cond.getName() != null) {
					where.and(m.name.startsWith(cond.getName()));
				}

				if (cond.getRegisteredFrom() != null) {
					where.and(m.registeredAt.goe(LocalDateTimeUtil
							.rangeFrom(cond.getRegisteredFrom())));
				}
				if (cond.getRegisteredTo() != null) {
					where.and(m.registeredAt.lt(LocalDateTimeUtil.rangeTo(cond
							.getRegisteredTo())));
				}

				BooleanBuilder bb = new BooleanBuilder();
				if (cond.getPublishedFlg().isEmpty()
						|| cond.getPublishedFlg().contains(FlagCode.TRUE)) {
					bb.or(m.publishedFlg.ne(FlagCode.FALSE.code()));
				}
				if (cond.getPublishedFlg().isEmpty()
						|| cond.getPublishedFlg().contains(FlagCode.FALSE)) {
					bb.or(m.publishedFlg.eq(FlagCode.FALSE.code()).and(
							m.ownedBy.eq(cond.getLoginId())));
				}
				where.and(bb);

				if (!cond.getSqlType().isEmpty()) {
					List<String> code = new ArrayList<>();
					for (SqlTypeCode c : cond.getSqlType()) {
						code.add(c.code());
					}
					where.and(m.sqlType.in(code));
				}

				where.and(m.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));

				return query.from(m).where(where);
			}

			@Override
			public SQLQuery orderBy(SQLQuery query) {
				return query.orderBy(m.id.asc());
			}
		};
	}

}
