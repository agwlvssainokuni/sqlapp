/*
 * Copyright 2014,2015 agwlvssainokuni
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

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;

import cherry.foundation.async.AsyncProcessFacade;
import cherry.sqlapp.controller.PathDef;
import cherry.sqlapp.controller.sqltool.MdFormUtil;
import cherry.sqlapp.db.dto.SqltoolLoad;
import cherry.sqlapp.db.dto.SqltoolMetadata;
import cherry.sqlapp.service.sqltool.DataSourceDef;
import cherry.sqlapp.service.sqltool.metadata.MetadataService;
import cherry.sqlapp.service.sqltool.query.LoadService;

@Component
public class SqltoolLoadControllerImpl implements SqltoolLoadController {

	public static final String ASYNC_PARAM = "asyncParam";

	@Autowired
	private DataSourceDef dataSourceDef;

	@Autowired
	private AsyncProcessFacade asyncProcessFacade;

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
	public ModelAndView init(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_SQLTOOL_LOAD_INIT);
		return mav;
	}

	@Override
	public ModelAndView execute(SqltoolLoadForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request, RedirectAttributes redirAttr) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_SQLTOOL_LOAD_INIT);
			return mav;
		}

		long asyncId = asyncProcessFacade.launchFileProcess(auth.getName(), "SqltoolLoadController", form.getFile(),
				"execLoadFileProcessHandler", form.getDatabaseName(), form.getSql());

		redirAttr.addFlashAttribute(ASYNC_PARAM, asyncId);

		UriComponents uc = fromMethodCall(on(SqltoolLoadController.class).finish(auth, locale, sitePref, request))
				.build();

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView finish(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_SQLTOOL_LOAD_FINISH);
		return mav;
	}

	@Override
	public ModelAndView create(SqltoolLoadForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_SQLTOOL_LOAD_INIT);
			return mav;
		}

		SqltoolLoad record = new SqltoolLoad();
		record.setDatabaseName(form.getDatabaseName());
		record.setQuery(form.getSql());
		record.setLockVersion(form.getLockVersion());

		int id = loadService.create(record, auth.getName());

		UriComponents uc = fromMethodCall(on(SqltoolLoadIdController.class).init(id, auth, locale, sitePref, request))
				.build();

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

}
