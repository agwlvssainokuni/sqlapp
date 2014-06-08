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

package cherry.sqlapp.service.secure.exec;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.common.lib.paginate.PageSet;
import cherry.spring.common.lib.paginate.Paginator;
import cherry.sqlapp.controller.secure.exec.ExecSearchForm;
import cherry.sqlapp.db.app.mapper.MetadataMapper;
import cherry.sqlapp.db.app.mapper.SqlCondition;
import cherry.sqlapp.db.gen.dto.SqlMetadata;
import cherry.sqlapp.db.gen.mapper.SqlMetadataMapper;

@Component
public class MetadataServiceImpl implements MetadataService {

	@Autowired
	private SqlMetadataMapper sqlMetadataMapper;

	@Autowired
	private MetadataMapper metadataMapper;

	@Autowired
	private Paginator paginator;

	@Transactional
	@Override
	public SqlMetadata findById(int id, String loginId) {
		SqlMetadata record = sqlMetadataMapper.selectByPrimaryKey(id);
		if (0 != record.getDeletedFlg()) {
			return null;
		}
		if (0 != record.getPublishedFlg()) {
			return record;
		}
		if (loginId.equals(record.getOwnedBy())) {
			return record;
		}
		return null;
	}

	@Transactional
	@Override
	public void update(SqlMetadata record) {
		int count = metadataMapper.update(record);
		if (count != 1) {
			throw new IllegalArgumentException(
					"sql_metadata is not updated; count=" + count);
		}
	}

	@Transactional
	@Override
	public Result search(ExecSearchForm form, String loginId, int pageNo,
			int pageSize) {

		SqlCondition cond = createSqlCondition(form, loginId);
		int itemCount = metadataMapper.count(cond);
		PageSet pageSet = paginator.paginate(pageNo, itemCount, pageSize);
		int offset = pageSet.getCurrent().getFrom();
		List<SqlMetadata> list = metadataMapper.search(cond, pageSize, offset);

		Result result = new Result();
		result.setPageSet(pageSet);
		result.setMetadataList(list);
		return result;
	}

	private SqlCondition createSqlCondition(ExecSearchForm form, String loginId) {
		SqlCondition cond = new SqlCondition();
		cond.setName(stringCond(form.getName()));
		cond.setSelect(form.isSelect());
		cond.setAny(form.isAny());
		cond.setCsv(form.isCsv());
		cond.setPublish(form.isPublish());
		cond.setNotPublish(form.isNotPublish());
		cond.setRegisteredFrom(dateFromCond(form.getRegisteredFrom()));
		cond.setRegisteredTo(dateToCond(form.getRegisteredTo()));
		cond.setLoginId(loginId);
		return cond;
	}

	private String stringCond(String string) {
		if (StringUtils.isBlank(string)) {
			return null;
		}
		return string.replaceAll("([%_\\\\])", "\\$1") + "%";
	}

	private LocalDateTime dateFromCond(LocalDateTime dt) {
		if (dt == null) {
			return null;
		}
		return dt;
	}

	private LocalDateTime dateToCond(LocalDateTime dt) {
		if (dt == null) {
			return null;
		}
		return dt.plusSeconds(1);
	}

}
