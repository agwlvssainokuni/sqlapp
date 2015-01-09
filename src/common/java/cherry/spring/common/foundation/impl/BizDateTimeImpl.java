/*
 * Copyright 2014,2015 agwlvssainokuni
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

import static com.mysema.query.types.expr.DateTimeExpression.currentTimestamp;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.foundation.type.DeletedFlag;
import cherry.sqlapp.db.gen.query.QBizdatetimeMaster;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.Expression;
import com.mysema.query.types.QTuple;

public class BizDateTimeImpl implements BizDateTime {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	@Override
	public LocalDate today() {
		QBizdatetimeMaster a = new QBizdatetimeMaster("a");
		SQLQuery query = createSqlQuery(a);
		LocalDate ldt = queryDslJdbcOperations.queryForObject(query, a.bizdate);
		if (ldt == null) {
			return LocalDate.now();
		}
		return ldt;
	}

	@Override
	public LocalDateTime now() {
		Expression<LocalDateTime> curDtm = currentTimestamp(LocalDateTime.class);
		QBizdatetimeMaster a = new QBizdatetimeMaster("a");
		SQLQuery query = createSqlQuery(a);
		Tuple tuple = queryDslJdbcOperations.queryForObject(query, new QTuple(curDtm, a.offsetDay, a.offsetHour,
				a.offsetMinute, a.offsetSecond));
		if (tuple == null) {
			return LocalDateTime.now();
		}
		return tuple.get(curDtm).plusDays(tuple.get(a.offsetDay)).plusHours(tuple.get(a.offsetHour))
				.plusMinutes(tuple.get(a.offsetMinute)).plusSeconds(tuple.get(a.offsetSecond));
	}

	private SQLQuery createSqlQuery(QBizdatetimeMaster a) {
		return queryDslJdbcOperations.newSqlQuery().from(a).where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()))
				.orderBy(a.id.desc()).limit(1);
	}

}
