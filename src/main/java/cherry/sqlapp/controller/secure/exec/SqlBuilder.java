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

import org.apache.commons.lang3.StringUtils;

public class SqlBuilder {

	private String select = "*";

	private String from = "dual";

	private String where = null;

	private String groupBy = null;

	private String having = null;

	private String orderBy = null;

	public String build(Integer limit, Integer offset) {
		StringBuilder builder = new StringBuilder();
		append(builder, "SELECT ", select);
		append(builder, " FROM ", from);
		append(builder, " WHERE ", where);
		append(builder, " GROUP BY ", groupBy);
		append(builder, " HAVING ", having);
		append(builder, " ORDER BY ", orderBy);
		append(builder, " LIMIT ", limit);
		append(builder, " OFFSET ", offset);
		return builder.toString();
	}

	public String buildCount() {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT COUNT(*) FROM (");
		append(builder, "SELECT ", select);
		append(builder, " FROM ", from);
		append(builder, " WHERE ", where);
		append(builder, " GROUP BY ", groupBy);
		append(builder, " HAVING ", having);
		builder.append(")");
		return builder.toString();
	}

	private void append(StringBuilder builder, String clause, String value) {
		if (StringUtils.isNotBlank(value)) {
			builder.append(clause).append(value);
		}
	}

	private void append(StringBuilder builder, String clause, Integer value) {
		if (value != null) {
			builder.append(clause).append(value);
		}
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public String getHaving() {
		return having;
	}

	public void setHaving(String having) {
		this.having = having;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

}
