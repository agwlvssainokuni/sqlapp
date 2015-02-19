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

package cherry.goods.telno;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeSet;

import org.apache.commons.collections4.Trie;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Triple;

public class TelNoNormalizerImpl implements TelNoNormalizer {

	private Trie<String, Integer> areaCodeTable;

	public void setAreaCodeTable(Trie<String, Integer> areaCodeTable) {
		this.areaCodeTable = areaCodeTable;
	}

	@Override
	public String flatten(String telNo) {
		if (StringUtils.isEmpty(telNo)) {
			return telNo;
		}
		return telNo.replaceAll("-", "");
	}

	@Override
	public List<String[]> normalize(String telNo) {
		if (telNo == null) {
			return null;
		}
		List<Triple<Integer, Integer, Integer>> list = decompose(telNo);
		if (list == null) {
			List<String[]> result = new ArrayList<>(1);
			result.add(new String[] { telNo });
			return result;
		}
		List<String[]> result = new ArrayList<>(list.size());
		for (Triple<Integer, Integer, Integer> triple : list) {
			if (telNo.length() >= triple.getLeft()) {
				result.add(split(telNo, triple));
			}
		}
		return result;
	}

	private List<Triple<Integer, Integer, Integer>> decompose(String telNo) {
		if (StringUtils.isEmpty(telNo)) {
			return null;
		} else if (telNo.startsWith("0120")) {
			// 着信課金用電話番号（0120）
			// 0120-DEF-GHJ
			return asList(Triple.of(4, 3, 3));
		} else if (telNo.startsWith("0800")) {
			// 着信課金用電話番号（0800）
			// 0800-DEF-GHJK
			return asList(Triple.of(4, 3, 4));
		} else if (telNo.startsWith("050")) {
			// IP電話の電話番号（050）
			// 050-CDEF-GHJK
			return asList(Triple.of(3, 4, 4));
		} else if (telNo.startsWith("070")) {
			// 携帯電話・PHSの電話番号（070）
			// 070-CDEF-GHJK
			return asList(Triple.of(3, 4, 4));
		} else if (telNo.startsWith("080")) {
			// 携帯電話・PHSの電話番号（080）
			// 080-CDEF-GHJK
			return asList(Triple.of(3, 4, 4));
		} else if (telNo.startsWith("090")) {
			// 携帯電話・PHSの電話番号（090）
			// 090-CDEF-GHJK
			return asList(Triple.of(3, 4, 4));
		} else if (telNo.startsWith("020")) {
			// 発信者課金ポケベル電話番号（020）
			// 020-CDEF-GHJK
			return asList(Triple.of(3, 4, 4));
		} else if (telNo.startsWith("0570")) {
			// 統一番号用電話番号（0570）
			// 0570-DEF-GHJ
			return asList(Triple.of(4, 3, 3));
		} else if (telNo.startsWith("0990")) {
			// 情報料代理徴収用電話番号（0990）
			// 0990-DE-FGHJ
			return asList(Triple.of(4, 2, 4));
		} else {
			// 固定電話等の電話番号
			if (telNo.length() <= 2) {
				return null;
			}
			String prefix = (telNo.length() > 6 ? telNo.substring(0, 6) : telNo);
			SortedMap<String, Integer> map = areaCodeTable.prefixMap(prefix);
			if (map.isEmpty()) {
				return null;
			}
			Set<Integer> set = new TreeSet<>(map.values());
			List<Triple<Integer, Integer, Integer>> list = new ArrayList<>(set.size());
			for (Integer l : set) {
				list.add(Triple.of(l, 6 - l, 4));
			}
			return list;
		}
	}

	private String[] split(String telNo, Triple<Integer, Integer, Integer> triple) {
		if (telNo.length() <= triple.getLeft()) {
			return new String[] { telNo };
		}
		String first = telNo.substring(0, triple.getLeft());
		if (telNo.length() <= triple.getLeft() + triple.getMiddle()) {
			String second = telNo.substring(triple.getLeft());
			return new String[] { first, second };
		}
		String second = telNo.substring(triple.getLeft(), triple.getLeft() + triple.getMiddle());
		String third = telNo.substring(triple.getLeft() + triple.getMiddle());
		return new String[] { first, second, third };
	}

}
