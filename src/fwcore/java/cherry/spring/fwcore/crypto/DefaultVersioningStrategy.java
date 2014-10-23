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

package cherry.spring.fwcore.crypto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DefaultVersioningStrategy implements
		VersioningStrategy<byte[], Integer> {

	@Override
	public byte[] encode(byte[] data, Integer version) {
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				DataOutputStream out2 = new DataOutputStream(out)) {
			out2.writeInt(version.intValue());
			out2.write(data);
			return out.toByteArray();
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

	@Override
	public VersionedData<byte[], Integer> decode(byte[] encoded) {
		try (ByteArrayInputStream in = new ByteArrayInputStream(encoded);
				DataInputStream in2 = new DataInputStream(in);
				ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			int version = in2.readInt();
			byte[] buff = new byte[4096];
			int len;
			while ((len = in2.read(buff)) >= 0) {
				out.write(buff, 0, len);
			}
			out.flush();
			return new VersionedData<byte[], Integer>(out.toByteArray(),
					version);
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

}
