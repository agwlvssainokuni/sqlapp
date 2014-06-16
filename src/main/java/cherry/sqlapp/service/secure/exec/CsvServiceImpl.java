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

import cherry.sqlapp.db.app.mapper.CsvMapper;
import cherry.sqlapp.db.app.mapper.MetadataMapper;
import cherry.sqlapp.db.gen.dto.SqlCsv;
import cherry.sqlapp.db.gen.dto.SqlMetadata;
import cherry.sqlapp.db.gen.mapper.SqlCsvMapper;

@Component
public class CsvServiceImpl implements CsvService {

	@Autowired
	private SqlCsvMapper sqlCsvMapper;

	@Autowired
	private CsvMapper csvMapper;

	@Autowired
	private MetadataMapper metadataMapper;

	@Transactional
	@Override
	public SqlCsv findById(int id) {
		SqlCsv record = sqlCsvMapper.selectByPrimaryKey(id);
		if (record.getDeletedFlg() != 0) {
			return null;
		}
		return record;
	}

	@Transactional
	@Override
	public int create(SqlCsv record, String ownedBy) {
		SqlMetadata metadata = new SqlMetadata();
		metadata.setDescription(ownedBy);
		metadata.setOwnedBy(ownedBy);
		int count0 = metadataMapper.createCsv(metadata);
		if (count0 != 1) {
			throw new IllegalArgumentException(
					"sql_metadata is not created; count=" + count0);
		}
		record.setId(metadata.getId());
		int count1 = csvMapper.create(record);
		if (count1 != 1) {
			throw new IllegalArgumentException("sql_csv is not created; count="
					+ count1);
		}
		return metadata.getId();
	}

	@Transactional
	@Override
	public void update(SqlCsv record) {
		int count = csvMapper.update(record);
		if (count != 1) {
			throw new IllegalArgumentException("sql_csv is not updated; count="
					+ count);
		}
	}

}
