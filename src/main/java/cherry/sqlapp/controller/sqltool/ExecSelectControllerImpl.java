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

package cherry.sqlapp.controller.sqltool;

import static org.springframework.web.util.UriComponentsBuilder.fromPath;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;

import cherry.sqlapp.db.gen.dto.SqlMetadata;
import cherry.sqlapp.db.gen.dto.SqlSelect;
import cherry.sqlapp.service.sqltool.ExecService;
import cherry.sqlapp.service.sqltool.MetadataService;
import cherry.sqlapp.service.sqltool.SelectService;
import cherry.sqlapp.service.sqltool.SqlBuilder;
import cherry.sqlapp.service.sqltool.ExecService.Result;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Controller
public class ExecSelectControllerImpl implements ExecSelectController {

	public static final String VIEW_PATH = "secure/exec/select/index";

	public static final String VIEW_PATH_ID = "secure/exec/select/indexId";

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

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public ExecMetadataForm getMetadata() {
		return new ExecMetadataForm();
	}

	@Override
	public ExecSelectForm getForm() {
		ExecSelectForm form = new ExecSelectForm();
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
					mav.addObject(getForm(record));
				}
			}
		}
		return mav;
	}

	@Override
	public ModelAndView request(ExecSelectForm form, BindingResult binding,
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
		Map<String, ?> paramMap = getParamMap(form.getParamMap());

		Result result = execService.exec(dataSource, builder, paramMap, pageNo,
				(pageSz <= 0 ? defaultPageSize : pageSz));

		ModelAndView mav = new ModelAndView(VIEW_PATH);
		mav.addObject(dataSourceDef);
		mav.addObject(result.getPageSet());
		mav.addObject(result.getExecResult());
		return mav;
	}

	@Override
	public ModelAndView create(ExecSelectForm form, BindingResult binding,
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
		ExecMetadataForm mdForm = getMdForm(md);

		SqlSelect record = selectService.findById(id);
		ExecSelectForm form = getForm(record);

		ModelAndView mav = new ModelAndView(VIEW_PATH_ID);
		mav.addObject(PATH_VAR, id);
		mav.addObject(dataSourceDef);
		mav.addObject(mdForm);
		mav.addObject(form);
		return mav;
	}

	@Override
	public ModelAndView requestId(int id, ExecSelectForm form,
			BindingResult binding, int pageNo, int pageSz,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {

		SqlMetadata md = metadataService.findById(id, authentication.getName());
		ExecMetadataForm mdForm = getMdForm(md);

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
		Map<String, ?> paramMap = getParamMap(form.getParamMap());

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
	public ModelAndView update(int id, ExecSelectForm form,
			BindingResult binding, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		SqlMetadata md = metadataService.findById(id, authentication.getName());
		ExecMetadataForm mdForm = getMdForm(md);

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
	public ModelAndView metadata(int id, ExecMetadataForm mdForm,
			BindingResult binding, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		SqlSelect record = selectService.findById(id);
		ExecSelectForm form = getForm(record);

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

	private SqlBuilder getSqlBuilder(ExecSelectForm form) {
		SqlBuilder builder = new SqlBuilder();
		builder.setSelect(form.getSelect());
		builder.setFrom(form.getFrom());
		builder.setWhere(form.getWhere());
		builder.setGroupBy(form.getGroupBy());
		builder.setHaving(form.getHaving());
		builder.setOrderBy(form.getOrderBy());
		return builder;
	}

	private ExecMetadataForm getMdForm(SqlMetadata record) {
		ExecMetadataForm mdForm = getMetadata();
		mdForm.setName(record.getName());
		mdForm.setDescription(record.getDescription());
		mdForm.setOwnedBy(record.getOwnedBy());
		mdForm.setPublishedFlg(record.getPublishedFlg() != 0);
		return mdForm;
	}

	private ExecSelectForm getForm(SqlSelect record) {
		ExecSelectForm form = getForm();
		form.setDatabaseName(record.getDatabaseName());
		form.setSelect(record.getSelectClause());
		form.setFrom(record.getFromClause());
		form.setWhere(record.getWhereClause());
		form.setGroupBy(record.getGroupByClause());
		form.setHaving(record.getHavingClause());
		form.setOrderBy(record.getOrderByClause());
		form.setParamMap(record.getParamMap());
		return form;
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
