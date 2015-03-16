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

package cherry.goods.command;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.google.common.io.CharStreams;

/**
 * コマンドを実行機能。<br />
 * コマンドを実行し、実行結果 (終了コード、標準出力、標準エラー出力) を取得する。
 */
public class CommandLauncherImpl implements CommandLauncher {

	/** 標準出力、標準エラー出力を収集する際の文字符号化方式。既定値はUTF-8。 */
	private Charset charset = StandardCharsets.UTF_8;

	/** 表樹エラー出力を標準出力にリダイレクトするか。既定値はtrue (リダイレクトする)。 */
	private boolean redirectErrorStream = true;

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	public void setRedirectErrorStream(boolean redirectErrorStream) {
		this.redirectErrorStream = redirectErrorStream;
	}

	/**
	 * コマンドを実行し、実行結果 (終了コード、標準出力、標準エラー出力) を取得する。
	 * 
	 * @param command 実行するコマンド (コマンドライン)。
	 * @return　実行結果 (終了コード、標準出力、標準エラー出力)。
	 * @throws IOException コマンド不正 (存在しない)、または、標準出力、標準エラー出力の読取り不正。
	 * @throws InterruptedException コマンドの終了待機時に割込みが発生したことを表す。
	 */
	@Override
	public CommandResult launch(String... command) throws IOException, InterruptedException {
		CommandResult result = new CommandResult();
		Process proc = new ProcessBuilder(command).redirectErrorStream(redirectErrorStream).start();
		try (InputStream in = proc.getErrorStream(); Reader r = new InputStreamReader(in, charset)) {
			result.setStderr(CharStreams.toString(r));
		}
		try (InputStream in = proc.getInputStream(); Reader r = new InputStreamReader(in, charset)) {
			result.setStdout(CharStreams.toString(r));
		}
		int exitValue = proc.waitFor();
		result.setExitValue(exitValue);
		return result;
	}

}
