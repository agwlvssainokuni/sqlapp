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

import static cherry.spring.fwcore.mvc.Contract.shouldExist;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.Locale;
import java.util.Map;

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

import cherry.spring.fwcore.type.FlagCode;
import cherry.sqlapp.controller.PathDef;
import cherry.sqlapp.controller.sqltool.LogicErrorUtil;
import cherry.sqlapp.controller.sqltool.MdFormUtil;
import cherry.sqlapp.controller.sqltool.SqltoolMetadataForm;
import cherry.sqlapp.db.dto.SqltoolLoad;
import cherry.sqlapp.db.dto.SqltoolMetadata;
import cherry.sqlapp.service.sqltool.DataSourceDef;
import cherry.sqlapp.service.sqltool.exec.ExecLoadService;
import cherry.sqlapp.service.sqltool.metadata.MetadataService;
import cherry.sqlapp.service.sqltool.query.LoadService;

@Component
public class SqltoolLoadIdControllerImpl implements SqltoolLoadIdController {

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

	@Autowired
	private LogicErrorUtil logicErrorUtil;

	@Override
	public SqltoolMetadataForm getMetadata(int id, Authentication auth) {
		SqltoolMetadata md = metadataService.findById(id, auth.getName());
		shouldExist(md, SqltoolMetadata.class, id, auth.getName());
		return mdFormUtil.getMdForm(md);
	}

	@Override
	public SqltoolLoadForm getForm(int id) {
		SqltoolLoad record = loadService.findById(id);
		shouldExist(record, SqltoolLoad.class, id);
		return formUtil.getForm(record);
	}

	@Override
	public ModelAndView init(int id, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_SQLTOOL_LOAD_ID_INIT);
		mav.addObject(PathDef.PATH_VAR_ID, id);
		return mav;
	}

	@Override
	public ModelAndView execute(int id, SqltoolLoadForm form,
			BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request,
			RedirectAttributes redirAttr) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_SQLTOOL_LOAD_ID_INIT);
			mav.addObject(PathDef.PATH_VAR_ID, id);
			return mav;
		}

		Map<String, String> asyncParam = execLoadService.launch(
				form.getDatabaseName(), form.getSql(), form.getFile(),
				auth.getName());

		redirAttr.addFlashAttribute(ASYNC_PARAM, asyncParam);

		UriComponents uc = fromMethodCall(
				on(SqltoolLoadIdController.class).finish(id, auth, locale,
						sitePref, request)).build();

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView finish(int id, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_SQLTOOL_LOAD_ID_FINISH);
		mav.addObject(PathDef.PATH_VAR_ID, id);
		return mav;
	}

	@Override
	public ModelAndView update(int id, SqltoolLoadForm form,
			BindingResult binding, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_SQLTOOL_LOAD_ID_INIT);
			mav.addObject(PathDef.PATH_VAR_ID, id);
			return mav;
		}

		SqltoolLoad record = new SqltoolLoad();
		record.setId(id);
		record.setDatabaseName(form.getDatabaseName());
		record.setQuery(form.getSql());
		record.setLockVersion(form.getLockVersion());

		if (loadService.update(record)) {
			ModelAndView mav = new ModelAndView();
			mav.setView(new RedirectView(PathDef.URI_SQLTOOL_LOAD_ID, true));
			mav.addObject(PathDef.PATH_VAR_ID, id);
			return mav;
		} else {
			logicErrorUtil.rejectOnOptimisticLockingFailure(binding);
			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_SQLTOOL_LOAD_ID_INIT);
			mav.addObject(PathDef.PATH_VAR_ID, id);
			return mav;
		}
	}

	@Override
	public ModelAndView metadata(int id, SqltoolMetadataForm mdForm,
			BindingResult binding, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_SQLTOOL_LOAD_ID_INIT);
			mav.addObject(PathDef.PATH_VAR_ID, id);
			return mav;
		}

		SqltoolMetadata md = new SqltoolMetadata();
		md.setId(id);
		md.setName(mdForm.getName());
		md.setDescription(mdForm.getDescription());
		md.setPublishedFlg(FlagCode.valueOf(mdForm.isPublishedFlg()));
		md.setLockVersion(mdForm.getLockVersion());

		if (metadataService.update(md)) {
			ModelAndView mav = new ModelAndView();
			mav.setView(new RedirectView(PathDef.URI_SQLTOOL_LOAD_ID, true));
			mav.addObject(PathDef.PATH_VAR_ID, id);
			return mav;
		} else {
			logicErrorUtil.rejectOnOptimisticLockingFailure(binding);
			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_SQLTOOL_LOAD_ID_INIT);
			mav.addObject(PathDef.PATH_VAR_ID, id);
			return mav;
		}
	}

}
