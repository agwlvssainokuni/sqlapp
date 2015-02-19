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

package cherry.goods.chartype;

import static java.lang.Integer.parseInt;
import static java.util.regex.Pattern.compile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableReader {

	private List<Entry> entries = null;

	public List<Entry> getEntries() {
		if (entries == null) {
			List<Entry> list = new LinkedList<>();
			try (InputStream in = getClass().getResourceAsStream("CP932.TXT");
					Reader r = new InputStreamReader(in);
					BufferedReader reader = new BufferedReader(r)) {
				Pattern pattern = compile("^0x([0-9A-F]{2,4})\\t0x([0-9A-F]{4})\\t#(.*)$");
				String line;
				while ((line = reader.readLine()) != null) {
					Matcher matcher = pattern.matcher(line);
					if (matcher.matches()) {
						int win31j = parseInt(matcher.group(1), 16);
						int unicode = parseInt(matcher.group(2), 16);
						list.add(new Entry(win31j, unicode, matcher.group(3)));
					}
				}
			} catch (IOException ex) {
				throw new IllegalStateException(ex);
			}
			entries = list;
		}
		return entries;
	}

	public class Entry {

		private final int win31j;

		private final int unicode;

		private final String description;

		public Entry(int win31j, int unicode, String description) {
			this.win31j = win31j;
			this.unicode = unicode;
			this.description = description;
		}

		public int getWin31j() {
			return win31j;
		}

		public int getUnicode() {
			return unicode;
		}

		public String getDescription() {
			return description;
		}
	}

}
