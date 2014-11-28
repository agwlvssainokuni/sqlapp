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

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.LocalDateTime;

public class BizdateDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer offsetDay;

	private Integer offsetHour;

	private Integer offsetMinute;

	private Integer offsetSecond;

	private LocalDateTime currentDateTime;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
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
