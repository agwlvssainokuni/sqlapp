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

package cherry.foundation.async;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessagePostProcessor;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.goods.command.CommandLauncher;
import cherry.goods.command.CommandResult;

/**
 * 非同期実行フレームワーク。<br />
 * 非同期にコマンドを実行する機能を提供する。
 */
public class AsyncCommandHandlerImpl implements AsyncCommandHandler {

	private static final String ASYNCID = "asyncId";

	private BizDateTime bizDateTime;

	private AsyncStatusStore asyncStatusStore;

	private JmsOperations jmsOperations;

	private CommandLauncher commandLauncher;

	private MessagePostProcessor messagePostProcessor;

	public void setBizDateTime(BizDateTime bizDateTime) {
		this.bizDateTime = bizDateTime;
	}

	public void setAsyncStatusStore(AsyncStatusStore asyncStatusStore) {
		this.asyncStatusStore = asyncStatusStore;
	}

	public void setJmsOperations(JmsOperations jmsOperations) {
		this.jmsOperations = jmsOperations;
	}

	public void setCommandLauncher(CommandLauncher commandLauncher) {
		this.commandLauncher = commandLauncher;
	}

	public void setMessagePostProcessor(
			MessagePostProcessor messagePostProcessor) {
		this.messagePostProcessor = messagePostProcessor;
	}

	/**
	 * 非同期のコマンド実行を実行登録する。
	 * 
	 * @param launcherId
	 *            非同期処理の実行者のID。
	 * @param command
	 *            実行するコマンド (コマンドライン)。
	 * @return 非同期実行状況の管理データのID。
	 */
	@Override
	public long launchCommand(String launcherId, String... command) {

		long asyncId = asyncStatusStore.createCommand(launcherId,
				bizDateTime.now(), command);

		Map<String, String> message = new HashMap<>();
		message.put(ASYNCID, String.valueOf(asyncId));
		for (int i = 0; i < command.length; i++) {
			message.put(String.valueOf(i), command[i]);
		}
		jmsOperations.convertAndSend(message, messagePostProcessor);

		asyncStatusStore.updateToLaunched(asyncId, bizDateTime.now());
		return asyncId;
	}

	/**
	 * 実行登録したコマンドを実行する。<br />
	 * 本メソッドはコンテナが呼出すことを意図するものである。
	 * 
	 * @param message
	 *            {@link #launchCommand(String, String...)}
	 *            において登録した内容がコンテナから受渡される。
	 */
	@Override
	public void handleMessage(Map<String, String> message) {

		long asyncId = Long.parseLong(message.get(ASYNCID));
		List<String> command = new ArrayList<>();
		for (int i = 0;; i++) {
			String v = message.get(String.valueOf(i));
			if (v == null) {
				break;
			}
			command.add(v);
		}

		asyncStatusStore.updateToProcessing(asyncId, bizDateTime.now());
		try {

			CommandResult result = commandLauncher.launch(command
					.toArray(new String[command.size()]));

			AsyncStatus status;
			if (result.getExitValue() == 0) {
				status = AsyncStatus.SUCCESS;
			} else {
				status = AsyncStatus.ERROR;
			}

			asyncStatusStore.finishCommand(asyncId, bizDateTime.now(), status,
					result);
		} catch (Exception ex) {
			asyncStatusStore
					.finishWithException(asyncId, bizDateTime.now(), ex);
		}
	}

}
