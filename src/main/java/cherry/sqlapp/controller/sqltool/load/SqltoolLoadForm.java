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

package cherry.sqlapp.controller.sqltool.load;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import cherry.sqlapp.controller.BaseForm;
import cherry.sqlapp.validation.SqlStatementSize;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class SqltoolLoadForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String databaseName;

	@NotEmpty
	@SqlStatementSize
	private String sql;

	private MultipartFile file;

}