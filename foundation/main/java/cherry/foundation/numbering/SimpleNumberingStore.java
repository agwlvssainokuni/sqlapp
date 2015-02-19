/*
 * Copyright 2015 agwlvssainokuni
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

package cherry.foundation.numbering;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.collections4.map.DefaultedMap;
import org.springframework.beans.factory.InitializingBean;

public class SimpleNumberingStore implements NumberingStore, InitializingBean {

	private Map<String, NumberingDefinition> numberingDefinitionMap;

	private Map<String, Long> currentValueMap;

	private Map<String, Lock> lockMap;

	public void setNumberingDefinitionMap(Map<String, NumberingDefinition> numberingDefinitionMap) {
		this.numberingDefinitionMap = numberingDefinitionMap;
	}

	@Override
	public void afterPropertiesSet() {
		currentValueMap = DefaultedMap.defaultedMap(new HashMap<String, Long>(), Long.valueOf(0L));
		lockMap = new HashMap<String, Lock>();
	}

	@Override
	public NumberingDefinition getDefinition(String numberName) {
		checkArgument(numberingDefinitionMap.containsKey(numberName), "%s must be defined", numberName);
		return numberingDefinitionMap.get(numberName);
	}

	@Override
	public synchronized long loadAndLock(String numberName) {
		Lock lock = lockMap.get(numberName);
		if (lock == null) {
			lock = new ReentrantLock(true);
			lockMap.put(numberName, lock);
		}
		lock.lock();
		return currentValueMap.get(numberName).longValue();
	}

	@Override
	public void saveAndUnlock(String numberName, long current) {
		currentValueMap.put(numberName, Long.valueOf(current));
		lockMap.get(numberName).unlock();
	}

}
