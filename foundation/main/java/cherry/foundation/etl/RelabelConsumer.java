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

import java.io.IOException;
import java.util.Map;

public class RelabelConsumer extends DelegateConsumer {

	private final Map<String, String> labelMap;

	public RelabelConsumer(Consumer delegate, Map<String, String> labelMap) {
		super(delegate);
		this.labelMap = labelMap;
	}

	@Override
	protected Column[] prepareBegin(Column[] col) throws IOException {
		Column[] adjusted = new Column[col.length];
		for (int i = 0; i < col.length; i++) {
			adjusted[i] = new Column();
			adjusted[i].setType(col[i].getType());
			if (labelMap.containsKey(col[i].getLabel())) {
				adjusted[i].setLabel(labelMap.get(col[i].getLabel()));
			} else {
				adjusted[i].setLabel(col[i].getLabel());
			}
		}
		return adjusted;
	}

	@Override
	protected Object[] prepareConsume(Object[] record) throws IOException {
		return record;
	}

	@Override
	protected void prepareEnd() throws IOException {
		// NOTHING
	}

}
