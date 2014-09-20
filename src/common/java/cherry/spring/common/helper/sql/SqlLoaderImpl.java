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

package cherry.spring.common.helper.sql;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import cherry.spring.common.lib.sql.SimpleSqlParser;

public class SqlLoaderImpl implements SqlLoader {

	@Value("${common.helper.sqlloader.charset}")
	private Charset charset;

	@Value("${common.helper.sqlloader.extension}")
	private String extension;

	@Value("${common.helper.sqlloader.namePattern}")
	private Pattern namePattern;

	@Autowired
	private SimpleSqlParser simpleSqlParser;

	@Override
	public Map<String, String> load(Class<?> klass) throws IOException {
		String name = klass.getSimpleName() + extension;
		try (InputStream in = klass.getResourceAsStream(name)) {
			return load(in);
		}
	}

	@Override
	public Map<String, String> load(Resource resource) throws IOException {
		try (InputStream in = resource.getInputStream()) {
			return load(in);
		}
	}

	@Override
	public Map<String, String> load(InputStream in) throws IOException {
		try (Reader reader = new InputStreamReader(in, charset)) {
			return load(reader);
		}
	}

	@Override
	public Map<String, String> load(Reader reader) throws IOException {

		Map<String, String> sqlmap = new LinkedHashMap<>();

		String name;
		while ((name = nextName(reader)) != null) {
			String statement = nextStatement(reader);
			if (statement == null) {
				break;
			}
			sqlmap.put(name, statement);
		}

		return sqlmap;
	}

	private String nextName(Reader reader) throws IOException {
		String comment;
		while ((comment = simpleSqlParser.nextComment(reader)) != null) {
			Matcher matcher = namePattern.matcher(comment.trim());
			if (matcher.find()) {
				return matcher.group(1);
			}
		}
		return null;
	}

	private String nextStatement(Reader reader) throws IOException {
		String statement;
		while ((statement = simpleSqlParser.nextStatement(reader)) != null) {
			String stmt = statement.trim();
			if (stmt.length() > 0) {
				return stmt;
			}
		}
		return null;
	}

}
