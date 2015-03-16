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

package cherry.spring.common.foundation.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.code.CodeEntry;
import cherry.foundation.code.CodeStore;
import cherry.foundation.type.DeletedFlag;
import cherry.sqlapp.db.gen.query.QCodeMaster;

import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.QBean;

public class CodeStoreImpl implements CodeStore {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	private final QCodeMaster qcm = new QCodeMaster("a");

	@Transactional(readOnly = true)
	@Override
	public CodeEntry findByValue(String codeName, String value) {
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(qcm);
		query.where(qcm.name.eq(codeName), qcm.value.eq(value), qcm.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		return queryDslJdbcOperations.queryForObject(query, new QBean<>(CodeEntry.class, qcm.value, qcm.label,
				qcm.sortOrder));
	}

	@Transactional(readOnly = true)
	@Override
	public List<CodeEntry> getCodeList(String codeName) {
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(qcm);
		query.where(qcm.name.eq(codeName), qcm.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		query.orderBy(qcm.sortOrder.asc());
		return queryDslJdbcOperations.query(query, new QBean<>(CodeEntry.class, qcm.value, qcm.label, qcm.sortOrder));
	}

}
