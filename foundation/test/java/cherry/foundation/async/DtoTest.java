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

package cherry.foundation.async;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;

import org.junit.Test;

public class DtoTest {

	@Test
	public void testFileProcessResult() {

		FileProcessResult result = new FileProcessResult();
		result.setTotalCount(10);
		result.setOkCount(9);
		result.setNgCount(1);

		FileRecordInfo info = new FileRecordInfo();
		info.setOk(false);
		info.setNumber(5);
		info.setDescription("DESC");
		result.setNgRecordInfoList(Arrays.asList(info));

		assertEquals(10L, result.getTotalCount());
		assertEquals(9L, result.getOkCount());
		assertEquals(1L, result.getNgCount());
		assertEquals(1L, result.getNgRecordInfoList().size());
		assertEquals(info, result.getNgRecordInfoList().get(0));

		assertEquals(
				"FileProcessResult[totalCount=10,okCount=9,ngCount=1,ngRecordInfoList=[FileRecordInfo[number=5,ok=false,description=DESC]]]",
				result.toString());
	}

	@Test
	public void testFileRecordInfo() {

		FileRecordInfo info = new FileRecordInfo();
		info.setOk(false);
		info.setNumber(5);
		info.setDescription("DESC");

		assertFalse(info.isOk());
		assertEquals(5L, info.getNumber());
		assertEquals("DESC", info.getDescription());

		assertEquals("FileRecordInfo[number=5,ok=false,description=DESC]", info.toString());
	}

}
