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

package cherry.sqlapp.service.secure.exec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cherry.sqlapp.db.app.mapper.MetadataMapper;
import cherry.sqlapp.db.app.mapper.SelectMapper;
import cherry.sqlapp.db.gen.dto.SqlMetadata;
import cherry.sqlapp.db.gen.dto.SqlSelect;
import cherry.sqlapp.db.gen.mapper.SqlSelectMapper;

@Component
public class SelectServiceImpl implements SelectService {

	@Autowired
	private SqlSelectMapper sqlSelectMapper;

	@Autowired
	private SelectMapper selectMapper;

	@Autowired
	private MetadataMapper metadataMapper;

	@Transactional
	@Override
	public SqlSelect findById(int id) {
		return sqlSelectMapper.selectByPrimaryKey(id);
	}

	@Transactional
	@Override
	public int create(SqlSelect record) {
		SqlMetadata metadata = new SqlMetadata();
		metadata.setName("name");
		metadata.setDescription("description");
		int count0 = metadataMapper.createSelect(metadata);
		if (count0 != 1) {
			throw new IllegalArgumentException(
					"sql_metadata is not created; count=" + count0);
		}
		record.setId(metadata.getId());
		int count1 = selectMapper.create(record);
		if (count1 != 1) {
			throw new IllegalArgumentException(
					"sql_select is not created; count=" + count1);
		}
		return metadata.getId();
	}

	@Transactional
	@Override
	public void update(SqlSelect record) {
		int count = selectMapper.update(record);
		if (count != 1) {
			throw new IllegalArgumentException(
					"sql_select is not updated; count=" + count);
		}
	}

}