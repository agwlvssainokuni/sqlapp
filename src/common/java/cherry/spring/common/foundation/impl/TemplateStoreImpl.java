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

package cherry.spring.common.foundation.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;

import cherry.foundation.mail.MailData;
import cherry.foundation.mail.TemplateStore;
import cherry.foundation.type.DeletedFlag;
import cherry.sqlapp.db.gen.query.QMailTemplate;
import cherry.sqlapp.db.gen.query.QMailTemplateAddress;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.QTuple;

public class TemplateStoreImpl implements TemplateStore {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	@Override
	public MailData getTemplate(String templateName) {
		QMailTemplate a = new QMailTemplate("a");
		SQLQuery querya = queryDslJdbcOperations.newSqlQuery();
		querya.from(a);
		querya.where(a.templateName.eq(templateName));
		querya.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		Tuple templ = queryDslJdbcOperations.queryForObject(querya, new QTuple(a.id, a.fromAddr, a.subject, a.body));

		QMailTemplateAddress b = new QMailTemplateAddress("b");
		SQLQuery queryb = queryDslJdbcOperations.newSqlQuery();
		queryb.from(b);
		queryb.where(b.templateId.eq(templ.get(a.id)));
		queryb.where(b.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		List<Tuple> templAddr = queryDslJdbcOperations.query(queryb, new QTuple(b.rcptType, b.rcptAddr));

		List<String> toAddr = new ArrayList<>();
		List<String> ccAddr = new ArrayList<>();
		List<String> bccAddr = new ArrayList<>();
		for (Tuple addr : templAddr) {
			RcptType type = RcptType.valueOf(addr.get(b.rcptType));
			if (type == RcptType.TO) {
				toAddr.add(addr.get(b.rcptAddr));
			}
			if (type == RcptType.CC) {
				ccAddr.add(addr.get(b.rcptAddr));
			}
			if (type == RcptType.BCC) {
				bccAddr.add(addr.get(b.rcptAddr));
			}
		}

		MailData template = new MailData();
		template.setFromAddr(templ.get(a.fromAddr));
		template.setToAddr(toAddr);
		template.setCcAddr(bccAddr);
		template.setBccAddr(bccAddr);
		template.setSubject(templ.get(a.subject));
		template.setBody(templ.get(a.body));
		return template;
	}

}
