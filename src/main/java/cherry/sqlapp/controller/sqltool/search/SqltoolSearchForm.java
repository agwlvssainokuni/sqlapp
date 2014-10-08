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
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import cherry.spring.common.type.format.CustomDateTimeFormat;
import cherry.spring.common.type.format.CustomDateTimeFormat.Range;
import cherry.sqlapp.validation.NameSize;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class SqltoolSearchForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@NameSize
	private String name;

	@CustomDateTimeFormat(Range.FROM)
	private LocalDate registeredFromDt;

	@CustomDateTimeFormat(Range.FROM)
	private LocalTime registeredFromTm;

	@CustomDateTimeFormat(Range.TO)
	private LocalDate registeredToDt;

	@CustomDateTimeFormat(Range.TO)
	private LocalTime registeredToTm;

	private List<SqlType> sqlType;

	private List<Published> published;

	private int pageNo = 0;

	private int pageSz = 0;

}
