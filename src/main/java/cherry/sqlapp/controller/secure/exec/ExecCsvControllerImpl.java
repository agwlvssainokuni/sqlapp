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

import static org.springframework.web.util.UriComponentsBuilder.fromPath;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;

import cherry.sqlapp.service.secure.exec.ImpService;
import cherry.sqlapp.service.secure.exec.MetadataService;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ExecCsvControllerImpl implements ExecCsvController {

	public static final String VIEW_PATH = "secure/exec/csv/index";

	public static final String VIEW_PATH_ID = "secure/exec/csv/indexId";

	@Autowired
	private DataSourceDef dataSourceDef;

	@Autowired
	private ImpService impService;

	@Autowired
	private MetadataService metadataService;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public ExecMetadataForm getMetadata() {
		return new ExecMetadataForm();
	}

	@Override
	public ExecCsvForm getForm() {
		ExecCsvForm form = new ExecCsvForm();
		form.setDatabaseName(dataSourceDef.getDefaultName());
		return form;
	}

	@Override
	public ModelAndView index(Integer ref, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH);
		mav.addObject(dataSourceDef);
		return mav;
	}

	@Override
	public ModelAndView request(ExecCsvForm form, BindingResult binding,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(dataSourceDef);
			return mav;
		}

		ModelAndView mav = new ModelAndView(VIEW_PATH);
		mav.addObject(dataSourceDef);
		return mav;
	}

	@Override
	public ModelAndView create(ExecCsvForm form, BindingResult binding,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(dataSourceDef);
			return mav;
		}

		int id = 0;

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(URI_PATH_ID, true));
		mav.addObject(PATH_VAR, id);
		return mav;
	}

	@Override
	public ModelAndView indexId(int id, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH_ID);
		mav.addObject(PATH_VAR, id);
		mav.addObject(dataSourceDef);
		return mav;
	}

	@Override
	public ModelAndView requestId(int id, ExecCsvForm form,
			BindingResult binding, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH_ID);
			mav.addObject(PATH_VAR, id);
			mav.addObject(dataSourceDef);
			return mav;
		}

		ModelAndView mav = new ModelAndView(VIEW_PATH_ID);
		mav.addObject(PATH_VAR, id);
		mav.addObject(dataSourceDef);
		return mav;
	}

	@Override
	public ModelAndView update(int id, ExecCsvForm form, BindingResult binding,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH_ID);
			mav.addObject(PATH_VAR, id);
			mav.addObject(dataSourceDef);
			return mav;
		}

		UriComponents uri = fromPath(URI_PATH).pathSegment(URI_PATH_ID)
				.buildAndExpand(id);
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uri.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView metadata(int id, ExecMetadataForm mdForm,
			BindingResult binding, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH_ID);
			mav.addObject(PATH_VAR, id);
			mav.addObject(dataSourceDef);
			return mav;
		}

		UriComponents uri = fromPath(URI_PATH).pathSegment(URI_PATH_ID)
				.buildAndExpand(id);
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uri.toUriString(), true));
		return mav;
	}

}
