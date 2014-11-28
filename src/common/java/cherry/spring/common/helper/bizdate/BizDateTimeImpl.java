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

package cherry.spring.common.helper.bizdate;

import static com.mysema.query.types.expr.DateTimeExpression.currentTimestamp;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import cherry.spring.fwcore.bizdtm.BizDateTime;
import cherry.spring.fwcore.type.DeletedFlag;
import cherry.spring.fwcore.type.jdbc.RowMapperCreator;
import cherry.sqlapp.db.gen.query.QBizdatetimeMaster;

import com.mysema.query.sql.SQLQuery;

public class BizDateTimeImpl implements BizDateTime, InitializingBean {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	@Autowired
	private RowMapperCreator rowMapperCreator;

	private RowMapper<BizdateDto> rowMapper;

	@Override
	public void afterPropertiesSet() {
		rowMapper = rowMapperCreator.create(BizdateDto.class);
	}

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
		QBizdatetimeMaster a = new QBizdatetimeMaster("a");
		SQLQuery query = createSqlQuery(a);
		BizdateDto dto = queryDslJdbcOperations.queryForObject(query,
				rowMapper, a.offsetDay, a.offsetHour, a.offsetMinute,
				a.offsetSecond,
				currentTimestamp(LocalDateTime.class).as("current_date_time"));
		if (dto == null) {
			return LocalDateTime.now();
		}
		return dto.getCurrentDateTime().plusDays(dto.getOffsetDay())
				.plusHours(dto.getOffsetHour())
				.plusMinutes(dto.getOffsetMinute())
				.plusSeconds(dto.getOffsetSecond());
	}

	private SQLQuery createSqlQuery(QBizdatetimeMaster a) {
		return queryDslJdbcOperations.newSqlQuery().from(a)
				.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()))
				.orderBy(a.id.desc()).limit(1);
	}

}
