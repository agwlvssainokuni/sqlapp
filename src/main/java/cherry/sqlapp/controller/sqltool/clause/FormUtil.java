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

package cherry.sqlapp.controller.sqltool.clause;

import org.springframework.stereotype.Component;

import cherry.sqlapp.db.gen.dto.SqlSelect;

@Component("clauseFormUtil")
public class FormUtil {

	public SqltoolClauseForm getForm(SqlSelect record) {
		SqltoolClauseForm form = new SqltoolClauseForm();
		form.setDatabaseName(record.getDatabaseName());
		form.setSelect(record.getSelectClause());
		form.setFrom(record.getFromClause());
		form.setWhere(record.getWhereClause());
		form.setGroupBy(record.getGroupByClause());
		form.setHaving(record.getHavingClause());
		form.setOrderBy(record.getOrderByClause());
		form.setParamMap(record.getParamMap());
		return form;
	}

}
