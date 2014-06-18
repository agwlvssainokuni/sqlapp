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

package cherry.sqlapp.controller.sqltool.clause;

import static org.springframework.web.util.UriComponentsBuilder.fromPath;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import cherry.sqlapp.db.gen.dto.SqlMetadata;
import cherry.sqlapp.db.gen.dto.SqlSelect;
import cherry.sqlapp.service.sqltool.DataSourceDef;
import cherry.sqlapp.service.sqltool.ExecService;
import cherry.sqlapp.service.sqltool.ExecService.Result;
import cherry.sqlapp.service.sqltool.metadata.MetadataService;
import cherry.sqlapp.service.sqltool.SelectService;
import cherry.sqlapp.service.sqltool.SqlBuilder;

@Controller
public class SqltoolClauseControllerImpl implements SqltoolClauseController {

	public static final String VIEW_PATH = "sqltool/clause/index";

	public static final String VIEW_PATH_ID = "sqltool/clause/indexId";

	@Value("${sqlapp.app.paginator.pageSize}")
	private int defaultPageSize;

	@Autowired
	private DataSourceDef dataSourceDef;

	@Autowired
	private ExecService execService;

	@Autowired
	private MetadataService metadataService;

	@Autowired
	private SelectService selectService;

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
	public SqltoolClauseForm getForm() {
		SqltoolClauseForm form = new SqltoolClauseForm();
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
				SqlSelect record = selectService.findById(ref);
				if (record != null) {
					mav.addObject(formUtil.getForm(record));
				}
			}
		}
		return mav;
	}

	@Override
	public ModelAndView request(SqltoolClauseForm form, BindingResult binding,
			int pageNo, int pageSz, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(dataSourceDef);
			return mav;
		}

		DataSource dataSource = dataSourceDef.getDataSource(form
				.getDatabaseName());

		SqlBuilder builder = getSqlBuilder(form);
		Map<String, ?> paramMap = paramMapUtil.getParamMap(form.getParamMap());

		Result result = execService.exec(dataSource, builder, paramMap, pageNo,
				(pageSz <= 0 ? defaultPageSize : pageSz));

		ModelAndView mav = new ModelAndView(VIEW_PATH);
		mav.addObject(dataSourceDef);
		mav.addObject(result.getPageSet());
		mav.addObject(result.getExecResult());
		return mav;
	}

	@Override
	public ModelAndView create(SqltoolClauseForm form, BindingResult binding,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(dataSourceDef);
			return mav;
		}

		SqlSelect record = new SqlSelect();
		record.setDatabaseName(form.getDatabaseName());
		record.setSelectClause(form.getSelect());
		record.setFromClause(form.getFrom());
		record.setWhereClause(form.getWhere());
		record.setGroupByClause(form.getGroupBy());
		record.setHavingClause(form.getHaving());
		record.setOrderByClause(form.getOrderBy());
		record.setParamMap(form.getParamMap());

		int id = selectService.create(record, authentication.getName());

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(URI_PATH_ID, true));
		mav.addObject(PATH_VAR, id);
		return mav;
	}

	@Override
	public ModelAndView indexId(int id, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		SqlMetadata md = metadataService.findById(id, authentication.getName());
		SqltoolMetadataForm mdForm = mdFormUtil.getMdForm(md);

		SqlSelect record = selectService.findById(id);
		SqltoolClauseForm form = formUtil.getForm(record);

		ModelAndView mav = new ModelAndView(VIEW_PATH_ID);
		mav.addObject(PATH_VAR, id);
		mav.addObject(dataSourceDef);
		mav.addObject(mdForm);
		mav.addObject(form);
		return mav;
	}

	@Override
	public ModelAndView requestId(int id, SqltoolClauseForm form,
			BindingResult binding, int pageNo, int pageSz,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {

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

		SqlBuilder builder = getSqlBuilder(form);
		Map<String, ?> paramMap = paramMapUtil.getParamMap(form.getParamMap());

		Result result = execService.exec(dataSource, builder, paramMap, pageNo,
				(pageSz <= 0 ? defaultPageSize : pageSz));

		ModelAndView mav = new ModelAndView(VIEW_PATH_ID);
		mav.addObject(PATH_VAR, id);
		mav.addObject(dataSourceDef);
		mav.addObject(mdForm);
		mav.addObject(result.getPageSet());
		mav.addObject(result.getExecResult());
		return mav;
	}

	@Override
	public ModelAndView update(int id, SqltoolClauseForm form,
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

		SqlSelect record = new SqlSelect();
		record.setId(id);
		record.setDatabaseName(form.getDatabaseName());
		record.setSelectClause(form.getSelect());
		record.setFromClause(form.getFrom());
		record.setWhereClause(form.getWhere());
		record.setGroupByClause(form.getGroupBy());
		record.setHavingClause(form.getHaving());
		record.setOrderByClause(form.getOrderBy());
		record.setParamMap(form.getParamMap());

		selectService.update(record);

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

		SqlSelect record = selectService.findById(id);
		SqltoolClauseForm form = formUtil.getForm(record);

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

	private SqlBuilder getSqlBuilder(SqltoolClauseForm form) {
		SqlBuilder builder = new SqlBuilder();
		builder.setSelect(form.getSelect());
		builder.setFrom(form.getFrom());
		builder.setWhere(form.getWhere());
		builder.setGroupBy(form.getGroupBy());
		builder.setHaving(form.getHaving());
		builder.setOrderBy(form.getOrderBy());
		return builder;
	}

}
