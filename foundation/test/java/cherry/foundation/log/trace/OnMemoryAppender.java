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

package cherry.foundation.log.trace;

import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;

/**
 * テスト用ログイベント蓄積機能。
 */
public class OnMemoryAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

	/** 蓄積したログイベント。 */
	private static List<ILoggingEvent> loggingEvent = null;

	/**
	 * 蓄積を開始する。
	 */
	public static void begin() {
		loggingEvent = new ArrayList<ILoggingEvent>();
	}

	/**
	 * 蓄積を終了する。
	 */
	public static void end() {
		loggingEvent = null;
	}

	/**
	 * 蓄積したログイベントを取得する。
	 * 
	 * @return 蓄積したログイベント。
	 */
	public static List<ILoggingEvent> getEvents() {
		return loggingEvent;
	}

	/**
	 * ログイベントを蓄積する。
	 * 
	 * @param event ログイベント。
	 */
	@Override
	protected void append(ILoggingEvent event) {
		if (loggingEvent != null) {
			loggingEvent.add(event);
		}
	}

}
