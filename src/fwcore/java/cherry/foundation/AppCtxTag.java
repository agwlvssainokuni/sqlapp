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

import org.springframework.context.ApplicationContext;

/**
 * アプリケーションコンテキスト ({@link ApplicationContext}) が管理するBeanを取出す機能を提供する。<br />
 * 特に、JSPカスタムタグ (関数) として使用することを想定する。
 */
public class AppCtxTag {

	/**
	 * 名前とクラスを指定してBeanを取出す。
	 * 
	 * @param name
	 *            Beanの名前。
	 * @param requiredType
	 *            Beanのクラス。
	 * @return 名前とクラスから特定されるBean。
	 */
	public static <T> T getBean(String name, Class<T> requiredType) {
		return getAppCtx().getBean(name, requiredType);
	}

	/**
	 * 名前を指定してBeanを取出す。
	 * 
	 * @param name
	 *            Beanの名前。
	 * @return 名前から特定されるBean。
	 */
	public static Object getBeanByName(String name) {
		return getAppCtx().getBean(name);
	}

	/**
	 * クラスを指定してBeanを取出す。
	 * 
	 * @param requiredType
	 *            Beanのクラス。
	 * @return クラスから特定されるBean。
	 */
	public static <T> T getBeanByClass(Class<T> requiredType) {
		return getAppCtx().getBean(requiredType);
	}

}
