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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ExtractorResultSetExtractor implements ResultSetExtractor<Long> {

	private Consumer consumer;

	private Limiter limiter;

	public ExtractorResultSetExtractor(Consumer consumer, Limiter limiter) {
		this.consumer = consumer;
		this.limiter = limiter;
	}

	@Override
	public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
		try {

			ResultSetMetaData metaData = rs.getMetaData();
			Column[] col = new Column[metaData.getColumnCount()];
			for (int i = 1; i <= col.length; i++) {
				col[i - 1] = new Column();
				col[i - 1].setType(metaData.getColumnType(i));
				col[i - 1].setLabel(metaData.getColumnLabel(i));
			}

			consumer.begin(col);

			long count;
			for (count = 0L; rs.next(); count++) {

				Object[] record = new Object[col.length];
				for (int i = 1; i <= record.length; i++) {
					record[i - 1] = rs.getObject(i);
				}

				consumer.consume(record);
				limiter.tick();
			}

			consumer.end();
			return count;

		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

}
