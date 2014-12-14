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

package cherry.foundation.async;

import org.joda.time.LocalDateTime;
import org.springframework.web.multipart.MultipartFile;

import cherry.goods.command.CommandResult;

/**
 * 非同期実行フレームワーク。<br />
 * 非同期処理の実行状況の管理データをDB管理するためのインタフェース。
 */
public interface AsyncProcessStore {

	/**
	 * 非同期処理 (ファイル処理) の管理データを作成する。
	 *
	 * @param launcherId
	 *            非同期処理の実行者のID。
	 * @param dtm
	 *            現在日時。
	 * @param description
	 *            内容表記。
	 * @param name
	 *            HTTPリクエストで受け渡された元のパラメタ名。{@link AsyncProcessFacade}呼出しで指定した
	 *            {@link MultipartFile}の内容を受け取る。
	 * @param originalFilename
	 *            HTTPリクエストで受け渡された元のファイル名。{@link AsyncProcessFacade}呼出しで指定した
	 *            {@link MultipartFile}の内容を受け取る。
	 * @param contentType
	 *            HTTPリクエストで受け渡されたコンテントタイプ。{@link AsyncProcessFacade}呼出しで指定した
	 *            {@link MultipartFile}の内容を受け取る。
	 * @param size
	 *            HTTPリクエストで受け渡された元のファイルのサイズ。{@link AsyncProcessFacade}呼出しで指定した
	 *            {@link MultipartFile}の内容を受け取る。
	 * @param handlerName
	 *            非同期のファイル処理の処理を実装したBeanの名前。同Beanは{@link FileProcessHandler}
	 *            を実装しなければならない。
	 * @param args
	 *            引数。
	 * @return 非同期実行状況の管理データのID。
	 */
	long createFileProcess(String launcherId, LocalDateTime dtm,
			String description, String name, String originalFilename,
			String contentType, long size, String handlerName, String... args);

	/**
	 * 非同期処理 (コマンド実行) の管理データを作成する。
	 *
	 * @param launcherId
	 *            非同期処理の実行者のID。
	 * @param dtm
	 *            現在日時。
	 * @param description
	 *            内容表記。
	 * @param command
	 *            実行するコマンド。
	 * @param args
	 *            引数。
	 * @return 非同期実行状況の管理データのID。
	 */
	long createCommand(String launcherId, LocalDateTime dtm,
			String description, String command, String... args);

	/**
	 * 非同期処理の実行状況を「キュー投入済み ({@link AsyncStatus#LAUNCHED})」に更新する。
	 *
	 * @param asyncId
	 *            非同期実行状況の管理データのID。
	 * @param dtm
	 *            現在日時。
	 */
	void updateToLaunched(long asyncId, LocalDateTime dtm);

	/**
	 * 非同期処理の実行状況を「非同期処理実行中 ({@link AsyncStatus#PROCESSING})」に更新する。
	 *
	 * @param asyncId
	 *            非同期実行状況の管理データのID。
	 * @param dtm
	 *            現在日時。
	 */
	void updateToProcessing(long asyncId, LocalDateTime dtm);

	/**
	 * 非同期処理 (ファイル処理) の実行状況を「非同期処理終了 ({@link AsyncStatus#SUCCESS},
	 * {@link AsyncStatus#WARN}, {@link AsyncStatus#ERROR})」に更新する。
	 *
	 * @param asyncId
	 *            非同期実行状況の管理データのID。
	 * @param dtm
	 *            現在日時。
	 * @param status
	 *            非同期処理の実行状況 (ステータス)。
	 * @param result
	 *            ファイル処理の結果。
	 */
	void finishFileProcess(long asyncId, LocalDateTime dtm, AsyncStatus status,
			FileProcessResult result);

	/**
	 * 非同期処理 (コマンド実行) の実行状況を「非同期処理終了 ({@link AsyncStatus#SUCCESS},
	 * {@link AsyncStatus#WARN}, {@link AsyncStatus#ERROR})」に更新する。
	 *
	 * @param asyncId
	 *            非同期実行状況の管理データのID。
	 * @param dtm
	 *            現在日時。
	 * @param status
	 *            非同期処理の実行状況 (ステータス)。
	 * @param result
	 *            コマンド実行の結果。
	 */
	void finishCommand(long asyncId, LocalDateTime dtm, AsyncStatus status,
			CommandResult result);

	/**
	 * 非同期処理の実行状況を「非同期処理例外終了 ({@link AsyncStatus#EXCEPTION})」に更新する。
	 *
	 * @param asyncId
	 *            非同期実行状況の管理データのID。
	 * @param dtm
	 *            現在日時。
	 * @param th
	 *            非同期処理で発生した例外。
	 */
	void finishWithException(long asyncId, LocalDateTime dtm, Throwable th);

}
