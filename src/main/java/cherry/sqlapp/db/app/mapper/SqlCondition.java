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

package cherry.sqlapp.db.app.mapper;

import java.util.Date;

import cherry.sqlapp.db.BaseDto;

public class SqlCondition extends BaseDto {

	private static final long serialVersionUID = 1L;

	private String name;

	private boolean select;

	private boolean any;

	private boolean csv;

	private boolean publish;

	private boolean notPublish;

	private Date registeredFrom;

	private Date registeredTo;

	private String loginId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Date getRegisteredFrom() {
		return registeredFrom;
	}

	public void setRegisteredFrom(Date registeredFrom) {
		this.registeredFrom = registeredFrom;
	}

	public Date getRegisteredTo() {
		return registeredTo;
	}

	public void setRegisteredTo(Date registeredTo) {
		this.registeredTo = registeredTo;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

}
