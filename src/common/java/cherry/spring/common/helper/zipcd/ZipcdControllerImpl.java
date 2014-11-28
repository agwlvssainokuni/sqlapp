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

package cherry.spring.common.helper.zipcd;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import cherry.spring.common.api.ApiResponse;
import cherry.spring.common.api.ApiStatus;

@Controller
public class ZipcdControllerImpl implements ZipcdController {

	@Autowired
	private ZipcdHelper zipcdHelper;

	@Override
	public ApiResponse<List<ZipcdAddress>> execute(String zipcd,
			Authentication auth, Locale locale, SitePreference sitePref,
			HttpServletRequest request) {
		return executeImpl(zipcd);
	}

	@Override
	public ApiResponse<List<ZipcdAddress>> executeByPath(String zipcd,
			Authentication auth, Locale locale, SitePreference sitePref,
			HttpServletRequest request) {
		return executeImpl(zipcd);
	}

	private ApiResponse<List<ZipcdAddress>> executeImpl(String zipcd) {

		List<ZipcdAddress> result = zipcdHelper.search(zipcd);
		ApiStatus status = result.isEmpty() ? ApiStatus.WARN : ApiStatus.OK;

		ApiResponse<List<ZipcdAddress>> response = new ApiResponse<>();
		response.setStatus(status.getValue());
		response.setResult(result);
		return response;
	}

}
