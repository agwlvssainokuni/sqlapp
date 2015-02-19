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

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

/**
 * データの版管理の機能を提供する。<br />
 * 素データはバイト列、版情報は整数とする。版管理の方式は、版情報 (4バイト) を素データの先頭に付加する形式をとる。
 */
public class DefaultVersionStrategy implements VersionStrategy<byte[], Integer> {

	@Override
	public byte[] encode(byte[] data, Integer version) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeInt(version.intValue());
		out.write(data);
		return out.toByteArray();
	}

	@Override
	public VersionedData<byte[], Integer> decode(byte[] encoded) {
		ByteArrayDataInput in = ByteStreams.newDataInput(encoded);
		int version = in.readInt();
		byte[] data = new byte[encoded.length - 4];
		in.readFully(data);
		return new VersionedData<byte[], Integer>(data, version);
	}

}
