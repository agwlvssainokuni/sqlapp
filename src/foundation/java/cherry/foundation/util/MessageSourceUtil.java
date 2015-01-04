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

package cherry.foundation.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class MessageSourceUtil {

	private static MessageSource messageSource;

	public static MessageSource setMessageSource(MessageSource msgSrc) {
		messageSource = msgSrc;
		return messageSource;
	}

	public List<String> getMessageList(BindingResult binding) {
		Locale locale = LocaleContextHolder.getLocale();
		List<String> list = new ArrayList<>(binding.getAllErrors().size());
		for (ObjectError objectError : binding.getAllErrors()) {
			list.add(messageSource.getMessage(objectError, locale));
		}
		return list;
	}

}
