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

package cherry.spring.common.foundation.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;

import cherry.foundation.mail.Template;
import cherry.foundation.mail.TemplateStore;
import cherry.foundation.type.DeletedFlag;
import cherry.foundation.type.jdbc.RowMapperCreator;
import cherry.spring.common.db.gen.query.QMailTemplate;
import cherry.spring.common.db.gen.query.QMailTemplateAddress;

import com.mysema.query.sql.SQLQuery;

public class TemplateStoreImpl implements TemplateStore {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	@Autowired
	private RowMapperCreator rowMapperCreator;

	@Override
	public Template getTemplate(String templateName) {
		QMailTemplate a = new QMailTemplate("a");
		SQLQuery querya = queryDslJdbcOperations.newSqlQuery();
		querya.from(a);
		querya.where(a.templateName.eq(templateName));
		querya.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		Templ templ = queryDslJdbcOperations.queryForObject(querya,
				rowMapperCreator.create(Templ.class), a.id, a.fromAddr,
				a.subject, a.body);

		QMailTemplateAddress b = new QMailTemplateAddress("b");
		SQLQuery queryb = queryDslJdbcOperations.newSqlQuery();
		queryb.from(b);
		queryb.where(b.templateId.eq(templ.getId()));
		queryb.where(b.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		List<TemplAddr> templAddr = queryDslJdbcOperations.query(queryb,
				rowMapperCreator.create(TemplAddr.class), b.rcptType,
				b.rcptAddr);

		List<String> toAddr = new ArrayList<>();
		List<String> ccAddr = new ArrayList<>();
		List<String> bccAddr = new ArrayList<>();
		for (TemplAddr addr : templAddr) {
			RcptType type = RcptType.valueOf(addr.getRcptType());
			if (type == RcptType.TO) {
				toAddr.add(addr.getRcptAddr());
			}
			if (type == RcptType.CC) {
				ccAddr.add(addr.getRcptAddr());
			}
			if (type == RcptType.BCC) {
				bccAddr.add(addr.getRcptAddr());
			}
		}

		Template template = new Template();
		template.setFromAddr(templ.getFromAddr());
		template.setToAddr(toAddr);
		template.setCcAddr(bccAddr);
		template.setBccAddr(bccAddr);
		template.setSubject(templ.getSubject());
		template.setBody(templ.getBody());
		return template;
	}

	@Setter
	@Getter
	@EqualsAndHashCode
	@ToString
	public static class Templ {
		private long id;
		private String fromAddr;
		private String subject;
		private String body;
	}

	@Setter
	@Getter
	@EqualsAndHashCode
	@ToString
	public static class TemplAddr {
		private String rcptType;
		private String rcptAddr;
	}

}
