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

package cherry.spring.common.foundation.impl;

import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;
import org.springframework.data.jdbc.query.SqlDeleteCallback;
import org.springframework.data.jdbc.query.SqlInsertCallback;
import org.springframework.data.jdbc.query.SqlInsertWithKeyCallback;
import org.springframework.data.jdbc.query.SqlUpdateCallback;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.mail.SimpleMailMessage;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.foundation.mail.MessageStore;
import cherry.foundation.type.DeletedFlag;
import cherry.foundation.type.FlagCode;
import cherry.sqlapp.db.gen.query.QMailLog;
import cherry.sqlapp.db.gen.query.QMailQueue;
import cherry.sqlapp.db.gen.query.QMailRcpt;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.QTuple;

public class MessageStoreImpl implements MessageStore {

	@Autowired
	private BizDateTime bizDateTime;

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	@Override
	public long createMessage(String launcherId, String messageName,
			LocalDateTime scheduledAt, String from, List<String> to,
			List<String> cc, List<String> bcc, String subject, String body) {
		long mailId = createMailLog(launcherId, bizDateTime.now(), messageName,
				scheduledAt, from, subject, body);
		createMailRcpt(mailId, RcptType.TO.name(), to);
		createMailRcpt(mailId, RcptType.CC.name(), cc);
		createMailRcpt(mailId, RcptType.BCC.name(), bcc);
		createMailQueue(mailId, scheduledAt);
		return mailId;
	}

	@Override
	public List<Long> listMessage(LocalDateTime dtm) {
		QMailQueue a = new QMailQueue("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.where(a.scheduledAt.goe(dtm));
		query.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		return queryDslJdbcOperations.query(query,
				new SingleColumnRowMapper<Long>(Long.class), a.mailId);
	}

	@Override
	public SimpleMailMessage getMessage(long messageId) {

		QMailLog a = new QMailLog("a");
		SQLQuery querya = queryDslJdbcOperations.newSqlQuery();
		querya.from(a).forUpdate();
		querya.where(a.id.eq(messageId));
		querya.where(a.mailStatus.eq(FlagCode.FALSE.code()));
		querya.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		Tuple maillog = queryDslJdbcOperations.queryForObject(querya,
				new QTuple(a.fromAddr, a.subject, a.body));
		if (maillog == null) {
			return null;
		}

		QMailRcpt b = new QMailRcpt("b");
		SQLQuery queryb = queryDslJdbcOperations.newSqlQuery();
		queryb.from(b).where(b.mailId.eq(messageId)).orderBy(b.id.asc());
		List<Tuple> mailrcpt = queryDslJdbcOperations.query(queryb, new QTuple(
				b.rcptType, b.rcptAddr));
		if (mailrcpt.isEmpty()) {
			return null;
		}

		List<String> to = new ArrayList<>();
		List<String> cc = new ArrayList<>();
		List<String> bcc = new ArrayList<>();
		for (Tuple rcpt : mailrcpt) {
			RcptType type = RcptType.valueOf(rcpt.get(b.rcptType));
			if (type == RcptType.TO) {
				to.add(rcpt.get(b.rcptAddr));
			}
			if (type == RcptType.CC) {
				cc.add(rcpt.get(b.rcptAddr));
			}
			if (type == RcptType.BCC) {
				bcc.add(rcpt.get(b.rcptAddr));
			}
		}

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(to.toArray(new String[to.size()]));
		msg.setCc(cc.toArray(new String[cc.size()]));
		msg.setBcc(bcc.toArray(new String[bcc.size()]));
		msg.setFrom(maillog.get(a.fromAddr));
		msg.setSubject(maillog.get(a.subject));
		msg.setText(maillog.get(a.body));
		return msg;
	}

	@Override
	public void finishMessage(final long messageId) {

		final QMailLog a = new QMailLog("a");
		long count = queryDslJdbcOperations.update(a, new SqlUpdateCallback() {
			@Override
			public long doInSqlUpdateClause(SQLUpdateClause update) {
				update.set(a.mailStatus, FlagCode.TRUE.code());
				update.set(a.lockVersion, a.lockVersion.add(1));
				update.where(a.id.eq(messageId));
				update.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
				return update.execute();
			}
		});
		checkState(count == 1L,
				"failed to update QMailLog: id={0}, mailStatus={1}", messageId,
				FlagCode.TRUE.code());

		final QMailQueue b = new QMailQueue("b");
		long c = queryDslJdbcOperations.delete(b, new SqlDeleteCallback() {
			@Override
			public long doInSqlDeleteClause(SQLDeleteClause delete) {
				delete.where(b.mailId.eq(messageId));
				delete.where(b.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
				return delete.execute();
			}
		});
		checkState(c == 1L, "failed to delete QMailQueue: mailId={0}",
				messageId);
	}

	private long createMailLog(final String launcherId,
			final LocalDateTime launchedAt, final String messageName,
			final LocalDateTime scheduledAt, final String from,
			final String subject, final String body) {
		final QMailLog a = new QMailLog("a");
		SqlInsertWithKeyCallback<Long> callback = new SqlInsertWithKeyCallback<Long>() {
			@Override
			public Long doInSqlInsertWithKeyClause(SQLInsertClause insert) {
				insert.set(a.launchedBy, launcherId);
				insert.set(a.launchedAt, launchedAt);
				insert.set(a.mailStatus, FlagCode.FALSE.code());
				insert.set(a.messageName, messageName);
				insert.set(a.scheduledAt, scheduledAt);
				insert.set(a.fromAddr, from);
				insert.set(a.subject, subject);
				insert.set(a.body, body);
				return insert.executeWithKey(Long.class);
			}
		};
		Long id = queryDslJdbcOperations.insertWithKey(a, callback);
		checkState(
				id != null,
				"failed to create QMailLog: launchedBy={0}, launchedAt={1}, mailStatus={2}, messageName={3}, scheduledAt={4}, fromAddr={5}, subject={6}, body={7}",
				launcherId, launchedAt, FlagCode.FALSE.code(), messageName,
				scheduledAt, from, subject, body);
		return id.longValue();
	}

	private void createMailRcpt(final long mailId, final String rcptType,
			List<String> rcptAddr) {
		if (rcptAddr == null || rcptAddr.isEmpty()) {
			return;
		}
		final QMailRcpt a = new QMailRcpt("a");
		for (final String addr : rcptAddr) {
			long c = queryDslJdbcOperations.insert(a, new SqlInsertCallback() {
				@Override
				public long doInSqlInsertClause(SQLInsertClause insert) {
					insert.set(a.mailId, mailId);
					insert.set(a.rcptType, rcptType);
					insert.set(a.rcptAddr, addr);
					return insert.execute();
				}
			});
			checkState(
					c == 1L,
					"failed to create QMailRcpt: mailId={0}, rcptType={1}, rcptAddr={2}",
					mailId, rcptType, addr);
		}
	}

	private void createMailQueue(final long mailId,
			final LocalDateTime scheduledAt) {
		final QMailQueue a = new QMailQueue("a");
		long count = queryDslJdbcOperations.insert(a, new SqlInsertCallback() {
			@Override
			public long doInSqlInsertClause(SQLInsertClause insert) {
				insert.set(a.mailId, mailId);
				insert.set(a.scheduledAt, scheduledAt);
				return insert.execute();
			}
		});
		checkState(count == 1L,
				"failed to create QMailQueue: mailId={0}, scheduledAt={1}",
				mailId, scheduledAt);
	}

}
