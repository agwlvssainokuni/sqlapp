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

package cherry.foundation.crypto;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class SecureBigDecimalEncoder extends SecureTypeEncoder<BigDecimal> {

	@Override
	protected byte[] typeToBytes(BigDecimal p) {
		int scale = p.scale();
		BigInteger bi = p.scaleByPowerOfTen(scale).toBigIntegerExact();
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeInt(scale);
		out.write(bi.toByteArray());
		return out.toByteArray();
	}

	@Override
	protected BigDecimal bytesToType(byte[] p) {
		ByteArrayDataInput in = ByteStreams.newDataInput(p);
		int scale = in.readInt();
		byte[] buff = new byte[p.length - 4];
		in.readFully(buff);
		BigInteger bi = new BigInteger(buff);
		return new BigDecimal(bi, scale);
	}

}
