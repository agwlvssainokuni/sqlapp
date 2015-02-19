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

package cherry.goods.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class CommandLauncherTest {

	@Test
	public void testJavaVersionStdout() {
		CommandLauncherImpl launcher = new CommandLauncherImpl();
		launcher.setCharset(StandardCharsets.UTF_8);
		launcher.setRedirectErrorStream(true);
		try {
			CommandResult result = launcher.launch("java", "-version");
			assertEquals(0, result.getExitValue());
			assertTrue(result.getStdout().startsWith("java version"));
			assertEquals("", result.getStderr());
			assertTrue(result.toString().startsWith("CommandResult[exitValue=0,"));
		} catch (IOException | InterruptedException ex) {
			fail("Exception must not be thrown");
		}
	}

	@Test
	public void testJavaVersionStderr() {
		CommandLauncherImpl launcher = new CommandLauncherImpl();
		launcher.setCharset(StandardCharsets.UTF_8);
		launcher.setRedirectErrorStream(false);
		try {
			CommandResult result = launcher.launch("java", "-version");
			assertEquals(0, result.getExitValue());
			assertTrue(result.getStderr().startsWith("java version"));
			assertEquals("", result.getStdout());
			assertTrue(result.toString().startsWith("CommandResult[exitValue=0,"));
		} catch (IOException | InterruptedException ex) {
			fail("Exception must not be thrown");
		}
	}

	@Test
	public void testInvalidCommand() {
		CommandLauncherImpl launcher = new CommandLauncherImpl();
		try {
			launcher.launch("notexist");
			fail("Exception must be thrown");
		} catch (IOException | InterruptedException ex) {
			// OK
			assertTrue(ex instanceof IOException);
		}
	}

}
