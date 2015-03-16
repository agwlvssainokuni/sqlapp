/*
 * Copyright 2015 agwlvssainokuni
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

package cherry.spring.common.foundation.impl;

import static com.google.common.base.Preconditions.checkState;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;
import org.springframework.data.jdbc.query.SqlUpdateCallback;

import cherry.foundation.numbering.NumberingDefinition;
import cherry.foundation.numbering.NumberingStore;
import cherry.foundation.type.DeletedFlag;
import cherry.sqlapp.db.gen.query.QNumberingMaster;

import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.QBean;

public class NumberingStoreImpl implements NumberingStore {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	private final QNumberingMaster nm = new QNumberingMaster("nm");

	@Override
	public NumberingDefinition getDefinition(String numberName) {
		SQLQuery query = createBaseQuery(numberName);
		List<NumberingDefinition> list = queryDslJdbcOperations.query(query, new QBean<NumberingDefinition>(
				NumberingDefinition.class, nm.template, nm.minValue, nm.maxValue));
		return DataAccessUtils.requiredSingleResult(list);
	}

	@Override
	public long loadAndLock(String numberName) {
		SQLQuery query = createBaseQuery(numberName);
		query.forUpdate();
		List<Long> list = queryDslJdbcOperations.query(query, nm.currentValue);
		return DataAccessUtils.requiredSingleResult(list).longValue();
	}

	@Override
	public void saveAndUnlock(final String numberName, final long current) {
		long count = queryDslJdbcOperations.update(nm, new SqlUpdateCallback() {
			@Override
			public long doInSqlUpdateClause(SQLUpdateClause update) {
				update.set(nm.currentValue, current);
				update.set(nm.lockVersion, nm.lockVersion.add(1));
				update.where(nm.name.eq(numberName), nm.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
				return update.execute();
			}
		});
		checkState(count == 1, "Failed to update {0}: name={1}, currentValue={2}, count={3}", nm.getTableName(),
				numberName, current, count);
	}

	private SQLQuery createBaseQuery(String numberName) {
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(nm);
		query.where(nm.name.eq(numberName), nm.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		return query;
	}

}
