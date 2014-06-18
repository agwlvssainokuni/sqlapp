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

package cherry.sqlapp.controller.sqltool.statement;

import static org.springframework.web.util.UriComponentsBuilder.fromPath;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;

import cherry.sqlapp.controller.sqltool.MdFormUtil;
import cherry.sqlapp.controller.sqltool.ParamMapUtil;
import cherry.sqlapp.controller.sqltool.SqltoolMetadataForm;
import cherry.sqlapp.db.gen.dto.SqlAny;
import cherry.sqlapp.db.gen.dto.SqlMetadata;
import cherry.sqlapp.service.sqltool.AnyService;
import cherry.sqlapp.service.sqltool.DataSourceDef;
import cherry.sqlapp.service.sqltool.ExecService;
import cherry.sqlapp.service.sqltool.ExecService.Result;
import cherry.sqlapp.service.sqltool.MetadataService;

@Controller
public class SqltoolStatementControllerImpl implements
		SqltoolStatementController {

	public static final String VIEW_PATH = "sqltool/statement/index";

	public static final String VIEW_PATH_ID = "sqltool/statement/indexId";

	@Autowired
	private DataSourceDef dataSourceDef;

	@Autowired
	private ExecService execService;

	@Autowired
	private MetadataService metadataService;

	@Autowired
	private AnyService anyService;

	@Autowired
	private FormUtil formUtil;

	@Autowired
	private MdFormUtil mdFormUtil;

	@Autowired
	private ParamMapUtil paramMapUtil;

	@Override
	public SqltoolMetadataForm getMetadata() {
		return new SqltoolMetadataForm();
	}

	@Override
	public SqltoolStatementForm getForm() {
		SqltoolStatementForm form = new SqltoolStatementForm();
		form.setDatabaseName(dataSourceDef.getDefaultName());
		return form;
	}

	@Override
	public ModelAndView index(Integer ref, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH);
		mav.addObject(dataSourceDef);
		if (ref != null) {
			SqlMetadata md = metadataService.findById(ref,
					authentication.getName());
			if (md != null) {
				SqlAny record = anyService.findById(ref);
				if (record != null) {
					mav.addObject(formUtil.getForm(record));
				}
			}
		}
		return mav;
	}

	@Override
	public ModelAndView request(SqltoolStatementForm form,
			BindingResult binding, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(dataSourceDef);
			return mav;
		}

		DataSource dataSource = dataSourceDef.getDataSource(form
				.getDatabaseName());

		Map<String, ?> paramMap = paramMapUtil.getParamMap(form.getParamMap());
		Result result = execService.exec(dataSource, form.getSql(), paramMap);

		ModelAndView mav = new ModelAndView(VIEW_PATH);
		mav.addObject(dataSourceDef);
		mav.addObject(result.getPageSet());
		mav.addObject(result.getExecResult());
		return mav;
	}

	@Override
	public ModelAndView create(SqltoolStatementForm form,
			BindingResult binding, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(dataSourceDef);
			return mav;
		}

		SqlAny record = new SqlAny();
		record.setDatabaseName(form.getDatabaseName());
		record.setQuery(form.getSql());
		record.setParamMap(form.getParamMap());

		int id = anyService.create(record, authentication.getName());

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(URI_PATH_ID));
		mav.addObject(PATH_VAR, id);
		return mav;
	}

	@Override
	public ModelAndView indexId(int id, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		SqlMetadata md = metadataService.findById(id, authentication.getName());
		SqltoolMetadataForm mdForm = mdFormUtil.getMdForm(md);

		SqlAny record = anyService.findById(id);
		SqltoolStatementForm form = formUtil.getForm(record);

		ModelAndView mav = new ModelAndView(VIEW_PATH_ID);
		mav.addObject(PATH_VAR, id);
		mav.addObject(dataSourceDef);
		mav.addObject(mdForm);
		mav.addObject(form);
		return mav;
	}

	@Override
	public ModelAndView requestId(int id, SqltoolStatementForm form,
			BindingResult binding, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		SqlMetadata md = metadataService.findById(id, authentication.getName());
		SqltoolMetadataForm mdForm = mdFormUtil.getMdForm(md);

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH_ID);
			mav.addObject(PATH_VAR, id);
			mav.addObject(dataSourceDef);
			mav.addObject(mdForm);
			return mav;
		}

		DataSource dataSource = dataSourceDef.getDataSource(form
				.getDatabaseName());

		Map<String, ?> paramMap = paramMapUtil.getParamMap(form.getParamMap());
		Result result = execService.exec(dataSource, form.getSql(), paramMap);

		ModelAndView mav = new ModelAndView(VIEW_PATH_ID);
		mav.addObject(PATH_VAR, id);
		mav.addObject(dataSourceDef);
		mav.addObject(mdForm);
		mav.addObject(result.getPageSet());
		mav.addObject(result.getExecResult());
		return mav;
	}

	@Override
	public ModelAndView update(int id, SqltoolStatementForm form,
			BindingResult binding, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		SqlMetadata md = metadataService.findById(id, authentication.getName());
		SqltoolMetadataForm mdForm = mdFormUtil.getMdForm(md);

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH_ID);
			mav.addObject(PATH_VAR, id);
			mav.addObject(dataSourceDef);
			mav.addObject(mdForm);
			return mav;
		}

		SqlAny record = new SqlAny();
		record.setId(id);
		record.setDatabaseName(form.getDatabaseName());
		record.setQuery(form.getSql());
		record.setParamMap(form.getParamMap());

		anyService.update(record);

		UriComponents uri = fromPath(URI_PATH).pathSegment(URI_PATH_ID)
				.buildAndExpand(id);
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uri.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView metadata(int id, SqltoolMetadataForm mdForm,
			BindingResult binding, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		SqlAny record = anyService.findById(id);
		SqltoolStatementForm form = formUtil.getForm(record);

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH_ID);
			mav.addObject(PATH_VAR, id);
			mav.addObject(dataSourceDef);
			mav.addObject(form);
			return mav;
		}

		SqlMetadata md = new SqlMetadata();
		md.setId(id);
		md.setName(mdForm.getName());
		md.setDescription(mdForm.getDescription());
		md.setPublishedFlg(mdForm.isPublishedFlg() ? 1 : 0);

		metadataService.update(md);

		UriComponents uri = fromPath(URI_PATH).pathSegment(URI_PATH_ID)
				.buildAndExpand(id);
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uri.toUriString(), true));
		return mav;
	}

}
