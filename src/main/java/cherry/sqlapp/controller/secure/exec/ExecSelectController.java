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
@RequestMapping(ExecSelectController.URI_PATH)
public interface ExecSelectController {

	public static final String URI_PATH = "/secure/exec/select";

	public static final String URI_PATH_EXEC = "exec";

	public static final String URI_PATH_ID = "{id}";

	public static final String URI_PATH_ID_METADATA = "{id}/metadata";

	public static final String URI_PATH_ID_UPDATE = "{id}/update";

	public static final String URI_PATH_ID_EXEC = "{id}/exec";

	public static final String PATH_VAR = "id";

	public static final String PARAM_NO = "no";

	public static final String PARAM_SZ = "sz";

	@ModelAttribute("execMetadataForm")
	ExecMetadataForm getMetadata();

	@ModelAttribute("execSelectForm")
	ExecSelectForm getForm();

	@RequestMapping()
	ModelAndView index(Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request);

	@RequestMapping(URI_PATH_EXEC)
	ModelAndView exec(
			@Validated ExecSelectForm form,
			BindingResult binding,
			@RequestParam(value = PARAM_NO, required = false, defaultValue = "0") int pageNo,
			@RequestParam(value = PARAM_SZ, required = false, defaultValue = "0") int pageSz,
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
			@Validated ExecSelectForm form, BindingResult binding,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request);

	@RequestMapping(URI_PATH_ID_EXEC)
	ModelAndView exec(
			@PathVariable(PATH_VAR) int id,
			@RequestParam(value = PARAM_NO, required = false, defaultValue = "0") int pageNo,
			@RequestParam(value = PARAM_SZ, required = false, defaultValue = "0") int pageSz,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request);

}
