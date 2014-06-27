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

import cherry.sqlapp.db.app.mapper.LoadMapper;
import cherry.sqlapp.db.app.mapper.MetadataMapper;
import cherry.sqlapp.db.gen.dto.SqltoolLoad;
import cherry.sqlapp.db.gen.dto.SqltoolMetadata;
import cherry.sqlapp.db.gen.mapper.SqltoolLoadMapper;

@Component
public class LoadServiceImpl implements LoadService {

	@Autowired
	private SqltoolLoadMapper sqlLoadMapper;

	@Autowired
	private LoadMapper loadMapper;

	@Autowired
	private MetadataMapper metadataMapper;

	@Transactional
	@Override
	public SqltoolLoad findById(int id) {
		SqltoolLoad record = sqlLoadMapper.selectByPrimaryKey(id);
		if (record.getDeletedFlg() != 0) {
			return null;
		}
		return record;
	}

	@Transactional
	@Override
	public int create(SqltoolLoad record, String ownedBy) {
		SqltoolMetadata metadata = new SqltoolMetadata();
		metadata.setDescription(ownedBy);
		metadata.setOwnedBy(ownedBy);
		int count0 = metadataMapper.createLoad(metadata);
		if (count0 != 1) {
			throw new IllegalArgumentException(
					"sql_metadata is not created; count=" + count0);
		}
		record.setId(metadata.getId());
		int count1 = loadMapper.create(record);
		if (count1 != 1) {
			throw new IllegalArgumentException("sql_csv is not created; count="
					+ count1);
		}
		return metadata.getId();
	}

	@Transactional
	@Override
	public boolean update(SqltoolLoad record) {
		int count = loadMapper.update(record);
		return count == 1;
	}

}
