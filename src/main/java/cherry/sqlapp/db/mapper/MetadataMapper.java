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

package cherry.sqlapp.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cherry.sqlapp.db.dto.SqltoolMetadata;

public interface MetadataMapper {

	int count(@Param("cond") MetadataCondition cond);

	List<SqltoolMetadata> search(@Param("cond") MetadataCondition cond,
			@Param("limit") int limit, @Param("offset") int offset);

}
