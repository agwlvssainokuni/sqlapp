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

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public abstract class SqltoolMetadataFormBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@org.hibernate.validator.constraints.NotEmpty
	@cherry.spring.fwcore.validator.MaxLength(50)
	private String name;

	@org.hibernate.validator.constraints.NotEmpty
	@cherry.spring.fwcore.validator.MaxLength(500)
	private String description;

	private String ownedBy;

	private boolean publishedFlg;

	private Integer lockVersion;

	@Getter
	public enum Prop {
		Name("name", "sqltoolMetadataForm.name"), //
		Description("description", "sqltoolMetadataForm.description"), //
		OwnedBy("ownedBy", "sqltoolMetadataForm.ownedBy"), //
		PublishedFlg("publishedFlg", "sqltoolMetadataForm.publishedFlg"), //
		LockVersion("lockVersion", "sqltoolMetadataForm.lockVersion"), //
		DUMMY("dummy", "dummy");

		private final String name;
		private final String nameWithForm;

		private Prop(String name, String nameWithForm) {
			this.name = name;
			this.nameWithForm = nameWithForm;
		}
	}

}
