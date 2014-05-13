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

package cherry.spring.common.log;

import java.util.Properties;

import org.springframework.beans.factory.InitializingBean;

/**
 * ログ文言を初期化する機能を提供する。
 */
public class LogInitializer implements InitializingBean {

	/** ログ文言定義を保持する。 */
	private Properties messageDef;

	/**
	 * ログ文言定義を設定する。
	 * 
	 * @param messageDef
	 *            ログ文言定義。
	 */
	public void setMessageDef(Properties messageDef) {
		this.messageDef = messageDef;
	}

	/**
	 * ログ文言を初期化する。
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		assert messageDef != null : "messageDef must not be null";
		Log.setMessageDef(messageDef);
	}

}
