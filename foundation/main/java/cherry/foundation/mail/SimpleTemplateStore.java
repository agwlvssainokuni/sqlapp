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

package cherry.foundation.mail;

import java.util.Map;

public class SimpleTemplateStore implements TemplateStore {

	private Map<String, MailData> mailDataMap;

	public void setMailDataMap(Map<String, MailData> mailDataMap) {
		this.mailDataMap = mailDataMap;
	}

	@Override
	public MailData getTemplate(String templateName) {
		return mailDataMap.get(templateName);
	}

	@Override
	public void putTemplate(String templateName, MailData mailData) {
		mailDataMap.put(templateName, mailData);
	}

}
