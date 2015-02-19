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

package cherry.foundation.download;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.etl.Column;
import cherry.foundation.etl.Consumer;
import cherry.foundation.etl.CsvConsumer;
import cherry.foundation.etl.DelegateConsumer;
import cherry.foundation.etl.ExcelConsumer;
import cherry.foundation.querydsl.QueryConfigurer;
import cherry.foundation.querydsl.QueryDslSupport;
import cherry.goods.excel.ExcelFactory;
import cherry.goods.excel.ExcelWriter;

import com.mysema.query.types.Expression;

public class TableDownloadTemplate implements TableDownloadOperation {

	private DownloadOperation downloadOperation;

	private QueryDslSupport queryDslSupport;

	private String csvType;

	private String excelType;

	public void setDownloadOperation(DownloadOperation downloadOperation) {
		this.downloadOperation = downloadOperation;
	}

	public void setQueryDslSupport(QueryDslSupport queryDslSupport) {
		this.queryDslSupport = queryDslSupport;
	}

	public void setCsvType(String csvType) {
		this.csvType = csvType;
	}

	public void setExcelType(String excelType) {
		this.excelType = excelType;
	}

	@Transactional
	@Override
	public void downloadCsv(HttpServletResponse response, final Charset charset, String filename,
			LocalDateTime timestamp, final List<String> header, final QueryConfigurer commonClause,
			final QueryConfigurer orderByClause, final Expression<?>... expressions) throws IOException {
		downloadOperation.download(response, csvType, charset, filename, timestamp, new DownloadAction() {
			@Override
			public long doDownload(OutputStream stream) throws IOException {
				try (Writer writer = new OutputStreamWriter(stream, charset)) {
					Consumer consumer = createCsvConsumer(writer, header);
					return queryDslSupport.download(commonClause, orderByClause, consumer, expressions);
				}
			}
		});
	}

	@Transactional
	@Override
	public void downloadXls(HttpServletResponse response, String filename, LocalDateTime timestamp,
			final List<String> header, final QueryConfigurer commonClause, final QueryConfigurer orderByClause,
			final Expression<?>... expressions) throws IOException {
		downloadOperation.download(response, excelType, null, filename, timestamp, new DownloadAction() {
			@Override
			public long doDownload(OutputStream stream) throws IOException {
				try (Workbook workbook = ExcelFactory.createBlankXls(); ExcelWriter writer = new ExcelWriter(workbook)) {
					Consumer consumer = createExcelConsumer(writer, header);
					long count = queryDslSupport.download(commonClause, orderByClause, consumer, expressions);
					workbook.write(stream);
					return count;
				}
			}
		});
	}

	@Transactional
	@Override
	public void downloadXlsx(HttpServletResponse response, String filename, LocalDateTime timestamp,
			final List<String> header, final QueryConfigurer commonClause, final QueryConfigurer orderByClause,
			final Expression<?>... expressions) throws IOException {
		downloadOperation.download(response, excelType, null, filename, timestamp, new DownloadAction() {
			@Override
			public long doDownload(OutputStream stream) throws IOException {
				try (Workbook workbook = ExcelFactory.createBlankXlsx(); ExcelWriter writer = new ExcelWriter(workbook)) {
					Consumer consumer = createExcelConsumer(writer, header);
					long count = queryDslSupport.download(commonClause, orderByClause, consumer, expressions);
					workbook.write(stream);
					return count;
				}
			}
		});
	}

	private Consumer createCsvConsumer(Writer writer, List<String> header) {
		if (header == null) {
			return new CsvConsumer(writer, false);
		} else {
			return new WithHeaderConsumer(new CsvConsumer(writer, true), header.toArray(new String[header.size()]));
		}
	}

	private Consumer createExcelConsumer(ExcelWriter writer, List<String> header) {
		if (header == null) {
			return new ExcelConsumer(writer, false);
		} else {
			return new WithHeaderConsumer(new ExcelConsumer(writer, true), header.toArray(new String[header.size()]));
		}
	}

	public class WithHeaderConsumer extends DelegateConsumer {

		private String[] header;

		public WithHeaderConsumer(Consumer delegate, String[] header) {
			super(delegate);
			this.header = header;
		}

		@Override
		protected Column[] prepareBegin(Column[] col) throws IOException {
			Column[] adjusted = new Column[col.length];
			for (int i = 0; i < col.length; i++) {
				adjusted[i] = new Column();
				adjusted[i].setType(col[i].getType());
				if (i < header.length) {
					adjusted[i].setLabel(header[i]);
				} else {
					adjusted[i].setLabel(col[i].getLabel());
				}
			}
			return adjusted;
		}

		@Override
		protected Object[] prepareConsume(Object[] record) throws IOException {
			return record;
		}

		@Override
		protected void prepareEnd() throws IOException {
			// NOTHING
		}
	}

}
