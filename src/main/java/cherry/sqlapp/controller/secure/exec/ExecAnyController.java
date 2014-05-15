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

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(ExecAnyController.URI_PATH)
public interface ExecAnyController {

	public static final String URI_PATH = "/secure/exec/any";

	public static final String URI_PATH_EXEC = "exec";

	public static final String URI_PATH_ID = "{id}";

	public static final String URI_PATH_ID_METADATA = "{id}/metadata";

	public static final String URI_PATH_ID_UPDATE = "{id}/update";

	public static final String URI_PATH_ID_EXEC = "{id}/exec";

	public static final String PATH_VAR = "id";

	public static final String PARAM_H = "h";

	@ModelAttribute("execMetadataForm")
	ExecMetadataForm getMetadata();

	@ModelAttribute("execAnyForm")
	ExecAnyForm getForm();

	@RequestMapping()
	ModelAndView index(
			@RequestParam(value = PARAM_H, required = false, defaultValue = "") Integer hid,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request);

	@RequestMapping(URI_PATH_EXEC)
	ModelAndView exec(@Validated ExecAnyForm form, BindingResult binding,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request);

	@RequestMapping(URI_PATH_ID)
	ModelAndView edit(@PathVariable(PATH_VAR) int id,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request);

	@RequestMapping(URI_PATH_ID_METADATA)
	ModelAndView metadata(@PathVariable(PATH_VAR) int id,
			@Validated ExecMetadataForm form, BindingResult binding,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request);

	@RequestMapping(URI_PATH_ID_UPDATE)
	ModelAndView update(@PathVariable(PATH_VAR) int id,
			@Validated ExecAnyForm form, BindingResult binding,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request);

	@RequestMapping(URI_PATH_ID_EXEC)
	ModelAndView exec(@PathVariable(PATH_VAR) int id,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request);

}
