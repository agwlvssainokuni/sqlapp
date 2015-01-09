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

package cherry.foundation.async;

import java.util.Map;

import org.springframework.jms.annotation.JmsListener;

/**
 * 非同期実行フレームワーク。<br />
 * 非同期にコマンドを実行する機能を提供する。
 */
public interface AsyncCommandHandler {

	/**
	 * 非同期のコマンド実行を実行登録する。
	 *
	 * @param launcherId 非同期処理の実行者のID。
	 * @param description 内容表記。
	 * @param command 実行するコマンド。
	 * @param args 引数。
	 * @return 非同期実行状況の管理データのID。
	 */
	long launchCommand(String launcherId, String description, String command, String... args);

	/**
	 * 実行登録したコマンドを実行する。<br />
	 * 本メソッドはコンテナが呼出すことを意図するものであり、{@link JmsListener}アノテーションを付与する。
	 *
	 * @param message {@link #launchCommand(String, String...)} において登録した内容がコンテナから受渡される。
	 */
	void handleMessage(Map<String, String> message);

}
