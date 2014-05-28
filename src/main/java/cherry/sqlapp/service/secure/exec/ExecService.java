/*
 * Copyright 2014 agwlvssainokuni
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

package cherry.sqlapp.service.secure.exec;

import java.util.Map;

import javax.sql.DataSource;

public interface ExecService {

	long execute(DataSource dataSource, String sql, Map<String, ?> paramMap,
			Consumer consumer);

	public static interface Consumer {

		void begin(Column[] col);

		void consume(Object[] record);

		void end();
	}

	public static class Column {

		/** カラムの型。 */
		private int type;

		/** カラムの表記名。 */
		private String label;

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}
	}
}
