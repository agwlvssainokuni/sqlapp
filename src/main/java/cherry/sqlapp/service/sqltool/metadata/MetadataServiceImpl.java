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
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.common.custom.DeletedFlag;
import cherry.spring.common.custom.FlagCode;
import cherry.spring.common.custom.jdbc.RowMapperCreator;
import cherry.spring.common.helper.querydsl.SQLQueryConfigurer;
import cherry.spring.common.helper.querydsl.SQLQueryHelper;
import cherry.spring.common.helper.querydsl.SQLQueryResult;
import cherry.sqlapp.code.SqlTypeCode;
import cherry.sqlapp.db.dao.SqltoolMetadataDao;
import cherry.sqlapp.db.dto.SqltoolMetadata;
import cherry.sqlapp.db.gen.query.QSqltoolMetadata;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.Expression;
import com.mysema.query.types.Predicate;

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

				List<Predicate> list = new LinkedList<>();
				if (cond.getName() != null) {
					list.add(m.name.startsWith(cond.getName()));
				}

				if (cond.getRegisteredFrom() != null) {
					list.add(m.registeredAt.goe(cond.getRegisteredFrom()));
				}
				if (cond.getRegisteredTo() != null) {
					list.add(m.registeredAt.lt(cond.getRegisteredTo()
							.plusSeconds(1)));
				}

				Predicate pub = m.publishedFlg.ne(FlagCode.FALSE.code());
				Predicate prv = m.publishedFlg.eq(FlagCode.FALSE.code()).and(
						m.ownedBy.eq(cond.getLoginId()));
				BooleanBuilder bb = new BooleanBuilder();
				if (cond.isPublish()) {
					bb.or(pub);
				}
				if (cond.isNotPublish()) {
					bb.or(prv);
				}
				if (!bb.hasValue()) {
					bb.or(pub);
					bb.or(prv);
				}
				list.add(bb);

				List<String> code = new ArrayList<>();
				if (cond.isClause()) {
					code.add(SqlTypeCode.CLAUSE.code());
				}
				if (cond.isStatement()) {
					code.add(SqlTypeCode.STATEMENT.code());
				}
				if (cond.isLoad()) {
					code.add(SqlTypeCode.LOAD.code());
				}
				if (!code.isEmpty()) {
					list.add(m.sqlType.in(code));
				}

				list.add(m.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));

				return query.from(m).where(
						list.toArray(new Predicate[list.size()]));
			}

			@Override
			public SQLQuery orderBy(SQLQuery query) {
				return query.orderBy(m.id.asc());
			}
		};
	}

}
