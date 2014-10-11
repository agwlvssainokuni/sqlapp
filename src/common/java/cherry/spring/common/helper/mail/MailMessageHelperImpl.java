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

package cherry.spring.common.helper.mail;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.common.helper.sql.SqlLoader;
import cherry.spring.common.type.jdbc.RowMapperCreator;

public class MailMessageHelperImpl implements MailMessageHelper,
		InitializingBean {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcOperations;

	@Autowired
	private RowMapperCreator rowMapperCreator;

	@Autowired
	private SqlLoader sqlLoader;

	private String findTemplate;

	private String findAddresses;

	private VelocityEngine velocityEngine;

	public void setFindTemplate(String findTemplate) {
		this.findTemplate = findTemplate;
	}

	public void setFindAddresses(String findAddresses) {
		this.findAddresses = findAddresses;
	}

	@Override
	public void afterPropertiesSet() throws IOException {
		BeanWrapper bw = new BeanWrapperImpl(this);
		bw.setPropertyValues(sqlLoader.load(getClass()));
		velocityEngine = new VelocityEngine();
		velocityEngine.init();
	}

	@Transactional(readOnly = true)
	@Override
	public SimpleMailMessage createMailMessage(IMailId mailId, String to,
			MailModel mailModel, Locale locale) {

		VelocityContext context = new VelocityContext();
		context.put("model", mailModel);

		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("name", mailId.templateName());
		paramMap.put("locale", locale.toString());

		MailTemplateDto template = namedParameterJdbcOperations.queryForObject(
				findTemplate, paramMap,
				rowMapperCreator.create(MailTemplateDto.class));

		List<MailAddressDto> addrList = namedParameterJdbcOperations.query(
				findAddresses, paramMap,
				rowMapperCreator.create(MailAddressDto.class));

		List<String> cc = new ArrayList<>();
		List<String> bcc = new ArrayList<>();
		for (MailAddressDto addr : addrList) {
			if (addr.getRcptType() == RcptType.CC) {
				cc.add(addr.getMailAddr());
			}
			if (addr.getRcptType() == RcptType.BCC) {
				bcc.add(addr.getMailAddr());
			}
		}

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(template.getSender());
		message.setTo(to);
		message.setCc(cc.toArray(new String[cc.size()]));
		message.setBcc(bcc.toArray(new String[bcc.size()]));
		message.setSubject(evaluate(template.getSubject(), context));
		message.setText(evaluate(template.getBody(), context));

		return message;
	}

	private String evaluate(String template, VelocityContext context) {
		try (StringWriter writer = new StringWriter()) {
			if (!velocityEngine.evaluate(context, writer, getClass().getName(),
					template)) {
				throw new IllegalStateException(
						"Failed to evaluate mail template");
			}
			return writer.toString();
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

}
