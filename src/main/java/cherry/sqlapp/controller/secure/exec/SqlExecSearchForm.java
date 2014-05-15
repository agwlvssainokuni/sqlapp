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

import org.joda.time.LocalDateTime;

import cherry.spring.common.custom.format.CustomDateTimeFormat;
import cherry.spring.common.custom.format.CustomDateTimeFormat.Range;
import cherry.sqlapp.controller.BaseForm;
import cherry.sqlapp.validation.NameSize;

public class SqlExecSearchForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	@NameSize
	private String name;

	@CustomDateTimeFormat(Range.FROM)
	private LocalDateTime registeredFrom;

	@CustomDateTimeFormat(Range.TO)
	private LocalDateTime registeredTo;

	private boolean select = true;

	private boolean any = true;

	private boolean csv = true;

	private boolean publish = true;

	private boolean notPublish = true;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getRegisteredFrom() {
		return registeredFrom;
	}

	public void setRegisteredFrom(LocalDateTime registeredFrom) {
		this.registeredFrom = registeredFrom;
	}

	public LocalDateTime getRegisteredTo() {
		return registeredTo;
	}

	public void setRegisteredTo(LocalDateTime registeredTo) {
		this.registeredTo = registeredTo;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public boolean isAny() {
		return any;
	}

	public void setAny(boolean any) {
		this.any = any;
	}

	public boolean isCsv() {
		return csv;
	}

	public void setCsv(boolean csv) {
		this.csv = csv;
	}

	public boolean isPublish() {
		return publish;
	}

	public void setPublish(boolean publish) {
		this.publish = publish;
	}

	public boolean isNotPublish() {
		return notPublish;
	}

	public void setNotPublish(boolean notPublish) {
		this.notPublish = notPublish;
	}

}
