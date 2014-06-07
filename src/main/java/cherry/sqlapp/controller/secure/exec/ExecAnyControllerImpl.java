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

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cherry.sqlapp.service.secure.exec.ExecService;
import cherry.sqlapp.service.secure.exec.ExecService.Result;
import cherry.sqlapp.service.secure.exec.MetadataService;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Controller
public class ExecAnyControllerImpl implements ExecAnyController {

	public static final String VIEW_PATH = "secure/exec/any/index";

	public static final String VIEW_PATH_ID = "secure/exec/any/indexId";

	@Autowired
	private DataSource dataSource;

	@Autowired
	private ExecService execService;

	@Autowired
	private MetadataService metadataService;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public ExecMetadataForm getMetadata() {
		return new ExecMetadataForm();
	}

	@Override
	public ExecAnyForm getForm() {
		return new ExecAnyForm();
	}

	@Override
	public ModelAndView index(Integer ref, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH);
		return mav;
	}

	@Override
	public ModelAndView request(ExecAnyForm form, BindingResult binding,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			return mav;
		}

		Map<String, ?> paramMap = getParamMap(form.getParamMap());
		Result result = execService.exec(dataSource, form.getSql(), paramMap);

		ModelAndView mav = new ModelAndView(VIEW_PATH);
		mav.addObject(result.getPageSet());
		mav.addObject(result.getExecResult());
		return mav;
	}

	@Override
	public ModelAndView create(ExecAnyForm form, BindingResult binding,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			return mav;
		}

		int id = 0;

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(URI_PATH_ID));
		mav.addObject(PATH_VAR, id);
		return mav;
	}

	@Override
	public ModelAndView indexId(int id, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH_ID);
		mav.addObject(PATH_VAR, id);
		return mav;
	}

	@Override
	public ModelAndView requestId(int id, ExecAnyForm form,
			BindingResult binding, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH_ID);
			mav.addObject(PATH_VAR, id);
			return mav;
		}

		ModelAndView mav = new ModelAndView(VIEW_PATH_ID);
		mav.addObject(PATH_VAR, id);
		return mav;
	}

	@Override
	public ModelAndView update(int id, ExecAnyForm form, BindingResult binding,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH_ID);
			mav.addObject(PATH_VAR, id);
			return mav;
		}

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(URI_PATH_ID));
		mav.addObject(PATH_VAR, id);
		return mav;
	}

	@Override
	public ModelAndView metadata(int id, ExecMetadataForm form,
			BindingResult binding, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH_ID);
			mav.addObject(PATH_VAR, id);
			return mav;
		}

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(URI_PATH_ID));
		mav.addObject(PATH_VAR, id);
		return mav;
	}

	private Map<String, ?> getParamMap(String pmap) {
		if (StringUtils.isBlank(pmap)) {
			return new HashMap<>();
		}
		try {
			JavaType type = TypeFactory.defaultInstance().constructMapType(
					Map.class, String.class, Object.class);
			return objectMapper.readValue(pmap, type);
		} catch (IOException ex) {
			return new HashMap<>();
		}
	}

}
