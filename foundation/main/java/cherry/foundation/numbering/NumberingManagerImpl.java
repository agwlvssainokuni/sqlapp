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
import static com.google.common.base.Preconditions.checkState;

import java.text.MessageFormat;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.type.Code;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class NumberingManagerImpl implements NumberingManager, InitializingBean {

	private NumberingStore numberingStore;

	private String cacheSpec;

	private LoadingCache<String, NumberingDefinition> definitionCache;

	public void setNumberingStore(NumberingStore numberingStore) {
		this.numberingStore = numberingStore;
	}

	public void setCacheSpec(String cacheSpec) {
		this.cacheSpec = cacheSpec;
	}

	@Override
	public void afterPropertiesSet() {
		definitionCache = CacheBuilder.from(cacheSpec).build(new CacheLoader<String, NumberingDefinition>() {
			@Override
			public NumberingDefinition load(String key) {
				return numberingStore.getDefinition(key);
			}
		});
	}

	@Transactional
	@Override
	public <T extends Code<String>> String issueAsString(T numberEnum) {
		return issueAsString(numberEnum.code());
	}

	@Transactional
	@Override
	public <T extends Code<String>> String[] issueAsString(T numberEnum, int count) {
		return issueAsString(numberEnum.code(), count);
	}

	@Transactional
	@Override
	public String issueAsString(String numberName) {

		checkArgument(numberName != null, "numberName must not be null");

		NumberingDefinition def = getNumberingDefinition(numberName);
		MessageFormat fmt = new MessageFormat(def.getTemplate());
		long current = numberingStore.loadAndLock(numberName);
		int offset = 0;
		try {

			long v = current + 1;
			checkState(v >= def.getMinValue(), "%s must be >= %s", numberName, def.getMinValue());
			checkState(v <= def.getMaxValue(), "%s must be <= %s", numberName, def.getMaxValue());
			String result = fmt.format(new Object[] { Long.valueOf(v) });

			offset = 1;
			return result;
		} finally {
			numberingStore.saveAndUnlock(numberName, current + offset);
		}
	}

	@Transactional
	@Override
	public String[] issueAsString(String numberName, int count) {

		checkArgument(numberName != null, "numberName must not be null");
		checkArgument(count > 0, "count must be > 0");

		NumberingDefinition def = getNumberingDefinition(numberName);
		MessageFormat fmt = new MessageFormat(def.getTemplate());
		long current = numberingStore.loadAndLock(numberName);
		int offset = 0;
		try {

			String[] result = new String[count];
			for (int i = 1; i <= count; i++) {
				long v = current + i;
				checkState(v >= def.getMinValue(), "%s must be >= %s", numberName, def.getMinValue());
				checkState(v <= def.getMaxValue(), "%s must be <= %s", numberName, def.getMaxValue());
				result[i - 1] = fmt.format(new Object[] { Long.valueOf(v) });
			}

			offset = count;
			return result;
		} finally {
			numberingStore.saveAndUnlock(numberName, current + offset);
		}
	}

	@Transactional
	@Override
	public <T extends Code<String>> long issueAsLong(T numberEnum) {
		return issueAsLong(numberEnum.code());
	}

	@Transactional
	@Override
	public <T extends Code<String>> long[] issueAsLong(T numberEnum, int count) {
		return issueAsLong(numberEnum.code(), count);
	}

	@Transactional
	@Override
	public long issueAsLong(String numberName) {

		checkArgument(numberName != null, "numberName must not be null");

		NumberingDefinition def = getNumberingDefinition(numberName);
		long current = numberingStore.loadAndLock(numberName);
		int offset = 0;
		try {

			long v = current + 1;
			checkState(v >= def.getMinValue(), "%s must be >= %s", numberName, def.getMinValue());
			checkState(v <= def.getMaxValue(), "%s must be <= %s", numberName, def.getMaxValue());

			offset = 1;
			return v;
		} finally {
			numberingStore.saveAndUnlock(numberName, current + offset);
		}
	}

	@Transactional
	@Override
	public long[] issueAsLong(String numberName, int count) {

		checkArgument(numberName != null, "numberName must not be null");
		checkArgument(count > 0, "count must be > 0");

		NumberingDefinition def = getNumberingDefinition(numberName);
		long current = numberingStore.loadAndLock(numberName);
		int offset = 0;
		try {

			long[] result = new long[count];
			for (int i = 1; i <= count; i++) {
				long v = current + i;
				checkState(v >= def.getMinValue(), "%s must be >= %s", numberName, def.getMinValue());
				checkState(v <= def.getMaxValue(), "%s must be <= %s", numberName, def.getMaxValue());
				result[i - 1] = v;
			}

			offset = count;
			return result;
		} finally {
			numberingStore.saveAndUnlock(numberName, current + offset);
		}
	}

	private NumberingDefinition getNumberingDefinition(String numberName) {
		try {
			return definitionCache.get(numberName);
		} catch (ExecutionException ex) {
			throw new IllegalStateException(ex);
		}
	}

}
