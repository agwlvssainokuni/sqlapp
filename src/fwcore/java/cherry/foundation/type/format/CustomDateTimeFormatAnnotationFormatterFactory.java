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

package cherry.foundation.type.format;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.format.datetime.joda.DateTimeParser;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.format.datetime.joda.LocalTimeParser;
import org.springframework.format.datetime.joda.ReadableInstantPrinter;
import org.springframework.format.datetime.joda.ReadablePartialPrinter;

import cherry.foundation.type.format.CustomDateTimeFormat.Range;

public class CustomDateTimeFormatAnnotationFormatterFactory implements
		AnnotationFormatterFactory<CustomDateTimeFormat> {

	private String dateToPrint;

	private String dateToParse;

	private String timeToPrint;

	private String timeToParseHm;

	private String timeToParseS;

	private String delimiterToPrint;

	private String delimiterToParse;

	private Set<Class<?>> fieldTypes = new HashSet<Class<?>>(Arrays.asList(
			LocalDate.class, LocalTime.class, LocalDateTime.class,
			DateTime.class));

	public void setDateToPrint(String dateToPrint) {
		this.dateToPrint = dateToPrint;
	}

	public void setDateToParse(String dateToParse) {
		this.dateToParse = dateToParse;
	}

	public void setTimeToPrint(String timeToPrint) {
		this.timeToPrint = timeToPrint;
	}

	public void setTimeToParseHm(String timeToParseHm) {
		this.timeToParseHm = timeToParseHm;
	}

	public void setTimeToParseS(String timeToParseS) {
		this.timeToParseS = timeToParseS;
	}

	public void setDelimiterToPrint(String delimiterToPrint) {
		this.delimiterToPrint = delimiterToPrint;
	}

	public void setDelimiterToParse(String delimiterToParse) {
		this.delimiterToParse = delimiterToParse;
	}

	public void setFieldTypes(Set<Class<?>> fieldTypes) {
		this.fieldTypes = fieldTypes;
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
			if (annotation.value() == Range.TO) {
				return new LocalDateToParser(toParse.toFormatter());
			} else {
				return new LocalDateParser(toParse.toFormatter());
			}
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

	static class LocalDateToParser implements Parser<LocalDate> {

		private final LocalDateParser parser;

		public LocalDateToParser(DateTimeFormatter formatter) {
			parser = new LocalDateParser(formatter);
		}

		@Override
		public LocalDate parse(String text, Locale locale)
				throws ParseException {
			return parser.parse(text, locale);
		}
	}

	static class LocalTimeToParser implements Parser<LocalTime> {

		private final LocalTimeParser parserHm;

		private final LocalTimeParser parserHms;

		public LocalTimeToParser(DateTimeFormatter formatterHm,
				DateTimeFormatter formatterHms) {
			parserHm = new LocalTimeParser(formatterHm);
			parserHms = new LocalTimeParser(formatterHms);
		}

		@Override
		public LocalTime parse(String text, Locale locale)
				throws ParseException {
			try {
				return parserHms.parse(text, locale);
			} catch (IllegalArgumentException ex) {
				return parserHm.parse(text, locale).plusMinutes(1)
						.minusSeconds(1);
			}
		}
	}

	static class LocalDateTimeToParser implements Parser<LocalDateTime> {

		private final LocalDateTimeParser parserYmd;

		private final LocalDateTimeParser parserYmdHm;

		private final LocalDateTimeParser parserYmdHms;

		public LocalDateTimeToParser(DateTimeFormatter formatterYmd,
				DateTimeFormatter formatterYmdHm,
				DateTimeFormatter formatterYmdHms) {
			parserYmd = new LocalDateTimeParser(formatterYmd);
			parserYmdHm = new LocalDateTimeParser(formatterYmdHm);
			parserYmdHms = new LocalDateTimeParser(formatterYmdHms);
		}

		@Override
		public LocalDateTime parse(String text, Locale locale)
				throws ParseException {
			try {
				return parserYmdHms.parse(text, locale);
			} catch (IllegalArgumentException ex) {
				try {
					return parserYmdHm.parse(text, locale).plusMinutes(1)
							.minusSeconds(1);
				} catch (IllegalArgumentException ex2) {
					return parserYmd.parse(text, locale).plusDays(1)
							.minusSeconds(1);
				}
			}
		}
	}

	static class DateTimeToParser implements Parser<DateTime> {

		private final DateTimeParser parserYmd;

		private final DateTimeParser parserYmdHm;

		private final DateTimeParser parserYmdHms;

		public DateTimeToParser(DateTimeFormatter formatterYmd,
				DateTimeFormatter formatterYmdHm,
				DateTimeFormatter formatterYmdHms) {
			parserYmd = new DateTimeParser(formatterYmd);
			parserYmdHm = new DateTimeParser(formatterYmdHm);
			parserYmdHms = new DateTimeParser(formatterYmdHms);
		}

		@Override
		public DateTime parse(String text, Locale locale) throws ParseException {
			try {
				return parserYmdHms.parse(text, locale);
			} catch (IllegalArgumentException ex) {
				try {
					return parserYmdHm.parse(text, locale).plusMinutes(1)
							.minusSeconds(1);
				} catch (IllegalArgumentException ex2) {
					return parserYmd.parse(text, locale).plusDays(1)
							.minusSeconds(1);
				}
			}
		}
	}

}
