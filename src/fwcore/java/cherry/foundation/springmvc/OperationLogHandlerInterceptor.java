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

package cherry.foundation.springmvc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class OperationLogHandlerInterceptor implements HandlerInterceptor {

	public static final String LOGIN_ID = "loginId";

	public static final String OPER_ENTER = "operation.ENTER";

	public static final String OPER_MIDDLE = "operation.MIDDLE";

	public static final String OPER_EXIT = "operation.EXIT";

	private final Logger loggerEnter = LoggerFactory.getLogger(OPER_ENTER);

	private final Logger loggerMiddle = LoggerFactory.getLogger(OPER_MIDDLE);

	private final Logger loggerExit = LoggerFactory.getLogger(OPER_EXIT);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		SecurityContext context = SecurityContextHolder.getContext();
		MDC.put(LOGIN_ID, context.getAuthentication().getName());

		StringBuilder builder = createBasicInfo(request);

		builder.append(" {");
		boolean first = true;
		for (Map.Entry<String, String[]> entry : request.getParameterMap()
				.entrySet()) {
			if (!first) {
				builder.append(", ");
			}
			first = false;
			builder.append(entry.getKey()).append(": ");
			if (entry.getKey().toLowerCase().contains("password")) {
				builder.append("<MASKED>");
			} else {
				builder.append(ToStringBuilder.reflectionToString(
						entry.getValue(), ToStringStyle.SHORT_PREFIX_STYLE));
			}
		}
		builder.append("}");

		loggerEnter.info(builder.toString());

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		StringBuilder builder = createBasicInfo(request);

		if (modelAndView == null) {
			builder.append(" ModelAndView=null");
		} else if (modelAndView.hasView()) {
			builder.append(" viewName=").append(modelAndView.getViewName());
		} else {
			builder.append(" noView");
		}

		loggerMiddle.info(builder.toString());
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		StringBuilder builder = createBasicInfo(request);
		if (ex == null) {
			loggerExit.info(builder.toString());
		} else {
			loggerExit.info(builder.toString(), ex);
		}

		MDC.remove(LOGIN_ID);
	}

	private StringBuilder createBasicInfo(HttpServletRequest request) {
		StringBuilder builder = new StringBuilder();
		return builder.append(request.getRemoteAddr()).append(" ")
				.append(request.getMethod()).append(" ")
				.append(request.getRequestURI());
	}

}
