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

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;

public class BizdateHelperImpl implements BizdateHelper {

	@Autowired
	private BizdateDao bizdateDao;

	@Override
	public LocalDate today() {
		List<BizdateDto> list = bizdateDao.selectFirst();
		if (list.isEmpty()) {
			return LocalDate.now();
		}
		BizdateDto dto = list.get(0);
		return dto.getBizdate();
	}

	@Override
	public LocalDateTime now() {
		List<BizdateDto> list = bizdateDao.selectFirst();
		if (list.isEmpty()) {
			return LocalDateTime.now();
		}
		BizdateDto dto = list.get(0);
		return dto.getCurrentDateTime().plusDays(dto.getOffsetDay())
				.plusHours(dto.getOffsetHour())
				.plusMinutes(dto.getOffsetMinute())
				.plusSeconds(dto.getOffsetSecond());
	}

}
