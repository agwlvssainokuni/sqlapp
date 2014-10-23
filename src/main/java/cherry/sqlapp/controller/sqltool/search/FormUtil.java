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

package cherry.sqlapp.controller.sqltool.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import cherry.goods.util.LocalDateTimeUtil;
import cherry.spring.fwcore.type.FlagCode;
import cherry.sqlapp.code.SqlTypeCode;
import cherry.sqlapp.service.sqltool.metadata.MetadataCondition;

@Component("searchFormUtil")
public class FormUtil {

	public MetadataCondition createSqlCondition(SqltoolSearchForm form,
			String loginId) {

		List<SqlTypeCode> sqlType = new ArrayList<>();
		if (form.getSqlType() != null) {
			for (SqlType s : form.getSqlType()) {
				sqlType.add(s.getSqlTypeCode());
			}
		}

		List<FlagCode> publishedFlg = new ArrayList<>();
		if (form.getPublished() != null) {
			for (Published p : form.getPublished()) {
				publishedFlg.add(p.getFlagCode());
			}
		}

		MetadataCondition cond = new MetadataCondition();
		cond.setName(form.getName());
		cond.setSqlType(sqlType);
		cond.setPublishedFlg(publishedFlg);
		cond.setRegisteredFrom(LocalDateTimeUtil.rangeFrom(
				form.getRegisteredFromDt(), form.getRegisteredFromTm()));
		cond.setRegisteredTo(LocalDateTimeUtil.rangeTo(
				form.getRegisteredToDt(), form.getRegisteredToTm()));
		cond.setLoginId(loginId);
		return cond;
	}
}
