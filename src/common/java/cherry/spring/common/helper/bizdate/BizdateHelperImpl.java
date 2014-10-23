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

import java.io.IOException;
import java.util.Map;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import cherry.spring.fwcore.sql.SqlLoader;
import cherry.spring.fwcore.type.jdbc.RowMapperCreator;

public class BizdateHelperImpl implements BizdateHelper, InitializingBean {

	@Autowired
	private NamedParameterJdbcOperations namedParameterJdbcOperations;

	@Autowired
	private RowMapperCreator rowMapperCreator;

	@Autowired
	private SqlLoader sqlLoader;

	private RowMapper<BizdateDto> rowMapper;

	private String findBizdate;

	public void setFindBizdate(String findBizdate) {
		this.findBizdate = findBizdate;
	}

	@Override
	public void afterPropertiesSet() throws IOException {
		BeanWrapper bw = new BeanWrapperImpl(this);
		bw.setPropertyValues(sqlLoader.load(getClass()));
		rowMapper = rowMapperCreator.create(BizdateDto.class);
	}

	@Override
	public LocalDate today() {
		try {
			BizdateDto dto = namedParameterJdbcOperations.queryForObject(
					findBizdate, (Map<String, ?>) null, rowMapper);
			return dto.getBizdate();
		} catch (IncorrectResultSizeDataAccessException ex) {
			return LocalDate.now();
		}
	}

	@Override
	public LocalDateTime now() {
		try {
			BizdateDto dto = namedParameterJdbcOperations.queryForObject(
					findBizdate, (Map<String, ?>) null, rowMapper);
			return dto.getCurrentDateTime().plusDays(dto.getOffsetDay())
					.plusHours(dto.getOffsetHour())
					.plusMinutes(dto.getOffsetMinute())
					.plusSeconds(dto.getOffsetSecond());
		} catch (IncorrectResultSizeDataAccessException ex) {
			return LocalDateTime.now();
		}
	}

}
