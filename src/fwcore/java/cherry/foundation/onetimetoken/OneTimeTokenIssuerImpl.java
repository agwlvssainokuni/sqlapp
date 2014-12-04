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

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class OneTimeTokenIssuerImpl implements OneTimeTokenIssuer {

	private String name;

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public OneTimeToken newToken(HttpServletRequest request) {
		String value = UUID.randomUUID().toString();
		request.getSession().setAttribute(name, value);
		OneTimeToken token = new OneTimeToken();
		token.setName(name);
		token.setValue(value);
		return token;
	}

}
