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

package cherry.foundation.code;

import static com.google.common.base.Preconditions.checkArgument;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.type.Code;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * 区分値管理機能。<br />
 */
public class CodeManagerImpl implements CodeManager, InitializingBean {

	private CodeStore codeStore;

	private String valueTemplate;

	private String entryCacheSpec;

	private String listCacheSpec;

	private LoadingCache<Pair<String, String>, CodeEntry> entryCache;

	private LoadingCache<String, List<CodeEntry>> listCache;

	private final CodeEntry nullEntry = new CodeEntry();

	public void setCodeStore(CodeStore codeStore) {
		this.codeStore = codeStore;
	}

	public void setValueTemplate(String valueTemplate) {
		this.valueTemplate = valueTemplate;
	}

	public void setEntryCacheSpec(String entryCacheSpec) {
		this.entryCacheSpec = entryCacheSpec;
	}

	public void setListCacheSpec(String listCacheSpec) {
		this.listCacheSpec = listCacheSpec;
	}

	@Override
	public void afterPropertiesSet() {
		entryCache = CacheBuilder.from(entryCacheSpec).build(new CacheLoader<Pair<String, String>, CodeEntry>() {
			@Override
			public CodeEntry load(Pair<String, String> key) {
				return ObjectUtils.firstNonNull(codeStore.findByValue(key.getLeft(), key.getRight()), nullEntry);
			}
		});
		listCache = CacheBuilder.from(listCacheSpec).build(new CacheLoader<String, List<CodeEntry>>() {
			@Override
			public List<CodeEntry> load(String key) {
				return codeStore.getCodeList(key);
			}
		});
	}

	@Transactional(readOnly = true)
	@Override
	public <T extends Code<String>> boolean isValidValue(T codeEnum, String value) {
		return isValidValue(codeEnum.code(), value);
	}

	@Transactional(readOnly = true)
	@Override
	public boolean isValidValue(String codeName, String value) {
		return getEntry(codeName, value) != nullEntry;
	}

	@Transactional(readOnly = true)
	@Override
	public <T extends Code<String>> CodeEntry findByValue(T codeEnum, String value) {
		return findByValue(codeEnum.code(), value, false);
	}

	@Transactional(readOnly = true)
	@Override
	public <T extends Code<String>> CodeEntry findByValue(T codeEnum, String value, boolean plainLabel) {
		return findByValue(codeEnum.code(), value, plainLabel);
	}

	@Transactional(readOnly = true)
	@Override
	public CodeEntry findByValue(String codeName, String value) {
		return findByValue(codeName, value, false);
	}

	@Transactional(readOnly = true)
	@Override
	public CodeEntry findByValue(String codeName, String value, boolean plainLabel) {
		CodeEntry entry = getEntry(codeName, value);
		checkArgument(entry != nullEntry, "Not defined for (%s, %s)", codeName, value);
		if (plainLabel) {
			return entry;
		}
		return adjustCodeEntry(entry);
	}

	@Transactional(readOnly = true)
	@Override
	public <T extends Code<String>> List<CodeEntry> getCodeList(T codeEnum) {
		return getCodeList(codeEnum.code(), false);
	}

	@Transactional(readOnly = true)
	@Override
	public <T extends Code<String>> List<CodeEntry> getCodeList(T codeEnum, boolean plainLabel) {
		return getCodeList(codeEnum.code(), plainLabel);
	}

	@Transactional(readOnly = true)
	@Override
	public List<CodeEntry> getCodeList(String codeName) {
		return getCodeList(codeName, false);
	}

	@Transactional(readOnly = true)
	@Override
	public List<CodeEntry> getCodeList(String codeName, boolean plainLabel) {
		List<CodeEntry> list = getList(codeName);
		if (plainLabel) {
			return list;
		}
		List<CodeEntry> result = new ArrayList<>(list.size());
		for (CodeEntry entry : list) {
			result.add(adjustCodeEntry(entry));
		}
		return result;
	}

	@Transactional(readOnly = true)
	@Override
	public <T extends Code<String>> Map<String, String> getCodeMap(T codeEnum) {
		return getCodeMap(codeEnum.code(), false);
	}

	@Transactional(readOnly = true)
	@Override
	public <T extends Code<String>> Map<String, String> getCodeMap(T codeEnum, boolean plainLabel) {
		return getCodeMap(codeEnum.code(), plainLabel);
	}

	@Transactional(readOnly = true)
	@Override
	public Map<String, String> getCodeMap(String codeName) {
		return getCodeMap(codeName, false);
	}

	@Transactional(readOnly = true)
	@Override
	public Map<String, String> getCodeMap(String codeName, boolean plainLabel) {
		List<CodeEntry> list = getList(codeName);
		Map<String, String> result = new LinkedHashMap<>();
		for (CodeEntry entry : list) {
			if (plainLabel) {
				result.put(entry.getValue(), entry.getLabel());
			} else {
				result.put(entry.getValue(), formatLabel(entry.getValue(), entry.getLabel()));
			}
		}
		return result;
	}

	private CodeEntry adjustCodeEntry(CodeEntry entry) {
		CodeEntry result = new CodeEntry();
		result.setValue(entry.getValue());
		result.setLabel(formatLabel(entry.getValue(), entry.getLabel()));
		result.setSortOrder(entry.getSortOrder());
		return result;
	}

	private String formatLabel(String value, String label) {
		return MessageFormat.format(valueTemplate, value, label);
	}

	private CodeEntry getEntry(String codeName, String value) {
		try {
			return entryCache.get(Pair.of(codeName, value));
		} catch (ExecutionException ex) {
			throw new IllegalStateException(ex);
		}
	}

	private List<CodeEntry> getList(String codeName) {
		try {
			return listCache.get(codeName);
		} catch (ExecutionException ex) {
			throw new IllegalStateException(ex);
		}
	}

}
