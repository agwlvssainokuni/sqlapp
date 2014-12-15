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

package cherry.foundation;

import static cherry.foundation.AppCtxHolder.getAppCtx;

import org.springframework.core.convert.ConversionService;

public class ConversionServiceTag {

	private static ConversionService conversionService = null;

	public static ConversionService getConversionService() {
		if (conversionService == null) {
			conversionService = getAppCtx().getBean(ConversionService.class);
		}
		return conversionService;
	}

	public static String convert(Object source) {
		return getConversionService().convert(source, String.class);
	}

}
