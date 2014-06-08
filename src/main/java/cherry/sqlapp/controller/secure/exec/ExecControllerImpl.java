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

package cherry.sqlapp.controller.secure.exec;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import cherry.sqlapp.service.secure.exec.MetadataService;
import cherry.sqlapp.service.secure.exec.MetadataService.Result;

@Controller
public class ExecControllerImpl implements ExecController {

	private static final String VIEW_PATH = "secure/exec/index";

	@Value("${sqlapp.app.search.defaultFromDays}")
	private Integer defaultFromDays;

	@Value("${sqlapp.app.paginator.pageSize}")
	private int defaultPageSize;

	@Autowired
	private MetadataService metadataService;

	@Override
	public ExecSearchForm getForm() {
		ExecSearchForm form = new ExecSearchForm();
		form.setRegisteredFrom(LocalDate.now().minusDays(defaultFromDays)
				.toLocalDateTime(LocalTime.MIDNIGHT));
		return form;
	}

	@Override
	public ModelAndView index(Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH);
		return mav;
	}

	@Override
	public ModelAndView request(ExecSearchForm form, BindingResult binding,
			int pageNo, int pageSz, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			return mav;
		}

		Result result = metadataService.search(form, authentication.getName(),
				pageNo, (pageSz <= 0 ? defaultPageSize : pageSz));

		ModelAndView mav = new ModelAndView(VIEW_PATH);
		mav.addObject(result);
		return mav;
	}
}
