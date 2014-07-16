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

package cherry.sqlapp.service.sqltool.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cherry.sqlapp.db.dao.SqltoolMetadataDao;
import cherry.sqlapp.db.dao.SqltoolStatementDao;
import cherry.sqlapp.db.dto.SqltoolMetadata;
import cherry.sqlapp.db.dto.SqltoolStatement;
import cherry.sqlapp.db.mapper.MetadataMapper;

@Component
public class StatementServiceImpl implements StatementService {

	@Autowired
	private SqltoolStatementDao sqltoolStatementDao;

	@Autowired
	private SqltoolMetadataDao sqltoolMetadataDao;

	@Autowired
	private MetadataMapper metadataMapper;

	@Transactional
	@Override
	public SqltoolStatement findById(int id) {
		return sqltoolStatementDao.findById(id);
	}

	@Transactional
	@Override
	public int create(SqltoolStatement record, String ownedBy) {
		SqltoolMetadata metadata = new SqltoolMetadata();
		metadata.setSqlType("statement");
		metadata.setDescription(ownedBy);
		metadata.setOwnedBy(ownedBy);
		int count0 = sqltoolMetadataDao.create(metadata);
		if (count0 != 1) {
			throw new IllegalArgumentException(
					"sqltool_metadata is not created; count=" + count0);
		}
		record.setId(metadata.getId());
		int count1 = sqltoolStatementDao.create(record);
		if (count1 != 1) {
			throw new IllegalArgumentException(
					"sqltool_statement is not created; count=" + count1);
		}
		return metadata.getId();
	}

	@Transactional
	@Override
	public boolean update(SqltoolStatement record) {
		int count = sqltoolStatementDao.update(record);
		return count == 1;
	}

}
