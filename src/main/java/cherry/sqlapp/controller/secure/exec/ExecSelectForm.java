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

package cherry.sqlapp.controller.secure.exec;

import org.hibernate.validator.constraints.NotEmpty;

import cherry.sqlapp.controller.BaseForm;
import cherry.sqlapp.validation.ParamMapSize;
import cherry.sqlapp.validation.SqlClauseSize;

public class ExecSelectForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String databaseName;

	@NotEmpty
	@SqlClauseSize
	private String select;

	@NotEmpty
	@SqlClauseSize
	private String from;

	@SqlClauseSize
	private String where;

	@SqlClauseSize
	private String groupBy;

	@SqlClauseSize
	private String having;

	@SqlClauseSize
	private String orderBy;

	@ParamMapSize
	private String paramMap;

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public String getHaving() {
		return having;
	}

	public void setHaving(String having) {
		this.having = having;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getParamMap() {
		return paramMap;
	}

	public void setParamMap(String paramMap) {
		this.paramMap = paramMap;
	}

}
