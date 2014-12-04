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

package cherry.foundation.onetimetoken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class OneTimeTokenValidatorImpl implements OneTimeTokenValidator {

	private String name;

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public synchronized boolean isValid(HttpServletRequest request) {
		String reqToken = request.getParameter(name);
		if (reqToken == null) {
			return false;
		}
		HttpSession session = request.getSession(false);
		if (session == null) {
			return false;
		}
		Object sesToken = session.getAttribute(name);
		if (sesToken == null) {
			return false;
		}
		session.removeAttribute(name);
		return reqToken.equals(sesToken);
	}

}
