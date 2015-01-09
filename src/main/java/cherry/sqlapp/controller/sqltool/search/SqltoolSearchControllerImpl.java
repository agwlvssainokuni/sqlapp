/*
 * Copyright 2014,2015 agwlvssainokuni
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
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.goods.paginate.PagedList;
import cherry.goods.util.LocalDateTimeUtil;
import cherry.sqlapp.controller.PathDef;
import cherry.sqlapp.db.dto.SqltoolMetadata;
import cherry.sqlapp.service.sqltool.metadata.MetadataCondition;
import cherry.sqlapp.service.sqltool.metadata.MetadataService;

@Controller
public class SqltoolSearchControllerImpl implements SqltoolSearchController {

	@Value("${sqlapp.app.search.defaultFromDays}")
	private Integer defaultFromDays;

	@Value("${sqlapp.app.paginator.pageSize}")
	private int defaultPageSize;

	@Autowired
	private MetadataService metadataService;

	@Autowired
	private BizDateTime bizDateTime;

	@Autowired
	private FormUtil formUtil;

	@Override
	public SqltoolSearchForm getForm() {
		LocalDate today = bizDateTime.today();
		SqltoolSearchForm form = new SqltoolSearchForm();

		LocalDateTime from = LocalDateTimeUtil.rangeFrom(today.minusDays(defaultFromDays));
		form.setRegisteredFromDt(from.toLocalDate());
		form.setRegisteredFromTm(from.toLocalTime());

		LocalDateTime to = LocalDateTimeUtil.rangeTo(today).minusSeconds(1);
		form.setRegisteredToDt(to.toLocalDate());
		form.setRegisteredToTm(to.toLocalTime());

		form.setSqlType(Arrays.asList(SqlType.CLAUSE, SqlType.STATEMENT, SqlType.LOAD));
		form.setPublished(Arrays.asList(Published.PUBLIC, Published.PRIVATE));
		return form;
	}

	@Override
	public ModelAndView init(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_SQLTOOL_SEARCH_INIT);
		mav.addObject(getForm());
		return mav;
	}

	@Override
	public ModelAndView execute(SqltoolSearchForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_SQLTOOL_SEARCH_INIT);
			return mav;
		}

		MetadataCondition cond = formUtil.createSqlCondition(form, auth.getName());
		long pageNo = form.getPageNo();
		long pageSz = (form.getPageSz() <= 0 ? defaultPageSize : form.getPageSz());

		PagedList<SqltoolMetadata> result = metadataService.search(cond, pageNo, pageSz);

		ModelAndView mav = new ModelAndView(PathDef.VIEW_SQLTOOL_SEARCH_INIT);
		mav.addObject(result);
		return mav;
	}

}
