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

package cherry.foundation.batch;

/**
 * バッチプログラムが実装すべきインタフェースを規定する。
 */
public interface IBatch {

	/**
	 * バッチプログラムの処理本体。
	 * 
	 * @param args 起動時にコマンドラインに指定された引数。
	 * @return バッチプログラムの終了ステータス。
	 */
	ExitStatus execute(String... args);

}
