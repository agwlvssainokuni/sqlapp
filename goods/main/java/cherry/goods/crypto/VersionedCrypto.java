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

import java.util.Map;

import cherry.goods.crypto.VersionStrategy.VersionedData;

public class VersionedCrypto implements Crypto {

	private Integer defaultVersion;

	private Map<Integer, Crypto> cryptoMap;

	private VersionStrategy<byte[], Integer> versionStrategy = new DefaultVersionStrategy();

	public void setDefaultVersion(Integer defaultVersion) {
		this.defaultVersion = defaultVersion;
	}

	public void setCryptoMap(Map<Integer, Crypto> cryptoMap) {
		this.cryptoMap = cryptoMap;
	}

	public void setVersionStrategy(VersionStrategy<byte[], Integer> versionStrategy) {
		this.versionStrategy = versionStrategy;
	}

	@Override
	public byte[] encrypt(byte[] in) {
		Crypto crypto = cryptoMap.get(defaultVersion);
		if (crypto == null) {
			throw new IllegalStateException("No matching Crypto for version " + defaultVersion);
		}
		byte[] b = crypto.encrypt(in);
		return versionStrategy.encode(b, defaultVersion);
	}

	@Override
	public byte[] decrypt(byte[] in) {
		VersionedData<byte[], Integer> vd = versionStrategy.decode(in);
		Crypto crypto = cryptoMap.get(vd.getVersion());
		if (crypto == null) {
			throw new IllegalStateException("No matching Crypto for version " + vd.getVersion());
		}
		return crypto.decrypt(vd.getData());
	}

}
