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

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public abstract class SqltoolSearchFormBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@cherry.foundation.validator.MaxLength(50)
	private String name;

	@cherry.foundation.type.format.CustomDateTimeFormat(cherry.foundation.type.format.CustomDateTimeFormat.Range.FROM)
	private org.joda.time.LocalDate registeredFromDt;

	@cherry.foundation.type.format.CustomDateTimeFormat(cherry.foundation.type.format.CustomDateTimeFormat.Range.FROM)
	private org.joda.time.LocalTime registeredFromTm;

	@cherry.foundation.type.format.CustomDateTimeFormat(cherry.foundation.type.format.CustomDateTimeFormat.Range.TO)
	private org.joda.time.LocalDate registeredToDt;

	@cherry.foundation.type.format.CustomDateTimeFormat(cherry.foundation.type.format.CustomDateTimeFormat.Range.TO)
	private org.joda.time.LocalTime registeredToTm;

	private java.util.List<cherry.sqlapp.controller.sqltool.search.SqlType> sqlType;

	private java.util.List<cherry.sqlapp.controller.sqltool.search.Published> published;

	private long pageNo = 0L;

	private long pageSz = 0L;

	@Getter
	public enum Prop {
		Name("name", "sqltoolSearchForm.name"), //
		RegisteredFrom("registeredFrom", "sqltoolSearchForm.registeredFrom"), //
		RegisteredFromDt("registeredFromDt", "sqltoolSearchForm.registeredFromDt"), //
		RegisteredFromTm("registeredFromTm", "sqltoolSearchForm.registeredFromTm"), //
		RegisteredTo("registeredTo", "sqltoolSearchForm.registeredTo"), //
		RegisteredToDt("registeredToDt", "sqltoolSearchForm.registeredToDt"), //
		RegisteredToTm("registeredToTm", "sqltoolSearchForm.registeredToTm"), //
		SqlType("sqlType", "sqltoolSearchForm.sqlType"), //
		Published("published", "sqltoolSearchForm.published"), //
		PageNo("pageNo", "sqltoolSearchForm.pageNo"), //
		PageSz("pageSz", "sqltoolSearchForm.pageSz"), //
		DUMMY("dummy", "dummy");

		private final String name;
		private final String nameWithForm;

		private Prop(String name, String nameWithForm) {
			this.name = name;
			this.nameWithForm = nameWithForm;
		}
	}

}
