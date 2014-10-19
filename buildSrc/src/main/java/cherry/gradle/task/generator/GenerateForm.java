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

package cherry.gradle.task.generator;

import static java.lang.System.out;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import lombok.Setter;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import cherry.parser.worksheet.SheetBasedParser;
import cherry.parser.worksheet.TypeDef;
import cherry.parser.worksheet.WorkbookParser;

@Setter
public class GenerateForm extends DefaultTask {

	private boolean verbose = false;

	private List<File> definitionFiles = new ArrayList<>();

	private String velocityConfig = "velocity.properties";

	private String templateJava = "form/java.vm";

	private String templateJavaBase = "form/javabase.vm";

	private String templateProperties = "form/properties.vm";

	private String templateEncoding = "UTF-8";

	private File javaBaseDir = new File("src/generated/java");

	private String javaEncoding = "UTF-8";

	private File propertiesFile = new File(
			"src/generated/resources/form.properties");

	private String propertiesEncoding = "UTF-8";

	@TaskAction
	public void generate() throws IOException {

		message("Starting to generate form files.");

		List<TypeDef> list = new LinkedList<>();
		for (File file : definitionFiles) {
			message("Parsing a file {0}", file);
			list.addAll(parseDef(file));
		}

		VelocityEngine engine = new VelocityEngine();
		try (InputStream in = new FileInputStream(velocityConfig)) {
			Properties props = new Properties();
			props.load(in);
			engine.init(props);
		}

		message("Loading templates.");
		Template javaTempl = engine.getTemplate(templateJava, templateEncoding);
		Template javaBaseTempl = engine.getTemplate(templateJavaBase,
				templateEncoding);
		Template propTempl = engine.getTemplate(templateProperties,
				templateEncoding);

		message("Preparing destination directories.");

		propertiesFile.getParentFile().mkdirs();
		javaBaseDir.mkdirs();

		message("Generating {0}", propertiesFile);

		try (OutputStream pout = new FileOutputStream(propertiesFile);
				Writer pwriter = new OutputStreamWriter(pout,
						propertiesEncoding)) {
			for (TypeDef typeDef : list) {
				VelocityContext context = new VelocityContext();
				context.put("typeDef", typeDef);

				propTempl.merge(context, pwriter);
				pwriter.flush();
			}
		}

		message("{0}", list);

		message("Generating forms.");

		for (TypeDef typeDef : list) {
			VelocityContext context = new VelocityContext();
			context.put("typeDef", typeDef);

			message("{0}", typeDef.getFullyQualifiedClassName());

			File pkgDir = new File(javaBaseDir, typeDef.getDirName());
			pkgDir.mkdirs();

			File javaFile = new File(pkgDir, typeDef.getClassName() + ".java");
			File javaBaseFile = new File(pkgDir, typeDef.getClassName()
					+ "Base.java");

			try (OutputStream jout = new FileOutputStream(javaFile);
					Writer jwriter = new OutputStreamWriter(jout, javaEncoding)) {
				javaTempl.merge(context, jwriter);
				jwriter.flush();
			}

			try (OutputStream jout = new FileOutputStream(javaBaseFile);
					Writer jwriter = new OutputStreamWriter(jout, javaEncoding)) {
				javaBaseTempl.merge(context, jwriter);
				jwriter.flush();
			}
		}

		message("Completed.");
	}

	private void message(String msg, Object... args) {
		if (verbose) {
			out.println(MessageFormat.format(msg, args));
		}
	}

	private List<TypeDef> parseDef(File file) throws IOException {
		WorkbookParser parser = new SheetBasedParser();
		List<TypeDef> list = new LinkedList<>();
		try (InputStream in = new FileInputStream(file)) {
			Workbook workbook = WorkbookFactory.create(in);
			for (TypeDef typeDef : parser.parse(workbook)) {
				if (typeDef.getFullyQualifiedClassName() != null) {
					list.add(typeDef);
				}
			}
		} catch (InvalidFormatException ex) {
			throw new IllegalArgumentException(ex);
		}
		return list;
	}

}
