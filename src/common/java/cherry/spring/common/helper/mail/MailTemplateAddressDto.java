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

import cherry.spring.common.db.BaseDto;

public class MailTemplateAddressDto extends BaseDto {

	/** シリアルバージョン。 */
	private static final long serialVersionUID = 1L;

	static enum Type {
		CC, BCC
	}

	/** 宛先ID。 */
	private Integer id;

	/** 宛先区分。 */
	private String rcptType;

	/** メールアドレス。 */
	private String mailAddr;

	public boolean isCc() {
		return Type.CC.name().equalsIgnoreCase(rcptType);
	}

	public boolean isBcc() {
		return Type.BCC.name().equalsIgnoreCase(rcptType);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRcptType() {
		return rcptType;
	}

	public void setRcptType(String rcptType) {
		this.rcptType = rcptType;
	}

	public String getMailAddr() {
		return mailAddr;
	}

	public void setMailAddr(String mailAddr) {
		this.mailAddr = mailAddr;
	}

}
