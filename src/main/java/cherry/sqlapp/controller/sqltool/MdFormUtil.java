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

package cherry.sqlapp.controller.sqltool;

import org.springframework.stereotype.Component;

import cherry.sqlapp.db.gen.dto.SqltoolMetadata;

@Component("metadataFormUtil")
public class MdFormUtil {

	public SqltoolMetadataForm getMdForm(SqltoolMetadata record) {
		SqltoolMetadataForm mdForm = new SqltoolMetadataForm();
		mdForm.setName(record.getName());
		mdForm.setDescription(record.getDescription());
		mdForm.setOwnedBy(record.getOwnedBy());
		mdForm.setPublishedFlg(record.getPublishedFlg() != 0);
		mdForm.setLockVersion(record.getLockVersion());
		return mdForm;
	}

}
