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

package cherry.goods.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link Map}変換ユーティリティ。
 * <ul>
 * <li>{@link Throwable}を変換する ({@link #fromThrowable(Throwable, int)})</li>
 * </ul>
 */
public class ToMapUtil {

	/**
	 * {@link Throwable}を{@link Map}に変換する。
	 * 
	 * @param th
	 *            変換対象の{@link Throwable}。
	 * @param maxDepth
	 *            {@link Map}に格納するスタックトレースの最大段数。
	 * @return {@link Throwable}の情報を保持する{@link Map}。
	 */
	public static Map<String, Object> fromThrowable(Throwable th, int maxDepth) {

		Map<String, Object> map = new LinkedHashMap<>();
		map.put("message", th.getMessage());

		int depth = maxDepth;
		List<String> stackTrace = new ArrayList<>();
		for (StackTraceElement ste : th.getStackTrace()) {
			if (depth <= 0) {
				stackTrace.add("...");
				break;
			}
			stackTrace.add(ste.toString());
			depth -= 1;
		}
		map.put("stackTrace", stackTrace);

		if (th.getCause() != null) {
			map.put("cause", fromThrowable(th.getCause(), depth));
		}

		return map;
	}

}
