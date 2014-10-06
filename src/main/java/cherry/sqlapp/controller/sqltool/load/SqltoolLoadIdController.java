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

package cherry.sqlapp.controller.sqltool.load;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cherry.sqlapp.controller.sqltool.SqltoolMetadataForm;

@RequestMapping(SqltoolLoadIdController.URI_PATH)
public interface SqltoolLoadIdController {

	public static final String URI_PATH = "/sqltool/load/{id}";

	public static final String URI_PATH_REQ = "req";

	public static final String URI_PATH_FIN = "fin";

	public static final String URI_PATH_METADATA = "metadata";

	public static final String PATH_VAR = "id";

	@ModelAttribute()
	SqltoolMetadataForm getMetadata(@PathVariable(PATH_VAR) int id,
			Authentication auth);

	@ModelAttribute()
	SqltoolLoadForm getForm(@PathVariable(PATH_VAR) int id);

	@RequestMapping()
	ModelAndView init(@PathVariable(PATH_VAR) int id, Authentication auth,
			Locale locale, SitePreference sitePref, HttpServletRequest request);

	@RequestMapping(URI_PATH_REQ)
	ModelAndView execute(@PathVariable(PATH_VAR) int id,
			@Validated SqltoolLoadForm form, BindingResult binding,
			Authentication auth, Locale locale, SitePreference sitePref,
			HttpServletRequest request, RedirectAttributes redirAttr);

	@RequestMapping(URI_PATH_FIN)
	ModelAndView finish(@PathVariable(PATH_VAR) int id, Authentication auth,
			Locale locale, SitePreference sitePref, HttpServletRequest request);

	@RequestMapping(value = URI_PATH_REQ, params = "update")
	ModelAndView update(@PathVariable(PATH_VAR) int id,
			@Validated SqltoolLoadForm form, BindingResult binding,
			Authentication auth, Locale locale, SitePreference sitePref,
			HttpServletRequest request);

	@RequestMapping(URI_PATH_METADATA)
	ModelAndView metadata(@PathVariable(PATH_VAR) int id,
			@Validated SqltoolMetadataForm mdForm, BindingResult binding,
			Authentication auth, Locale locale, SitePreference sitePref,
			HttpServletRequest request);

}
