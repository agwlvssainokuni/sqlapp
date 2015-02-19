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

package cherry.foundation.async;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

public class AsyncProcessFacadeImplTest {

	private AsyncFileProcessHandler asyncFileProcessHandler = mock(AsyncFileProcessHandler.class);
	private AsyncCommandHandler asyncCommandHandler = mock(AsyncCommandHandler.class);

	@Test
	public void testLaunchFileProcess() {
		AsyncProcessFacadeImpl impl = createImpl();
		MultipartFile file = mock(MultipartFile.class);
		when(asyncFileProcessHandler.launchFileProcess("a", "b", file, "c", "d", "e")).thenReturn(10L);
		long asyncId = impl.launchFileProcess("a", "b", file, "c", "d", "e");
		assertEquals(10L, asyncId);
		verify(asyncFileProcessHandler).launchFileProcess("a", "b", file, "c", "d", "e");
	}

	@Test
	public void testLaunchCommand() {
		AsyncProcessFacadeImpl impl = createImpl();
		when(asyncCommandHandler.launchCommand("a", "b", "c", "d", "e")).thenReturn(10L);
		long asyncId = impl.launchCommand("a", "b", "c", "d", "e");
		assertEquals(10L, asyncId);
		verify(asyncCommandHandler).launchCommand("a", "b", "c", "d", "e");
	}

	private AsyncProcessFacadeImpl createImpl() {
		AsyncProcessFacadeImpl impl = new AsyncProcessFacadeImpl();
		impl.setAsyncFileProcessHandler(asyncFileProcessHandler);
		impl.setAsyncCommandHandler(asyncCommandHandler);
		return impl;
	}

}
