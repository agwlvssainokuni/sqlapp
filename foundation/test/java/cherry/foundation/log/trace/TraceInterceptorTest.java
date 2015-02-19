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

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * {@link TraceInterceptor} のテスト。
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:cherry/foundation/log/trace/applicationContext.xml")
public class TraceInterceptorTest {

	@Autowired
	private ApplicationContext appCtx;

	@Autowired
	private TraceTest0 impl0;

	@Autowired
	private TraceTest1 impl1;

	@Test
	public void 引数と返却値がない() {

		// 実行
		OnMemoryAppender.begin();
		impl0.test0();

		// 検証
		List<ILoggingEvent> event = OnMemoryAppender.getEvents();
		OnMemoryAppender.end();

		assertEquals(2, event.size());
		ILoggingEvent e0 = event.get(0);
		assertEquals(Level.TRACE, e0.getLevel());
		assertEquals("trace.ENTER", e0.getLoggerName());
		assertEquals("cherry.foundation.log.trace.TraceTest0Impl#test0: ()", e0.getMessage());
		ILoggingEvent e1 = event.get(1);
		assertEquals(Level.TRACE, e1.getLevel());
		assertEquals("trace.EXIT", e1.getLoggerName());
		assertEquals("cherry.foundation.log.trace.TraceTest0Impl#test0: void", e1.getMessage());
	}

	@Test
	public void 引数と返却値がプリミティブ() {

		// 実行
		OnMemoryAppender.begin();
		impl0.test1(12345);

		// 検証
		List<ILoggingEvent> event = OnMemoryAppender.getEvents();
		OnMemoryAppender.end();

		assertEquals(2, event.size());
		ILoggingEvent e0 = event.get(0);
		assertEquals(Level.TRACE, e0.getLevel());
		assertEquals("trace.ENTER", e0.getLoggerName());
		assertEquals("cherry.foundation.log.trace.TraceTest0Impl#test1: (12345)", e0.getMessage());
		ILoggingEvent e1 = event.get(1);
		assertEquals(Level.TRACE, e1.getLevel());
		assertEquals("trace.EXIT", e1.getLoggerName());
		assertEquals("cherry.foundation.log.trace.TraceTest0Impl#test1: 12345", e1.getMessage());
	}

	@Test
	public void 引数と返却値が配列() {

		// 実行
		OnMemoryAppender.begin();
		impl0.test2(new int[] { 12345, 67890 });

		// 検証
		List<ILoggingEvent> event = OnMemoryAppender.getEvents();
		OnMemoryAppender.end();

		assertEquals(2, event.size());
		ILoggingEvent e0 = event.get(0);
		assertEquals(Level.TRACE, e0.getLevel());
		assertEquals("trace.ENTER", e0.getLoggerName());
		assertEquals("cherry.foundation.log.trace.TraceTest0Impl#test2: ([12345, 67890])", e0.getMessage());
		ILoggingEvent e1 = event.get(1);
		assertEquals(Level.TRACE, e1.getLevel());
		assertEquals("trace.EXIT", e1.getLoggerName());
		assertEquals("cherry.foundation.log.trace.TraceTest0Impl#test2: [12345, 67890]", e1.getMessage());
	}

	@Test
	public void 引数と返却値がnull() {

		// 実行
		OnMemoryAppender.begin();
		impl0.test2(null);

		// 検証
		List<ILoggingEvent> event = OnMemoryAppender.getEvents();
		OnMemoryAppender.end();

		assertEquals(2, event.size());
		ILoggingEvent e0 = event.get(0);
		assertEquals(Level.TRACE, e0.getLevel());
		assertEquals("trace.ENTER", e0.getLoggerName());
		assertEquals("cherry.foundation.log.trace.TraceTest0Impl#test2: (<null>)", e0.getMessage());
		ILoggingEvent e1 = event.get(1);
		assertEquals(Level.TRACE, e1.getLevel());
		assertEquals("trace.EXIT", e1.getLoggerName());
		assertEquals("cherry.foundation.log.trace.TraceTest0Impl#test2: <null>", e1.getMessage());
	}

	@Test
	public void 引数が複数() {

		// 実行
		OnMemoryAppender.begin();
		impl0.test3("12345", "67890");

		// 検証
		List<ILoggingEvent> event = OnMemoryAppender.getEvents();
		OnMemoryAppender.end();

		assertEquals(2, event.size());
		ILoggingEvent e0 = event.get(0);
		assertEquals(Level.TRACE, e0.getLevel());
		assertEquals("trace.ENTER", e0.getLoggerName());
		assertEquals("cherry.foundation.log.trace.TraceTest0Impl#test3: (12345, 67890)", e0.getMessage());
		ILoggingEvent e1 = event.get(1);
		assertEquals(Level.TRACE, e1.getLevel());
		assertEquals("trace.EXIT", e1.getLoggerName());
		assertEquals("cherry.foundation.log.trace.TraceTest0Impl#test3: 1234567890", e1.getMessage());
	}

	@Test
	public void 引数が複数でマスクつき() {

		// 実行
		OnMemoryAppender.begin();
		impl0.test4("12345", "67890");

		// 検証
		List<ILoggingEvent> event = OnMemoryAppender.getEvents();
		OnMemoryAppender.end();

		assertEquals(2, event.size());
		ILoggingEvent e0 = event.get(0);
		assertEquals(Level.TRACE, e0.getLevel());
		assertEquals("trace.ENTER", e0.getLoggerName());
		assertEquals("cherry.foundation.log.trace.TraceTest0Impl#test4: (12345, <MASKED>)", e0.getMessage());
		ILoggingEvent e1 = event.get(1);
		assertEquals(Level.TRACE, e1.getLevel());
		assertEquals("trace.EXIT", e1.getLoggerName());
		assertEquals("cherry.foundation.log.trace.TraceTest0Impl#test4: <MASKED>", e1.getMessage());
	}

	@Test
	public void 例外が発生する() {

		// 実行
		OnMemoryAppender.begin();
		try {
			impl0.test5();
		} catch (Exception ex) {
			// IGNORE
		}

		// 検証
		List<ILoggingEvent> event = OnMemoryAppender.getEvents();
		OnMemoryAppender.end();

		assertEquals(2, event.size());
		ILoggingEvent e0 = event.get(0);
		assertEquals(Level.TRACE, e0.getLevel());
		assertEquals("trace.ENTER", e0.getLoggerName());
		assertEquals("cherry.foundation.log.trace.TraceTest0Impl#test5: ()", e0.getMessage());
		ILoggingEvent e1 = event.get(1);
		assertEquals(Level.TRACE, e1.getLevel());
		assertEquals("trace.EXCEPTION", e1.getLoggerName());
		assertEquals("cherry.foundation.log.trace.TraceTest0Impl#test5: java.lang.Exception", e1.getMessage());
	}

	@Test
	public void 制限あり_配列のサイズOK() {

		// 実行
		OnMemoryAppender.begin();
		impl1.test(new int[] { 1, 2, 3, 4, 5 });

		// 検証
		List<ILoggingEvent> event = OnMemoryAppender.getEvents();
		OnMemoryAppender.end();

		assertEquals(2, event.size());
		ILoggingEvent e0 = event.get(0);
		assertEquals(Level.TRACE, e0.getLevel());
		assertEquals("trace.ENTER", e0.getLoggerName());
		assertEquals("cherry.foundation.log.trace.TraceTest1Impl#test: ([1, 2, 3, 4, 5])", e0.getMessage());
		ILoggingEvent e1 = event.get(1);
		assertEquals(Level.TRACE, e1.getLevel());
		assertEquals("trace.EXIT", e1.getLoggerName());
		assertEquals("cherry.foundation.log.trace.TraceTest1Impl#test: [1, 2, 3, 4, 5]", e1.getMessage());
	}

	@Test
	public void 制限あり_配列のサイズ超過() {

		// 実行
		OnMemoryAppender.begin();
		impl1.test(new int[] { 1, 2, 3, 4, 5, 6 });

		// 検証
		List<ILoggingEvent> event = OnMemoryAppender.getEvents();
		OnMemoryAppender.end();

		assertEquals(2, event.size());
		ILoggingEvent e0 = event.get(0);
		assertEquals(Level.TRACE, e0.getLevel());
		assertEquals("trace.ENTER", e0.getLoggerName());
		assertEquals("cherry.foundation.log.trace.TraceTest1Impl#test: ([array of int size 6])", e0.getMessage());
		ILoggingEvent e1 = event.get(1);
		assertEquals(Level.TRACE, e1.getLevel());
		assertEquals("trace.EXIT", e1.getLoggerName());
		assertEquals("cherry.foundation.log.trace.TraceTest1Impl#test: [array of int size 6]", e1.getMessage());
	}

	@Test
	public void 制限あり_文字列長超過() {

		// 実行
		OnMemoryAppender.begin();
		impl1.test(new int[] { 11111, 22222, 33333, 44444, 55555 });

		// 検証
		List<ILoggingEvent> event = OnMemoryAppender.getEvents();
		OnMemoryAppender.end();

		assertEquals(2, event.size());
		ILoggingEvent e0 = event.get(0);
		assertEquals(Level.TRACE, e0.getLevel());
		assertEquals("trace.ENTER", e0.getLoggerName());
		assertEquals("cherry.foundation.log.trace.TraceTest1Impl#test: ([11111, 22222, 33333,...<truncated>...)",
				e0.getMessage());
		ILoggingEvent e1 = event.get(1);
		assertEquals(Level.TRACE, e1.getLevel());
		assertEquals("trace.EXIT", e1.getLoggerName());
		assertEquals("cherry.foundation.log.trace.TraceTest1Impl#test: [11111, 22222, 33333,...<truncated>...",
				e1.getMessage());
	}

}
