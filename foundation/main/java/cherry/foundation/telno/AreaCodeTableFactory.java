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

package cherry.foundation.telno;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.Resource;

import cherry.goods.telno.SoumuExcelParser;

/**
 * 電話番号正規化機能。<br />
 * 電話番号を分割する上で照会する局番割当データを生成する。データの構成は、キーは「局番 (6桁)」、値は「市外局番の長さ」のマップ。
 */
public class AreaCodeTableFactory implements FactoryBean<Trie<String, Integer>> {

	/** [DI]総務省のサイトにて公開されている固定電話の局番割当ファイル (Excel形式) の解析機能。 */
	private SoumuExcelParser soumuExcelParser;

	/** [DI]総務省のサイトにて公開されている固定電話の局番割当ファイル (Excel形式) の参照先。 */
	private List<Resource> resources;

	public void setSoumuExcelParser(SoumuExcelParser soumuExcelParser) {
		this.soumuExcelParser = soumuExcelParser;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	/**
	 * 局番割当データ (キー「局番 (6桁)」、値「市外局番の長さ」のマップ) を取得する。
	 * 
	 * @return 局番割当データ (キー「局番 (6桁)」、値「市外局番の長さ」のマップ)。
	 * @throws InvalidFormatException 局番割当ファイルの形式が不正。
	 * @throws IOException 局番割当ファイルの読込み異常。
	 */
	@Override
	public Trie<String, Integer> getObject() throws InvalidFormatException, IOException {
		Trie<String, Integer> trie = new PatriciaTrie<>();
		for (Resource r : resources) {
			try (InputStream in = r.getInputStream()) {
				Map<String, Pair<String, String>> map = soumuExcelParser.parse(in);
				for (Map.Entry<String, Pair<String, String>> entry : map.entrySet()) {
					trie.put(entry.getKey(), entry.getValue().getLeft().length());
				}
			}
		}
		return trie;
	}

	@Override
	public Class<?> getObjectType() {
		return Trie.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
