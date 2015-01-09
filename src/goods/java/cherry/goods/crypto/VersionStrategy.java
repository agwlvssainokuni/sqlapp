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

package cherry.goods.crypto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * データの版管理の方式を規定するインタフェースを定義する。<br />
 * 下記の2つのメソッドを規定する。
 * <ul>
 * <li>素データに版情報を付加したデータを生成する ({@link #encode})</li>
 * <li>データから版情報と素データを分離する ({@link #decode})</li>
 * </ul>
 *
 * @param <T> データの型。
 * @param <V> 版情報の型。
 */
public interface VersionStrategy<T, V> {

	/**
	 * 素データに版情報を付加したデータを生成する。
	 * 
	 * @param data 素データ。
	 * @param version 版情報。
	 * @return 版情報を付加したデータ。
	 */
	T encode(T data, V version);

	/**
	 * データから版情報と素データを分離する。
	 * 
	 * @param encoded 版情報を付加したデータ。
	 * @return 分離したデータ (版情報と素データを保持する)。
	 */
	VersionedData<T, V> decode(T encoded);

	/**
	 * 分離された版情報と素データを保持する。
	 *
	 * @param <T> データの型。
	 * @param <V> 版情報の型。
	 */
	public static class VersionedData<T, V> {

		/** 素データを保持する。 */
		private T data;

		/** 版情報を保持する。 */
		private V version;

		/**
		 * @param data 素データ。
		 * @param version 版情報。
		 */
		public VersionedData(T data, V version) {
			this.data = data;
			this.version = version;
		}

		/**
		 * @return 素データ。
		 */
		public T getData() {
			return data;
		}

		/**
		 * @return 版情報。
		 */
		public V getVersion() {
			return version;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}
	}

}
