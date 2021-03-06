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

package cherry.foundation.logicalerror;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import cherry.foundation.type.Code;

public class LogicalErrorUtil {

	public static void reject(BindingResult binding, ILogicalError logicalError, Object... args) {
		binding.reject(logicalError.code(), args, logicalError.code());
	}

	public static void rejectValue(BindingResult binding, String name, ILogicalError logicError, Object... args) {
		binding.rejectValue(name, logicError.code(), args, logicError.code());
	}

	public static MessageSourceResolvable resolve(ILogicalError code, Object... args) {
		return resolve(code.code(), args);
	}

	public static MessageSourceResolvable resolve(Code<String> code, Object... args) {
		return resolve(code.code(), args);
	}

	public static MessageSourceResolvable resolve(String code, Object... args) {
		return new DefaultMessageSourceResolvable(new String[] { code }, args);
	}

	public static void rejectOnOneTimeTokenError(BindingResult binding) {
		reject(binding, LogicalError.OneTimeTokenError);
	}

	public static void rejectOnOptimisticLockError(BindingResult binding) {
		reject(binding, LogicalError.OptimisticLockError);
	}

	public static void rejectOnSearchResultEmpty(BindingResult binding) {
		reject(binding, LogicalError.SearchResultEmpty);
	}

}
