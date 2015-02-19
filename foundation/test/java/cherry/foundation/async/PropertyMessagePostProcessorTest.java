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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;

import org.junit.Test;
import org.springframework.jms.core.MessagePostProcessor;

public class PropertyMessagePostProcessorTest {

	@Test
	public void testStringProperty() throws JMSException {
		MessagePostProcessor processor = createProcessor("key", "value");
		Message message = mock(Message.class);
		processor.postProcessMessage(message);
		verify(message).setStringProperty("key", "value");
	}

	@Test
	public void testIntProperty() throws JMSException {
		MessagePostProcessor processor = createProcessor("key", 1);
		Message message = mock(Message.class);
		processor.postProcessMessage(message);
		verify(message).setIntProperty("key", 1);
	}

	@Test
	public void testLongProperty() throws JMSException {
		MessagePostProcessor processor = createProcessor("key", 1L);
		Message message = mock(Message.class);
		processor.postProcessMessage(message);
		verify(message).setLongProperty("key", 1L);
	}

	@Test
	public void testShortProperty() throws JMSException {
		MessagePostProcessor processor = createProcessor("key", (short) 1);
		Message message = mock(Message.class);
		processor.postProcessMessage(message);
		verify(message).setShortProperty("key", (short) 1);
	}

	@Test
	public void testByteProperty() throws JMSException {
		MessagePostProcessor processor = createProcessor("key", (byte) 1);
		Message message = mock(Message.class);
		processor.postProcessMessage(message);
		verify(message).setByteProperty("key", (byte) 1);
	}

	@Test
	public void testBooleanProperty() throws JMSException {
		MessagePostProcessor processor = createProcessor("key", true);
		Message message = mock(Message.class);
		processor.postProcessMessage(message);
		verify(message).setBooleanProperty("key", true);
	}

	@Test
	public void testDoubleProperty() throws JMSException {
		MessagePostProcessor processor = createProcessor("key", 1.5);
		Message message = mock(Message.class);
		processor.postProcessMessage(message);
		verify(message).setDoubleProperty("key", 1.5);
	}

	@Test
	public void testFloatProperty() throws JMSException {
		MessagePostProcessor processor = createProcessor("key", (float) 1.5);
		Message message = mock(Message.class);
		processor.postProcessMessage(message);
		verify(message).setFloatProperty("key", (float) 1.5);
	}

	@Test
	public void testObjectProperty() throws JMSException {
		Object object = new Object();
		MessagePostProcessor processor = createProcessor("key", object);
		Message message = mock(Message.class);
		processor.postProcessMessage(message);
		verify(message).setObjectProperty("key", object);
	}

	private PropertyMessagePostProcessor createProcessor(String key, Object value) {
		Map<String, Object> properties = new HashMap<>();
		properties.put(key, value);
		PropertyMessagePostProcessor processor = new PropertyMessagePostProcessor();
		processor.setProperties(properties);
		return processor;
	}

}
