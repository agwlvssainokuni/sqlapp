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

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.NullProgressCallback;

public class MyBatisPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		final MyBatisExtension ext = project.getExtensions().create("mybatis",
				MyBatisExtension.class);
		project.task("mybatisgen").doLast(new Closure<Void>(this) {
			@Override
			public Void call() {
				try {
					List<String> warnings = new ArrayList<String>();
					ConfigurationParser cp = new ConfigurationParser(warnings);
					Configuration config = cp.parseConfiguration(ext
							.getConfigFile());
					DefaultShellCallback callback = new DefaultShellCallback(
							ext.isOverwrite());
					MyBatisGenerator myBatisGenerator = new MyBatisGenerator(
							config, callback, warnings);
					myBatisGenerator.generate(new NullProgressCallback() {
						@Override
						public void startTask(String taskName) {
							if (ext.isVerbose()) {
								System.out.println(taskName);
							}
						}
					});
					return null;
				} catch (XMLParserException | InvalidConfigurationException
						| InterruptedException | SQLException | IOException ex) {
					throw new IllegalArgumentException(ex);
				}
			}
		});
	}
}
