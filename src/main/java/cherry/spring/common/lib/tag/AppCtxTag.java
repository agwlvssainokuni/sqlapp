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

package cherry.spring.common.lib.tag;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class AppCtxTag {

	public static ApplicationContext getAppCtx(ServletContext sc) {
		return WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
	}

	public static <T> T getBean(ServletContext sc, String name,
			Class<T> requiredType) {
		return getAppCtx(sc).getBean(name, requiredType);
	}

	public static Object getBeanByName(ServletContext sc, String name) {
		return getAppCtx(sc).getBean(name);
	}

	public static <T> T getBeanByClass(ServletContext sc, Class<T> requiredType) {
		return getAppCtx(sc).getBean(requiredType);
	}

}
