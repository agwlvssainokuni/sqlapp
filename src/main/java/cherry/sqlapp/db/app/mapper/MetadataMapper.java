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

package cherry.sqlapp.db.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cherry.sqlapp.db.gen.dto.SqlMetadata;

public interface MetadataMapper {

	int createSelect(SqlMetadata record);

	int createAny(SqlMetadata record);

	int createCsv(SqlMetadata record);

	int update(SqlMetadata record);

	int count(@Param("cond") SqlCondition cond);

	List<SqlMetadata> search(@Param("cond") SqlCondition cond, int limit,
			int offset);

}
