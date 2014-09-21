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

package cherry.spring.common.helper.signup;

import org.joda.time.LocalDateTime;

public interface SignupRequestDao {

	Integer createSignupRequest(String mailAddr, String token,
			LocalDateTime appliedAt);

	boolean validateMailAddr(String mailAddr, LocalDateTime intervalFrom,
			LocalDateTime rangeFrom, int numOfReq);

	boolean validateToken(String mailAddr, String token, LocalDateTime validFrom);

}
