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

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ログ出力機能を提供する。
 */
public class Log {

	/** ログ文言定義を保持する。 */
	private static ResourceBundle messageDef;

	/**
	 * ログ文言定義を設定する。
	 * 
	 * @param msgDef
	 *            ログ文言定義。
	 * @return ログ文言定義 (引数に指定されたものをそのまま返却する)。
	 */
	public static ResourceBundle setMessageDef(ResourceBundle msgDef) {
		messageDef = msgDef;
		return messageDef;
	}

	/** ログ出力の実態 (SLF4J) を保持する。 */
	private final Logger logger;

	/**
	 * ログ出力機能を生成する。ログ出力機能は{@link LoggerFactory}を介して生成することとし、これを直接呼び出してはならない。
	 * 
	 * @param logger
	 *            ログ出力の実態。
	 */
	Log(Logger logger) {
		this.logger = logger;
	}

	/**
	 * デバッグレベルのログを出力する。
	 * 
	 * @param msg
	 *            ログ文言。
	 * @param args
	 *            ログ文言に埋め込むデータ。
	 */
	public void debug(String msg, Object... args) {
		logger.debug(createMessage(msg, args));
	}

	/**
	 * デバッグレベルのログを出力する。
	 * 
	 * @param ex
	 *            例外オブジェクト。
	 * @param msg
	 *            ログ文言。
	 * @param args
	 *            ログ文言に埋め込むデータ。
	 */
	public void debug(Throwable ex, String msg, Object... args) {
		logger.debug(createMessage(msg, args), ex);
	}

	/**
	 * 通常、警告、異常レベルのログを出力する。
	 * 
	 * @param id
	 *            ログID。
	 * @param args
	 *            ログ文言に埋め込むデータ。
	 */
	public void log(ILogId id, Object... args) {
		switch (id.getLevel()) {
		case INFO:
			if (logger.isInfoEnabled()) {
				logger.info(createMessage(id, args));
			}
			break;
		case WARN:
			if (logger.isWarnEnabled()) {
				logger.warn(createMessage(id, args));
			}
			break;
		case ERROR:
			if (logger.isErrorEnabled()) {
				logger.error(createMessage(id, args));
			}
			break;
		default:
			if (logger.isErrorEnabled()) {
				logger.error(createMessage(id, args));
			}
			break;
		}
	}

	/**
	 * 通常、警告、異常レベルのログを出力する。
	 * 
	 * @param ex
	 *            例外オブジェクト。
	 * @param id
	 *            ログID。
	 * @param args
	 *            ログ文言に埋め込むデータ。
	 */
	public void log(Throwable ex, ILogId id, Object... args) {
		switch (id.getLevel()) {
		case INFO:
			if (logger.isInfoEnabled()) {
				logger.info(createMessage(id, args), ex);
			}
			break;
		case WARN:
			if (logger.isWarnEnabled()) {
				logger.warn(createMessage(id, args), ex);
			}
			break;
		case ERROR:
			if (logger.isErrorEnabled()) {
				logger.error(createMessage(id, args), ex);
			}
			break;
		default:
			if (logger.isErrorEnabled()) {
				logger.error(createMessage(id, args), ex);
			}
			break;
		}
	}

	/**
	 * デバッグログが有効化か否か判定。
	 * 
	 * @return 有効化されている(true)か否か(false)。
	 */
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	/**
	 * 通常ログが有効化か否か判定。
	 * 
	 * @return 有効化されている(true)か否か(false)。
	 */
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	/**
	 * 警告ログが有効化か否か判定。
	 * 
	 * @return 有効化されている(true)か否か(false)。
	 */
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	/**
	 * 異常ログが有効化か否か判定。
	 * 
	 * @return 有効化されている(true)か否か(false)。
	 */
	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	/**
	 * ログ出力する文言を形成する。
	 * 
	 * @param msg
	 *            ログ文言テンプレート。
	 * @param args
	 *            ログ文言に埋め込むデータ。
	 * @return ログ文言。
	 */
	private String createMessage(String msg, Object... args) {
		return MessageFormat.format(msg, args);
	}

	/**
	 * ログ出力する文言を形成する。
	 * 
	 * @param id
	 *            ログID。
	 * @param msg
	 *            ログ文言テンプレート。
	 * @param args
	 *            ログ文言に埋め込むデータ。
	 * @return ログ文言。
	 */
	private String createMessage(ILogId id, Object... args) {
		String msg = messageDef.getString(id.getId());
		return MessageFormat.format(msg, args);
	}

}
