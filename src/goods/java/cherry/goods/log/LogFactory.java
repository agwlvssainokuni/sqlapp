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

package cherry.goods.log;

import org.slf4j.LoggerFactory;

/**
 * ログ出力機能を生成する機能を提供する。
 */
public class LogFactory {

	/**
	 * ログ出力機能を生成する。
	 * 
	 * @param klass ログに紐付けるクラス。
	 * @return ログ出力機能。
	 */
	public static Log getLog(Class<?> klass) {
		return new Log(LoggerFactory.getLogger(klass));
	}

	/**
	 * ログ出力機能を生成する。
	 * 
	 * @param name ログに紐付ける名前。
	 * @return ログ出力機能。
	 */
	public static Log getLog(String name) {
		return new Log(LoggerFactory.getLogger(name));
	}

}
