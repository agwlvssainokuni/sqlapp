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

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import cherry.spring.common.db.BaseDto;

public class BizdateDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private LocalDate bizdate;

	private Integer offsetDay;

	private Integer offsetHour;

	private Integer offsetMinute;

	private Integer offsetSecond;

	private LocalDateTime currentDateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getBizdate() {
		return bizdate;
	}

	public void setBizdate(LocalDate bizdate) {
		this.bizdate = bizdate;
	}

	public Integer getOffsetDay() {
		return offsetDay;
	}

	public void setOffsetDay(Integer offsetDay) {
		this.offsetDay = offsetDay;
	}

	public Integer getOffsetHour() {
		return offsetHour;
	}

	public void setOffsetHour(Integer offsetHour) {
		this.offsetHour = offsetHour;
	}

	public Integer getOffsetMinute() {
		return offsetMinute;
	}

	public void setOffsetMinute(Integer offsetMinute) {
		this.offsetMinute = offsetMinute;
	}

	public Integer getOffsetSecond() {
		return offsetSecond;
	}

	public void setOffsetSecond(Integer offsetSecond) {
		this.offsetSecond = offsetSecond;
	}

	public LocalDateTime getCurrentDateTime() {
		return currentDateTime;
	}

	public void setCurrentDateTime(LocalDateTime currentDateTime) {
		this.currentDateTime = currentDateTime;
	}

}
