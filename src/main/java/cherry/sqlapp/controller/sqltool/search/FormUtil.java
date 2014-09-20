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

import org.springframework.stereotype.Component;

import cherry.sqlapp.service.sqltool.metadata.MetadataCondition;

@Component("searchFormUtil")
public class FormUtil {

	public MetadataCondition createSqlCondition(SqltoolSearchForm form,
			String loginId) {
		MetadataCondition cond = new MetadataCondition();
		cond.setName(form.getName());
		cond.setClause(form.isClause());
		cond.setStatement(form.isStatement());
		cond.setLoad(form.isLoad());
		cond.setPublish(form.isPublish());
		cond.setNotPublish(form.isNotPublish());
		cond.setRegisteredFrom(form.getRegisteredFrom());
		cond.setRegisteredTo(form.getRegisteredTo());
		cond.setLoginId(loginId);
		return cond;
	}

}
