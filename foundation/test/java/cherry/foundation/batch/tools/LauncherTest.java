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

package cherry.foundation.batch.tools;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cherry.foundation.batch.ExitStatus;

public class LauncherTest {

	@Before
	public void before() {
		System.setProperty("batch.appCtx", "classpath:config/applicationContext-test.xml");
	}

	@Test
	public void testExitWithNormal() {
		Launcher launcher = new Launcher("test01Batch");
		ExitStatus status = launcher.launch("NORMAL");
		assertEquals(ExitStatus.NORMAL, status);
	}

	@Test
	public void testExitWithWarn() {
		Launcher launcher = new Launcher("test01Batch");
		ExitStatus status = launcher.launch("WARN");
		assertEquals(ExitStatus.WARN, status);
	}

	@Test
	public void testExitWithError() {
		Launcher launcher = new Launcher("test01Batch");
		ExitStatus status = launcher.launch("ERROR");
		assertEquals(ExitStatus.ERROR, status);
	}

	@Test
	public void testExitWithFatal() {
		Launcher launcher = new Launcher("test01Batch");
		ExitStatus status = launcher.launch("FATAL");
		assertEquals(ExitStatus.FATAL, status);
	}

	@Test
	public void testExitWithException() {
		Launcher launcher = new Launcher("test01Batch");
		ExitStatus status = launcher.launch("NONE");
		assertEquals(ExitStatus.FATAL, status);
	}

}
