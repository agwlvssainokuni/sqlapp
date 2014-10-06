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

package cherry.sqlapp.controller.sqltool.statement;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cherry.sqlapp.controller.PathDef;

@RequestMapping(PathDef.URI_SQLTOOL_STATEMENT)
public interface SqltoolStatementController {

	@ModelAttribute()
	SqltoolStatementForm getForm(
			@RequestParam(value = PathDef.PARAM_REF, required = false) Integer ref,
			Authentication auth);

	@RequestMapping()
	ModelAndView init(Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request);

	@RequestMapping(PathDef.SUBURI_EXECUTE)
	ModelAndView execute(@Validated SqltoolStatementForm form,
			BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request);

	@RequestMapping(value = PathDef.SUBURI_EXECUTE, params = PathDef.METHOD_DOWNLOAD)
	ModelAndView download(@Validated SqltoolStatementForm form,
			BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request,
			HttpServletResponse response);

	@RequestMapping(value = PathDef.SUBURI_EXECUTE, params = PathDef.METHOD_CREATE)
	ModelAndView create(@Validated SqltoolStatementForm form,
			BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request);

}
