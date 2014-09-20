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
import java.io.Reader;
import java.util.Map;

import org.springframework.core.io.Resource;

public interface SqlLoader {

	Map<String, String> load(Class<?> klass) throws IOException;

	Map<String, String> load(Resource resource) throws IOException;

	Map<String, String> load(InputStream in) throws IOException;

	Map<String, String> load(Reader reader) throws IOException;

}