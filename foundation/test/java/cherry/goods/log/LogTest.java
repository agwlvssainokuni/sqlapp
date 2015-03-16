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

package cherry.goods.log;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import cherry.foundation.log.trace.OnMemoryAppender;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class LogTest {

	private Log log = LogFactory.getLog(getClass());

	private Log logtest = LogFactory.getLog("logtest");

	@Test
	public void debug() {

		OnMemoryAppender.begin();
		log.debug("message {0}", "param1");
		log.debug(new IllegalStateException("msg"), "message {0}", "param1");
		List<ILoggingEvent> event = OnMemoryAppender.getEvents();
		OnMemoryAppender.end();

		assertEquals(2, event.size());
		ILoggingEvent e0 = event.get(0);
		assertEquals(Level.DEBUG, e0.getLevel());
		assertEquals("cherry.goods.log.LogTest", e0.getLoggerName());
		assertEquals("message param1", e0.getMessage());
		assertNull(e0.getThrowableProxy());
		ILoggingEvent e1 = event.get(1);
		assertEquals(Level.DEBUG, e1.getLevel());
		assertEquals("cherry.goods.log.LogTest", e1.getLoggerName());
		assertEquals("message param1", e1.getMessage());
		assertNotNull(e1.getThrowableProxy());
		assertEquals("msg", e1.getThrowableProxy().getMessage());
		assertEquals(IllegalStateException.class.getName(), e1.getThrowableProxy().getClassName());
	}

	@Test
	public void info() {

		OnMemoryAppender.begin();
		log.log(LogId.LOG101, "param1");
		log.log(new IllegalStateException("msg"), LogId.LOG101, "param1");
		logtest.log(LogId.LOG201, "param1");
		logtest.log(new IllegalStateException("msg"), LogId.LOG201, "param1");
		List<ILoggingEvent> event = OnMemoryAppender.getEvents();
		OnMemoryAppender.end();

		assertEquals(2, event.size());
		ILoggingEvent e0 = event.get(0);
		assertEquals(Level.INFO, e0.getLevel());
		assertEquals("cherry.goods.log.LogTest", e0.getLoggerName());
		assertEquals("LOG101 - message param1", e0.getMessage());
		assertNull(e0.getThrowableProxy());
		ILoggingEvent e1 = event.get(1);
		assertEquals(Level.INFO, e1.getLevel());
		assertEquals("cherry.goods.log.LogTest", e1.getLoggerName());
		assertEquals("LOG101 - message param1", e1.getMessage());
		assertNotNull(e1.getThrowableProxy());
		assertEquals("msg", e1.getThrowableProxy().getMessage());
		assertEquals(IllegalStateException.class.getName(), e1.getThrowableProxy().getClassName());
	}

	@Test
	public void warn() {

		OnMemoryAppender.begin();
		log.log(LogId.LOG102, "param1");
		log.log(new IllegalStateException("msg"), LogId.LOG102, "param1");
		logtest.log(LogId.LOG102, "param1");
		logtest.log(new IllegalStateException("msg"), LogId.LOG102, "param1");
		List<ILoggingEvent> event = OnMemoryAppender.getEvents();
		OnMemoryAppender.end();

		assertEquals(2, event.size());
		ILoggingEvent e0 = event.get(0);
		assertEquals(Level.WARN, e0.getLevel());
		assertEquals("cherry.goods.log.LogTest", e0.getLoggerName());
		assertEquals("LOG102 - message param1", e0.getMessage());
		assertNull(e0.getThrowableProxy());
		ILoggingEvent e1 = event.get(1);
		assertEquals(Level.WARN, e1.getLevel());
		assertEquals("cherry.goods.log.LogTest", e1.getLoggerName());
		assertEquals("LOG102 - message param1", e1.getMessage());
		assertNotNull(e1.getThrowableProxy());
		assertEquals("msg", e1.getThrowableProxy().getMessage());
		assertEquals(IllegalStateException.class.getName(), e1.getThrowableProxy().getClassName());
	}

	@Test
	public void error() {

		OnMemoryAppender.begin();
		log.log(LogId.LOG103, "param1");
		log.log(new IllegalStateException("msg"), LogId.LOG103, "param1");
		logtest.log(LogId.LOG103, "param1");
		logtest.log(new IllegalStateException("msg"), LogId.LOG103, "param1");
		List<ILoggingEvent> event = OnMemoryAppender.getEvents();
		OnMemoryAppender.end();

		assertEquals(2, event.size());
		ILoggingEvent e0 = event.get(0);
		assertEquals(Level.ERROR, e0.getLevel());
		assertEquals("cherry.goods.log.LogTest", e0.getLoggerName());
		assertEquals("LOG103 - message param1", e0.getMessage());
		assertNull(e0.getThrowableProxy());
		ILoggingEvent e1 = event.get(1);
		assertEquals(Level.ERROR, e1.getLevel());
		assertEquals("cherry.goods.log.LogTest", e1.getLoggerName());
		assertEquals("LOG103 - message param1", e1.getMessage());
		assertNotNull(e1.getThrowableProxy());
		assertEquals("msg", e1.getThrowableProxy().getMessage());
		assertEquals(IllegalStateException.class.getName(), e1.getThrowableProxy().getClassName());
	}

	@Test
	public void isEnabled() {
		assertTrue(log.isDebugEnabled());
		assertTrue(log.isInfoEnabled());
		assertTrue(log.isWarnEnabled());
		assertTrue(log.isErrorEnabled());
		assertFalse(logtest.isDebugEnabled());
		assertFalse(logtest.isInfoEnabled());
		assertFalse(logtest.isWarnEnabled());
		assertFalse(logtest.isErrorEnabled());
	}

	@Test
	public void nomessage() {

		OnMemoryAppender.begin();
		log.log(LogId.NOMSG);
		List<ILoggingEvent> event = OnMemoryAppender.getEvents();
		OnMemoryAppender.end();

		assertEquals(1, event.size());
		ILoggingEvent e0 = event.get(0);
		assertEquals(Level.INFO, e0.getLevel());
		assertEquals("cherry.goods.log.LogTest", e0.getLoggerName());
		assertEquals("", e0.getMessage());
		assertNull(e0.getThrowableProxy());
	}

	enum LogId implements ILogId {
		LOG101(cherry.goods.log.Level.INFO), //
		LOG102(cherry.goods.log.Level.WARN), //
		LOG103(cherry.goods.log.Level.ERROR), //
		LOG201(cherry.goods.log.Level.INFO), //
		LOG202(cherry.goods.log.Level.WARN), //
		LOG203(cherry.goods.log.Level.ERROR), //
		NOMSG(cherry.goods.log.Level.INFO);

		private cherry.goods.log.Level level;

		private LogId(cherry.goods.log.Level level) {
			this.level = level;
		}

		@Override
		public String getId() {
			return name();
		}

		@Override
		public cherry.goods.log.Level getLevel() {
			return level;
		}
	}

}
