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

package cherry.spring.common.custom.converter;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

public class JodaTimeSqlConverterRegistrar implements FormatterRegistrar {

	@Override
	public void registerFormatters(FormatterRegistry registry) {
		registry.addConverter(new LocalDateConverter());
		registry.addConverter(new LocalTimeConverter());
		registry.addConverter(new LocalDateTimeConverter());
	}

	static class LocalDateConverter implements Converter<Date, LocalDate> {
		@Override
		public LocalDate convert(Date source) {
			return new LocalDate(source.getTime());
		}
	}

	static class LocalTimeConverter implements Converter<Time, LocalTime> {
		@Override
		public LocalTime convert(Time source) {
			return new LocalTime(source.getTime()
					+ LocalDate.now().toDate().getTime());
		}
	}

	static class LocalDateTimeConverter implements
			Converter<Timestamp, LocalDateTime> {
		@Override
		public LocalDateTime convert(Timestamp source) {
			return new LocalDateTime(source.getTime());
		}
	}

}
