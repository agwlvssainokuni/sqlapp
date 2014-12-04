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

package cherry.foundation.batch.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.context.support.ResourceBundleMessageSource;

import cherry.foundation.batch.ExitStatus;
import cherry.foundation.batch.IBatch;

/**
 * バッチプログラムを起動する機能を提供する。
 */
public class Launcher {

	/** アプリケーションコンテキスト定義ファイルのパス。 */
	public static final String APPCTX = "classpath:config/applicationContext.xml";

	private Logger log = LoggerFactory.getLogger(getClass());

	/** 起動すべきバッチプログラムの識別名を保持する。 */
	private String batchId;

	/**
	 * バッチプログラム起動機能を生成する。
	 * 
	 * @param batchId
	 *            起動すべきバッチプログラムの識別名。
	 */
	public Launcher(String batchId) {
		this.batchId = batchId;
		MDC.put("batchId", batchId);
	}

	/**
	 * バッチプログラムを起動する。
	 * 
	 * @param args
	 *            起動時にコマンドラインに指定された引数。
	 * @return バッチプログラムの終了ステータス。
	 */
	public ExitStatus launch(String... args) {
		Msg msg = new Msg();
		try {

			log.info(msg.resolve("BATCH {0} STARTING", batchId));
			for (String arg : args) {
				log.info(msg.resolve("{0}", arg));
			}

			IBatch batch = getBatch(batchId);

			log.info(msg.resolve("BATCH {0} STARTED", batchId));

			ExitStatus status = batch.execute(args);

			switch (status) {
			case NORMAL:
				log.info(msg.resolve("BATCH {0} ENDED WITH {1}", batchId,
						status));
				break;
			case WARN:
				log.warn(msg.resolve("BATCH {0} ENDED WITH {1}", batchId,
						status));
				break;
			case ERROR:
				log.error(msg.resolve("BATCH {0} ENDED WITH {1}", batchId,
						status));
				break;
			default:
				log.error(msg.resolve("BATCH {0} ENDED WITH {1}", batchId,
						status));
				break;
			}

			return status;

		} catch (Exception ex) {
			log.error(msg.resolve("BATCH {0} ENDED WITH EXCEPTION", batchId),
					ex);
			return ExitStatus.FATAL;
		}
	}

	/**
	 * バッチプログラムの実装本体を取出す。
	 * 
	 * @param id
	 *            バッチプログラムの識別名。
	 * @return バッチプログラムの実装本体。
	 */
	private IBatch getBatch(String id) {
		@SuppressWarnings("resource")
		ApplicationContext appCtx = new ClassPathXmlApplicationContext(APPCTX);
		return appCtx.getBean(id, IBatch.class);
	}

	/**
	 * バッチプログラムを起動する際に出力するログの文言を解決する機能を提供する。
	 */
	private class Msg {

		/** 文言定義を保持する。 */
		private MessageSource msgSrc = createMessageSource();

		/**
		 * ログの文言を解決する。
		 * 
		 * @param code
		 *            ログの文言の識別名。
		 * @param batchId
		 *            バッチプログラムの識別名。
		 * @return ログの文言。
		 */
		public String resolve(String code, String batchId) {
			MessageSourceResolvable name = getResolvable(batchId);
			MessageSourceResolvable msg = getResolvable(code, name);
			return msgSrc.getMessage(msg, null);
		}

		/**
		 * ログの文言を解決する。
		 * 
		 * @param code
		 *            ログの文言の識別名。
		 * @param batchId
		 *            バッチプログラムの識別名。
		 * @param status
		 *            終了ステータス。
		 * @return ログの文言。
		 */
		public String resolve(String code, String batchId, ExitStatus status) {
			MessageSourceResolvable name = getResolvable(batchId, batchId);
			MessageSourceResolvable msg = getResolvable(code, name,
					status.name());
			return msgSrc.getMessage(msg, null);
		}

		/**
		 * 文言を解決するためのデータ構造 ({@link MessageSourceResolvable}) を生成する。
		 * 
		 * @param code
		 *            文言の識別名。
		 * @param args
		 *            文言に埋込むデータ。
		 * @return 文言。
		 */
		private MessageSourceResolvable getResolvable(String code,
				Object... args) {
			return new DefaultMessageSourceResolvable(new String[] { code },
					args);
		}

		/**
		 * 文言定義を生成する。
		 * 
		 * @return 文言定義。
		 */
		private MessageSource createMessageSource() {
			ResourceBundleMessageSource msgSrc = new ResourceBundleMessageSource();
			msgSrc.setBasenames("message/launcher", "message/batchId");
			msgSrc.setUseCodeAsDefaultMessage(true);
			return msgSrc;
		}
	}

}
