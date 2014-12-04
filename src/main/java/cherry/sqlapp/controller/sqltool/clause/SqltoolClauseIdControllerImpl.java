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

import static cherry.foundation.springmvc.Contract.shouldExist;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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

import cherry.foundation.bizdtm.BizDateTime;
import cherry.foundation.download.DownloadAction;
import cherry.foundation.download.DownloadOperation;
import cherry.foundation.etl.CsvConsumer;
import cherry.foundation.type.FlagCode;
import cherry.goods.paginate.PageSet;
import cherry.sqlapp.controller.PathDef;
import cherry.sqlapp.controller.sqltool.LogicErrorUtil;
import cherry.sqlapp.controller.sqltool.MdFormUtil;
import cherry.sqlapp.controller.sqltool.ParamMapUtil;
import cherry.sqlapp.controller.sqltool.SqltoolMetadataForm;
import cherry.sqlapp.db.dto.SqltoolClause;
import cherry.sqlapp.db.dto.SqltoolMetadata;
import cherry.sqlapp.service.sqltool.DataSourceDef;
import cherry.sqlapp.service.sqltool.exec.ExecQueryService;
import cherry.sqlapp.service.sqltool.exec.QueryBuilder;
import cherry.sqlapp.service.sqltool.exec.ResultSet;
import cherry.sqlapp.service.sqltool.metadata.MetadataService;
import cherry.sqlapp.service.sqltool.query.ClauseService;

@Controller
public class SqltoolClauseIdControllerImpl implements SqltoolClauseIdController {

	@Value("${sqlapp.app.paginator.pageSize}")
	private long defaultPageSize;

	@Value("${sqlapp.app.export.contentType}")
	private String contentType;

	private Charset charset = StandardCharsets.UTF_8;

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
	private BizDateTime bizDateTime;

	@Autowired
	private DownloadOperation downloadOperation;

	@Autowired
	private FormUtil formUtil;

	@Autowired
	private MdFormUtil mdFormUtil;

	@Autowired
	private ParamMapUtil paramMapUtil;

	@Autowired
	private LogicErrorUtil logicErrorUtil;

	@Override
	public SqltoolMetadataForm getMetadata(int id, Authentication auth) {
		SqltoolMetadata md = metadataService.findById(id, auth.getName());
		shouldExist(md, SqltoolMetadata.class, id, auth.getName());
		return mdFormUtil.getMdForm(md);
	}

	@Override
	public SqltoolClauseForm getForm(int id) {
		SqltoolClause record = clauseService.findById(id);
		shouldExist(record, SqltoolClause.class, id);
		return formUtil.getForm(record);
	}

	@Override
	public ModelAndView init(int id, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_SQLTOOL_CLAUSE_ID_INIT);
		mav.addObject(PathDef.PATH_VAR_ID, id);
		return mav;
	}

	@Override
	public ModelAndView execute(int id, SqltoolClauseForm form,
			BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_SQLTOOL_CLAUSE_ID_INIT);
			mav.addObject(PathDef.PATH_VAR_ID, id);
			return mav;
		}

		QueryBuilder builder = formUtil.getQueryBuilder(form);
		Map<String, ?> paramMap = paramMapUtil.getParamMap(form.getParamMap());
		long pageNo = form.getPageNo();
		long pageSz = (form.getPageSz() <= 0L ? defaultPageSize : form
				.getPageSz());

		try {

			ResultSet resultSet = new ResultSet();
			PageSet pageSet = execQueryService.query(form.getDatabaseName(),
					builder, paramMap, pageNo, pageSz, resultSet);

			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_SQLTOOL_CLAUSE_ID_INIT);
			mav.addObject(PathDef.PATH_VAR_ID, id);
			mav.addObject(pageSet);
			mav.addObject(resultSet);
			return mav;

		} catch (BadSqlGrammarException ex) {
			logicErrorUtil.rejectOnBadSqlGrammer(binding, ex);
			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_SQLTOOL_CLAUSE_ID_INIT);
			mav.addObject(PathDef.PATH_VAR_ID, id);
			return mav;
		}
	}

	@Override
	public ModelAndView download(int id, final SqltoolClauseForm form,
			BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request,
			HttpServletResponse response) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_SQLTOOL_CLAUSE_ID_INIT);
			mav.addObject(PathDef.PATH_VAR_ID, id);
			return mav;
		}

		final QueryBuilder builder = formUtil.getQueryBuilder(form);
		final Map<String, ?> paramMap = paramMapUtil.getParamMap(form
				.getParamMap());

		try {

			DownloadAction action = new DownloadAction() {
				@Override
				public long doDownload(OutputStream out) throws IOException {
					try (Writer writer = new OutputStreamWriter(out, charset)) {
						PageSet ps = execQueryService.query(
								form.getDatabaseName(), builder.build(),
								paramMap, new CsvConsumer(writer, true));
						return ps.getLast().getTo() + 1L;
					}
				}
			};
			downloadOperation.download(response, contentType, charset,
					filename, bizDateTime.now(), action);

			return null;

		} catch (BadSqlGrammarException ex) {
			logicErrorUtil.rejectOnBadSqlGrammer(binding, ex);
			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_SQLTOOL_CLAUSE_ID_INIT);
			mav.addObject(PathDef.PATH_VAR_ID, id);
			return mav;
		}
	}

	@Override
	public ModelAndView update(int id, SqltoolClauseForm form,
			BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {

		SqltoolMetadata md = metadataService.findById(id, auth.getName());
		SqltoolMetadataForm mdForm = mdFormUtil.getMdForm(md);

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_SQLTOOL_CLAUSE_ID_INIT);
			mav.addObject(PathDef.PATH_VAR_ID, id);
			mav.addObject(mdForm);
			return mav;
		}

		SqltoolClause record = new SqltoolClause();
		record.setId(id);
		record.setDatabaseName(form.getDatabaseName());
		record.setSelectClause(form.getSelect());
		record.setFromClause(form.getFrom());
		record.setWhereClause(form.getWhere());
		record.setGroupByClause(form.getGroupBy());
		record.setHavingClause(form.getHaving());
		record.setOrderByClause(form.getOrderBy());
		record.setParamMap(form.getParamMap());
		record.setLockVersion(form.getLockVersion());

		if (clauseService.update(record)) {
			UriComponents uc = fromMethodCall(
					on(SqltoolClauseIdController.class).init(id, auth, locale,
							sitePref, request)).build();
			ModelAndView mav = new ModelAndView();
			mav.setView(new RedirectView(uc.toUriString(), true));
			return mav;
		} else {
			logicErrorUtil.rejectOnOptimisticLockingFailure(binding);
			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_SQLTOOL_CLAUSE_ID_INIT);
			mav.addObject(PathDef.PATH_VAR_ID, id);
			mav.addObject(mdForm);
			return mav;

		}
	}

	@Override
	public ModelAndView metadata(int id, SqltoolMetadataForm mdForm,
			BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_SQLTOOL_CLAUSE_ID_INIT);
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
			UriComponents uc = fromMethodCall(
					on(SqltoolClauseIdController.class).init(id, auth, locale,
							sitePref, request)).build();
			ModelAndView mav = new ModelAndView();
			mav.setView(new RedirectView(uc.toUriString(), true));
			return mav;
		} else {
			logicErrorUtil.rejectOnOptimisticLockingFailure(binding);
			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_SQLTOOL_CLAUSE_ID_INIT);
			mav.addObject(PathDef.PATH_VAR_ID, id);
			return mav;
		}
	}

}
