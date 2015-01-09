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

package cherry.foundation.etl;

import java.io.IOException;

public abstract class DelegateConsumer implements Consumer {

	private final Consumer delegate;

	protected DelegateConsumer(Consumer delegate) {
		this.delegate = delegate;
	}

	@Override
	public void begin(Column[] col) throws IOException {
		delegate.begin(prepareBegin(col));
	}

	@Override
	public void consume(Object[] record) throws IOException {
		delegate.consume(prepareConsume(record));
	}

	@Override
	public void end() throws IOException {
		prepareEnd();
		delegate.end();
	}

	protected abstract Column[] prepareBegin(Column[] col) throws IOException;

	protected abstract Object[] prepareConsume(Object[] record) throws IOException;

	protected abstract void prepareEnd() throws IOException;

}
