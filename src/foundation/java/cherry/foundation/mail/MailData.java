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

package cherry.foundation.mail;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MailData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fromAddr;

	private List<String> toAddr;

	private List<String> ccAddr;

	private List<String> bccAddr;

	private String subject;

	private String body;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getFromAddr() {
		return fromAddr;
	}

	public void setFromAddr(String fromAddr) {
		this.fromAddr = fromAddr;
	}

	public List<String> getToAddr() {
		return toAddr;
	}

	public void setToAddr(List<String> toAddr) {
		this.toAddr = toAddr;
	}

	public List<String> getCcAddr() {
		return ccAddr;
	}

	public void setCcAddr(List<String> ccAddr) {
		this.ccAddr = ccAddr;
	}

	public List<String> getBccAddr() {
		return bccAddr;
	}

	public void setBccAddr(List<String> bccAddr) {
		this.bccAddr = bccAddr;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
