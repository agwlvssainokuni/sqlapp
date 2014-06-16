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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping(ExecCsvController.URI_PATH)
public interface ExecCsvController {

	public static final String URI_PATH = "/secure/exec/csv";

	public static final String URI_PATH_REQ = "req";

	public static final String URI_PATH_FIN = "fin";

	public static final String URI_PATH_ID = "{id}";

	public static final String URI_PATH_ID_REQ = "{id}/req";

	public static final String URI_PATH_ID_FIN = "{id}/fin";

	public static final String URI_PATH_ID_METADATA = "{id}/metadata";

	public static final String PATH_VAR = "id";

	public static final String PARAM_REF = "ref";

	@ModelAttribute("execMetadataForm")
	ExecMetadataForm getMetadata();

	@ModelAttribute("execCsvForm")
	ExecCsvForm getForm();

	@RequestMapping()
	ModelAndView index(
			@RequestParam(value = PARAM_REF, required = false, defaultValue = "") Integer ref,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request);

	@RequestMapping(URI_PATH_REQ)
	ModelAndView request(@Validated ExecCsvForm form, BindingResult binding,
			RedirectAttributes redirectAttributes,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request);

	@RequestMapping(URI_PATH_FIN)
	ModelAndView finish(RedirectAttributes redirectAttributes,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request);

	@RequestMapping(value = URI_PATH_REQ, params = { "proc=create" })
	ModelAndView create(@Validated ExecCsvForm form, BindingResult binding,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request);

	@RequestMapping(URI_PATH_ID)
	ModelAndView indexId(@PathVariable(PATH_VAR) int id,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request);

	@RequestMapping(URI_PATH_ID_REQ)
	ModelAndView requestId(@PathVariable(PATH_VAR) int id,
			@Validated ExecCsvForm form, BindingResult binding,
			RedirectAttributes redirectAttributes,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request);

	@RequestMapping(URI_PATH_ID_FIN)
	ModelAndView finishId(@PathVariable(PATH_VAR) int id,
			RedirectAttributes redirectAttributes,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request);

	@RequestMapping(value = URI_PATH_ID_REQ, params = { "proc=update" })
	ModelAndView update(@PathVariable(PATH_VAR) int id,
			@Validated ExecCsvForm form, BindingResult binding,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request);

	@RequestMapping(URI_PATH_ID_METADATA)
	ModelAndView metadata(@PathVariable(PATH_VAR) int id,
			@Validated ExecMetadataForm mdForm, BindingResult binding,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request);

}
