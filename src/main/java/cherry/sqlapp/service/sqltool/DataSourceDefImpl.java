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

package cherry.sqlapp.service.sqltool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class DataSourceDefImpl implements DataSourceDef {

	private String defaultName;

	private Map<String, DataSource> dataSourceMap;

	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

	public void setDataSourceMap(Map<String, DataSource> dataSourceMap) {
		this.dataSourceMap = dataSourceMap;
	}

	@Override
	public String getDefaultName() {
		return defaultName;
	}

	@Override
	public List<String> getNames() {
		return new ArrayList<>(dataSourceMap.keySet());
	}

	@Override
	public DataSource getDataSource(String name) {
		return dataSourceMap.get(name);
	}

}
