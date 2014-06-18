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

import cherry.sqlapp.db.app.mapper.StatementMapper;
import cherry.sqlapp.db.app.mapper.MetadataMapper;
import cherry.sqlapp.db.gen.dto.SqlAny;
import cherry.sqlapp.db.gen.dto.SqlMetadata;
import cherry.sqlapp.db.gen.mapper.SqlAnyMapper;

@Component
public class StatementServiceImpl implements StatementService {

	@Autowired
	private SqlAnyMapper sqlStatementMapper;

	@Autowired
	private StatementMapper statementMapper;

	@Autowired
	private MetadataMapper metadataMapper;

	@Transactional
	@Override
	public SqlAny findById(int id) {
		SqlAny record = sqlStatementMapper.selectByPrimaryKey(id);
		if (record.getDeletedFlg() != 0) {
			return null;
		}
		return record;
	}

	@Transactional
	@Override
	public int create(SqlAny record, String ownedBy) {
		SqlMetadata metadata = new SqlMetadata();
		metadata.setDescription(ownedBy);
		metadata.setOwnedBy(ownedBy);
		int count0 = metadataMapper.createStatement(metadata);
		if (count0 != 1) {
			throw new IllegalArgumentException(
					"sql_metadata is not created; count=" + count0);
		}
		record.setId(metadata.getId());
		int count1 = statementMapper.create(record);
		if (count1 != 1) {
			throw new IllegalArgumentException("sql_any is not created; count="
					+ count1);
		}
		return metadata.getId();
	}

	@Transactional
	@Override
	public void update(SqlAny record) {
		int count = statementMapper.update(record);
		if (count != 1) {
			throw new IllegalArgumentException("sql_any is not updated; count="
					+ count);
		}
	}

}
