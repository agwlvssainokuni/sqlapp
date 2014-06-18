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

package cherry.sqlapp.service.sqltool.metadata;

import org.joda.time.LocalDateTime;

import cherry.sqlapp.db.BaseDto;

public class Condition extends BaseDto {

	private static final long serialVersionUID = 1L;

	private String name;

	private LocalDateTime registeredFrom;

	private LocalDateTime registeredTo;

	private boolean clause = true;

	private boolean statement = true;

	private boolean load = true;

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

	public boolean isClause() {
		return clause;
	}

	public void setClause(boolean clause) {
		this.clause = clause;
	}

	public boolean isStatement() {
		return statement;
	}

	public void setStatement(boolean statement) {
		this.statement = statement;
	}

	public boolean isLoad() {
		return load;
	}

	public void setLoad(boolean load) {
		this.load = load;
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
