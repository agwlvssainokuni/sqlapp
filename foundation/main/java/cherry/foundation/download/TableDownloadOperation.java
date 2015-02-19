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

package cherry.foundation.download;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDateTime;

import cherry.foundation.querydsl.QueryConfigurer;

import com.mysema.query.types.Expression;

public interface TableDownloadOperation {

	void downloadCsv(HttpServletResponse response, Charset charset, String filename, LocalDateTime timestamp,
			List<String> header, QueryConfigurer commonClause, QueryConfigurer orderByClause,
			Expression<?>... expressions) throws IOException;

	void downloadXls(HttpServletResponse response, String filename, LocalDateTime timestamp, List<String> header,
			QueryConfigurer commonClause, QueryConfigurer orderByClause, Expression<?>... expressions)
			throws IOException;

	void downloadXlsx(HttpServletResponse response, String filename, LocalDateTime timestamp, List<String> header,
			QueryConfigurer commonClause, QueryConfigurer orderByClause, Expression<?>... expressions)
			throws IOException;

}
