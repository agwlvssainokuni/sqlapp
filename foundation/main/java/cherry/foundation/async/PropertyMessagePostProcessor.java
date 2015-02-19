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

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.jms.core.MessagePostProcessor;

public class PropertyMessagePostProcessor implements MessagePostProcessor {

	private Map<String, Object> properties;

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	@Override
	public Message postProcessMessage(Message message) throws JMSException {
		for (Map.Entry<String, Object> entry : properties.entrySet()) {
			String name = entry.getKey();
			Object v = entry.getValue();
			if (v instanceof String) {
				message.setStringProperty(name, (String) v);
			} else if (v instanceof Integer) {
				message.setIntProperty(name, ((Integer) v).intValue());
			} else if (v instanceof Long) {
				message.setLongProperty(name, ((Long) v).longValue());
			} else if (v instanceof Short) {
				message.setShortProperty(name, ((Short) v).shortValue());
			} else if (v instanceof Byte) {
				message.setByteProperty(name, ((Byte) v).byteValue());
			} else if (v instanceof Boolean) {
				message.setBooleanProperty(name, ((Boolean) v).booleanValue());
			} else if (v instanceof Double) {
				message.setDoubleProperty(name, ((Double) v).doubleValue());
			} else if (v instanceof Float) {
				message.setFloatProperty(name, ((Float) v).floatValue());
			} else {
				message.setObjectProperty(name, v);
			}
		}
		return message;
	}

}
