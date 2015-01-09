/*
 * Copyright 2012,2015 agwlvssainokuni
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

package cherry.goods.log.appender;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fluentd.logger.FluentLogger;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.core.UnsynchronizedAppenderBase;

/**
 * Logbackに出力されたログをfluentdサーバに転送する。
 */
public class FluentLoggerAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

	/** fluentdタグ。 */
	private String tag = "myapp";

	/** fluentdサーバホスト。 */
	private String host = "localhost";

	/** fluentdサーバポート。 */
	private int port = 24224;

	/** fluentdサーバ通信タイムアウト。 */
	private Integer timeout = null;

	/** fluentdサーバ通信バッファサイズ。 */
	private Integer bufferCapacity = null;

	/** MDC出力フラグ。 */
	private boolean outputMdc = true;

	/** fluent-logger-java。 */
	private FluentLogger fluentLogger = null;

	/**
	 * Logbackに出力されたログをfluentdサーバに転送する。
	 * 
	 * @param event ログイベント。
	 */
	@Override
	protected void append(ILoggingEvent event) {

		Level level = event.getLevel();
		long timestamp = event.getTimeStamp();

		Map<String, Object> data = new LinkedHashMap<>();
		data.put("timestamp", timestamp);
		data.put("level", level.toString());
		data.put("thread", event.getThreadName());
		data.put("logger", event.getLoggerName());
		data.put("message", event.getFormattedMessage());

		if (outputMdc) {
			Map<String, String> mdc = new LinkedHashMap<>();
			for (Map.Entry<String, String> entry : event.getMDCPropertyMap().entrySet()) {
				mdc.put(entry.getKey().replace(".", "_"), entry.getValue());
			}
			data.put("mdc", mdc);
		}

		if (event.getThrowableProxy() != null) {
			data.put("throwable", createThrowableData(event.getThrowableProxy()));
		}

		fluentLogger.log(level.toString(), data, timestamp / 1000L);
	}

	/**
	 * fluentdサーバに転送するログデータに格納する例外情報を生成する。
	 * 
	 * @param th 例外データ。
	 * @return 例外情報。
	 */
	private Map<String, Object> createThrowableData(IThrowableProxy th) {

		Map<String, Object> data = new LinkedHashMap<>();
		data.put("className", th.getClassName());
		data.put("message", th.getMessage());

		List<String> stackTrace = new ArrayList<>();
		for (StackTraceElementProxy ste : th.getStackTraceElementProxyArray()) {
			stackTrace.add(ste.toString());
		}
		data.put("stackTrace", stackTrace);

		if (th.getCause() != null) {
			data.put("cause", createThrowableData(th.getCause()));
		}

		return data;
	}

	/**
	 * ログ転送を開始する。<br />
	 * fluent-logger-java のインスタンスを生成する。
	 */
	@Override
	public void start() {
		fluentLogger = createFluentLogger(tag, host, port, timeout, bufferCapacity);
		super.start();
	}

	/**
	 * ログ転送を終了する。<br />
	 * fluent-logger-java のインスタンスを閉じる。
	 */
	@Override
	public void stop() {
		super.stop();
		if (fluentLogger != null) {
			fluentLogger.close();
			fluentLogger = null;
		}
	}

	/**
	 * fluent-logger-java のインスタンスを生成する。
	 * 
	 * @param tag fluentdタグ。
	 * @param host fluentdサーバホスト。
	 * @param port fluentdサーバポート。
	 * @param timeout fluentdサーバ通信タイムアウト。
	 * @param bufferCapacity fluentdサーバ通信バッファサイズ。
	 * @return fluent-logger-javaインスタンス。
	 */
	private FluentLogger createFluentLogger(String tag, String host, int port, Integer timeout, Integer bufferCapacity) {
		if (timeout == null || bufferCapacity == null) {
			return FluentLogger.getLogger(tag, host, port);
		} else {
			return FluentLogger.getLogger(tag, host, port, timeout, bufferCapacity);
		}
	}

	/**
	 * fluentdタグ を設定する。
	 * 
	 * @param tag fluentdタグ。
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * fluentdサーバホスト を設定する。
	 * 
	 * @param host fluentdサーバホスト。
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * fluentdサーバポート を設定する。
	 * 
	 * @param port fluentdサーバポート。
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * fluentdサーバ通信タイムアウト を設定する。
	 * 
	 * @param timeout fluentdサーバ通信タイムアウト。
	 */
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	/**
	 * fluentdサーバ通信バッファサイズ を設定する。
	 * 
	 * @param bufferCapacity fluentdサーバ通信バッファサイズ。
	 */
	public void setBufferCapacity(Integer bufferCapacity) {
		this.bufferCapacity = bufferCapacity;
	}

	/**
	 * MDC出力フラグ を設定する。
	 * 
	 * @param outputMdc MDC出力フラグ。
	 */
	public void setOutputMdc(boolean outputMdc) {
		this.outputMdc = outputMdc;
	}

}
