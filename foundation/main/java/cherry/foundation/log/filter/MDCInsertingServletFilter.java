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

package cherry.foundation.log.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;

/**
 * logbackのMDCInsertingServletFilter互換機能。<br />
 * JBoss Logging配下で使用する際、MDCの値にnullが許容されないため、「nullの場合はputしない」という判定を追加。
 */
public class MDCInsertingServletFilter implements Filter {

	private static final String REQ_REMOTE_HOST = "req.remoteHost";

	private static final String REQ_REQUEST_URI = "req.requestURI";

	private static final String REQ_REQUEST_URL = "req.requestURL";

	private static final String REQ_QUERY_STRING = "req.queryString";

	private static final String REQ_USER_AGENT = "req.userAgent";

	private static final String REQ_XFORWARDED_FOR = "req.xForwardedFor";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// NOTHING
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		putMdc(request);
		try {
			chain.doFilter(request, response);
		} finally {
			removeMdc();
		}
	}

	@Override
	public void destroy() {
		// NOTHING
	}

	private void putMdc(ServletRequest request) {
		put(REQ_REMOTE_HOST, request.getRemoteHost());
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpReq = (HttpServletRequest) request;
			put(REQ_REQUEST_URI, httpReq.getRequestURI());
			put(REQ_REQUEST_URL, httpReq.getRequestURL());
			put(REQ_QUERY_STRING, httpReq.getQueryString());
			put(REQ_USER_AGENT, httpReq.getHeader("User-Agent"));
			put(REQ_XFORWARDED_FOR, httpReq.getHeader("X-Forwarded-For"));
		}
	}

	private void removeMdc() {
		MDC.remove(REQ_REMOTE_HOST);
		MDC.remove(REQ_REQUEST_URI);
		MDC.remove(REQ_REQUEST_URL);
		MDC.remove(REQ_QUERY_STRING);
		MDC.remove(REQ_USER_AGENT);
		MDC.remove(REQ_XFORWARDED_FOR);
	}

	private void put(String key, CharSequence val) {
		if (val != null) {
			MDC.put(key, val.toString());
		}
	}

}
