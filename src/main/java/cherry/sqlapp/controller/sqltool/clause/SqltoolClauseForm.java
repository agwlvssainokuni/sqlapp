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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.NotEmpty;

import cherry.sqlapp.controller.BaseForm;
import cherry.sqlapp.validation.ParamMapSize;
import cherry.sqlapp.validation.SqlClauseSize;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class SqltoolClauseForm extends BaseForm {

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

}
