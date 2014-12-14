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

package cherry.spring.common.foundation.impl;

import static com.mysema.query.types.expr.DateTimeExpression.currentTimestamp;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.foundation.type.DeletedFlag;
import cherry.foundation.type.jdbc.RowMapperCreator;
import cherry.spring.common.db.gen.query.QBizdatetimeMaster;

import com.mysema.query.sql.SQLQuery;

public class BizDateTimeImpl implements BizDateTime, InitializingBean {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	@Autowired
	private RowMapperCreator rowMapperCreator;

	private RowMapper<BizDateTimeDto> rowMapper;

	@Override
	public void afterPropertiesSet() {
		rowMapper = rowMapperCreator.create(BizDateTimeDto.class);
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
		BizDateTimeDto dto = queryDslJdbcOperations.queryForObject(query,
				rowMapper,
				currentTimestamp(LocalDateTime.class).as("current_dtm"),
				a.offsetDay, a.offsetHour, a.offsetMinute, a.offsetSecond);
		if (dto == null) {
			return LocalDateTime.now();
		}
		return dto.getCurrentDtm().plusDays(dto.getOffsetDay())
				.plusHours(dto.getOffsetHour())
				.plusMinutes(dto.getOffsetMinute())
				.plusSeconds(dto.getOffsetSecond());
	}

	private SQLQuery createSqlQuery(QBizdatetimeMaster a) {
		return queryDslJdbcOperations.newSqlQuery().from(a)
				.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()))
				.orderBy(a.id.desc()).limit(1);
	}

	@Setter
	@Getter
	@EqualsAndHashCode
	@ToString
	public static class BizDateTimeDto {
		private LocalDateTime currentDtm;
		private int offsetDay;
		private int offsetHour;
		private int offsetMinute;
		private int offsetSecond;
	}

}
