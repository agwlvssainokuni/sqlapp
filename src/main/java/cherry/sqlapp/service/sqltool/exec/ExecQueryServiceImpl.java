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

package cherry.sqlapp.service.sqltool.exec;

import java.io.IOException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionOperations;
import org.springframework.transaction.support.TransactionTemplate;

import cherry.goods.paginate.PageSet;
import cherry.goods.paginate.Paginator;
import cherry.spring.fwcore.etl.Consumer;
import cherry.spring.fwcore.etl.Extractor;
import cherry.spring.fwcore.etl.NoneLimiter;
import cherry.sqlapp.service.sqltool.DataSourceDef;

public class ExecQueryServiceImpl implements ExecQueryService {

	@Autowired
	private DataSourceDef dataSourceDef;

	@Autowired
	private Extractor extractor;

	@Autowired
	private Paginator paginator;

	@Override
	public PageSet query(String databaseName, final String sql,
			final Map<String, ?> paramMap, final Consumer consumer) {

		final DataSource dataSource = dataSourceDef.getDataSource(databaseName);
		PlatformTransactionManager txMgr = new DataSourceTransactionManager(
				dataSource);
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setReadOnly(true);

		TransactionOperations txOp = new TransactionTemplate(txMgr, txDef);
		return txOp.execute(new TransactionCallback<PageSet>() {
			@Override
			public PageSet doInTransaction(TransactionStatus status) {
				try {

					long numOfItems = extractor.extract(dataSource, sql,
							paramMap, consumer, new NoneLimiter());
					PageSet pageSet = paginator.paginate(0L, numOfItems,
							(numOfItems <= 0L ? 1L : numOfItems));

					return pageSet;
				} catch (IOException ex) {
					throw new IllegalStateException(ex);
				}
			}
		});
	}

	@Override
	public PageSet query(String databaseName, final QueryBuilder queryBuilder,
			final Map<String, ?> paramMap, final long pageNo,
			final long pageSz, final Consumer consumer) {

		final DataSource dataSource = dataSourceDef.getDataSource(databaseName);
		PlatformTransactionManager txMgr = new DataSourceTransactionManager(
				dataSource);
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setReadOnly(true);

		TransactionOperations txOp = new TransactionTemplate(txMgr, txDef);
		return txOp.execute(new TransactionCallback<PageSet>() {
			@Override
			public PageSet doInTransaction(TransactionStatus status) {
				try {

					long count = count(dataSource, queryBuilder.buildCount(),
							paramMap);
					PageSet pageSet = paginator.paginate(pageNo, count, pageSz);

					long numOfItems = extractor.extract(dataSource,
							queryBuilder.build(pageSz, pageSet.getCurrent()
									.getFrom()), paramMap, consumer,
							new NoneLimiter());
					if (numOfItems != pageSet.getCurrent().getCount()) {
						throw new IllegalStateException();
					}

					return pageSet;
				} catch (IOException ex) {
					throw new IllegalStateException(ex);
				}
			}
		});
	}

	private long count(DataSource dataSource, String sql,
			Map<String, ?> paramMap) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				dataSource);
		return template.queryForObject(sql, paramMap, Long.class);
	}

}
