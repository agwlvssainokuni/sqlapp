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
import org.springframework.web.multipart.MultipartFile;

import cherry.sqlapp.controller.BaseForm;
import cherry.sqlapp.validation.SqlStatementSize;

public class SqlExecCsvForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@SqlStatementSize
	private String sql;

	@SqlStatementSize
	private String sqlAlt1;

	@SqlStatementSize
	private String sqlAlt2;

	private MultipartFile file;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getSqlAlt1() {
		return sqlAlt1;
	}

	public void setSqlAlt1(String sqlAlt1) {
		this.sqlAlt1 = sqlAlt1;
	}

	public String getSqlAlt2() {
		return sqlAlt2;
	}

	public void setSqlAlt2(String sqlAlt2) {
		this.sqlAlt2 = sqlAlt2;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
