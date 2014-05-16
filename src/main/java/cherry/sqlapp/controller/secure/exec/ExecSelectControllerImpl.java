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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;

@Controller
public class ExecSelectControllerImpl implements ExecSelectController {

	public static final String VIEW_PATH = "secure/exec/select/index";

	public static final String VIEW_PATH_EDIT = "secure/exec/select/edit";

	@Override
	public ExecMetadataForm getMetadata() {
		return new ExecMetadataForm();
	}

	@Override
	public ExecSelectForm getForm() {
		return new ExecSelectForm();
	}

	@Override
	public ModelAndView index(Integer hid, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH);
		return mav;
	}

	@Override
	public ModelAndView exec(ExecSelectForm form, BindingResult binding,
			String pmap, int pageNo, int pageSz, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			return mav;
		}

		ModelAndView mav = new ModelAndView(VIEW_PATH);
		return mav;
	}

	@Override
	public ModelAndView edit(int id, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH_EDIT);
		return mav;
	}

	@Override
	public ModelAndView metadata(int id, ExecMetadataForm form,
			BindingResult binding, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH_EDIT);
			return mav;
		}

		UriComponents uri = MvcUriComponentsBuilder.fromMethodName(
				ExecSelectController.class, "edit", id, authentication, locale,
				sitePreference, request).build();
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uri.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView update(int id, ExecSelectForm form,
			BindingResult binding, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH_EDIT);
			return mav;
		}

		UriComponents uri = MvcUriComponentsBuilder.fromMethodName(
				ExecSelectController.class, "edit", id, authentication, locale,
				sitePreference, request).build();
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uri.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView exec(int id, String pmap, int pageNo, int pageSz,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH_EDIT);
		return mav;
	}

}
