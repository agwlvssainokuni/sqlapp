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
import cherry.sqlapp.db.gen.dto.SqlMetadata;
import cherry.sqlapp.db.gen.mapper.SqlMetadataMapper;

@Component
public class MetadataServiceImpl implements MetadataService {

	@Autowired
	private SqlMetadataMapper sqlMetadataMapper;

	@Autowired
	private MetadataMapper metadataMapper;

	@Transactional
	@Override
	public SqlMetadata findById(int id, String ownedBy) {
		SqlMetadata record = sqlMetadataMapper.selectByPrimaryKey(id);
		if (0 != record.getDeletedFlg()) {
			return null;
		}
		if (0 != record.getPublishedFlg()) {
			return record;
		}
		if (ownedBy.equals(record.getOwnedBy())) {
			return record;
		}
		return null;
	}

	@Transactional
	@Override
	public void update(SqlMetadata record) {
		int count = metadataMapper.update(record);
		if (count != 1) {
			throw new IllegalArgumentException(
					"sql_metadata is not updated; count=" + count);
		}
	}

}
