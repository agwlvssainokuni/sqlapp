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

package cherry.sqlapp.controller.sqltool.search;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@SessionAttributes(types = SqltoolSearchForm.class)
@RequestMapping(SqltoolSearchController.URI_PATH)
public interface SqltoolSearchController {

	public static final String URI_PATH = "/sqltool/search";

	public static final String URI_PATH_REQ = "req";

	public static final String PARAM_NO = "no";

	public static final String PARAM_SZ = "sz";

	@ModelAttribute("sqltoolSearchForm")
	SqltoolSearchForm getForm();

	@RequestMapping()
	ModelAndView index(Authentication authentication, Locale locale,
			SitePreference sitePref, HttpServletRequest request);

	@RequestMapping(URI_PATH_REQ)
	ModelAndView request(@Validated SqltoolSearchForm form,
			BindingResult binding,
			@RequestParam(value = PARAM_NO, defaultValue = "0") int pageNo,
			@RequestParam(value = PARAM_SZ, defaultValue = "0") int pageSz,
			Authentication auth, Locale locale, SitePreference sitePref,
			HttpServletRequest request);

}
