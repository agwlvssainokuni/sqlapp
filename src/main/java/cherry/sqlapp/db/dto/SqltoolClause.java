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

package cherry.sqlapp.db.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.joda.time.LocalDateTime;

import cherry.spring.fwcore.type.DeletedFlag;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
public class SqltoolClause implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String databaseName;

	private String selectClause;

	private String fromClause;

	private String whereClause;

	private String groupByClause;

	private String havingClause;

	private String orderByClause;

	private String paramMap;

	private LocalDateTime updatedAt;

	private LocalDateTime createdAt;

	private Integer lockVersion;

	private DeletedFlag deletedFlg;

}
