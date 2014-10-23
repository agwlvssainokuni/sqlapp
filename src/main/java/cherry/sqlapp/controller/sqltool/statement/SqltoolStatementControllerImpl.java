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

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;

import cherry.goods.paginate.PageSet;
import cherry.spring.common.helper.bizdate.BizdateHelper;
import cherry.spring.fwcore.download.DownloadAction;
import cherry.spring.fwcore.download.DownloadHelper;
import cherry.spring.fwcore.etl.CsvConsumer;
import cherry.sqlapp.controller.PathDef;
import cherry.sqlapp.controller.sqltool.LogicErrorUtil;
import cherry.sqlapp.controller.sqltool.MdFormUtil;
import cherry.sqlapp.controller.sqltool.ParamMapUtil;
import cherry.sqlapp.db.dto.SqltoolMetadata;
import cherry.sqlapp.db.dto.SqltoolStatement;
import cherry.sqlapp.service.sqltool.DataSourceDef;
import cherry.sqlapp.service.sqltool.exec.ExecQueryService;
import cherry.sqlapp.service.sqltool.exec.ResultSet;
import cherry.sqlapp.service.sqltool.metadata.MetadataService;
import cherry.sqlapp.service.sqltool.query.StatementService;

@Controller
public class SqltoolStatementControllerImpl implements
		SqltoolStatementController {

	@Value("${sqlapp.app.export.contentType}")
	private String contentType;

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
	private BizdateHelper bizdateHelper;

	@Autowired
	private DownloadHelper downloadHelper;

	@Autowired
	private FormUtil formUtil;

	@Autowired
	private MdFormUtil mdFormUtil;

	@Autowired
	private ParamMapUtil paramMapUtil;

	@Autowired
	private LogicErrorUtil logicErrorUtil;

	@Override
	public SqltoolStatementForm getForm(Integer ref, Authentication auth) {
		if (ref != null) {
			SqltoolMetadata md = metadataService.findById(ref, auth.getName());
			if (md != null) {
				SqltoolStatement record = statementService.findById(ref);
				if (record != null) {
					return formUtil.getForm(record);
				}
			}
		}
		SqltoolStatementForm form = new SqltoolStatementForm();
		form.setDatabaseName(dataSourceDef.getDefaultName());
		return form;
	}

	@Override
	public ModelAndView init(Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_SQLTOOL_STATEMENT_INIT);
		return mav;
	}

	@Override
	public ModelAndView execute(SqltoolStatementForm form,
			BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_SQLTOOL_STATEMENT_INIT);
			return mav;
		}

		Map<String, ?> paramMap = paramMapUtil.getParamMap(form.getParamMap());

		try {

			ResultSet resultSet = new ResultSet();
			PageSet pageSet = execQueryService.query(form.getDatabaseName(),
					form.getSql(), paramMap, resultSet);

			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_SQLTOOL_STATEMENT_INIT);
			mav.addObject(pageSet);
			mav.addObject(resultSet);
			return mav;

		} catch (BadSqlGrammarException ex) {
			logicErrorUtil.rejectOnBadSqlGrammer(binding, ex);
			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_SQLTOOL_STATEMENT_INIT);
			return mav;
		}
	}

	@Override
	public ModelAndView download(final SqltoolStatementForm form,
			BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request,
			HttpServletResponse response) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_SQLTOOL_STATEMENT_INIT);
			return mav;
		}

		final Map<String, ?> paramMap = paramMapUtil.getParamMap(form
				.getParamMap());

		try {

			DownloadAction action = new DownloadAction() {
				@Override
				public long doDownload(Writer writer) throws IOException {
					PageSet ps = execQueryService.query(form.getDatabaseName(),
							form.getSql(), paramMap, new CsvConsumer(writer,
									true));
					return ps.getLast().getTo() + 1L;
				}
			};
			downloadHelper.download(response, contentType, filename,
					bizdateHelper.now(), action);

			return null;

		} catch (BadSqlGrammarException ex) {
			logicErrorUtil.rejectOnBadSqlGrammer(binding, ex);
			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_SQLTOOL_STATEMENT_INIT);
			return mav;
		}
	}

	@Override
	public ModelAndView create(SqltoolStatementForm form,
			BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_SQLTOOL_STATEMENT_INIT);
			return mav;
		}

		SqltoolStatement record = new SqltoolStatement();
		record.setDatabaseName(form.getDatabaseName());
		record.setQuery(form.getSql());
		record.setParamMap(form.getParamMap());
		record.setLockVersion(form.getLockVersion());

		int id = statementService.create(record, auth.getName());

		UriComponents uc = fromMethodCall(
				on(SqltoolStatementIdController.class).init(id, auth, locale,
						sitePref, request)).build();

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

}
