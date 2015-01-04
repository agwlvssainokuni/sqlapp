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

package cherry.foundation.querydsl;

import java.math.BigDecimal;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import com.mysema.query.Tuple;
import com.mysema.query.support.Expressions;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.DatePath;
import com.mysema.query.types.path.DateTimePath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.SimplePath;
import com.mysema.query.types.path.StringPath;
import com.mysema.query.types.path.TimePath;

public class PathExpressions {

	public static SimplePath<Tuple> simplePath(String variable) {
		return Expressions.path(Tuple.class, variable);
	}

	public static BooleanPath booleanPath(Path<?> parent, String property) {
		return Expressions.booleanPath(parent, property);
	}

	public static NumberPath<Integer> intPath(Path<?> parent, String property) {
		return Expressions.numberPath(Integer.class, parent, property);
	}

	public static NumberPath<Long> longPath(Path<?> parent, String property) {
		return Expressions.numberPath(Long.class, parent, property);
	}

	public static NumberPath<BigDecimal> decimalPath(Path<?> parent,
			String property) {
		return Expressions.numberPath(BigDecimal.class, parent, property);
	}

	public static StringPath stringPath(Path<?> parent, String property) {
		return Expressions.stringPath(parent, property);
	}

	public static DatePath<LocalDate> datePath(Path<?> parent, String property) {
		return Expressions.datePath(LocalDate.class, parent, property);
	}

	public static TimePath<LocalTime> timePath(Path<?> parent, String property) {
		return Expressions.timePath(LocalTime.class, parent, property);
	}

	public static DateTimePath<LocalDateTime> dateTimePath(Path<?> parent,
			String property) {
		return Expressions.dateTimePath(LocalDateTime.class, parent, property);
	}

}
