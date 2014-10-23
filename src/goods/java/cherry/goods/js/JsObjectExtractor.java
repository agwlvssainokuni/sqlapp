/*
 * Copyright 2012,2014 agwlvssainokuni
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

package cherry.goods.js;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * JavaScriptの値をJavaオブジェクトに変換する.<br>
 * 変換のルールは下記のとおり。
 * <table>
 * <thead>
 * <tr>
 * <th>JavaScript</th>
 * <th>Java</th>
 * </tr>
 * </thead> <tbody>
 * <tr>
 * <td><code>null</code></td>
 * <td><code>null</code></td>
 * </tr>
 * <tr>
 * <td><code>undefined</code></td>
 * <td><code>null</code></td>
 * </tr>
 * <tr>
 * <td><code>true/false</code></td>
 * <td><code>java.lang.Boolean (TRUE/FALSE)</code></td>
 * </tr>
 * <tr>
 * <td><code>NaN</code></td>
 * <td><code>java.lang.Double.NaN</code></td>
 * </tr>
 * <tr>
 * <td><code>数値(Number)</code></td>
 * <td><code>java.lang.Double</code></td>
 * </tr>
 * <tr>
 * <td><code>文字列(String)</code></td>
 * <td><code>java.lang.String</code></td>
 * </tr>
 * <tr>
 * <td><code>配列(Array)</code></td>
 * <td><code>java.lang.List (ArrayList)</code></td>
 * </tr>
 * <tr>
 * <td><code>上記以外</code></td>
 * <td><code>java.lang.Map (LinkedHashMap)</code></td>
 * </tr>
 * </tbody>
 * </table>
 */
public class JsObjectExtractor {

	/** JavaScriptコード実行用スクリプトエンジン. */
	private ScriptEngine engine;

	/**
	 * 変換機能を初期化する.<br>
	 */
	public void initialize() {
		String scriptname = getClass().getSimpleName() + ".js";
		try (InputStream in = getClass().getResourceAsStream(scriptname);
				Reader reader = new InputStreamReader(in,
						Charset.forName("UTF-8"))) {
			ScriptEngineManager manager = new ScriptEngineManager();
			engine = manager.getEngineByName("JavaScript");
			engine.eval(reader);
		} catch (ScriptException | IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

	/**
	 * JavaScriptコードを評価する.<br>
	 * 
	 * @param jsCode
	 *            評価するJavaScriptコード
	 * @return JavaScriptコードを評価した結果
	 * @throws ScriptException
	 *             JavaScriptコードの実行でエラー
	 */
	public Object eval(String jsCode) throws ScriptException {
		return engine.eval(jsCode);
	}

	/**
	 * JavaScript値をJavaオブジェクトに変換する.<br>
	 * 
	 * @param jsValue
	 *            JavaScript値
	 * @return Javaオブジェクト
	 * @throws ScriptException
	 *             不正なJavaScript値が指定された
	 */
	public <T> T toJavaObject(String jsValue) throws ScriptException {
		StringBuilder builder = new StringBuilder();
		builder.append("toJavaObject(");
		builder.append(jsValue);
		builder.append(");");
		Object javaValue = engine.eval(builder.toString());

		@SuppressWarnings("unchecked")
		T typedJavaValue = (T) javaValue;
		return typedJavaValue;
	}

}
