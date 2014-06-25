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

package cherry.spring.common.helper.async;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class AsyncProcHelperImpl implements AsyncProcHelper {

	@Autowired
	private AsyncProcDao asyncProcDao;

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public int createAsyncProc(String name, String launcherId) {
		Integer id = asyncProcDao.createAsyncProc(name, launcherId);
		if (id == null) {
			throw new IllegalStateException(
					"async_procs is not created for name=" + name);
		}
		return id;
	}

	@Transactional
	@Override
	public void invokeAsyncProc(int id) {
		int count = asyncProcDao.invokeAsyncProc(id);
		if (count != 1) {
			throw new IllegalStateException(
					"async_procs is not updated (invoke) for id=" + id);
		}
	}

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public void startAsyncProc(int id) {
		int count = asyncProcDao.startAsyncProc(id);
		if (count != 1) {
			throw new IllegalStateException(
					"async_procs is not updated (start) for id=" + id);
		}
	}

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public void successAsyncProc(int id, String result) {
		int count = asyncProcDao.successAsyncProc(id, result);
		if (count != 1) {
			throw new IllegalStateException(
					"async_procs is not updated (success) for id=" + id);
		}
	}

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public void errorAsyncProc(int id, String result) {
		int count = asyncProcDao.errorAsyncProc(id, result);
		if (count != 1) {
			throw new IllegalStateException(
					"async_procs is not updated (error) for id=" + id);
		}
	}

}
