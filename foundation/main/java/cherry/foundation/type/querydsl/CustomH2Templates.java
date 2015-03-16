/*
 * Copyright 2015 agwlvssainokuni
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

package cherry.foundation.type.querydsl;

import com.mysema.query.sql.H2Templates;
import com.mysema.query.sql.types.Type;

public class CustomH2Templates extends H2Templates {

	public CustomH2Templates() {
		this('\\', false);
	}

	public CustomH2Templates(boolean quote) {
		this('\\', quote);
	}

	public CustomH2Templates(char escape, boolean quote) {
		super(escape, quote);
	}

	public void setCustomTypes(Type<?>... types) {
		for (Type<?> type : types) {
			super.addCustomType(type);
		}
	}

}
