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

package cherry.spring.common.helper.crypto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SecureLongHelper extends SecureTypeBaseHelper<Long> {

	@Override
	protected byte[] typeToBytes(Long p) {
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				DataOutputStream out2 = new DataOutputStream(out)) {
			out2.writeLong(p.longValue());
			out2.flush();
			return out.toByteArray();
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

	@Override
	protected Long bytesToType(byte[] p) {
		try (ByteArrayInputStream in = new ByteArrayInputStream(p);
				DataInputStream in2 = new DataInputStream(in)) {
			return in2.readLong();
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

}
