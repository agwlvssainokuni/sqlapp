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

package cherry.sqlapp.controller.secure.exec;

import static org.springframework.web.util.UriComponentsBuilder.fromPath;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;

import cherry.sqlapp.db.gen.dto.SqlCsv;
import cherry.sqlapp.db.gen.dto.SqlMetadata;
import cherry.sqlapp.service.secure.exec.CsvService;
import cherry.sqlapp.service.secure.exec.ImpService;
import cherry.sqlapp.service.secure.exec.MetadataService;

@Component
public class ExecCsvControllerImpl implements ExecCsvController {

	public static final String VIEW_PATH = "secure/exec/csv/index";

	public static final String VIEW_PATH_FIN = "secure/exec/csv/finish";

	public static final String VIEW_PATH_ID = "secure/exec/csv/indexId";

	public static final String VIEW_PATH_ID_FIN = "secure/exec/csv/finishId";

	public static final String ASYNC_PARAM = "asyncParam";

	@Autowired
	private DataSourceDef dataSourceDef;

	@Autowired
	private ImpService impService;

	@Autowired
	private MetadataService metadataService;

	@Autowired
	private CsvService csvService;

	@Override
	public ExecMetadataForm getMetadata() {
		return new ExecMetadataForm();
	}

	@Override
	public ExecCsvForm getForm() {
		ExecCsvForm form = new ExecCsvForm();
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
				SqlCsv record = csvService.findById(ref);
				if (record != null) {
					mav.addObject(getForm(record));
				}
			}
		}
		return mav;
	}

	@Override
	public ModelAndView request(ExecCsvForm form, BindingResult binding,
			RedirectAttributes redirectAttributes,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(dataSourceDef);
			return mav;
		}

		Map<String, String> asyncParam = impService.launch(
				form.getDatabaseName(), form.getSql(), form.getFile(),
				authentication.getName());
		redirectAttributes.addFlashAttribute(ASYNC_PARAM, asyncParam);

		UriComponents uri = fromPath(URI_PATH).pathSegment(URI_PATH_FIN)
				.build();
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uri.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView finish(RedirectAttributes redirectAttributes,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH_FIN);
		mav.addObject(dataSourceDef);
		mav.addAllObjects(redirectAttributes.getFlashAttributes());
		return mav;
	}

	@Override
	public ModelAndView create(ExecCsvForm form, BindingResult binding,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(dataSourceDef);
			return mav;
		}

		SqlCsv record = new SqlCsv();
		record.setDatabaseName(form.getDatabaseName());
		record.setQuery(form.getSql());

		int id = csvService.create(record, authentication.getName());

		UriComponents uri = fromPath(URI_PATH).pathSegment(URI_PATH_ID)
				.buildAndExpand(id);
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uri.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView indexId(int id, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		SqlMetadata md = metadataService.findById(id, authentication.getName());
		ExecMetadataForm mdForm = getMdForm(md);

		SqlCsv record = csvService.findById(id);
		ExecCsvForm form = getForm(record);

		ModelAndView mav = new ModelAndView(VIEW_PATH_ID);
		mav.addObject(PATH_VAR, id);
		mav.addObject(dataSourceDef);
		mav.addObject(mdForm);
		mav.addObject(form);
		return mav;
	}

	@Override
	public ModelAndView requestId(int id, ExecCsvForm form,
			BindingResult binding, RedirectAttributes redirectAttributes,
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

		Map<String, String> asyncParam = impService.launch(
				form.getDatabaseName(), form.getSql(), form.getFile(),
				authentication.getName());
		redirectAttributes.addFlashAttribute(ASYNC_PARAM, asyncParam);

		UriComponents uri = fromPath(URI_PATH).pathSegment(URI_PATH_ID_FIN)
				.buildAndExpand(id);
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uri.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView finishId(@PathVariable(PATH_VAR) int id,
			RedirectAttributes redirectAttributes,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH_ID_FIN);
		mav.addObject(PATH_VAR, id);
		mav.addObject(dataSourceDef);
		mav.addAllObjects(redirectAttributes.getFlashAttributes());
		return mav;
	}

	@Override
	public ModelAndView update(int id, ExecCsvForm form, BindingResult binding,
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

		SqlCsv record = new SqlCsv();
		record.setId(id);
		record.setDatabaseName(form.getDatabaseName());
		record.setQuery(form.getSql());

		csvService.update(record);

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

		SqlCsv record = csvService.findById(id);
		ExecCsvForm form = getForm(record);

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

	private ExecCsvForm getForm(SqlCsv record) {
		ExecCsvForm form = getForm();
		form.setDatabaseName(record.getDatabaseName());
		form.setSql(record.getQuery());
		return form;
	}

}
