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

package cherry.sqlapp.service.sqltool.metadata;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.querydsl.QueryConfigurer;
import cherry.foundation.querydsl.QueryDslSupport;
import cherry.foundation.type.DeletedFlag;
import cherry.foundation.type.FlagCode;
import cherry.foundation.type.jdbc.RowMapperCreator;
import cherry.goods.paginate.PagedList;
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
	private QueryDslSupport queryDslSupport;

	@Autowired
	private RowMapperCreator rowMapperCreator;

	@Transactional(readOnly = true)
	@Override
	public SqltoolMetadata findById(int id, String loginId) {
		SqltoolMetadata record = sqltoolMetadataDao.findById(id, loginId);
		if (record.getPublishedFlg().booleanValue()) {
			return record;
		}
		if (record.getOwnedBy().equals(loginId)) {
			return record;
		}
		return null;
	}

	@Transactional
	@Override
	public boolean update(SqltoolMetadata record) {
		int count = sqltoolMetadataDao.update(record);
		return count == 1;
	}

	@Transactional(readOnly = true)
	@Override
	public PagedList<SqltoolMetadata> search(MetadataCondition cond, long pageNo, long pageSz) {
		QSqltoolMetadata m = new QSqltoolMetadata("m");
		return queryDslSupport.search(commonClause(m, cond), orderByClause(m, cond), pageNo, pageSz,
				rowMapperCreator.create(SqltoolMetadata.class), getColumns(m));
	}

	private Expression<?>[] getColumns(QSqltoolMetadata m) {
		return new Expression<?>[] { m.id, m.sqlType, m.name, m.description, m.ownedBy, m.publishedFlg, m.registeredAt,
				m.updatedAt, m.createdAt, m.lockVersion, m.deletedFlg };
	}

	private QueryConfigurer commonClause(final QSqltoolMetadata m, final MetadataCondition cond) {
		return new QueryConfigurer() {
			@Override
			public SQLQuery configure(SQLQuery query) {
				query.from(m);

				if (StringUtils.isNotEmpty(cond.getName())) {
					query.where(m.name.startsWith(cond.getName()));
				}

				if (cond.getRegisteredFrom() != null) {
					query.where(m.registeredAt.goe(cond.getRegisteredFrom()));
				}
				if (cond.getRegisteredTo() != null) {
					query.where(m.registeredAt.lt(cond.getRegisteredTo()));
				}

				BooleanBuilder bb = new BooleanBuilder();
				if (cond.getPublishedFlg().isEmpty() || cond.getPublishedFlg().contains(FlagCode.TRUE)) {
					bb.or(m.publishedFlg.ne(FlagCode.FALSE.code()));
				}
				if (cond.getPublishedFlg().isEmpty() || cond.getPublishedFlg().contains(FlagCode.FALSE)) {
					bb.or(m.publishedFlg.eq(FlagCode.FALSE.code()).and(m.ownedBy.eq(cond.getLoginId())));
				}
				query.where(bb);

				if (!cond.getSqlType().isEmpty()) {
					List<String> code = new ArrayList<>();
					for (SqlTypeCode c : cond.getSqlType()) {
						code.add(c.code());
					}
					query.where(m.sqlType.in(code));
				}

				query.where(m.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
				return query;
			}
		};
	}

	private QueryConfigurer orderByClause(final QSqltoolMetadata m, final MetadataCondition cond) {
		return new QueryConfigurer() {
			@Override
			public SQLQuery configure(SQLQuery query) {
				return query.orderBy(m.id.asc());
			}
		};
	}

}
