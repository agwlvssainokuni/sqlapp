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

import cherry.sqlapp.db.app.mapper.MetadataMapper;
import cherry.sqlapp.db.app.mapper.ClauseMapper;
import cherry.sqlapp.db.gen.dto.SqltoolMetadata;
import cherry.sqlapp.db.gen.dto.SqltoolClause;
import cherry.sqlapp.db.gen.mapper.SqltoolClauseMapper;

@Component
public class ClauseServiceImpl implements ClauseService {

	@Autowired
	private SqltoolClauseMapper sqlClauseMapper;

	@Autowired
	private ClauseMapper clauseMapper;

	@Autowired
	private MetadataMapper metadataMapper;

	@Transactional
	@Override
	public SqltoolClause findById(int id) {
		SqltoolClause record = sqlClauseMapper.selectByPrimaryKey(id);
		if (record.getDeletedFlg() != 0) {
			return null;
		}
		return record;
	}

	@Transactional
	@Override
	public int create(SqltoolClause record, String ownedBy) {
		SqltoolMetadata metadata = new SqltoolMetadata();
		metadata.setDescription(ownedBy);
		metadata.setOwnedBy(ownedBy);
		int count0 = metadataMapper.createClause(metadata);
		if (count0 != 1) {
			throw new IllegalArgumentException(
					"sql_metadata is not created; count=" + count0);
		}
		record.setId(metadata.getId());
		int count1 = clauseMapper.create(record);
		if (count1 != 1) {
			throw new IllegalArgumentException(
					"sql_select is not created; count=" + count1);
		}
		return metadata.getId();
	}

	@Transactional
	@Override
	public void update(SqltoolClause record) {
		int count = clauseMapper.update(record);
		if (count != 1) {
			throw new IllegalArgumentException(
					"sql_select is not updated; count=" + count);
		}
	}

}