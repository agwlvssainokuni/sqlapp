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

package cherry.spring.common.helper.async;

import static com.google.common.base.Preconditions.checkState;
import static com.mysema.query.types.expr.DateTimeExpression.currentTimestamp;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;
import org.springframework.data.jdbc.query.SqlInsertCallback;
import org.springframework.data.jdbc.query.SqlUpdateCallback;
import org.springframework.transaction.annotation.Transactional;

import cherry.goods.command.CommandResult;
import cherry.spring.fwcore.async.AsyncStatus;
import cherry.spring.fwcore.async.AsyncStatusStore;
import cherry.spring.fwcore.async.FileProcessResult;
import cherry.spring.fwcore.type.DeletedFlag;
import cherry.sqlapp.db.gen.query.QAsyncProc;

import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;

public class AsyncStatusStoreImpl implements AsyncStatusStore {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	@Transactional(value = "jtaTransactionManager", propagation = REQUIRES_NEW)
	@Override
	public long createFileProcess(final String launcherId,
			final LocalDateTime dtm, String name, String originalFilename,
			String contentType, long size, final String handlerName) {
		final QAsyncProc a = new QAsyncProc("a");
		return queryDslJdbcOperations.insert(a, new SqlInsertCallback() {
			@Override
			public long doInSqlInsertClause(SQLInsertClause insert) {
				insert.set(a.name, handlerName);
				insert.set(a.launcherId, launcherId);
				insert.set(a.status, "PREPARING");
				insert.set(a.registeredAt, dtm);
				Long id = insert.executeWithKey(Long.class);
				checkState(
						id != null,
						"failed to create async_proc: name={0}, launcherId={1}, registeredAt={2}",
						handlerName, launcherId, dtm);
				return id.longValue();
			}
		});
	}

	@Transactional(value = "jtaTransactionManager", propagation = REQUIRES_NEW)
	public long createCommand(final String launcherId, final LocalDateTime dtm,
			final String... command) {
		final QAsyncProc a = new QAsyncProc("a");
		return queryDslJdbcOperations.insert(a, new SqlInsertCallback() {
			@Override
			public long doInSqlInsertClause(SQLInsertClause insert) {
				insert.set(a.name, command[0]);
				insert.set(a.launcherId, launcherId);
				insert.set(a.status, "PREPARING");
				insert.set(a.registeredAt, dtm);
				Long id = insert.executeWithKey(Long.class);
				checkState(
						id != null,
						"failed to create async_proc: name={0}, launcherId={1}, registeredAt={2}",
						command[0], launcherId, dtm);
				return id.longValue();
			}
		});
	}

	@Transactional("jtaTransactionManager")
	public void updateToLaunched(final long asyncId, final LocalDateTime dtm) {
		final QAsyncProc a = new QAsyncProc("a");
		long count = queryDslJdbcOperations.update(a, new SqlUpdateCallback() {
			@Override
			public long doInSqlUpdateClause(SQLUpdateClause update) {
				update.set(a.status, "INVOKED");
				update.set(a.invokedAt, dtm);
				update.set(a.updatedAt, currentTimestamp(LocalDateTime.class));
				update.set(a.lockVersion, a.lockVersion.add(1));
				update.where(a.id.eq((int) asyncId));
				update.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
				return update.execute();
			}
		});
		checkState(
				count == 1,
				"failed to update async_proc: id={0}, invokedAt={1}, count={2}",
				asyncId, dtm, count);
	}

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public void updateToProcessing(final long asyncId, final LocalDateTime dtm) {
		final QAsyncProc a = new QAsyncProc("a");
		long count = queryDslJdbcOperations.update(a, new SqlUpdateCallback() {
			@Override
			public long doInSqlUpdateClause(SQLUpdateClause update) {
				update.set(a.status, "PROCESSING");
				update.set(a.startedAt, dtm);
				update.set(a.updatedAt, currentTimestamp(LocalDateTime.class));
				update.set(a.lockVersion, a.lockVersion.add(1));
				update.where(a.id.eq((int) asyncId));
				update.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
				return update.execute();
			}
		});
		checkState(
				count == 1,
				"failed to update async_proc: id={0}, startedAt={1}, count={2}",
				asyncId, dtm, count);
	}

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public void finishFileProcess(final long asyncId, final LocalDateTime dtm,
			AsyncStatus status, final FileProcessResult result) {
		final QAsyncProc a = new QAsyncProc("a");
		long count = queryDslJdbcOperations.update(a, new SqlUpdateCallback() {
			@Override
			public long doInSqlUpdateClause(SQLUpdateClause update) {
				update.set(a.status, "SUCCESS");
				update.set(a.finishedAt, dtm);
				update.set(a.result, result.toString());
				update.set(a.updatedAt, currentTimestamp(LocalDateTime.class));
				update.set(a.lockVersion, a.lockVersion.add(1));
				update.where(a.id.eq((int) asyncId));
				update.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
				return update.execute();
			}
		});
		checkState(
				count == 1,
				"failed to update async_proc: id={0}, finishedAt={1}, result={2}, count={3}",
				asyncId, dtm, result.toString(), count);
	}

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public void finishCommand(final long asyncId, final LocalDateTime dtm,
			AsyncStatus status, final CommandResult result) {
		final QAsyncProc a = new QAsyncProc("a");
		long count = queryDslJdbcOperations.update(a, new SqlUpdateCallback() {
			@Override
			public long doInSqlUpdateClause(SQLUpdateClause update) {
				update.set(a.status, "SUCCESS");
				update.set(a.finishedAt, dtm);
				update.set(a.result, result.toString());
				update.set(a.updatedAt, currentTimestamp(LocalDateTime.class));
				update.set(a.lockVersion, a.lockVersion.add(1));
				update.where(a.id.eq((int) asyncId));
				update.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
				return update.execute();
			}
		});
		checkState(
				count == 1,
				"failed to update async_proc: id={0}, finishedAt={1}, result={2}, count={3}",
				asyncId, dtm, result.toString(), count);
	}

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public void finishWithException(final long asyncId,
			final LocalDateTime dtm, final Throwable th) {
		final QAsyncProc a = new QAsyncProc("a");
		long count = queryDslJdbcOperations.update(a, new SqlUpdateCallback() {
			@Override
			public long doInSqlUpdateClause(SQLUpdateClause update) {
				update.set(a.status, "ERROR");
				update.set(a.finishedAt, dtm);
				update.set(a.result, th.getMessage());
				update.set(a.updatedAt, currentTimestamp(LocalDateTime.class));
				update.set(a.lockVersion, a.lockVersion.add(1));
				update.where(a.id.eq((int) asyncId));
				update.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
				return update.execute();
			}
		});
		checkState(
				count == 1,
				"failed to update async_proc: id={0}, finishedAt={1}, result={2}, count={3}",
				asyncId, dtm, th.getMessage(), count);
	}

}
