/*
 * Copyright 2015 agwlvssainokuni
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

package cherry.spring.common.foundation.impl;

import static cherry.foundation.type.DeletedFlag.NOT_DELETED;
import static com.mysema.query.support.Expressions.constant;
import static com.mysema.query.support.Expressions.dateOperation;
import static com.mysema.query.support.Expressions.datePath;
import static com.mysema.query.support.Expressions.numberPath;
import static com.mysema.query.support.Expressions.path;

import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;

import cherry.foundation.workday.WorkdayStore;
import cherry.sqlapp.db.gen.query.QDayoffMaster;
import cherry.sqlapp.db.gen.query.QDigit;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLSubQuery;
import com.mysema.query.types.Ops.DateTimeOps;
import com.mysema.query.types.path.DatePath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.SimplePath;
import com.mysema.query.types.query.ListSubQuery;

public class WorkdayStoreImpl implements WorkdayStore {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	private final QDigit a = new QDigit("a");
	private final QDigit b = new QDigit("b");
	private final QDayoffMaster h0 = new QDayoffMaster("h0");
	private final QDayoffMaster h1 = new QDayoffMaster("h1");

	@Override
	public int getNumberOfWorkday(String name, LocalDate from, LocalDate to) {
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(h0);
		query.where(h0.name.eq(name), h0.dt.between(constant(from), constant(to)), h0.deletedFlg.eq(NOT_DELETED.code()));
		long count = queryDslJdbcOperations.queryForObject(query, h0.dt.count());
		return Days.daysBetween(from, to).getDays() + 1 - (int) count;
	}

	@Override
	public LocalDate getNextWorkday(String name, LocalDate from, int numberOfWorkday) {
		ListSubQuery<Tuple> subquery = new SQLSubQuery().from(a, b).list(
				a.d.multiply(10).add(b.d).as("n"),
				dateOperation(LocalDate.class, DateTimeOps.ADD_DAYS, constant(from), a.d.multiply(10).add(b.d))
						.as("dt"));

		SimplePath<Tuple> d = path(Tuple.class, "d");
		NumberPath<Long> dn = numberPath(Long.class, d, "n");
		DatePath<LocalDate> ddt = datePath(LocalDate.class, d, "dt");

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(subquery, d).leftJoin(h0)
				.on(h0.name.eq(name), h0.dt.between(constant(from), ddt), h0.deletedFlg.eq(NOT_DELETED.code()));
		query.where(new SQLSubQuery().from(h1)
				.where(h1.name.eq(name), h1.dt.eq(ddt), h1.deletedFlg.eq(NOT_DELETED.code())).notExists());
		query.groupBy(dn);
		query.having(h0.dt.count().eq(dn.subtract(numberOfWorkday).add(1)));
		List<LocalDate> list = queryDslJdbcOperations.query(query, ddt.min());

		return DataAccessUtils.requiredSingleResult(list);
	}

}
