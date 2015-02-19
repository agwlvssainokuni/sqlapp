/*
 * Copyright 2015 agwlvssainokuni
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

package cherry.foundation.numbering;

/**
 * 発番管理機能。<br />
 * 発番処理で発生するDBアクセスを抽象化する。
 */
public interface NumberingStore {

	/**
	 * 発番処理の構成情報を取得する。<br />
	 * 
	 * @param numberName 番号を識別する名前。
	 * @return 発番処理の構成情報。
	 */
	NumberingDefinition getDefinition(String numberName);

	/**
	 * 直前に発行した番号を取得する。<br />
	 * 直前に発行した番号を取得し、かつ、悲観ロックする。
	 * 
	 * @param numberName 番号を識別する名前。
	 * @return 直前に発行した番号を取得する。
	 */
	long loadAndLock(String numberName);

	/**
	 * 発行済みの番号の情報を保存する。<br />
	 * 発行済みの番号を保存し、かつ、悲観ロックを解除する。ただし、実態としてはトランザクションの終了によってロックが解除されるのものであり、明示的に「解除」する処理は発生しない。
	 * 
	 * @param numberName 番号を識別する番号。
	 * @param current 発行済みの番号。
	 */
	void saveAndUnlock(String numberName, long current);

}
