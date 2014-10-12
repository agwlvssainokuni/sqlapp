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

package cherry.gradle.plugin;

import groovy.lang.Closure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class QuerydslPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		final QuerydslExtension ext = project.getExtensions().create(
				"querydsl", QuerydslExtension.class);
		project.task("querydslgen").doLast(new Closure<Void>(this) {
			@Override
			public Void call() {
				ext.getMetaDataExporter().setConfiguration(
						ext.getConfiguration());
				try {
					Class.forName(ext.getDriver());
					try (Connection c = DriverManager.getConnection(
							ext.getUrl(), ext.getUser(), ext.getPassword())) {
						ext.getMetaDataExporter().export(c.getMetaData());
					}
					return null;
				} catch (ClassNotFoundException | SQLException ex) {
					throw new IllegalArgumentException(ex);
				}
			}
		});
	}

}
