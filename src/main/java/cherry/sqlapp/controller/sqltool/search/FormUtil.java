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

package cherry.sqlapp.controller.sqltool.search;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

import cherry.sqlapp.db.mapper.MetadataCondition;

@Component("searchFormUtil")
public class FormUtil {

	public MetadataCondition createSqlCondition(SqltoolSearchForm form,
			String loginId) {
		MetadataCondition cond = new MetadataCondition();
		cond.setName(stringCond(form.getName()));
		cond.setClause(form.isClause());
		cond.setStatement(form.isStatement());
		cond.setLoad(form.isLoad());
		cond.setPublish(form.isPublish());
		cond.setNotPublish(form.isNotPublish());
		cond.setRegisteredFrom(dateFromCond(form.getRegisteredFrom()));
		cond.setRegisteredTo(dateToCond(form.getRegisteredTo()));
		cond.setLoginId(loginId);
		return cond;
	}

	private String stringCond(String string) {
		if (StringUtils.isBlank(string)) {
			return null;
		}
		return string.replaceAll("([%_\\\\])", "\\$1") + "%";
	}

	private LocalDateTime dateFromCond(LocalDateTime dt) {
		if (dt == null) {
			return null;
		}
		return dt;
	}

	private LocalDateTime dateToCond(LocalDateTime dt) {
		if (dt == null) {
			return null;
		}
		return dt.plusSeconds(1);
	}

}
