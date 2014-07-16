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

import static java.text.MessageFormat.format;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cherry.spring.common.lib.etl.CsvConsumer;
import cherry.spring.common.lib.paginate.PageSet;
import cherry.sqlapp.controller.sqltool.LogicErrorUtil;
import cherry.sqlapp.controller.sqltool.MdFormUtil;
import cherry.sqlapp.controller.sqltool.ParamMapUtil;
import cherry.sqlapp.controller.sqltool.SqltoolMetadataForm;
import cherry.sqlapp.db.dto.SqltoolMetadata;
import cherry.sqlapp.db.dto.SqltoolStatement;
import cherry.sqlapp.service.sqltool.DataSourceDef;
import cherry.sqlapp.service.sqltool.exec.ExecQueryService;
import cherry.sqlapp.service.sqltool.exec.ResultSet;
import cherry.sqlapp.service.sqltool.metadata.MetadataService;
import cherry.sqlapp.service.sqltool.query.StatementService;

@Controller
public class SqltoolStatementIdControllerImpl implements
		SqltoolStatementIdController {

	public static final String VIEW_PATH = "sqltool/statement/indexId";

	@Value("${sqlapp.app.export.contentType}")
	private String contentType;

	@Value("${sqlapp.app.export.charset}")
	private Charset charset;

	@Value("${sqlapp.app.export.headerName}")
	private String headerName;

	@Value("${sqlapp.app.export.headerValue}")
	private String headerValue;

	@Value("${sqlapp.app.export.filename}")
	private String filename;

	@Autowired
	private DataSourceDef dataSourceDef;

	@Autowired
	private ExecQueryService execQueryService;

	@Autowired
	private MetadataService metadataService;

	@Autowired
	private StatementService statementService;

	@Autowired
	private FormUtil formUtil;

	@Autowired
	private MdFormUtil mdFormUtil;

	@Autowired
	private ParamMapUtil paramMapUtil;

	@Autowired
	private LogicErrorUtil logicErrorUtil;

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
	public ModelAndView index(int id, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		SqltoolMetadata md = metadataService.findById(id,
				authentication.getName());
		SqltoolMetadataForm mdForm = mdFormUtil.getMdForm(md);

		SqltoolStatement record = statementService.findById(id);
		SqltoolStatementForm form = formUtil.getForm(record);

		ModelAndView mav = new ModelAndView(VIEW_PATH);
		mav.addObject(PATH_VAR, id);
		mav.addObject(mdForm);
		mav.addObject(form);
		return mav;
	}

	@Override
	public ModelAndView request(int id, SqltoolStatementForm form,
			BindingResult binding, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		SqltoolMetadata md = metadataService.findById(id,
				authentication.getName());
		SqltoolMetadataForm mdForm = mdFormUtil.getMdForm(md);

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(PATH_VAR, id);
			mav.addObject(mdForm);
			return mav;
		}

		Map<String, ?> paramMap = paramMapUtil.getParamMap(form.getParamMap());

		try {

			ResultSet resultSet = new ResultSet();
			PageSet pageSet = execQueryService.query(form.getDatabaseName(),
					form.getSql(), paramMap, resultSet);

			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(PATH_VAR, id);
			mav.addObject(mdForm);
			mav.addObject(pageSet);
			mav.addObject(resultSet);
			return mav;

		} catch (BadSqlGrammarException ex) {
			logicErrorUtil.rejectOnBadSqlGrammer(binding, ex);
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(PATH_VAR, id);
			mav.addObject(mdForm);
			return mav;
		}
	}

	@Override
	public ModelAndView download(int id, SqltoolStatementForm form,
			BindingResult binding, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request, HttpServletResponse response) {

		SqltoolMetadata md = metadataService.findById(id,
				authentication.getName());
		SqltoolMetadataForm mdForm = mdFormUtil.getMdForm(md);

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(PATH_VAR, id);
			mav.addObject(mdForm);
			return mav;
		}

		Map<String, ?> paramMap = paramMapUtil.getParamMap(form.getParamMap());

		try {

			response.setContentType(contentType);
			response.setCharacterEncoding(charset.name());
			String fname = format(filename, LocalDateTime.now().toDate());
			response.setHeader(headerName, format(headerValue, fname));

			try (OutputStream out = response.getOutputStream();
					Writer writer = new OutputStreamWriter(out, charset)) {
				execQueryService.query(form.getDatabaseName(), form.getSql(),
						paramMap, new CsvConsumer(writer, true));
			} catch (IOException ex) {
				throw new IllegalStateException(ex);
			}

			return null;

		} catch (BadSqlGrammarException ex) {
			logicErrorUtil.rejectOnBadSqlGrammer(binding, ex);
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(PATH_VAR, id);
			mav.addObject(mdForm);
			return mav;
		}
	}

	@Override
	public ModelAndView update(int id, SqltoolStatementForm form,
			BindingResult binding, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		SqltoolMetadata md = metadataService.findById(id,
				authentication.getName());
		SqltoolMetadataForm mdForm = mdFormUtil.getMdForm(md);

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(PATH_VAR, id);
			mav.addObject(mdForm);
			return mav;
		}

		SqltoolStatement record = new SqltoolStatement();
		record.setId(id);
		record.setDatabaseName(form.getDatabaseName());
		record.setQuery(form.getSql());
		record.setParamMap(form.getParamMap());
		record.setLockVersion(form.getLockVersion());

		if (statementService.update(record)) {
			ModelAndView mav = new ModelAndView();
			mav.setView(new RedirectView(URI_PATH, true));
			mav.addObject(PATH_VAR, id);
			return mav;
		} else {
			logicErrorUtil.rejectOnOptimisticLockingFailure(binding);
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(PATH_VAR, id);
			mav.addObject(mdForm);
			return mav;
		}
	}

	@Override
	public ModelAndView metadata(int id, SqltoolMetadataForm mdForm,
			BindingResult binding, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		SqltoolStatement record = statementService.findById(id);
		SqltoolStatementForm form = formUtil.getForm(record);

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(PATH_VAR, id);
			mav.addObject(form);
			return mav;
		}

		SqltoolMetadata md = new SqltoolMetadata();
		md.setId(id);
		md.setName(mdForm.getName());
		md.setDescription(mdForm.getDescription());
		md.setPublishedFlg(mdForm.isPublishedFlg() ? 1 : 0);
		md.setLockVersion(mdForm.getLockVersion());

		if (metadataService.update(md)) {
			ModelAndView mav = new ModelAndView();
			mav.setView(new RedirectView(URI_PATH, true));
			mav.addObject(PATH_VAR, id);
			return mav;
		} else {
			logicErrorUtil.rejectOnOptimisticLockingFailure(binding);
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(PATH_VAR, id);
			mav.addObject(form);
			return mav;
		}
	}

}
