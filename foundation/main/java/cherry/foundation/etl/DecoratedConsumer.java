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

package cherry.foundation.etl;

import java.util.Map;

public class DecoratedConsumer extends DelegateConsumer {

	private final Map<String, Decorator> decoratorMap;

	private Decorator[] decorator;

	public DecoratedConsumer(Consumer delegate, Map<String, Decorator> decoratorMap) {
		super(delegate);
		this.decoratorMap = decoratorMap;
		this.decorator = null;
	}

	@Override
	protected Column[] prepareBegin(Column[] col) {

		decorator = new Decorator[col.length];
		for (int i = 0; i < col.length; i++) {
			final String label = col[i].getLabel();
			if (decoratorMap.containsKey(label)) {
				decorator[i] = decoratorMap.get(label);
			} else {
				decorator[i] = new Decorator() {
					@Override
					public Object decorate(Object field) {
						return field;
					}
				};
			}
		}

		return col;
	}

	@Override
	protected Object[] prepareConsume(Object[] record) {
		Object[] adjusted = new Object[record.length];
		for (int i = 0; i < record.length; i++) {
			adjusted[i] = decorator[i].decorate(record[i]);
		}
		return adjusted;
	}

	@Override
	protected void prepareEnd() {
		// NOTHING
	}

}
