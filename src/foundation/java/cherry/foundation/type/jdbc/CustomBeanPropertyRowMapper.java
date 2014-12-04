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

package cherry.foundation.type.jdbc;

import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.core.convert.ConversionService;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import cherry.goods.masker.Masker;

public class CustomBeanPropertyRowMapper<T> extends BeanPropertyRowMapper<T> {

	private ConversionService conversionService;

	private Map<String, Masker<?>> maskerMap;

	public CustomBeanPropertyRowMapper(Class<T> mappedClass,
			ConversionService conversionService,
			Map<String, Masker<?>> maskerMap) {
		super(mappedClass);
		this.conversionService = conversionService;
		this.maskerMap = maskerMap;
	}

	@Override
	protected void initBeanWrapper(BeanWrapper bw) {
		bw.setConversionService(conversionService);
	}

	@Override
	protected Object getColumnValue(ResultSet rs, int index,
			PropertyDescriptor pd) throws SQLException {

		Object object = super.getColumnValue(rs, index, pd);
		if (maskerMap == null || !maskerMap.containsKey(pd.getName())) {
			return object;
		}

		@SuppressWarnings("unchecked")
		Masker<Object> masker = (Masker<Object>) maskerMap.get(pd.getName());
		return masker.mask(object);
	}

}
