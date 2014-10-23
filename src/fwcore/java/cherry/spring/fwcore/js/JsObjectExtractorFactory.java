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

package cherry.spring.fwcore.js;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.Charset;

import javax.script.ScriptException;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.Resource;

import cherry.goods.js.JsObjectExtractor;

/**
 * 設定データ抽出機能のファクトリ.
 */
public class JsObjectExtractorFactory implements FactoryBean<JsObjectExtractor> {

	/** 設定データの抽出元. */
	private Resource jsResource;

	/** 設定データファイルのエンコーディング. */
	private Charset charset;

	public void setJsResource(Resource jsResource) {
		this.jsResource = jsResource;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	/**
	 * 設定データ抽出機能を取得する.
	 * 
	 * @return 設定データ抽出機能
	 */
	@Override
	public JsObjectExtractor getObject() throws ScriptException, IOException {

		JsObjectExtractor objectExtractor = new JsObjectExtractor();
		objectExtractor.initialize();

		try (InputStream in = jsResource.getInputStream();
				Reader reader = new InputStreamReader(in, charset)) {
			StringWriter writer = new StringWriter();
			int ch;
			while ((ch = reader.read()) >= 0) {
				writer.write(ch);
			}
			String jsCode = writer.toString();
			objectExtractor.eval(jsCode);
		}

		return objectExtractor;
	}

	/**
	 * ファクトリが作成するインスタンスのクラスを取得する.
	 * 
	 * @return {@link ObjectExtractor}クラス
	 */
	@Override
	public Class<?> getObjectType() {
		return JsObjectExtractor.class;
	}

	/**
	 * ファクトリが作成するインスタンスがシングルトンか否か.
	 * 
	 * @return 真 (シングルトンである)
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}

}
