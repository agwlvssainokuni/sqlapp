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
import cherry.sqlapp.validation.SqlStatementSize;

public class ExecAnyForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String databaseName;

	@NotEmpty
	@SqlStatementSize
	private String sql;

	@ParamMapSize
	private String paramMap;

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getParamMap() {
		return paramMap;
	}

	public void setParamMap(String paramMap) {
		this.paramMap = paramMap;
	}

}
