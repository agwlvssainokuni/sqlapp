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

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * アプリケーションコンテキスト ({@link ApplicationContext}) を保持する。
 */
public class AppCtxHolder implements ApplicationContextAware {

	private static ApplicationContext appCtx;

	/**
	 * アプリケーションコンテキストを照会する。
	 * 
	 * @return アプリケーションコンテキスト。
	 */
	public static ApplicationContext getAppCtx() {
		return appCtx;
	}

	/**
	 * アプリケーションコンテキストの初期化時に当該アプリケーションコンテキストを保持する。
	 * 
	 * @param applicationContext
	 *            アプリケーションコンテキスト。
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		appCtx = applicationContext;
	}

}
