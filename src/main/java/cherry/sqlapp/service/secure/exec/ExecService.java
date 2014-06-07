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

import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import cherry.spring.common.lib.paginate.PageSet;

public interface ExecService {

	Result exec(DataSource dataSource, String sql, Map<String, ?> paramMap);

	Result exec(DataSource dataSource, SqlBuilder sqlBuilder,
			Map<String, ?> paramMap, int pageNo, int pageSize);

	public static class Result {

		private PageSet pageSet;

		private ExecResult execResult;

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this,
					ToStringStyle.SHORT_PREFIX_STYLE);
		}

		public PageSet getPageSet() {
			return pageSet;
		}

		public void setPageSet(PageSet pageSet) {
			this.pageSet = pageSet;
		}

		public ExecResult getExecResult() {
			return execResult;
		}

		public void setExecResult(ExecResult execResult) {
			this.execResult = execResult;
		}
	}

}
