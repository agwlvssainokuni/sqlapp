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

package cherry.foundation.workday;

import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.tuple.Triple;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.InitializingBean;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.foundation.type.Code;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * 営業日管理機能。<br />
 */
public class WorkdayManagerImpl implements WorkdayManager, InitializingBean {

	private WorkdayStore workdayStore;

	private String defaultName;

	private BizDateTime bizDateTime;

	private String numberOfWorkdayCacheSpec;

	private String nextWorkdayCacheSpec;

	private LoadingCache<Triple<String, LocalDate, LocalDate>, Integer> numberOfWorkdayCache;

	private LoadingCache<Triple<String, LocalDate, Integer>, LocalDate> nextWorkdayCache;

	public void setWorkdayStore(WorkdayStore workdayStore) {
		this.workdayStore = workdayStore;
	}

	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

	public void setBizDateTime(BizDateTime bizDateTime) {
		this.bizDateTime = bizDateTime;
	}

	public void setNumberOfWorkdayCacheSpec(String numberOfWorkdayCacheSpec) {
		this.numberOfWorkdayCacheSpec = numberOfWorkdayCacheSpec;
	}

	public void setNextWorkdayCacheSpec(String nextWorkdayCacheSpec) {
		this.nextWorkdayCacheSpec = nextWorkdayCacheSpec;
	}

	@Override
	public void afterPropertiesSet() {
		numberOfWorkdayCache = CacheBuilder.from(numberOfWorkdayCacheSpec).build(
				new CacheLoader<Triple<String, LocalDate, LocalDate>, Integer>() {
					@Override
					public Integer load(Triple<String, LocalDate, LocalDate> key) {
						return workdayStore.getNumberOfWorkday(key.getLeft(), key.getMiddle(), key.getRight());
					}
				});
		nextWorkdayCache = CacheBuilder.from(nextWorkdayCacheSpec).build(
				new CacheLoader<Triple<String, LocalDate, Integer>, LocalDate>() {
					@Override
					public LocalDate load(Triple<String, LocalDate, Integer> key) {
						return workdayStore.getNextWorkday(key.getLeft(), key.getMiddle(), key.getRight());
					}
				});
	}

	@Override
	public int getNumberOfWorkday(LocalDate to) {
		return getNumberOfWorkday(defaultName, to);
	}

	@Override
	public <T extends Code<String>> int getNumberOfWorkday(T code, LocalDate to) {
		return getNumberOfWorkday(code.code(), to);
	}

	@Override
	public int getNumberOfWorkday(String name, LocalDate to) {
		return getNumberOfWorkday(name, bizDateTime.today(), to);
	}

	@Override
	public int getNumberOfWorkday(LocalDate from, LocalDate to) {
		return getNumberOfWorkday(defaultName, from, to);
	}

	@Override
	public <T extends Code<String>> int getNumberOfWorkday(T code, LocalDate from, LocalDate to) {
		return getNumberOfWorkday(code.code(), from, to);
	}

	@Override
	public int getNumberOfWorkday(String name, LocalDate from, LocalDate to) {
		try {
			return numberOfWorkdayCache.get(Triple.of(name, from, to));
		} catch (ExecutionException ex) {
			throw new IllegalStateException(ex);
		}
	}

	@Override
	public LocalDate getNextWorkday(int numberOfWorkday) {
		return getNextWorkday(defaultName, numberOfWorkday);
	}

	@Override
	public <T extends Code<String>> LocalDate getNextWorkday(T code, int numberOfWorkday) {
		return getNextWorkday(code.code(), numberOfWorkday);
	}

	@Override
	public LocalDate getNextWorkday(String name, int numberOfWorkday) {
		return getNextWorkday(name, bizDateTime.today(), numberOfWorkday);
	}

	@Override
	public LocalDate getNextWorkday(LocalDate from, int numberOfWorkday) {
		return getNextWorkday(defaultName, from, numberOfWorkday);
	}

	@Override
	public <T extends Code<String>> LocalDate getNextWorkday(T code, LocalDate from, int numberOfWorkday) {
		return getNextWorkday(code.code(), from, numberOfWorkday);
	}

	@Override
	public LocalDate getNextWorkday(String name, LocalDate from, int numberOfWorkday) {
		try {
			return nextWorkdayCache.get(Triple.of(name, from, numberOfWorkday));
		} catch (ExecutionException ex) {
			throw new IllegalStateException(ex);
		}
	}

}
