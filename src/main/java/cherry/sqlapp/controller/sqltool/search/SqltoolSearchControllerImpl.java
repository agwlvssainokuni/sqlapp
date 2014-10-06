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

package cherry.sqlapp.controller.sqltool.search;

import java.util.Arrays;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import cherry.spring.common.helper.bizdate.BizdateHelper;
import cherry.spring.common.lib.util.LocalDateTimeUtil;
import cherry.sqlapp.service.sqltool.metadata.MetadataCondition;
import cherry.sqlapp.service.sqltool.metadata.MetadataService;
import cherry.sqlapp.service.sqltool.metadata.Result;

@Controller
public class SqltoolSearchControllerImpl implements SqltoolSearchController {

	private static final String VIEW_PATH = "sqltool/search/index";

	@Value("${sqlapp.app.search.defaultFromDays}")
	private Integer defaultFromDays;

	@Value("${sqlapp.app.paginator.pageSize}")
	private int defaultPageSize;

	@Autowired
	private MetadataService metadataService;

	@Autowired
	private BizdateHelper bizdateHelper;

	@Autowired
	private FormUtil formUtil;

	@Override
	public SqltoolSearchForm getForm() {
		LocalDate today = bizdateHelper.today();
		SqltoolSearchForm form = new SqltoolSearchForm();
		form.setRegisteredFrom(LocalDateTimeUtil.rangeFrom(today
				.minusDays(defaultFromDays)));
		form.setRegisteredTo(LocalDateTimeUtil.rangeTo(today).minusSeconds(1));
		form.setSqlType(Arrays.asList(SqlType.CLAUSE, SqlType.STATEMENT,
				SqlType.LOAD));
		form.setPublished(Arrays.asList(Published.PUBLIC, Published.PRIVATE));
		return form;
	}

	@Override
	public ModelAndView init(Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH);
		mav.addObject(getForm());
		return mav;
	}

	@Override
	public ModelAndView execute(SqltoolSearchForm form, BindingResult binding,
			Authentication auth, Locale locale, SitePreference sitePref,
			HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			return mav;
		}

		MetadataCondition cond = formUtil.createSqlCondition(form,
				auth.getName());
		int pageNo = form.getPageNo();
		int pageSz = (form.getPageSz() <= 0 ? defaultPageSize : form
				.getPageSz());

		Result result = metadataService.search(cond, pageNo, pageSz);

		ModelAndView mav = new ModelAndView(VIEW_PATH);
		mav.addObject(result);
		return mav;
	}

}
