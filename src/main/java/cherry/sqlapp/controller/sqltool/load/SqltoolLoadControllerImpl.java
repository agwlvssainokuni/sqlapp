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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;

import cherry.sqlapp.controller.sqltool.MdFormUtil;
import cherry.sqlapp.db.dto.SqltoolLoad;
import cherry.sqlapp.db.dto.SqltoolMetadata;
import cherry.sqlapp.service.sqltool.DataSourceDef;
import cherry.sqlapp.service.sqltool.exec.ExecLoadService;
import cherry.sqlapp.service.sqltool.metadata.MetadataService;
import cherry.sqlapp.service.sqltool.query.LoadService;

@Component
public class SqltoolLoadControllerImpl implements SqltoolLoadController {

	public static final String VIEW_PATH = "sqltool/load/index";

	public static final String VIEW_PATH_FIN = "sqltool/load/finish";

	public static final String ASYNC_PARAM = "asyncParam";

	@Autowired
	private DataSourceDef dataSourceDef;

	@Autowired
	private ExecLoadService execLoadService;

	@Autowired
	private MetadataService metadataService;

	@Autowired
	private LoadService loadService;

	@Autowired
	private FormUtil formUtil;

	@Autowired
	private MdFormUtil mdFormUtil;

	@Override
	public SqltoolLoadForm getForm(Integer ref, Authentication auth) {
		if (ref != null) {
			SqltoolMetadata md = metadataService.findById(ref, auth.getName());
			if (md != null) {
				SqltoolLoad record = loadService.findById(ref);
				if (record != null) {
					return formUtil.getForm(record);
				}
			}
		}
		SqltoolLoadForm form = new SqltoolLoadForm();
		form.setDatabaseName(dataSourceDef.getDefaultName());
		return form;
	}

	@Override
	public ModelAndView index(Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH);
		return mav;
	}

	@Override
	public ModelAndView request(SqltoolLoadForm form, BindingResult binding,
			Authentication auth, Locale locale, SitePreference sitePref,
			HttpServletRequest request, RedirectAttributes redirAttr) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			return mav;
		}

		Map<String, String> asyncParam = execLoadService.launch(
				form.getDatabaseName(), form.getSql(), form.getFile(),
				auth.getName());

		redirAttr.addFlashAttribute(ASYNC_PARAM, asyncParam);

		UriComponents uc = MvcUriComponentsBuilder.fromMethodName(
				SqltoolLoadController.class, "finish", auth, locale, sitePref,
				request).build();

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView finish(Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH_FIN);
		return mav;
	}

	@Override
	public ModelAndView create(SqltoolLoadForm form, BindingResult binding,
			Authentication auth, Locale locale, SitePreference sitePref,
			HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			return mav;
		}

		SqltoolLoad record = new SqltoolLoad();
		record.setDatabaseName(form.getDatabaseName());
		record.setQuery(form.getSql());
		record.setLockVersion(form.getLockVersion());

		int id = loadService.create(record, auth.getName());

		UriComponents uc = MvcUriComponentsBuilder.fromMethodName(
				SqltoolLoadIdController.class, "index", id, auth, locale,
				sitePref, request).build();

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

}
