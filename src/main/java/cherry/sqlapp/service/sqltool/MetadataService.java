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

package cherry.sqlapp.service.sqltool;

import java.util.List;

import cherry.spring.common.lib.paginate.PageSet;
import cherry.sqlapp.controller.sqltool.search.SqltoolSearchForm;
import cherry.sqlapp.db.BaseDto;
import cherry.sqlapp.db.gen.dto.SqlMetadata;

public interface MetadataService {

	SqlMetadata findById(int id, String loginId);

	void update(SqlMetadata record);

	Result search(SqltoolSearchForm form, String loginId, int pageNo, int pageSize);

	public static class Result extends BaseDto {

		private static final long serialVersionUID = 1L;

		private PageSet pageSet;

		private List<SqlMetadata> metadataList;

		public PageSet getPageSet() {
			return pageSet;
		}

		public void setPageSet(PageSet pageSet) {
			this.pageSet = pageSet;
		}

		public List<SqlMetadata> getMetadataList() {
			return metadataList;
		}

		public void setMetadataList(List<SqlMetadata> metadataList) {
			this.metadataList = metadataList;
		}

	}

}
