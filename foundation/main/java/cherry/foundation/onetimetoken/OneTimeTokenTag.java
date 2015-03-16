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

package cherry.foundation.onetimetoken;

import static com.google.common.html.HtmlEscapers.htmlEscaper;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

public class OneTimeTokenTag extends RequestContextAwareTag {

	private static final long serialVersionUID = 1L;

	private String template = "<input type=\"hidden\" name=\"{0}\" value=\"{1}\" />";

	public void setTemplate(String template) {
		this.template = template;
	}

	@Override
	protected int doStartTagInternal() throws Exception {
		OneTimeTokenIssuer issuer = getRequestContext().getWebApplicationContext().getBean(OneTimeTokenIssuer.class);
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		OneTimeToken token = issuer.newToken(request);
		pageContext.getOut().write(handleToken(token));
		return SKIP_BODY;
	}

	private String handleToken(OneTimeToken token) {
		return MessageFormat.format(template, htmlEscaper().escape(token.getName()),
				htmlEscaper().escape(token.getValue()));
	}

}
