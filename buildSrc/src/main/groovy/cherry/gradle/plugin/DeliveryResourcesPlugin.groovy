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

package cherry.gradle.plugin

import org.apache.tools.ant.filters.ReplaceTokens
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy

class DeliveryResourcesPlugin implements Plugin<Project> {
	void apply(Project project) {

		def props = new Properties()
		project.extensions.create("deliveryResources", DeliveryResourcesPluginExtension, project, props)

		project.task("processDeliveryResources", type: Copy, overwrite: true) {
			from "src/main/deliveryResources"
			into project.sourceSets.main.output.resourcesDir
			filter(ReplaceTokens, tokens: props)
			dependsOn project.tasks.processResources
		}

		project.tasks.classes.mustRunAfter project.tasks.processDeliveryResources

		project.task("assembleDelivery") {
			dependsOn project.tasks.processDeliveryResources
			dependsOn project.tasks.assemble
		}
	}
}

class DeliveryResourcesPluginExtension {
	Project project
	Properties props
	DeliveryResourcesPluginExtension(Project project, Properties props) {
		this.project = project
		this.props = props
	}
	void from(String d) {
		project.tasks.processDeliveryResources.from(d)
	}
	void tokens(String f) {
		tokens { p ->
			project.file(f).withInputStream { p.load(it) }
		}
	}
	void tokens(Closure<?> c) {
		c.delegate = props
		c.call(props)
	}
}
