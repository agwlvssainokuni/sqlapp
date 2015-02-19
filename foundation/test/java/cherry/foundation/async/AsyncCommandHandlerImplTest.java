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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessagePostProcessor;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.goods.command.CommandLauncher;
import cherry.goods.command.CommandResult;

public class AsyncCommandHandlerImplTest {

	private BizDateTime bizDateTime = mock(BizDateTime.class);
	private AsyncProcessStore asyncProcessStore = mock(AsyncProcessStore.class);
	private JmsOperations jmsOperations = mock(JmsOperations.class);
	private CommandLauncher commandLauncher = mock(CommandLauncher.class);
	private MessagePostProcessor messagePostProcessor = mock(MessagePostProcessor.class);

	@Test
	public void testTaunchCommand_NO_ARG() {

		AsyncCommandHandlerImpl impl = createImpl();

		LocalDateTime now = LocalDateTime.now();
		when(bizDateTime.now()).thenReturn(now);
		when(asyncProcessStore.createCommand("a", now, "b", "c")).thenReturn(10L);

		Map<String, String> message = new HashMap<>();
		message.put("asyncId", "10");
		message.put("command", "c");

		long asyncId = impl.launchCommand("a", "b", "c");
		assertEquals(10L, asyncId);
		verify(bizDateTime, times(2)).now();
		verify(jmsOperations).convertAndSend(message, messagePostProcessor);
		verify(asyncProcessStore).createCommand("a", now, "b", "c");
		verify(asyncProcessStore).updateToLaunched(10L, now);
	}

	@Test
	public void testTaunchCommand_2_ARGS() {

		AsyncCommandHandlerImpl impl = createImpl();

		LocalDateTime now = LocalDateTime.now();
		when(bizDateTime.now()).thenReturn(now);
		when(asyncProcessStore.createCommand("a", now, "b", "c", "d", "e")).thenReturn(10L);

		Map<String, String> message = new HashMap<>();
		message.put("asyncId", "10");
		message.put("command", "c");
		message.put("0", "d");
		message.put("1", "e");

		long asyncId = impl.launchCommand("a", "b", "c", "d", "e");
		assertEquals(10L, asyncId);
		verify(bizDateTime, times(2)).now();
		verify(jmsOperations).convertAndSend(message, messagePostProcessor);
		verify(asyncProcessStore).createCommand("a", now, "b", "c", "d", "e");
		verify(asyncProcessStore).updateToLaunched(10L, now);
	}

	@Test
	public void testHandleMessage_NO_ARG() throws Exception {

		AsyncCommandHandlerImpl impl = createImpl();

		LocalDateTime now = LocalDateTime.now();
		when(bizDateTime.now()).thenReturn(now);

		CommandResult result = new CommandResult();
		result.setExitValue(0);
		result.setStdout("out");
		result.setStderr("err");
		when(commandLauncher.launch("a")).thenReturn(result);

		Map<String, String> message = new HashMap<>();
		message.put("asyncId", "10");
		message.put("command", "a");

		impl.handleMessage(message);

		verify(commandLauncher).launch("a");
		verify(asyncProcessStore).updateToProcessing(10L, now);
		verify(asyncProcessStore).finishCommand(10L, now, AsyncStatus.SUCCESS, result);
	}

	@Test
	public void testHandleMessage_2_ARGS() throws Exception {

		AsyncCommandHandlerImpl impl = createImpl();

		LocalDateTime now = LocalDateTime.now();
		when(bizDateTime.now()).thenReturn(now);

		CommandResult result = new CommandResult();
		result.setExitValue(0);
		result.setStdout("out");
		result.setStderr("err");
		when(commandLauncher.launch("a", "b", "c")).thenReturn(result);

		Map<String, String> message = new HashMap<>();
		message.put("asyncId", "10");
		message.put("command", "a");
		message.put("0", "b");
		message.put("1", "c");

		impl.handleMessage(message);

		verify(commandLauncher).launch("a", "b", "c");
		verify(asyncProcessStore).updateToProcessing(10L, now);
		verify(asyncProcessStore).finishCommand(10L, now, AsyncStatus.SUCCESS, result);
	}

	@Test
	public void testHandleMessage_exitValue_1() throws Exception {

		AsyncCommandHandlerImpl impl = createImpl();

		LocalDateTime now = LocalDateTime.now();
		when(bizDateTime.now()).thenReturn(now);

		CommandResult result = new CommandResult();
		result.setExitValue(1);
		result.setStdout("out");
		result.setStderr("err");
		when(commandLauncher.launch("a")).thenReturn(result);

		Map<String, String> message = new HashMap<>();
		message.put("asyncId", "10");
		message.put("command", "a");

		impl.handleMessage(message);

		verify(commandLauncher).launch("a");
		verify(asyncProcessStore).updateToProcessing(10L, now);
		verify(asyncProcessStore).finishCommand(10L, now, AsyncStatus.ERROR, result);
	}

	@Test
	public void testHandleMessage_exception() throws Exception {

		AsyncCommandHandlerImpl impl = createImpl();

		LocalDateTime now = LocalDateTime.now();
		when(bizDateTime.now()).thenReturn(now);

		Exception exception = new IllegalStateException();
		when(commandLauncher.launch("a")).thenThrow(exception);

		Map<String, String> message = new HashMap<>();
		message.put("asyncId", "10");
		message.put("command", "a");

		impl.handleMessage(message);

		verify(commandLauncher).launch("a");
		verify(asyncProcessStore).updateToProcessing(10L, now);
		verify(asyncProcessStore).finishWithException(10L, now, exception);
	}

	private AsyncCommandHandlerImpl createImpl() {
		AsyncCommandHandlerImpl impl = new AsyncCommandHandlerImpl();
		impl.setBizDateTime(bizDateTime);
		impl.setAsyncProcessStore(asyncProcessStore);
		impl.setJmsOperations(jmsOperations);
		impl.setCommandLauncher(commandLauncher);
		impl.setMessagePostProcessor(messagePostProcessor);
		return impl;
	}

}
