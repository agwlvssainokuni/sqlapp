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
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cherry.spring.common.lib.etl.CsvConsumer;
import cherry.spring.common.lib.paginate.PageSet;
import cherry.sqlapp.controller.sqltool.MdFormUtil;
import cherry.sqlapp.controller.sqltool.ParamMapUtil;
import cherry.sqlapp.db.gen.dto.SqltoolClause;
import cherry.sqlapp.db.gen.dto.SqltoolMetadata;
import cherry.sqlapp.service.sqltool.DataSourceDef;
import cherry.sqlapp.service.sqltool.exec.ExecQueryService;
import cherry.sqlapp.service.sqltool.exec.QueryBuilder;
import cherry.sqlapp.service.sqltool.exec.ResultSet;
import cherry.sqlapp.service.sqltool.metadata.MetadataService;
import cherry.sqlapp.service.sqltool.query.ClauseService;

@Controller
public class SqltoolClauseControllerImpl implements SqltoolClauseController {

	public static final String VIEW_PATH = "sqltool/clause/index";

	@Value("${sqlapp.app.paginator.pageSize}")
	private int defaultPageSize;

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
	private ClauseService clauseService;

	@Autowired
	private FormUtil formUtil;

	@Autowired
	private MdFormUtil mdFormUtil;

	@Autowired
	private ParamMapUtil paramMapUtil;

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
		if (ref != null) {
			SqltoolMetadata md = metadataService.findById(ref,
					authentication.getName());
			if (md != null) {
				SqltoolClause record = clauseService.findById(ref);
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
			return mav;
		}

		QueryBuilder builder = formUtil.getQueryBuilder(form);
		Map<String, ?> paramMap = paramMapUtil.getParamMap(form.getParamMap());

		ResultSet resultSet = new ResultSet();
		PageSet pageSet = execQueryService.query(form.getDatabaseName(),
				builder, paramMap, pageNo, (pageSz <= 0 ? defaultPageSize
						: pageSz), resultSet);

		ModelAndView mav = new ModelAndView(VIEW_PATH);
		mav.addObject(pageSet);
		mav.addObject(resultSet);
		return mav;
	}

	@Override
	public ModelAndView download(SqltoolClauseForm form, BindingResult binding,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request,
			HttpServletResponse response) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			return mav;
		}

		QueryBuilder builder = formUtil.getQueryBuilder(form);
		Map<String, ?> paramMap = paramMapUtil.getParamMap(form.getParamMap());

		response.setContentType(contentType);
		response.setCharacterEncoding(charset.name());
		String fname = format(filename, LocalDateTime.now().toDate());
		response.setHeader(headerName, format(headerValue, fname));

		try (OutputStream out = response.getOutputStream();
				Writer writer = new OutputStreamWriter(out, charset)) {
			execQueryService.query(form.getDatabaseName(), builder.build(),
					paramMap, new CsvConsumer(writer, true));
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}

		return null;
	}

	@Override
	public ModelAndView create(SqltoolClauseForm form, BindingResult binding,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			return mav;
		}

		SqltoolClause record = new SqltoolClause();
		record.setDatabaseName(form.getDatabaseName());
		record.setSelectClause(form.getSelect());
		record.setFromClause(form.getFrom());
		record.setWhereClause(form.getWhere());
		record.setGroupByClause(form.getGroupBy());
		record.setHavingClause(form.getHaving());
		record.setOrderByClause(form.getOrderBy());
		record.setParamMap(form.getParamMap());

		int id = clauseService.create(record, authentication.getName());

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(SqltoolClauseIdController.URI_PATH, true));
		mav.addObject(SqltoolClauseIdController.PATH_VAR, id);
		return mav;
	}

}
