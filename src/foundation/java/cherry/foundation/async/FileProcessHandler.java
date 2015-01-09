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

package cherry.foundation.async;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * 非同期実行フレームワーク。<br />
 * 非同期で実行するファイル処理の処理本体のインタフェース。ファイル処理の実体は本インタフェースを実装することとする。具体的には、{@link AsyncProcessFacade}呼出しで指定した
 * {@link MultipartFile}の内容を受取って処理する形をとる。
 */
public interface FileProcessHandler {

	/**
	 * 非同期で実行するファイル処理の本体。
	 * 
	 * @param file 処理対象のファイル。{@link AsyncProcessFacade}呼出しで指定した{@link MultipartFile}の内容を受け取る。
	 * @param name HTTPリクエストで受け渡された元のパラメタ名。{@link AsyncProcessFacade}呼出しで指定した{@link MultipartFile}の内容を受け取る。
	 * @param originalFilename HTTPリクエストで受け渡された元のファイル名。{@link AsyncProcessFacade}呼出しで指定した{@link MultipartFile}の内容を受け取る。
	 * @param contentType HTTPリクエストで受け渡されたコンテントタイプ。{@link AsyncProcessFacade}呼出しで指定した{@link MultipartFile}の内容を受け取る。
	 * @param size HTTPリクエストで受け渡された元のファイルのサイズ。{@link AsyncProcessFacade}呼出しで指定した{@link MultipartFile}の内容を受け取る。
	 * @param asyncId 非同期実行状況の管理データのID。
	 * @param args 追加パラメタ。
	 * @return 非同期で実行したファイル処理の結果。
	 * @throws IOException ファイルの処理にあたり発生したI/O例外は、非同期処理フレームワークがまとめて捕捉して処理する
	 *             (エラーログを出力し、非同期実行状況は異常終了を記録する)。特段の理由がない限り、個々のファイル処理 (の実装) ではI/O例外に対する処理を実装しない。
	 */
	FileProcessResult handleFile(File file, String name, String originalFilename, String contentType, long size,
			long asyncId, String... args) throws IOException;

}
