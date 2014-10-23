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

import java.util.Map;

import cherry.spring.fwcore.crypto.VersioningStrategy.VersionedData;

public class VersionedCipherHelper implements CipherHelper {

	private Integer defaultVersion;

	private Map<Integer, CipherHelper> cipherHelperMap;

	private VersioningStrategy<byte[], Integer> versioningStrategy = new DefaultVersioningStrategy();

	public void setDefaultVersion(Integer defaultVersion) {
		this.defaultVersion = defaultVersion;
	}

	public void setCipherHelperMap(Map<Integer, CipherHelper> cipherHelperMap) {
		this.cipherHelperMap = cipherHelperMap;
	}

	public void setVersioningStrategy(
			VersioningStrategy<byte[], Integer> versioningStrategy) {
		this.versioningStrategy = versioningStrategy;
	}

	@Override
	public byte[] encrypt(byte[] in) {
		CipherHelper helper = cipherHelperMap.get(defaultVersion);
		if (helper == null) {
			throw new IllegalStateException(
					"No matching CipherHelper for version " + defaultVersion);
		}
		byte[] crypto = helper.encrypt(in);
		return versioningStrategy.encode(crypto, defaultVersion);
	}

	@Override
	public byte[] decrypt(byte[] in) {
		VersionedData<byte[], Integer> vd = versioningStrategy.decode(in);
		CipherHelper helper = cipherHelperMap.get(vd.getVersion());
		if (helper == null) {
			throw new IllegalStateException(
					"No matching CipherHelper for version " + vd.getVersion());
		}
		return helper.decrypt(vd.getData());
	}

}
