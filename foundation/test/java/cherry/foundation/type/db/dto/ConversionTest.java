/*
 * Copyright 2014,2015 agwlvssainokuni
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

package cherry.foundation.type.db.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import cherry.foundation.type.DeletedFlag;
import cherry.foundation.type.FlagCode;
import cherry.foundation.type.SecureBigDecimal;
import cherry.foundation.type.SecureBigInteger;
import cherry.foundation.type.SecureInteger;
import cherry.foundation.type.SecureLong;
import cherry.foundation.type.SecureString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ConversionTest {

	private int id;

	private LocalDate jodaDate;

	private LocalTime jodaTime;

	private LocalDateTime jodaDatetime;

	private SecureString secStr;

	private SecureInteger secInt;

	private SecureLong secLong;

	private SecureBigInteger secBigint;

	private SecureBigDecimal secBigdec;

	private FlagCode flagCode;

	private DeletedFlag deletedFlg;

}
