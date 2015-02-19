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

package cherry.foundation.mail;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.springframework.mail.MailSendException;

import cherry.foundation.batch.ExitStatus;
import cherry.foundation.bizdtm.BizDateTime;

public class SendMailBatchTest {

	private BizDateTime bizDateTime;

	private MailSendHandler mailSendHandler;

	@Test
	public void testNormal() throws Exception {

		final File shutdownTrigger = new File("./shutdownTrigger.txt");
		shutdownTrigger.deleteOnExit();
		Callable<Boolean> callable = new Callable<Boolean>() {
			@Override
			public Boolean call() {
				try (FileOutputStream os = new FileOutputStream(shutdownTrigger)) {
					return true;
				} catch (IOException ex) {
					return false;
				}
			}
		};

		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		ScheduledFuture<Boolean> future = service.schedule(callable, 5L, TimeUnit.SECONDS);

		SendMailBatch batch = create(1000L, shutdownTrigger, false);
		ExitStatus status = batch.execute();

		assertEquals(ExitStatus.NORMAL, status);
		assertTrue(future.get().booleanValue());
		assertFalse(shutdownTrigger.exists());

		verify(bizDateTime, atLeastOnce()).now();
		verify(mailSendHandler, atLeastOnce()).listMessage((LocalDateTime) eq(null));
		verify(mailSendHandler, atLeastOnce()).sendMessage(eq(1L));
		verify(mailSendHandler, atLeastOnce()).sendMessage(eq(2L));
		verify(mailSendHandler, atLeastOnce()).sendMessage(eq(3L));
	}

	@Test
	public void testMailSendException() throws Exception {

		final File shutdownTrigger = new File("./shutdownTrigger.txt");
		shutdownTrigger.deleteOnExit();
		Callable<Boolean> callable = new Callable<Boolean>() {
			@Override
			public Boolean call() {
				try (FileOutputStream os = new FileOutputStream(shutdownTrigger)) {
					return true;
				} catch (IOException ex) {
					return false;
				}
			}
		};

		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		ScheduledFuture<Boolean> future = service.schedule(callable, 5L, TimeUnit.SECONDS);

		SendMailBatch batch = create(1000L, shutdownTrigger, true);
		ExitStatus status = batch.execute();

		assertEquals(ExitStatus.NORMAL, status);
		assertTrue(future.get().booleanValue());
		assertFalse(shutdownTrigger.exists());

		verify(bizDateTime, atLeastOnce()).now();
		verify(mailSendHandler, atLeastOnce()).listMessage((LocalDateTime) eq(null));
		verify(mailSendHandler, atLeastOnce()).sendMessage(eq(1L));
		verify(mailSendHandler, never()).sendMessage(eq(2L));
		verify(mailSendHandler, never()).sendMessage(eq(3L));
	}

	@Test
	public void testInterrupt() throws Exception {

		final File shutdownTrigger = new File("./shutdownTrigger.txt");
		shutdownTrigger.deleteOnExit();
		Callable<Boolean> callable = new Callable<Boolean>() {
			@Override
			public Boolean call() {
				try (FileOutputStream os = new FileOutputStream(shutdownTrigger)) {
					return true;
				} catch (IOException ex) {
					return false;
				}
			}
		};

		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		ScheduledFuture<Boolean> future = service.schedule(callable, 5L, TimeUnit.SECONDS);
		final Thread currentThread = Thread.currentThread();
		ScheduledFuture<Boolean> interrupt = service.schedule(new Callable<Boolean>() {
			@Override
			public Boolean call() {
				currentThread.interrupt();
				return true;
			}
		}, 2L, TimeUnit.SECONDS);

		SendMailBatch batch = create(1000L, shutdownTrigger, false);
		ExitStatus status = batch.execute();

		assertEquals(ExitStatus.NORMAL, status);
		assertTrue(future.get().booleanValue());
		assertTrue(interrupt.get().booleanValue());
		assertFalse(shutdownTrigger.exists());

		verify(bizDateTime, atLeastOnce()).now();
		verify(mailSendHandler, atLeastOnce()).listMessage((LocalDateTime) eq(null));
		verify(mailSendHandler, atLeastOnce()).sendMessage(eq(1L));
		verify(mailSendHandler, atLeastOnce()).sendMessage(eq(2L));
		verify(mailSendHandler, atLeastOnce()).sendMessage(eq(3L));
	}

	private SendMailBatch create(long intervalMillis, File shutdownTrigger, boolean sendMailException) {

		bizDateTime = mock(BizDateTime.class);
		mailSendHandler = mock(MailSendHandler.class);
		when(mailSendHandler.listMessage((LocalDateTime) any())).thenReturn(asList(1L, 2L, 3L));
		if (sendMailException) {
			when(mailSendHandler.sendMessage(anyLong())).thenThrow(new MailSendException("sending mail failed"));
		}

		SendMailBatch batch = new SendMailBatch();
		batch.setBizDateTime(bizDateTime);
		batch.setMailSendHandler(mailSendHandler);
		batch.setIntervalMillis(intervalMillis);
		batch.setShutdownTrigger(shutdownTrigger);
		return batch;
	}

}
