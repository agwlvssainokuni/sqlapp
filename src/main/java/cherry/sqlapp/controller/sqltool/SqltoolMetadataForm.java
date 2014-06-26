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

package cherry.sqlapp.controller.sqltool;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.NotEmpty;

import cherry.sqlapp.controller.BaseForm;
import cherry.sqlapp.validation.DescriptionSize;
import cherry.sqlapp.validation.NameSize;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class SqltoolMetadataForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@NameSize
	private String name;

	@NotEmpty
	@DescriptionSize
	private String description;

	private String ownedBy;

	private boolean publishedFlg;

	private Integer lockVersion;

}
