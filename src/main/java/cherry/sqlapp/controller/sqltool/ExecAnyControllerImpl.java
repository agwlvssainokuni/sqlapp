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
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;

import cherry.sqlapp.db.gen.dto.SqlAny;
import cherry.sqlapp.db.gen.dto.SqlMetadata;
import cherry.sqlapp.service.sqltool.AnyService;
import cherry.sqlapp.service.sqltool.DataSourceDef;
import cherry.sqlapp.service.sqltool.ExecService;
import cherry.sqlapp.service.sqltool.MetadataService;
import cherry.sqlapp.service.sqltool.ExecService.Result;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Controller
public class ExecAnyControllerImpl implements ExecAnyController {

	public static final String VIEW_PATH = "secure/exec/any/index";

	public static final String VIEW_PATH_ID = "secure/exec/any/indexId";

	@Autowired
	private DataSourceDef dataSourceDef;

	@Autowired
	private ExecService execService;

	@Autowired
	private MetadataService metadataService;

	@Autowired
	private AnyService anyService;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public ExecMetadataForm getMetadata() {
		return new ExecMetadataForm();
	}

	@Override
	public ExecAnyForm getForm() {
		ExecAnyForm form = new ExecAnyForm();
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
					mav.addObject(getForm(record));
				}
			}
		}
		return mav;
	}

	@Override
	public ModelAndView request(ExecAnyForm form, BindingResult binding,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(dataSourceDef);
			return mav;
		}

		DataSource dataSource = dataSourceDef.getDataSource(form
				.getDatabaseName());

		Map<String, ?> paramMap = getParamMap(form.getParamMap());
		Result result = execService.exec(dataSource, form.getSql(), paramMap);

		ModelAndView mav = new ModelAndView(VIEW_PATH);
		mav.addObject(dataSourceDef);
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
		ExecMetadataForm mdForm = getMdForm(md);

		SqlAny record = anyService.findById(id);
		ExecAnyForm form = getForm(record);

		ModelAndView mav = new ModelAndView(VIEW_PATH_ID);
		mav.addObject(PATH_VAR, id);
		mav.addObject(dataSourceDef);
		mav.addObject(mdForm);
		mav.addObject(form);
		return mav;
	}

	@Override
	public ModelAndView requestId(int id, ExecAnyForm form,
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

		DataSource dataSource = dataSourceDef.getDataSource(form
				.getDatabaseName());

		Map<String, ?> paramMap = getParamMap(form.getParamMap());
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
	public ModelAndView update(int id, ExecAnyForm form, BindingResult binding,
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

		SqlAny record = new SqlAny();
		record.setId(id);
		record.setDatabaseName(record.getDatabaseName());
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
	public ModelAndView metadata(int id, ExecMetadataForm mdForm,
			BindingResult binding, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		SqlAny record = anyService.findById(id);
		ExecAnyForm form = getForm(record);

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

	private ExecMetadataForm getMdForm(SqlMetadata record) {
		ExecMetadataForm mdForm = getMetadata();
		mdForm.setName(record.getName());
		mdForm.setDescription(record.getDescription());
		mdForm.setOwnedBy(record.getOwnedBy());
		mdForm.setPublishedFlg(record.getPublishedFlg() != 0);
		return mdForm;
	}

	private ExecAnyForm getForm(SqlAny record) {
		ExecAnyForm form = getForm();
		form.setDatabaseName(record.getDatabaseName());
		form.setSql(record.getQuery());
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
