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

package cherry.spring.common.custom.format;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.format.datetime.joda.DateTimeParser;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.format.datetime.joda.LocalTimeParser;
import org.springframework.format.datetime.joda.ReadableInstantPrinter;
import org.springframework.format.datetime.joda.ReadablePartialPrinter;

import cherry.spring.common.custom.format.CustomDateTimeFormat.Range;

public class CustomDateTimeFormatAnnotationFormatterFactory implements
		AnnotationFormatterFactory<CustomDateTimeFormat> {

	@Value("${common.format.date.print}")
	private String dateToPrint;

	@Value("${common.format.date.parse}")
	private String dateToParse;

	@Value("${common.format.time.print}")
	private String timeToPrint;

	@Value("${common.format.time.parse.hm}")
	private String timeToParseHm;

	@Value("${common.format.time.parse.s}")
	private String timeToParseS;

	@Value("${common.format.delimiter.print}")
	private String delimiterToPrint;

	@Value("${common.format.delimiter.parse}")
	private String delimiterToParse;

	private Set<Class<?>> fieldTypes = createFieldTypes();

	private Set<Class<?>> createFieldTypes() {
		return new HashSet<Class<?>>(Arrays.asList(LocalDate.class,
				LocalTime.class, LocalDateTime.class, DateTime.class));
	}

	@Override
	public Set<Class<?>> getFieldTypes() {
		return fieldTypes;
	}

	@Override
	public Printer<?> getPrinter(CustomDateTimeFormat annotation,
			Class<?> fieldType) {
		if (fieldType == LocalDate.class) {
			DateTimeFormatterBuilder toPrint = builder(dateToPrint);
			return new ReadablePartialPrinter(toPrint.toFormatter());
		} else if (fieldType == LocalTime.class) {
			DateTimeFormatterBuilder toPrint = builder(timeToPrint);
			return new ReadablePartialPrinter(toPrint.toFormatter());
		} else if (fieldType == LocalDateTime.class) {
			DateTimeFormatterBuilder toPrint = builder(dateToPrint)
					.appendPattern(delimiterToPrint).appendPattern(timeToPrint);
			return new ReadablePartialPrinter(toPrint.toFormatter());
		} else if (fieldType == DateTime.class) {
			DateTimeFormatterBuilder toPrint = builder(dateToPrint)
					.appendPattern(delimiterToPrint).appendPattern(timeToPrint);
			return new ReadableInstantPrinter(toPrint.toFormatter());
		} else {
			throw new IllegalStateException();
		}
	}

	@Override
	public Parser<?> getParser(CustomDateTimeFormat annotation,
			Class<?> fieldType) {
		if (fieldType == LocalDate.class) {
			DateTimeFormatterBuilder toParse = builder(dateToParse);
			return new LocalDateParser(toParse.toFormatter());
		} else if (fieldType == LocalTime.class) {
			if (annotation.optional()) {
				if (annotation.value() == Range.TO) {
					DateTimeFormatterBuilder hm = builder(timeToParseHm);
					DateTimeFormatterBuilder hms = builder(timeToParseHm)
							.appendPattern(timeToParseS);
					return new LocalTimeToParser(hm.toFormatter(),
							hms.toFormatter());
				} else {
					DateTimeFormatterBuilder toParse = builder(timeToParseHm)
							.appendOptional(builder(timeToParseS).toParser());
					return new LocalTimeParser(toParse.toFormatter());
				}
			} else {
				DateTimeFormatterBuilder toParse = builder(timeToParseHm)
						.appendPattern(timeToParseS);
				return new LocalTimeParser(toParse.toFormatter());
			}
		} else if (fieldType == LocalDateTime.class) {
			if (annotation.optional()) {
				if (annotation.value() == Range.TO) {
					DateTimeFormatterBuilder ymd = builder(dateToParse);
					DateTimeFormatterBuilder ymdhm = builder(dateToParse)
							.appendPattern(delimiterToParse).appendPattern(
									timeToParseHm);
					DateTimeFormatterBuilder ymdhms = builder(dateToParse)
							.appendPattern(delimiterToParse)
							.appendPattern(timeToParseHm)
							.appendPattern(timeToParseS);
					return new LocalDateTimeToParser(ymd.toFormatter(),
							ymdhm.toFormatter(), ymdhms.toFormatter());
				} else {
					DateTimeFormatterBuilder toParse = builder(dateToParse)
							.appendOptional(
									builder(delimiterToParse)
											.appendPattern(timeToParseHm)
											.appendOptional(
													builder(timeToParseS)
															.toParser())
											.toParser());
					return new LocalDateTimeParser(toParse.toFormatter());
				}
			} else {
				DateTimeFormatterBuilder toParse = builder(dateToParse)
						.appendPattern(delimiterToParse)
						.appendPattern(timeToParseHm)
						.appendPattern(timeToParseS);
				return new LocalDateTimeParser(toParse.toFormatter());
			}
		} else if (fieldType == DateTime.class) {
			if (annotation.optional()) {
				if (annotation.value() == Range.TO) {
					DateTimeFormatterBuilder ymd = builder(dateToParse);
					DateTimeFormatterBuilder ymdhm = builder(dateToParse)
							.appendPattern(delimiterToParse).appendPattern(
									timeToParseHm);
					DateTimeFormatterBuilder ymdhms = builder(dateToParse)
							.appendPattern(delimiterToParse)
							.appendPattern(timeToParseHm)
							.appendPattern(timeToParseS);
					return new DateTimeToParser(ymd.toFormatter(),
							ymdhm.toFormatter(), ymdhms.toFormatter());
				} else {
					DateTimeFormatterBuilder toParse = builder(dateToParse)
							.appendOptional(
									builder(delimiterToParse)
											.appendPattern(timeToParseHm)
											.appendOptional(
													builder(timeToParseS)
															.toParser())
											.toParser());
					return new DateTimeParser(toParse.toFormatter());
				}
			} else {
				DateTimeFormatterBuilder toParse = builder(dateToParse)
						.appendPattern(delimiterToParse)
						.appendPattern(timeToParseHm)
						.appendPattern(timeToParseS);
				return new DateTimeParser(toParse.toFormatter());
			}
		} else {
			throw new IllegalStateException();
		}
	}

	private DateTimeFormatterBuilder builder(String pattern) {
		return new DateTimeFormatterBuilder().appendPattern(pattern);
	}

}
