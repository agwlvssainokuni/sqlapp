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

package cherry.sqlapp.controller.sqltool;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import cherry.sqlapp.LogicError;

@Component("logicErrorUtil")
public class LogicErrorUtil {

	public void rejectOnBadSqlGrammer(BindingResult binding, BadSqlGrammarException ex) {
		Throwable rootCause = getRootCause(ex);
		binding.reject(LogicError.BadSqlGrammer.name(), new Object[] { rootCause.getMessage() },
				LogicError.BadSqlGrammer.name());
	}

	public void rejectOnOptimisticLockingFailure(BindingResult binding) {
		binding.reject(LogicError.OptimisticLockingFailure.name(), new Object[] {},
				LogicError.OptimisticLockingFailure.name());
	}

	private Throwable getRootCause(Throwable ex) {
		Throwable result = ex;
		while (result.getCause() != null) {
			result = result.getCause();
		}
		return result;
	}

}
