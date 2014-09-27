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

package cherry.sqlapp.controller.login;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;

@Controller
public class LoginControllerImpl implements LoginController {

	public static final String VIEW_PATH = "login/index";

	@Override
	public ModelAndView index(Locale locale, SitePreference sitePref,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH);
		return mav;
	}

	@Override
	public ModelAndView loginFailed(Locale locale, SitePreference sitePref,
			HttpServletRequest request, RedirectAttributes redirAttr) {

		redirAttr.addFlashAttribute("loginFailed", true);

		UriComponents uc = MvcUriComponentsBuilder.fromMethodName(
				LoginController.class, "index", locale, sitePref, request)
				.build();

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView loggedOut(Locale locale, SitePreference sitePref,
			HttpServletRequest request, RedirectAttributes redirAttributes) {

		redirAttributes.addFlashAttribute("loggedOut", true);

		UriComponents uc = MvcUriComponentsBuilder.fromMethodName(
				LoginController.class, "index", locale, sitePref, request)
				.build();

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}
}
