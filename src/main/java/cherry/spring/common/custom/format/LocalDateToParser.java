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

import java.text.ParseException;
import java.util.Locale;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.format.Parser;
import org.springframework.format.datetime.joda.LocalDateParser;

public class LocalDateToParser implements Parser<LocalDate> {

	private final LocalDateParser parser;

	public LocalDateToParser(DateTimeFormatter formatter) {
		parser = new LocalDateParser(formatter);
	}

	@Override
	public LocalDate parse(String text, Locale locale) throws ParseException {
		return parser.parse(text, locale);
	}

}
