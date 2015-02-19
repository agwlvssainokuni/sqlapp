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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.web.multipart.MultipartFile;

import cherry.foundation.bizdtm.BizDateTime;

public class AsyncFileProcessHandlerImplTest {

	private BizDateTime bizDateTime = mock(BizDateTime.class);
	private AsyncProcessStore asyncProcessStore = mock(AsyncProcessStore.class);
	private JmsOperations jmsOperations = mock(JmsOperations.class);
	private ApplicationContext applicationContext = mock(ApplicationContext.class);
	private File tempDir = new File(".");
	private String tempPrefix = "prefix_{0,date,yyyyMMddHHmmss}_";
	private String tempSuffix = ".csv";
	private MessagePostProcessor messagePostProcessor = mock(MessagePostProcessor.class);

	@Test
	public void testLaunchFileProcess_NO_ARG() throws Exception {

		AsyncFileProcessHandlerImpl impl = createImpl();

		LocalDateTime now = LocalDateTime.now();
		when(bizDateTime.now()).thenReturn(now);
		when(asyncProcessStore.createFileProcess("a", now, "b", "c", "d", "e", 100L, "f")).thenReturn(10L);

		MultipartFile file = mock(MultipartFile.class);
		when(file.getName()).thenReturn("c");
		when(file.getOriginalFilename()).thenReturn("d");
		when(file.getContentType()).thenReturn("e");
		when(file.getSize()).thenReturn(100L);
		when(file.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[0]));

		@SuppressWarnings("rawtypes")
		ArgumentCaptor<Map> message = ArgumentCaptor.forClass(Map.class);

		long asyncId = impl.launchFileProcess("a", "b", file, "f");
		assertEquals(10L, asyncId);
		verify(jmsOperations).convertAndSend(message.capture(), eq(messagePostProcessor));
		assertEquals("10", message.getValue().get("asyncId"));
		String fileName = (String) message.getValue().get("file");
		assertTrue(fileName.startsWith((new File(tempDir, "prefix_")).getAbsolutePath()));
		assertTrue(fileName.endsWith(".csv"));
		assertEquals("c", message.getValue().get("name"));
		assertEquals("d", message.getValue().get("originalFilename"));
		assertEquals("e", message.getValue().get("contentType"));
		assertEquals("100", message.getValue().get("size"));
		assertEquals("f", message.getValue().get("handlerName"));
		verify(asyncProcessStore).createFileProcess("a", now, "b", "c", "d", "e", 100L, "f");
		verify(asyncProcessStore).updateToLaunched(10L, now);
	}

	@Test
	public void testLaunchFileProcess_2_ARGS() throws Exception {

		AsyncFileProcessHandlerImpl impl = createImpl();

		LocalDateTime now = LocalDateTime.now();
		when(bizDateTime.now()).thenReturn(now);
		when(asyncProcessStore.createFileProcess("a", now, "b", "c", "d", "e", 100L, "f", "g", "h")).thenReturn(10L);

		MultipartFile file = mock(MultipartFile.class);
		when(file.getName()).thenReturn("c");
		when(file.getOriginalFilename()).thenReturn("d");
		when(file.getContentType()).thenReturn("e");
		when(file.getSize()).thenReturn(100L);
		when(file.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[0]));

		@SuppressWarnings("rawtypes")
		ArgumentCaptor<Map> message = ArgumentCaptor.forClass(Map.class);

		long asyncId = impl.launchFileProcess("a", "b", file, "f", "g", "h");
		assertEquals(10L, asyncId);
		verify(jmsOperations).convertAndSend(message.capture(), eq(messagePostProcessor));
		assertEquals("10", message.getValue().get("asyncId"));
		String fileName = (String) message.getValue().get("file");
		assertTrue(fileName.startsWith((new File(tempDir, "prefix_")).getAbsolutePath()));
		assertTrue(fileName.endsWith(".csv"));
		assertEquals("c", message.getValue().get("name"));
		assertEquals("d", message.getValue().get("originalFilename"));
		assertEquals("e", message.getValue().get("contentType"));
		assertEquals("100", message.getValue().get("size"));
		assertEquals("f", message.getValue().get("handlerName"));
		assertEquals("g", message.getValue().get("0"));
		assertEquals("h", message.getValue().get("1"));
		verify(asyncProcessStore).createFileProcess("a", now, "b", "c", "d", "e", 100L, "f", "g", "h");
		verify(asyncProcessStore).updateToLaunched(10L, now);
	}

	@Test
	public void testLaunchFileProcess_exception() throws Exception {

		AsyncFileProcessHandlerImpl impl = createImpl();

		LocalDateTime now = LocalDateTime.now();
		when(bizDateTime.now()).thenReturn(now);
		when(asyncProcessStore.createFileProcess("a", now, "b", "c", "d", "e", 100L, "f")).thenReturn(10L);

		IOException exception = new IOException();
		InputStream in = mock(InputStream.class);
		when(in.read((byte[]) any())).thenThrow(exception);

		MultipartFile file = mock(MultipartFile.class);
		when(file.getName()).thenReturn("c");
		when(file.getOriginalFilename()).thenReturn("d");
		when(file.getContentType()).thenReturn("e");
		when(file.getSize()).thenReturn(100L);
		when(file.getInputStream()).thenReturn(in);

		try {
			impl.launchFileProcess("a", "b", file, "f");
			fail("Exception must be thrown");
		} catch (IllegalStateException ex) {
			verify(asyncProcessStore).createFileProcess("a", now, "b", "c", "d", "e", 100L, "f");
			verify(asyncProcessStore).finishWithException(10L, now, exception);
		}
	}

	@Test
	public void testLaunchFileProcess_IOException() throws Exception {

		AsyncFileProcessHandlerImpl impl = createImpl();

		LocalDateTime now = LocalDateTime.now();
		when(bizDateTime.now()).thenReturn(now);
		when(asyncProcessStore.createFileProcess("a", now, "b", "c", "d", "e", 100L, "f")).thenReturn(10L);

		IOException ioException = new IOException();
		MultipartFile file = mock(MultipartFile.class);
		when(file.getName()).thenReturn("c");
		when(file.getOriginalFilename()).thenReturn("d");
		when(file.getContentType()).thenReturn("e");
		when(file.getSize()).thenReturn(100L);
		when(file.getInputStream()).thenThrow(ioException);

		try {
			impl.launchFileProcess("a", "b", file, "f");
			fail("Exception must be thrown");
		} catch (IllegalStateException ex) {
			assertEquals(ioException, ex.getCause());
		}
	}

	@Test
	public void testHandleMessage_NO_ARG() throws Exception {

		AsyncFileProcessHandlerImpl impl = createImpl();

		LocalDateTime now = LocalDateTime.now();
		when(bizDateTime.now()).thenReturn(now);

		File tempFile = File.createTempFile("./prefix_", ".csv");

		Map<String, String> message = new HashMap<>();
		message.put("asyncId", "10");
		message.put("file", tempFile.getAbsolutePath());
		message.put("name", "a");
		message.put("originalFilename", "b");
		message.put("contentType", "c");
		message.put("size", "100");
		message.put("handlerName", "d");

		FileProcessResult result = new FileProcessResult();
		result.setTotalCount(10L);
		result.setOkCount(10L);
		result.setNgCount(0L);
		result.setNgRecordInfoList(new ArrayList<FileRecordInfo>());

		FileProcessHandler handler = mock(FileProcessHandler.class);
		when(handler.handleFile(tempFile, "a", "b", "c", 100L, 10L)).thenReturn(result);
		when(applicationContext.getBean("d", FileProcessHandler.class)).thenReturn(handler);

		impl.handleMessage(message);
		assertFalse(tempFile.exists());

		verify(handler).handleFile(tempFile, "a", "b", "c", 100L, 10L);
		verify(asyncProcessStore).updateToProcessing(10L, now);
		verify(asyncProcessStore).finishFileProcess(10L, now, AsyncStatus.SUCCESS, result);
	}

	@Test
	public void testHandleMessage_2_ARGS() throws Exception {

		AsyncFileProcessHandlerImpl impl = createImpl();

		LocalDateTime now = LocalDateTime.now();
		when(bizDateTime.now()).thenReturn(now);

		File tempFile = File.createTempFile("./prefix_", ".csv");

		Map<String, String> message = new HashMap<>();
		message.put("asyncId", "10");
		message.put("file", tempFile.getAbsolutePath());
		message.put("name", "a");
		message.put("originalFilename", "b");
		message.put("contentType", "c");
		message.put("size", "100");
		message.put("handlerName", "d");
		message.put("0", "e");
		message.put("1", "f");

		FileProcessResult result = new FileProcessResult();
		result.setTotalCount(10L);
		result.setOkCount(10L);
		result.setNgCount(0L);
		result.setNgRecordInfoList(new ArrayList<FileRecordInfo>());

		FileProcessHandler handler = mock(FileProcessHandler.class);
		when(handler.handleFile(tempFile, "a", "b", "c", 100L, 10L, "e", "f")).thenReturn(result);
		when(applicationContext.getBean("d", FileProcessHandler.class)).thenReturn(handler);

		impl.handleMessage(message);
		assertFalse(tempFile.exists());

		verify(handler).handleFile(tempFile, "a", "b", "c", 100L, 10L, "e", "f");
		verify(asyncProcessStore).updateToProcessing(10L, now);
		verify(asyncProcessStore).finishFileProcess(10L, now, AsyncStatus.SUCCESS, result);
	}

	@Test
	public void testHandleMessage_ERROR() throws Exception {

		AsyncFileProcessHandlerImpl impl = createImpl();

		LocalDateTime now = LocalDateTime.now();
		when(bizDateTime.now()).thenReturn(now);

		File tempFile = File.createTempFile("./prefix_", ".csv");

		Map<String, String> message = new HashMap<>();
		message.put("asyncId", "10");
		message.put("file", tempFile.getAbsolutePath());
		message.put("name", "a");
		message.put("originalFilename", "b");
		message.put("contentType", "c");
		message.put("size", "100");
		message.put("handlerName", "d");

		FileProcessResult result = new FileProcessResult();
		result.setTotalCount(10L);
		result.setOkCount(0L);
		result.setNgCount(10L);
		result.setNgRecordInfoList(new ArrayList<FileRecordInfo>());

		FileProcessHandler handler = mock(FileProcessHandler.class);
		when(handler.handleFile(tempFile, "a", "b", "c", 100L, 10L)).thenReturn(result);
		when(applicationContext.getBean("d", FileProcessHandler.class)).thenReturn(handler);

		impl.handleMessage(message);
		assertFalse(tempFile.exists());

		verify(handler).handleFile(tempFile, "a", "b", "c", 100L, 10L);
		verify(asyncProcessStore).updateToProcessing(10L, now);
		verify(asyncProcessStore).finishFileProcess(10L, now, AsyncStatus.ERROR, result);
	}

	@Test
	public void testHandleMessage_WARN() throws Exception {

		AsyncFileProcessHandlerImpl impl = createImpl();

		LocalDateTime now = LocalDateTime.now();
		when(bizDateTime.now()).thenReturn(now);

		File tempFile = File.createTempFile("./prefix_", ".csv");

		Map<String, String> message = new HashMap<>();
		message.put("asyncId", "10");
		message.put("file", tempFile.getAbsolutePath());
		message.put("name", "a");
		message.put("originalFilename", "b");
		message.put("contentType", "c");
		message.put("size", "100");
		message.put("handlerName", "d");

		FileProcessResult result = new FileProcessResult();
		result.setTotalCount(10L);
		result.setOkCount(5L);
		result.setNgCount(5L);
		result.setNgRecordInfoList(new ArrayList<FileRecordInfo>());

		FileProcessHandler handler = mock(FileProcessHandler.class);
		when(handler.handleFile(tempFile, "a", "b", "c", 100L, 10L)).thenReturn(result);
		when(applicationContext.getBean("d", FileProcessHandler.class)).thenReturn(handler);

		impl.handleMessage(message);
		assertFalse(tempFile.exists());

		verify(handler).handleFile(tempFile, "a", "b", "c", 100L, 10L);
		verify(asyncProcessStore).updateToProcessing(10L, now);
		verify(asyncProcessStore).finishFileProcess(10L, now, AsyncStatus.WARN, result);
	}

	@Test
	public void testHandleMessage_exception() throws Exception {

		AsyncFileProcessHandlerImpl impl = createImpl();

		LocalDateTime now = LocalDateTime.now();
		when(bizDateTime.now()).thenReturn(now);

		File tempFile = File.createTempFile("./prefix_", ".csv");

		Map<String, String> message = new HashMap<>();
		message.put("asyncId", "10");
		message.put("file", tempFile.getAbsolutePath());
		message.put("name", "a");
		message.put("originalFilename", "b");
		message.put("contentType", "c");
		message.put("size", "100");
		message.put("handlerName", "d");

		IllegalStateException exception = new IllegalStateException();
		FileProcessHandler handler = mock(FileProcessHandler.class);
		when(handler.handleFile(tempFile, "a", "b", "c", 100L, 10L)).thenThrow(exception);
		when(applicationContext.getBean("d", FileProcessHandler.class)).thenReturn(handler);

		impl.handleMessage(message);
		assertFalse(tempFile.exists());

		verify(handler).handleFile(tempFile, "a", "b", "c", 100L, 10L);
		verify(asyncProcessStore).updateToProcessing(10L, now);
		verify(asyncProcessStore).finishWithException(10L, now, exception);
	}

	@Test
	public void testHandleMessage_failedToDelete() throws Exception {

		AsyncFileProcessHandlerImpl impl = createImpl();

		LocalDateTime now = LocalDateTime.now();
		when(bizDateTime.now()).thenReturn(now);

		File tempFile = File.createTempFile("./prefix_", ".csv");
		tempFile.delete();

		Map<String, String> message = new HashMap<>();
		message.put("asyncId", "10");
		message.put("file", tempFile.getAbsolutePath());
		message.put("name", "a");
		message.put("originalFilename", "b");
		message.put("contentType", "c");
		message.put("size", "100");
		message.put("handlerName", "d");

		FileProcessResult result = new FileProcessResult();
		result.setTotalCount(10L);
		result.setOkCount(10L);
		result.setNgCount(0L);
		result.setNgRecordInfoList(new ArrayList<FileRecordInfo>());

		FileProcessHandler handler = mock(FileProcessHandler.class);
		when(handler.handleFile(tempFile, "a", "b", "c", 100L, 10L)).thenReturn(result);
		when(applicationContext.getBean("d", FileProcessHandler.class)).thenReturn(handler);

		impl.handleMessage(message);
		assertFalse(tempFile.exists());

		verify(handler).handleFile(tempFile, "a", "b", "c", 100L, 10L);
		verify(asyncProcessStore).updateToProcessing(10L, now);
		verify(asyncProcessStore).finishFileProcess(10L, now, AsyncStatus.SUCCESS, result);
	}

	private AsyncFileProcessHandlerImpl createImpl() {
		AsyncFileProcessHandlerImpl impl = new AsyncFileProcessHandlerImpl();
		impl.setBizDateTime(bizDateTime);
		impl.setAsyncProcessStore(asyncProcessStore);
		impl.setJmsOperations(jmsOperations);
		impl.setApplicationContext(applicationContext);
		impl.setTempDir(tempDir);
		impl.setTempPrefix(tempPrefix);
		impl.setTempSuffix(tempSuffix);
		impl.setMessagePostProcessor(messagePostProcessor);
		return impl;
	}

}
