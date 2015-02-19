/*
 * Copyright 2011,2015 agwlvssainokuni
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

package cherry.goods.ipaddr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigInteger;

import org.junit.Test;

public class IpAddrUtilTest {

	@Test
	public void testIsIpv4Addr() {
		assertFalse(IpAddrUtil.isIpv4Addr(null));
		assertTrue(IpAddrUtil.isIpv4Addr("0.0.0.0"));
		assertTrue(IpAddrUtil.isIpv4Addr("00.00.00.00"));
		assertTrue(IpAddrUtil.isIpv4Addr("000.000.000.000"));
		assertTrue(IpAddrUtil.isIpv4Addr("255.255.255.255"));
		assertTrue(IpAddrUtil.isIpv4Addr("127.0.0.1"));
		assertTrue(IpAddrUtil.isIpv4Addr("10.0.0.1"));
		assertTrue(IpAddrUtil.isIpv4Addr("192.168.0.1"));
		assertFalse(IpAddrUtil.isIpv4Addr(""));
		assertFalse(IpAddrUtil.isIpv4Addr("0"));
		assertFalse(IpAddrUtil.isIpv4Addr("0."));
		assertFalse(IpAddrUtil.isIpv4Addr("0.0"));
		assertFalse(IpAddrUtil.isIpv4Addr("0.0."));
		assertFalse(IpAddrUtil.isIpv4Addr("0.0.0"));
		assertFalse(IpAddrUtil.isIpv4Addr("0.0.0."));
		assertFalse(IpAddrUtil.isIpv4Addr("0000.0.0.0"));
		assertFalse(IpAddrUtil.isIpv4Addr("0.0000.0.0"));
		assertFalse(IpAddrUtil.isIpv4Addr("0.0.0000.0"));
		assertFalse(IpAddrUtil.isIpv4Addr("0.0.0.0000"));
		assertFalse(IpAddrUtil.isIpv4Addr("z.z.z.z"));
		assertFalse(IpAddrUtil.isIpv4Addr("z.0.0.0"));
		assertFalse(IpAddrUtil.isIpv4Addr("0.z.0.0"));
		assertFalse(IpAddrUtil.isIpv4Addr("0.0.z.0"));
		assertFalse(IpAddrUtil.isIpv4Addr("0.0.0.z"));

		assertTrue(IpAddrUtil.isIpv4Addr("0.0.0.0"));
		assertTrue(IpAddrUtil.isIpv4Addr("9.0.0.0"));
		assertTrue(IpAddrUtil.isIpv4Addr("00.0.0.0"));
		assertTrue(IpAddrUtil.isIpv4Addr("99.0.0.0"));
		assertTrue(IpAddrUtil.isIpv4Addr("000.0.0.0"));
		assertTrue(IpAddrUtil.isIpv4Addr("099.0.0.0"));
		assertTrue(IpAddrUtil.isIpv4Addr("100.0.0.0"));
		assertTrue(IpAddrUtil.isIpv4Addr("199.0.0.0"));
		assertTrue(IpAddrUtil.isIpv4Addr("200.0.0.0"));
		assertTrue(IpAddrUtil.isIpv4Addr("209.0.0.0"));
		assertTrue(IpAddrUtil.isIpv4Addr("240.0.0.0"));
		assertTrue(IpAddrUtil.isIpv4Addr("249.0.0.0"));
		assertTrue(IpAddrUtil.isIpv4Addr("250.0.0.0"));
		assertTrue(IpAddrUtil.isIpv4Addr("255.0.0.0"));
		assertFalse(IpAddrUtil.isIpv4Addr("256.0.0.0"));
		assertFalse(IpAddrUtil.isIpv4Addr("260.0.0.0"));
		assertFalse(IpAddrUtil.isIpv4Addr("270.0.0.0"));
		assertFalse(IpAddrUtil.isIpv4Addr("280.0.0.0"));
		assertFalse(IpAddrUtil.isIpv4Addr("290.0.0.0"));

		assertTrue(IpAddrUtil.isIpv4Addr("0.0.0.0"));
		assertTrue(IpAddrUtil.isIpv4Addr("0.0.0.9"));
		assertTrue(IpAddrUtil.isIpv4Addr("0.0.0.00"));
		assertTrue(IpAddrUtil.isIpv4Addr("0.0.0.99"));
		assertTrue(IpAddrUtil.isIpv4Addr("0.0.0.000"));
		assertTrue(IpAddrUtil.isIpv4Addr("0.0.0.099"));
		assertTrue(IpAddrUtil.isIpv4Addr("0.0.0.100"));
		assertTrue(IpAddrUtil.isIpv4Addr("0.0.0.199"));
		assertTrue(IpAddrUtil.isIpv4Addr("0.0.0.200"));
		assertTrue(IpAddrUtil.isIpv4Addr("0.0.0.209"));
		assertTrue(IpAddrUtil.isIpv4Addr("0.0.0.240"));
		assertTrue(IpAddrUtil.isIpv4Addr("0.0.0.249"));
		assertTrue(IpAddrUtil.isIpv4Addr("0.0.0.250"));
		assertTrue(IpAddrUtil.isIpv4Addr("0.0.0.255"));
		assertFalse(IpAddrUtil.isIpv4Addr("0.0.0.256"));
		assertFalse(IpAddrUtil.isIpv4Addr("0.0.0.260"));
		assertFalse(IpAddrUtil.isIpv4Addr("0.0.0.270"));
		assertFalse(IpAddrUtil.isIpv4Addr("0.0.0.280"));
		assertFalse(IpAddrUtil.isIpv4Addr("0.0.0.290"));
	}

	@Test
	public void testIsIpv6Addr() {
		assertFalse(IpAddrUtil.isIpv6Addr(null));
		assertFalse(IpAddrUtil.isIpv6Addr(""));
		assertTrue(IpAddrUtil.isIpv6Addr("2001:0db8:0020:0003:1000:0100:0020:0003"));
		assertTrue(IpAddrUtil.isIpv6Addr("2001:db8:20:3:1000:100:20:3"));
		assertTrue(IpAddrUtil.isIpv6Addr("::"));
		assertTrue(IpAddrUtil.isIpv6Addr("::1.1.1.1"));
	}

	@Test
	public void testIsIpv6Addr_省略無し() {
		assertTrue(IpAddrUtil.isIpv6Addr("0:0:0:0:0:0:0:0"));
		assertTrue(IpAddrUtil.isIpv6Addr("00:00:00:00:00:00:00:00"));
		assertTrue(IpAddrUtil.isIpv6Addr("000:000:000:000:000:000:000:000"));
		assertTrue(IpAddrUtil.isIpv6Addr("0000:0000:0000:0000:0000:0000:0000:0000"));
		assertTrue(IpAddrUtil.isIpv6Addr("1111:1111:1111:1111:1111:1111:1111:1111"));
		assertTrue(IpAddrUtil.isIpv6Addr("2222:2222:2222:2222:2222:2222:2222:2222"));
		assertTrue(IpAddrUtil.isIpv6Addr("3333:3333:3333:3333:3333:3333:3333:3333"));
		assertTrue(IpAddrUtil.isIpv6Addr("4444:4444:4444:4444:4444:4444:4444:4444"));
		assertTrue(IpAddrUtil.isIpv6Addr("5555:5555:5555:5555:5555:5555:5555:5555"));
		assertTrue(IpAddrUtil.isIpv6Addr("6666:6666:6666:6666:6666:6666:6666:6666"));
		assertTrue(IpAddrUtil.isIpv6Addr("7777:7777:7777:7777:7777:7777:7777:7777"));
		assertTrue(IpAddrUtil.isIpv6Addr("8888:8888:8888:8888:8888:8888:8888:8888"));
		assertTrue(IpAddrUtil.isIpv6Addr("9999:9999:9999:9999:9999:9999:9999:9999"));
		assertTrue(IpAddrUtil.isIpv6Addr("aaaa:aaaa:aaaa:aaaa:aaaa:aaaa:aaaa:aaaa"));
		assertTrue(IpAddrUtil.isIpv6Addr("bbbb:bbbb:bbbb:bbbb:bbbb:bbbb:bbbb:bbbb"));
		assertTrue(IpAddrUtil.isIpv6Addr("cccc:cccc:cccc:cccc:cccc:cccc:cccc:cccc"));
		assertTrue(IpAddrUtil.isIpv6Addr("dddd:dddd:dddd:dddd:dddd:dddd:dddd:dddd"));
		assertTrue(IpAddrUtil.isIpv6Addr("eeee:eeee:eeee:eeee:eeee:eeee:eeee:eeee"));
		assertTrue(IpAddrUtil.isIpv6Addr("ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff"));
		assertTrue(IpAddrUtil.isIpv6Addr("AAAA:AAAA:AAAA:AAAA:AAAA:AAAA:AAAA:AAAA"));
		assertTrue(IpAddrUtil.isIpv6Addr("BBBB:BBBB:BBBB:BBBB:BBBB:BBBB:BBBB:BBBB"));
		assertTrue(IpAddrUtil.isIpv6Addr("CCCC:CCCC:CCCC:CCCC:CCCC:CCCC:CCCC:CCCC"));
		assertTrue(IpAddrUtil.isIpv6Addr("DDDD:DDDD:DDDD:DDDD:DDDD:DDDD:DDDD:DDDD"));
		assertTrue(IpAddrUtil.isIpv6Addr("EEEE:EEEE:EEEE:EEEE:EEEE:EEEE:EEEE:EEEE"));
		assertTrue(IpAddrUtil.isIpv6Addr("FFFF:FFFF:FFFF:FFFF:FFFF:FFFF:FFFF:FFFF"));
		assertFalse(IpAddrUtil.isIpv6Addr("00000:0:0:0:0:0:0:0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:00000:0:0:0:0:0:0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:00000:0:0:0:0:0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:00000:0:0:0:0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:00000:0:0:0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:00000:0:0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:0:00000:0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:0:0:00000"));
		assertFalse(IpAddrUtil.isIpv6Addr("0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:0:"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:0:0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:0:0:"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:0:0:0:"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:0:0:0:0"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:z:z:z:z:z:z:z"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:0:0:0:0:0:0:0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:z:0:0:0:0:0:0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:z:0:0:0:0:0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:z:0:0:0:0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:z:0:0:0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:z:0:0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:0:z:0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:0:0:z"));
	}

	@Test
	public void testIsIpv6Addr_前省略() {
		assertTrue(IpAddrUtil.isIpv6Addr("::1"));
		assertTrue(IpAddrUtil.isIpv6Addr("::1:1"));
		assertTrue(IpAddrUtil.isIpv6Addr("::1:1:1"));
		assertTrue(IpAddrUtil.isIpv6Addr("::1:1:1:1"));
		assertTrue(IpAddrUtil.isIpv6Addr("::1:1:1:1:1"));
		assertTrue(IpAddrUtil.isIpv6Addr("::1:1:1:1:1:1"));
		assertTrue(IpAddrUtil.isIpv6Addr("::1:1:1:1:1:1:1"));
		assertFalse(IpAddrUtil.isIpv6Addr("::1:1:1:1:1:1:1:1"));
		assertFalse(IpAddrUtil.isIpv6Addr("::z:z:z:z:z:z:z"));
		assertFalse(IpAddrUtil.isIpv6Addr("::z:1:1:1:1:1:1"));
		assertFalse(IpAddrUtil.isIpv6Addr("::1:z:1:1:1:1:1"));
		assertFalse(IpAddrUtil.isIpv6Addr("::1:1:z:1:1:1:1"));
		assertFalse(IpAddrUtil.isIpv6Addr("::1:1:1:z:1:1:1"));
		assertFalse(IpAddrUtil.isIpv6Addr("::1:1:1:1:z:1:1"));
		assertFalse(IpAddrUtil.isIpv6Addr("::1:1:1:1:1:z:1"));
		assertFalse(IpAddrUtil.isIpv6Addr("::1:1:1:1:1:1:z"));
	}

	@Test
	public void testIsIpv6Addr_後省略() {
		assertTrue(IpAddrUtil.isIpv6Addr("1::"));
		assertTrue(IpAddrUtil.isIpv6Addr("1:1::"));
		assertTrue(IpAddrUtil.isIpv6Addr("1:1:1::"));
		assertTrue(IpAddrUtil.isIpv6Addr("1:1:1:1::"));
		assertTrue(IpAddrUtil.isIpv6Addr("1:1:1:1:1::"));
		assertTrue(IpAddrUtil.isIpv6Addr("1:1:1:1:1:1::"));
		assertTrue(IpAddrUtil.isIpv6Addr("1:1:1:1:1:1:1::"));
		assertFalse(IpAddrUtil.isIpv6Addr("1:1:1:1:1:1:1:1::"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:z:z:z:z:z:z::"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:1:1:1:1:1:1::"));
		assertFalse(IpAddrUtil.isIpv6Addr("1:z:1:1:1:1:1::"));
		assertFalse(IpAddrUtil.isIpv6Addr("1:1:z:1:1:1:1::"));
		assertFalse(IpAddrUtil.isIpv6Addr("1:1:1:z:1:1:1::"));
		assertFalse(IpAddrUtil.isIpv6Addr("1:1:1:1:z:1:1::"));
		assertFalse(IpAddrUtil.isIpv6Addr("1:1:1:1:1:z:1::"));
		assertFalse(IpAddrUtil.isIpv6Addr("1:1:1:1:1:1:z::"));
	}

	@Test
	public void testIsIpv6Addr_中省略() {

		assertTrue(IpAddrUtil.isIpv6Addr("1::1"));
		assertTrue(IpAddrUtil.isIpv6Addr("1::1:1"));
		assertTrue(IpAddrUtil.isIpv6Addr("1::1:1:1"));
		assertTrue(IpAddrUtil.isIpv6Addr("1::1:1:1:1"));
		assertTrue(IpAddrUtil.isIpv6Addr("1::1:1:1:1:1"));
		assertTrue(IpAddrUtil.isIpv6Addr("1::1:1:1:1:1:1"));
		assertFalse(IpAddrUtil.isIpv6Addr("1::1:1:1:1:1:1:1"));
		assertFalse(IpAddrUtil.isIpv6Addr("z::z:z:z:z:z:z"));
		assertFalse(IpAddrUtil.isIpv6Addr("z::1:1:1:1:1:1"));
		assertFalse(IpAddrUtil.isIpv6Addr("1::z:1:1:1:1:1"));
		assertFalse(IpAddrUtil.isIpv6Addr("1::1:z:1:1:1:1"));
		assertFalse(IpAddrUtil.isIpv6Addr("1::1:1:z:1:1:1"));
		assertFalse(IpAddrUtil.isIpv6Addr("1::1:1:1:z:1:1"));
		assertFalse(IpAddrUtil.isIpv6Addr("1::1:1:1:1:z:1"));
		assertFalse(IpAddrUtil.isIpv6Addr("1::1:1:1:1:1:z"));

		assertTrue(IpAddrUtil.isIpv6Addr("2:2::2"));
		assertTrue(IpAddrUtil.isIpv6Addr("2:2::2:2"));
		assertTrue(IpAddrUtil.isIpv6Addr("2:2::2:2:2"));
		assertTrue(IpAddrUtil.isIpv6Addr("2:2::2:2:2:2"));
		assertTrue(IpAddrUtil.isIpv6Addr("2:2::2:2:2:2:2"));
		assertFalse(IpAddrUtil.isIpv6Addr("2:2::2:2:2:2:2:2"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:z::z:z:z:z:z"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:2::2:2:2:2:2"));
		assertFalse(IpAddrUtil.isIpv6Addr("2:z::2:2:2:2:2"));
		assertFalse(IpAddrUtil.isIpv6Addr("2:2::z:2:2:2:2"));
		assertFalse(IpAddrUtil.isIpv6Addr("2:2::2:z:2:2:2"));
		assertFalse(IpAddrUtil.isIpv6Addr("2:2::2:2:z:2:2"));
		assertFalse(IpAddrUtil.isIpv6Addr("2:2::2:2:2:z:2"));
		assertFalse(IpAddrUtil.isIpv6Addr("2:2::2:2:2:2:z"));

		assertTrue(IpAddrUtil.isIpv6Addr("3:3:3::3"));
		assertTrue(IpAddrUtil.isIpv6Addr("3:3:3::3:3"));
		assertTrue(IpAddrUtil.isIpv6Addr("3:3:3::3:3:3"));
		assertTrue(IpAddrUtil.isIpv6Addr("3:3:3::3:3:3:3"));
		assertFalse(IpAddrUtil.isIpv6Addr("3:3:3::3:3:3:3:3"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:z:z::z:z:z:z"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:3:3::3:3:3:3"));
		assertFalse(IpAddrUtil.isIpv6Addr("3:z:3::3:3:3:3"));
		assertFalse(IpAddrUtil.isIpv6Addr("3:3:z::3:3:3:3"));
		assertFalse(IpAddrUtil.isIpv6Addr("3:3:3::z:3:3:3"));
		assertFalse(IpAddrUtil.isIpv6Addr("3:3:3::3:z:3:3"));
		assertFalse(IpAddrUtil.isIpv6Addr("3:3:3::3:3:z:3"));
		assertFalse(IpAddrUtil.isIpv6Addr("3:3:3::3:3:3:z"));

		assertTrue(IpAddrUtil.isIpv6Addr("4:4:4:4::4"));
		assertTrue(IpAddrUtil.isIpv6Addr("4:4:4:4::4:4"));
		assertTrue(IpAddrUtil.isIpv6Addr("4:4:4:4::4:4:4"));
		assertFalse(IpAddrUtil.isIpv6Addr("4:4:4:4::4:4:4:4"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:z:z:z::z:z:z"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:4:4:4::4:4:4"));
		assertFalse(IpAddrUtil.isIpv6Addr("4:z:4:4::4:4:4"));
		assertFalse(IpAddrUtil.isIpv6Addr("4:4:z:4::4:4:4"));
		assertFalse(IpAddrUtil.isIpv6Addr("4:4:4:z::4:4:4"));
		assertFalse(IpAddrUtil.isIpv6Addr("4:4:4:4::z:4:4"));
		assertFalse(IpAddrUtil.isIpv6Addr("4:4:4:4::4:z:4"));
		assertFalse(IpAddrUtil.isIpv6Addr("4:4:4:4::4:4:z"));

		assertTrue(IpAddrUtil.isIpv6Addr("5:5:5:5:5::5"));
		assertTrue(IpAddrUtil.isIpv6Addr("5:5:5:5:5::5:5"));
		assertFalse(IpAddrUtil.isIpv6Addr("5:5:5:5:5::5:5:5"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:z:z:z:z::z:z"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:5:5:5:5::5:5"));
		assertFalse(IpAddrUtil.isIpv6Addr("5:z:5:5:5::5:5"));
		assertFalse(IpAddrUtil.isIpv6Addr("5:5:z:5:5::5:5"));
		assertFalse(IpAddrUtil.isIpv6Addr("5:5:5:z:5::5:5"));
		assertFalse(IpAddrUtil.isIpv6Addr("5:5:5:5:z::5:5"));
		assertFalse(IpAddrUtil.isIpv6Addr("5:5:5:5:5::z:5"));
		assertFalse(IpAddrUtil.isIpv6Addr("5:5:5:5:5::5:z"));

		assertTrue(IpAddrUtil.isIpv6Addr("6:6:6:6:6:6::6"));
		assertFalse(IpAddrUtil.isIpv6Addr("6:6:6:6:6:6::6:6"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:z:z:z:z:z::z"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:6:6:6:6:6::6"));
		assertFalse(IpAddrUtil.isIpv6Addr("6:z:6:6:6:6::6"));
		assertFalse(IpAddrUtil.isIpv6Addr("6:6:z:6:6:6::6"));
		assertFalse(IpAddrUtil.isIpv6Addr("6:6:6:z:6:6::6"));
		assertFalse(IpAddrUtil.isIpv6Addr("6:6:6:6:z:6::6"));
		assertFalse(IpAddrUtil.isIpv6Addr("6:6:6:6:6:z::6"));
		assertFalse(IpAddrUtil.isIpv6Addr("6:6:6:6:6:6::z"));

		assertFalse(IpAddrUtil.isIpv6Addr("7:7:7:7:7:7:7::7"));
	}

	@Test
	public void testIsIpv6Addr_後IPv6省略無し() {
		assertTrue(IpAddrUtil.isIpv6Addr("0:0:0:0:0:0:0.0.0.0"));
		assertTrue(IpAddrUtil.isIpv6Addr("00:00:00:00:00:00:00.00.00.00"));
		assertTrue(IpAddrUtil.isIpv6Addr("000:000:000:000:000:000:000.000.000.000"));
		assertTrue(IpAddrUtil.isIpv6Addr("0000:0000:0000:0000:0000:0000:000.000.000.000"));
		assertTrue(IpAddrUtil.isIpv6Addr("1111:1111:1111:1111:1111:1111:1.1.1.1"));
		assertTrue(IpAddrUtil.isIpv6Addr("2222:2222:2222:2222:2222:2222:2.2.2.2"));
		assertTrue(IpAddrUtil.isIpv6Addr("3333:3333:3333:3333:3333:3333:3.3.3.3"));
		assertTrue(IpAddrUtil.isIpv6Addr("4444:4444:4444:4444:4444:4444:4.4.4.4"));
		assertTrue(IpAddrUtil.isIpv6Addr("5555:5555:5555:5555:5555:5555:5.5.5.5"));
		assertTrue(IpAddrUtil.isIpv6Addr("6666:6666:6666:6666:6666:6666:6.6.6.6"));
		assertTrue(IpAddrUtil.isIpv6Addr("7777:7777:7777:7777:7777:7777:7.7.7.7"));
		assertTrue(IpAddrUtil.isIpv6Addr("8888:8888:8888:8888:8888:8888:8.8.8.8"));
		assertTrue(IpAddrUtil.isIpv6Addr("9999:9999:9999:9999:9999:9999:9.9.9.9"));
		assertTrue(IpAddrUtil.isIpv6Addr("aaaa:aaaa:aaaa:aaaa:aaaa:aaaa:10.10.10.10"));
		assertTrue(IpAddrUtil.isIpv6Addr("bbbb:bbbb:bbbb:bbbb:bbbb:bbbb:11.11.11.11"));
		assertTrue(IpAddrUtil.isIpv6Addr("cccc:cccc:cccc:cccc:cccc:cccc:12.12.12.12"));
		assertTrue(IpAddrUtil.isIpv6Addr("dddd:dddd:dddd:dddd:dddd:dddd:13.13.13.13"));
		assertTrue(IpAddrUtil.isIpv6Addr("eeee:eeee:eeee:eeee:eeee:eeee:14.14.14.14"));
		assertTrue(IpAddrUtil.isIpv6Addr("ffff:ffff:ffff:ffff:ffff:ffff:15.15.15.15"));
		assertTrue(IpAddrUtil.isIpv6Addr("AAAA:AAAA:AAAA:AAAA:AAAA:AAAA:10.10.10.10"));
		assertTrue(IpAddrUtil.isIpv6Addr("BBBB:BBBB:BBBB:BBBB:BBBB:BBBB:11.11.11.11"));
		assertTrue(IpAddrUtil.isIpv6Addr("CCCC:CCCC:CCCC:CCCC:CCCC:CCCC:12.12.12.12"));
		assertTrue(IpAddrUtil.isIpv6Addr("DDDD:DDDD:DDDD:DDDD:DDDD:DDDD:13.13.13.13"));
		assertTrue(IpAddrUtil.isIpv6Addr("EEEE:EEEE:EEEE:EEEE:EEEE:EEEE:14.14.14.14"));
		assertTrue(IpAddrUtil.isIpv6Addr("FFFF:FFFF:FFFF:FFFF:FFFF:FFFF:15.15.15.15"));
		assertFalse(IpAddrUtil.isIpv6Addr("00000:0:0:0:0:0:0.0.0.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:00000:0:0:0:0:0.0.0.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:00000:0:0:0:0.0.0.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:00000:0:0:0.0.0.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:00000:0:0.0.0.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:00000:0.0.0.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:0:256.256.256.256"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:0:256.0.0.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:0:0.256.0.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:0:0.0.256.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:0:0.0.0.256"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0.0.0.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0.0.0.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0.0.0.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0.0.0.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:0.0.0.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:0:0:0.0.0.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:z:z:z:z:z:z.z.z.z"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:0:0:0:0:0:0.0.0.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:z:0:0:0:0:0.0.0.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:z:0:0:0:0.0.0.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:z:0:0:0.0.0.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:z:0:0.0.0.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:z:0.0.0.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:0:z.0.0.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:0:0.z.0.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:0:0.0.z.0"));
		assertFalse(IpAddrUtil.isIpv6Addr("0:0:0:0:0:0:0.0.0.z"));
	}

	@Test
	public void testIsIpv6Addr_後IPv4前省略() {
		assertTrue(IpAddrUtil.isIpv6Addr("::1:1.1.1.1"));
		assertTrue(IpAddrUtil.isIpv6Addr("::1:1:1.1.1.1"));
		assertTrue(IpAddrUtil.isIpv6Addr("::1:1:1:1.1.1.1"));
		assertTrue(IpAddrUtil.isIpv6Addr("::1:1:1:1:1.1.1.1"));
		assertTrue(IpAddrUtil.isIpv6Addr("::1:1:1:1:1:1.1.1.1"));
		assertFalse(IpAddrUtil.isIpv6Addr("::1:1:1:1:1:1:1.1.1.1"));
		assertFalse(IpAddrUtil.isIpv6Addr("::z:z:z:z:z:1.1.1.1"));
		assertFalse(IpAddrUtil.isIpv6Addr("::z:1:1:1:1:1.1.1.1"));
		assertFalse(IpAddrUtil.isIpv6Addr("::1:z:1:1:1:1.1.1.1"));
		assertFalse(IpAddrUtil.isIpv6Addr("::1:1:z:1:1:1.1.1.1"));
		assertFalse(IpAddrUtil.isIpv6Addr("::1:1:1:z:1:1.1.1.1"));
		assertFalse(IpAddrUtil.isIpv6Addr("::1:1:1:1:z:1.1.1.1"));
	}

	@Test
	public void testIsIpv6Addr_後IPv4後省略() {
		assertTrue(IpAddrUtil.isIpv6Addr("1::1.1.1.1"));
		assertTrue(IpAddrUtil.isIpv6Addr("1:1::1.1.1.1"));
		assertTrue(IpAddrUtil.isIpv6Addr("1:1:1::1.1.1.1"));
		assertTrue(IpAddrUtil.isIpv6Addr("1:1:1:1::1.1.1.1"));
		assertTrue(IpAddrUtil.isIpv6Addr("1:1:1:1:1::1.1.1.1"));
		assertFalse(IpAddrUtil.isIpv6Addr("1:1:1:1:1:1::1.1.1.1"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:z:z:z:z::1.1.1.1"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:1:1:1:1::1.1.1.1"));
		assertFalse(IpAddrUtil.isIpv6Addr("1:z:1:1:1::1.1.1.1"));
		assertFalse(IpAddrUtil.isIpv6Addr("1:1:z:1:1::1.1.1.1"));
		assertFalse(IpAddrUtil.isIpv6Addr("1:1:1:z:1::1.1.1.1"));
		assertFalse(IpAddrUtil.isIpv6Addr("1:1:1:1:z::1.1.1.1"));
	}

	@Test
	public void testIsIpv6Addr_後IPv4中省略() {

		assertTrue(IpAddrUtil.isIpv6Addr("1::1:1.1.1.1"));
		assertTrue(IpAddrUtil.isIpv6Addr("1::1:1:1.1.1.1"));
		assertTrue(IpAddrUtil.isIpv6Addr("1::1:1:1:1.1.1.1"));
		assertTrue(IpAddrUtil.isIpv6Addr("1::1:1:1:1:1.1.1.1"));
		assertFalse(IpAddrUtil.isIpv6Addr("1::1:1:1:1:1:1.1.1.1"));
		assertFalse(IpAddrUtil.isIpv6Addr("z::z:z:z:z:1.1.1.1"));
		assertFalse(IpAddrUtil.isIpv6Addr("z::1:1:1:1:1.1.1.1"));
		assertFalse(IpAddrUtil.isIpv6Addr("1::z:1:1:1:1.1.1.1"));
		assertFalse(IpAddrUtil.isIpv6Addr("1::1:z:1:1:1.1.1.1"));
		assertFalse(IpAddrUtil.isIpv6Addr("1::1:1:z:1:1.1.1.1"));
		assertFalse(IpAddrUtil.isIpv6Addr("1::1:1:1:z:1.1.1.1"));

		assertTrue(IpAddrUtil.isIpv6Addr("2:2::2:2.2.2.2"));
		assertTrue(IpAddrUtil.isIpv6Addr("2:2::2:2:2.2.2.2"));
		assertTrue(IpAddrUtil.isIpv6Addr("2:2::2:2:2:2.2.2.2"));
		assertFalse(IpAddrUtil.isIpv6Addr("2:2::2:2:2:2:2.2.2.2"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:z::z:z:z:2.2.2.2"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:2::2:2:2:2.2.2.2"));
		assertFalse(IpAddrUtil.isIpv6Addr("2:z::2:2:2:2.2.2.2"));
		assertFalse(IpAddrUtil.isIpv6Addr("2:2::z:2:2:2.2.2.2"));
		assertFalse(IpAddrUtil.isIpv6Addr("2:2::2:z:2:2.2.2.2"));
		assertFalse(IpAddrUtil.isIpv6Addr("2:2::2:2:z:2.2.2.2"));

		assertTrue(IpAddrUtil.isIpv6Addr("3:3:3::3:3.3.3.3"));
		assertTrue(IpAddrUtil.isIpv6Addr("3:3:3::3:3:3.3.3.3"));
		assertFalse(IpAddrUtil.isIpv6Addr("3:3:3::3:3:3:3.3.3.3"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:z:z::z:z:3.3.3.3"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:3:3::3:3:3.3.3.3"));
		assertFalse(IpAddrUtil.isIpv6Addr("3:z:3::3:3:3.3.3.3"));
		assertFalse(IpAddrUtil.isIpv6Addr("3:3:z::3:3:3.3.3.3"));
		assertFalse(IpAddrUtil.isIpv6Addr("3:3:3::z:3:3.3.3.3"));
		assertFalse(IpAddrUtil.isIpv6Addr("3:3:3::3:z:3.3.3.3"));

		assertTrue(IpAddrUtil.isIpv6Addr("4:4:4:4::4:4.4.4.4"));
		assertFalse(IpAddrUtil.isIpv6Addr("4:4:4:4::4:4:4.4.4.4"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:z:z:z::z:4.4.4.4"));
		assertFalse(IpAddrUtil.isIpv6Addr("z:4:4:4::4:4.4.4.4"));
		assertFalse(IpAddrUtil.isIpv6Addr("4:z:4:4::4:4.4.4.4"));
		assertFalse(IpAddrUtil.isIpv6Addr("4:4:z:4::4:4.4.4.4"));
		assertFalse(IpAddrUtil.isIpv6Addr("4:4:4:z::4:4.4.4.4"));
		assertFalse(IpAddrUtil.isIpv6Addr("4:4:4:4::z:4.4.4.4"));

		assertFalse(IpAddrUtil.isIpv6Addr("5:5:5:5:5::5:5.5.5.5"));
	}

	@Test
	public void testDecompressIpv6Addr() {
		assertEquals("2001:0db8:0020:0003:1000:0100:0020:0003",
				IpAddrUtil.decompressIpv6Addr("2001:db8:20:3:1000:100:20:3"));
		assertEquals("2001:0db8:0000:0000:1234:0000:0000:9abc",
				IpAddrUtil.decompressIpv6Addr("2001:db8::1234:0:0:9abc"));
		assertEquals("2001:0db8:0000:0000:0000:0000:0000:9abc", IpAddrUtil.decompressIpv6Addr("2001:db8::9abc"));
		assertEquals("0001:0000:0000:0001:0000:0000:0000:0001", IpAddrUtil.decompressIpv6Addr("1::1:0:0:0:1"));
		assertEquals("0001:0000:0000:0001:0000:0000:0001:0001", IpAddrUtil.decompressIpv6Addr("1:0:0:1::1:1"));
	}

	@Test
	public void testCompressIpv6Addr() {
		assertEquals("2001:db8:20:3:1000:100:20:3",
				IpAddrUtil.compressIpv6Addr("2001:0db8:0020:0003:1000:0100:0020:0003"));
		assertEquals("2001:db8::1234:0:0:9abc", IpAddrUtil.compressIpv6Addr("2001:0db8:0000:0000:1234:0000:0000:9abc"));
		assertEquals("2001:db8::9abc", IpAddrUtil.compressIpv6Addr("2001:0db8:0000:0000:0000:0000:0000:9abc"));
		assertEquals("1:0:0:1::1", IpAddrUtil.compressIpv6Addr("1::1:0:0:0:1"));
		assertEquals("1::1:0:0:1:1", IpAddrUtil.compressIpv6Addr("1:0:0:1::1:1"));
	}

	@Test
	public void testIpAddressCompression_全省略() {
		testIpv6AddressCompression("::", "0000:0000:0000:0000:0000:0000:0000:0000");
	}

	@Test
	public void testIpAddressCompression_省略無() {
		testIpv6AddressCompression("1:2:3:4:5:6:7:8", "0001:0002:0003:0004:0005:0006:0007:0008");
		testIpv6AddressCompression("11:22:33:44:55:66:77:88", "0011:0022:0033:0044:0055:0066:0077:0088");
		testIpv6AddressCompression("111:222:333:444:555:666:777:888", "0111:0222:0333:0444:0555:0666:0777:0888");
		testIpv6AddressCompression("1111:2222:3333:4444:5555:6666:7777:8888", "1111:2222:3333:4444:5555:6666:7777:8888");
	}

	@Test
	public void testIpAddressCompression_前省略() {
		testIpv6AddressCompression("::1", "0000:0000:0000:0000:0000:0000:0000:0001");
		testIpv6AddressCompression("::1:1", "0000:0000:0000:0000:0000:0000:0001:0001");
		testIpv6AddressCompression("::1:1:1", "0000:0000:0000:0000:0000:0001:0001:0001");
		testIpv6AddressCompression("::1:1:1:1", "0000:0000:0000:0000:0001:0001:0001:0001");
		testIpv6AddressCompression("::1:1:1:1:1", "0000:0000:0000:0001:0001:0001:0001:0001");
		testIpv6AddressCompression("::1:1:1:1:1:1", "0000:0000:0001:0001:0001:0001:0001:0001");

		// testIpv6AddressCompression("::1:1:1:1:1:1:1",
		// "0000:0001:0001:0001:0001:0001:0001:0001");
		assertEquals("0000:0001:0001:0001:0001:0001:0001:0001", IpAddrUtil.decompressIpv6Addr("::1:1:1:1:1:1:1"));
		assertEquals("0000:0001:0001:0001:0001:0001:0001:0001",
				IpAddrUtil.decompressIpv6Addr("0000:0001:0001:0001:0001:0001:0001:0001"));
		assertEquals("0:1:1:1:1:1:1:1", IpAddrUtil.compressIpv6Addr("0000:0001:0001:0001:0001:0001:0001:0001"));
		assertEquals("0:1:1:1:1:1:1:1", IpAddrUtil.compressIpv6Addr("::1:1:1:1:1:1:1"));
	}

	@Test
	public void testIpAddressCompression_後省略() {
		testIpv6AddressCompression("1::", "0001:0000:0000:0000:0000:0000:0000:0000");
		testIpv6AddressCompression("1:1::", "0001:0001:0000:0000:0000:0000:0000:0000");
		testIpv6AddressCompression("1:1:1::", "0001:0001:0001:0000:0000:0000:0000:0000");
		testIpv6AddressCompression("1:1:1:1::", "0001:0001:0001:0001:0000:0000:0000:0000");
		testIpv6AddressCompression("1:1:1:1:1::", "0001:0001:0001:0001:0001:0000:0000:0000");
		testIpv6AddressCompression("1:1:1:1:1:1::", "0001:0001:0001:0001:0001:0001:0000:0000");

		// testIpv6AddressCompression("1:1:1:1:1:1:1::",
		// "0001:0001:0001:0001:0001:0001:0001:0000");
		assertEquals("0001:0001:0001:0001:0001:0001:0001:0000", IpAddrUtil.decompressIpv6Addr("1:1:1:1:1:1:1::"));
		assertEquals("0001:0001:0001:0001:0001:0001:0001:0000",
				IpAddrUtil.decompressIpv6Addr("0001:0001:0001:0001:0001:0001:0001:0000"));
		assertEquals("1:1:1:1:1:1:1:0", IpAddrUtil.compressIpv6Addr("0001:0001:0001:0001:0001:0001:0001:0000"));
		assertEquals("1:1:1:1:1:1:1:0", IpAddrUtil.compressIpv6Addr("1:1:1:1:1:1:1::"));
	}

	@Test
	public void testIpAddressCompression_中省略() {

		testIpv6AddressCompression("1::1", "0001:0000:0000:0000:0000:0000:0000:0001");
		testIpv6AddressCompression("1::1:1", "0001:0000:0000:0000:0000:0000:0001:0001");
		testIpv6AddressCompression("1::1:1:1", "0001:0000:0000:0000:0000:0001:0001:0001");
		testIpv6AddressCompression("1::1:1:1:1", "0001:0000:0000:0000:0001:0001:0001:0001");
		testIpv6AddressCompression("1::1:1:1:1:1", "0001:0000:0000:0001:0001:0001:0001:0001");
		// testIpv6AddressCompression("1::1:1:1:1:1:1",
		// "0001:0000:0001:0001:0001:0001:0001:0001");
		assertEquals("0001:0000:0001:0001:0001:0001:0001:0001", IpAddrUtil.decompressIpv6Addr("1::1:1:1:1:1:1"));
		assertEquals("0001:0000:0001:0001:0001:0001:0001:0001",
				IpAddrUtil.decompressIpv6Addr("0001:0000:0001:0001:0001:0001:0001:0001"));
		assertEquals("1:0:1:1:1:1:1:1", IpAddrUtil.compressIpv6Addr("0001:0000:0001:0001:0001:0001:0001:0001"));
		assertEquals("1:0:1:1:1:1:1:1", IpAddrUtil.compressIpv6Addr("1::1:1:1:1:1:1"));

		testIpv6AddressCompression("2:2::2", "0002:0002:0000:0000:0000:0000:0000:0002");
		testIpv6AddressCompression("2:2::2:2", "0002:0002:0000:0000:0000:0000:0002:0002");
		testIpv6AddressCompression("2:2::2:2:2", "0002:0002:0000:0000:0000:0002:0002:0002");
		testIpv6AddressCompression("2:2::2:2:2:2", "0002:0002:0000:0000:0002:0002:0002:0002");
		// testIpv6AddressCompression("2:2::2:2:2:2:2",
		// "0002:0002:0000:0002:0002:0002:0002:0002");
		assertEquals("0002:0002:0000:0002:0002:0002:0002:0002", IpAddrUtil.decompressIpv6Addr("2:2::2:2:2:2:2"));
		assertEquals("0002:0002:0000:0002:0002:0002:0002:0002",
				IpAddrUtil.decompressIpv6Addr("0002:0002:0000:0002:0002:0002:0002:0002"));
		assertEquals("2:2:0:2:2:2:2:2", IpAddrUtil.compressIpv6Addr("0002:0002:0000:0002:0002:0002:0002:0002"));
		assertEquals("2:2:0:2:2:2:2:2", IpAddrUtil.compressIpv6Addr("2:2::2:2:2:2:2"));

		testIpv6AddressCompression("3:3:3::3", "0003:0003:0003:0000:0000:0000:0000:0003");
		testIpv6AddressCompression("3:3:3::3:3", "0003:0003:0003:0000:0000:0000:0003:0003");
		testIpv6AddressCompression("3:3:3::3:3:3", "0003:0003:0003:0000:0000:0003:0003:0003");
		// testIpv6AddressCompression("3:3:3::3:3:3:3",
		// "0003:0003:0003:0000:0003:0003:0003:0003");
		assertEquals("0003:0003:0003:0000:0003:0003:0003:0003", IpAddrUtil.decompressIpv6Addr("3:3:3::3:3:3:3"));
		assertEquals("0003:0003:0003:0000:0003:0003:0003:0003",
				IpAddrUtil.decompressIpv6Addr("0003:0003:0003:0000:0003:0003:0003:0003"));
		assertEquals("3:3:3:0:3:3:3:3", IpAddrUtil.compressIpv6Addr("0003:0003:0003:0000:0003:0003:0003:0003"));
		assertEquals("3:3:3:0:3:3:3:3", IpAddrUtil.compressIpv6Addr("3:3:3::3:3:3:3"));

		testIpv6AddressCompression("4:4:4:4::4", "0004:0004:0004:0004:0000:0000:0000:0004");
		testIpv6AddressCompression("4:4:4:4::4:4", "0004:0004:0004:0004:0000:0000:0004:0004");
		// testIpv6AddressCompression("4:4:4:4::4:4:4",
		// "0004:0004:0004:0004:0000:0004:0004:0004");
		assertEquals("0004:0004:0004:0004:0000:0004:0004:0004", IpAddrUtil.decompressIpv6Addr("4:4:4:4::4:4:4"));
		assertEquals("0004:0004:0004:0004:0000:0004:0004:0004",
				IpAddrUtil.decompressIpv6Addr("0004:0004:0004:0004:0000:0004:0004:0004"));
		assertEquals("4:4:4:4:0:4:4:4", IpAddrUtil.compressIpv6Addr("0004:0004:0004:0004:0000:0004:0004:0004"));
		assertEquals("4:4:4:4:0:4:4:4", IpAddrUtil.compressIpv6Addr("4:4:4:4::4:4:4"));

		testIpv6AddressCompression("5:5:5:5:5::5", "0005:0005:0005:0005:0005:0000:0000:0005");
		// testIpv6AddressCompression("5:5:5:5:5::5:5",
		// "0005:0005:0005:0005:0005:0000:0005:0005");
		assertEquals("0005:0005:0005:0005:0005:0000:0005:0005", IpAddrUtil.decompressIpv6Addr("5:5:5:5:5::5:5"));
		assertEquals("0005:0005:0005:0005:0005:0000:0005:0005",
				IpAddrUtil.decompressIpv6Addr("0005:0005:0005:0005:0005:0000:0005:0005"));
		assertEquals("5:5:5:5:5:0:5:5", IpAddrUtil.compressIpv6Addr("0005:0005:0005:0005:0005:0000:0005:0005"));
		assertEquals("5:5:5:5:5:0:5:5", IpAddrUtil.compressIpv6Addr("5:5:5:5:5::5:5"));

		// testIpv6AddressCompression("6:6:6:6:6:6::6",
		// "0006:0006:0006:0006:0006:0006:0000:0006");
		assertEquals("0006:0006:0006:0006:0006:0006:0000:0006", IpAddrUtil.decompressIpv6Addr("6:6:6:6:6:6::6"));
		assertEquals("0006:0006:0006:0006:0006:0006:0000:0006",
				IpAddrUtil.decompressIpv6Addr("0006:0006:0006:0006:0006:0006:0000:0006"));
		assertEquals("6:6:6:6:6:6:0:6", IpAddrUtil.compressIpv6Addr("0006:0006:0006:0006:0006:0006:0000:0006"));
		assertEquals("6:6:6:6:6:6:0:6", IpAddrUtil.compressIpv6Addr("6:6:6:6:6:6::6"));
	}

	@Test
	public void testIpAddressCompression_後IPv4全省略() {
		testIpv6AddressCompression("::1.1.1.1", "0000:0000:0000:0000:0000:0000:1.1.1.1");
	}

	@Test
	public void testIpAddressCompression_後IPv4省略無() {
		testIpv6AddressCompression("1:2:3:4:5:6:1.1.1.1", "0001:0002:0003:0004:0005:0006:1.1.1.1");
		testIpv6AddressCompression("11:22:33:44:55:66:1.1.1.1", "0011:0022:0033:0044:0055:0066:1.1.1.1");
		testIpv6AddressCompression("111:222:333:444:555:666:1.1.1.1", "0111:0222:0333:0444:0555:0666:1.1.1.1");
		testIpv6AddressCompression("1111:2222:3333:4444:5555:6666:1.1.1.1", "1111:2222:3333:4444:5555:6666:1.1.1.1");
	}

	@Test
	public void testIpAddressCompression_後IPv4前省略() {
		testIpv6AddressCompression("::1:1.1.1.1", "0000:0000:0000:0000:0000:0001:1.1.1.1");
		testIpv6AddressCompression("::1:1:1.1.1.1", "0000:0000:0000:0000:0001:0001:1.1.1.1");
		testIpv6AddressCompression("::1:1:1:1.1.1.1", "0000:0000:0000:0001:0001:0001:1.1.1.1");
		testIpv6AddressCompression("::1:1:1:1:1.1.1.1", "0000:0000:0001:0001:0001:0001:1.1.1.1");
		// testIpv6AddressCompression("::1:1:1:1:1:1.1.1.1",
		// "0000:0001:0001:0001:0001:0001:1.1.1.1");
		assertEquals("0000:0001:0001:0001:0001:0001:1.1.1.1", IpAddrUtil.decompressIpv6Addr("::1:1:1:1:1:1.1.1.1"));
		assertEquals("0000:0001:0001:0001:0001:0001:1.1.1.1",
				IpAddrUtil.decompressIpv6Addr("0000:0001:0001:0001:0001:0001:1.1.1.1"));
		assertEquals("0:1:1:1:1:1:1.1.1.1", IpAddrUtil.compressIpv6Addr("0000:0001:0001:0001:0001:0001:1.1.1.1"));
		assertEquals("0:1:1:1:1:1:1.1.1.1", IpAddrUtil.compressIpv6Addr("::1:1:1:1:1:1.1.1.1"));
	}

	@Test
	public void testIpAddressCompression_後IPv4後省略() {
		testIpv6AddressCompression("1::1.1.1.1", "0001:0000:0000:0000:0000:0000:1.1.1.1");
		testIpv6AddressCompression("1:1::1.1.1.1", "0001:0001:0000:0000:0000:0000:1.1.1.1");
		testIpv6AddressCompression("1:1:1::1.1.1.1", "0001:0001:0001:0000:0000:0000:1.1.1.1");
		testIpv6AddressCompression("1:1:1:1::1.1.1.1", "0001:0001:0001:0001:0000:0000:1.1.1.1");
		// testIpv6AddressCompression("1:1:1:1:1::1.1.1.1",
		// "0001:0001:0001:0001:0001:0000:1.1.1.1");
		assertEquals("0001:0001:0001:0001:0001:0000:1.1.1.1", IpAddrUtil.decompressIpv6Addr("1:1:1:1:1::1.1.1.1"));
		assertEquals("0001:0001:0001:0001:0001:0000:1.1.1.1",
				IpAddrUtil.decompressIpv6Addr("0001:0001:0001:0001:0001:0000:1.1.1.1"));
		assertEquals("1:1:1:1:1:0:1.1.1.1", IpAddrUtil.compressIpv6Addr("0001:0001:0001:0001:0001:0000:1.1.1.1"));
		assertEquals("1:1:1:1:1:0:1.1.1.1", IpAddrUtil.compressIpv6Addr("1:1:1:1:1::1.1.1.1"));
	}

	@Test
	public void testIpAddressCompression_後IPv4中省略() {

		testIpv6AddressCompression("1::1:1.1.1.1", "0001:0000:0000:0000:0000:0001:1.1.1.1");
		testIpv6AddressCompression("1::1:1:1.1.1.1", "0001:0000:0000:0000:0001:0001:1.1.1.1");
		testIpv6AddressCompression("1::1:1:1:1.1.1.1", "0001:0000:0000:0001:0001:0001:1.1.1.1");
		// testIpv6AddressCompression("1::1:1:1:1:1.1.1.1",
		// "0001:0000:0001:0001:0001:0001:1.1.1.1");
		assertEquals("0001:0000:0001:0001:0001:0001:1.1.1.1", IpAddrUtil.decompressIpv6Addr("1::1:1:1:1:1.1.1.1"));
		assertEquals("0001:0000:0001:0001:0001:0001:1.1.1.1",
				IpAddrUtil.decompressIpv6Addr("0001:0000:0001:0001:0001:0001:1.1.1.1"));
		assertEquals("1:0:1:1:1:1:1.1.1.1", IpAddrUtil.compressIpv6Addr("0001:0000:0001:0001:0001:0001:1.1.1.1"));
		assertEquals("1:0:1:1:1:1:1.1.1.1", IpAddrUtil.compressIpv6Addr("1::1:1:1:1:1.1.1.1"));

		testIpv6AddressCompression("2:2::2:2.2.2.2", "0002:0002:0000:0000:0000:0002:2.2.2.2");
		testIpv6AddressCompression("2:2::2:2:2.2.2.2", "0002:0002:0000:0000:0002:0002:2.2.2.2");
		// testIpv6AddressCompression("2:2::2:2:2:2.2.2.2",
		// "0002:0002:0000:0002:0002:0002:2.2.2.2");
		assertEquals("0002:0002:0000:0002:0002:0002:2.2.2.2", IpAddrUtil.decompressIpv6Addr("2:2::2:2:2:2.2.2.2"));
		assertEquals("0002:0002:0000:0002:0002:0002:2.2.2.2",
				IpAddrUtil.decompressIpv6Addr("0002:0002:0000:0002:0002:0002:2.2.2.2"));
		assertEquals("2:2:0:2:2:2:2.2.2.2", IpAddrUtil.compressIpv6Addr("0002:0002:0000:0002:0002:0002:2.2.2.2"));
		assertEquals("2:2:0:2:2:2:2.2.2.2", IpAddrUtil.compressIpv6Addr("2:2::2:2:2:2.2.2.2"));

		testIpv6AddressCompression("3:3:3::3:3.3.3.3", "0003:0003:0003:0000:0000:0003:3.3.3.3");
		// testIpv6AddressCompression("3:3:3::3:3:3.3.3.3",
		// "0003:0003:0003:0000:0003:0003:3.3.3.3");
		assertEquals("0003:0003:0003:0000:0003:0003:3.3.3.3", IpAddrUtil.decompressIpv6Addr("3:3:3::3:3:3.3.3.3"));
		assertEquals("0003:0003:0003:0000:0003:0003:3.3.3.3",
				IpAddrUtil.decompressIpv6Addr("0003:0003:0003:0000:0003:0003:3.3.3.3"));
		assertEquals("3:3:3:0:3:3:3.3.3.3", IpAddrUtil.compressIpv6Addr("0003:0003:0003:0000:0003:0003:3.3.3.3"));
		assertEquals("3:3:3:0:3:3:3.3.3.3", IpAddrUtil.compressIpv6Addr("3:3:3::3:3:3.3.3.3"));

		// testIpv6AddressCompression("4:4:4:4::4:4.4.4.4",
		// "0004:0004:0004:0004:0000:0004:4.4.4.4");
		assertEquals("0004:0004:0004:0004:0000:0004:4.4.4.4", IpAddrUtil.decompressIpv6Addr("4:4:4:4::4:4.4.4.4"));
		assertEquals("0004:0004:0004:0004:0000:0004:4.4.4.4",
				IpAddrUtil.decompressIpv6Addr("0004:0004:0004:0004:0000:0004:4.4.4.4"));
		assertEquals("4:4:4:4:0:4:4.4.4.4", IpAddrUtil.compressIpv6Addr("0004:0004:0004:0004:0000:0004:4.4.4.4"));
		assertEquals("4:4:4:4:0:4:4.4.4.4", IpAddrUtil.compressIpv6Addr("4:4:4:4::4:4.4.4.4"));
	}

	private void testIpv6AddressCompression(String comped, String decomped) {
		assertEquals(decomped, IpAddrUtil.decompressIpv6Addr(comped));
		assertEquals(decomped, IpAddrUtil.decompressIpv6Addr(decomped));
		assertEquals(comped, IpAddrUtil.compressIpv6Addr(decomped));
		assertEquals(comped, IpAddrUtil.compressIpv6Addr(comped));
	}

	@Test
	public void testGetIpv4AddrAsNumber() {
		assertEquals(BigInteger.valueOf(0x00000000L), IpAddrUtil.getIpv4AddrAsNumber("0.0.0.0"));
		assertEquals(BigInteger.valueOf(0x000000FFL), IpAddrUtil.getIpv4AddrAsNumber("0.0.0.255"));
		assertEquals(BigInteger.valueOf(0x0000FF00L), IpAddrUtil.getIpv4AddrAsNumber("0.0.255.0"));
		assertEquals(BigInteger.valueOf(0x00FF0000L), IpAddrUtil.getIpv4AddrAsNumber("0.255.0.0"));
		assertEquals(BigInteger.valueOf(0xFF000000L), IpAddrUtil.getIpv4AddrAsNumber("255.0.0.0"));
		assertEquals(BigInteger.valueOf(0xFFFFFFFFL), IpAddrUtil.getIpv4AddrAsNumber("255.255.255.255"));
	}

	@Test
	public void testGetIpv6AsNumber() {
		assertEquals(new BigInteger("00000000000000000000000000000000", 16),
				IpAddrUtil.getIpv6AddrAsNumber("0000:0000:0000:0000:0000:0000:0000:0000"));
		assertEquals(new BigInteger("00000000000000000000000000000000", 16), IpAddrUtil.getIpv6AddrAsNumber("::"));
		assertEquals(new BigInteger("000000000000000000000000ffff0000", 16),
				IpAddrUtil.getIpv6AddrAsNumber("0000:0000:0000:0000:0000:0000:ffff:0000"));
		assertEquals(new BigInteger("000000000000000000000000ffff0000", 16), IpAddrUtil.getIpv6AddrAsNumber("::ffff:0"));
		assertEquals(new BigInteger("00000000000000000000ffff00000000", 16),
				IpAddrUtil.getIpv6AddrAsNumber("0000:0000:0000:0000:0000:ffff:0000:0000"));
		assertEquals(new BigInteger("00000000000000000000ffff00000000", 16),
				IpAddrUtil.getIpv6AddrAsNumber("::ffff:0:0"));
		assertEquals(new BigInteger("0000000000000000ffff000000000000", 16),
				IpAddrUtil.getIpv6AddrAsNumber("0000:0000:0000:0000:ffff:0000:0000:0000"));
		assertEquals(new BigInteger("0000000000000000ffff000000000000", 16),
				IpAddrUtil.getIpv6AddrAsNumber("::ffff:0:0:0"));
		assertEquals(new BigInteger("000000000000ffff0000000000000000", 16),
				IpAddrUtil.getIpv6AddrAsNumber("0000:0000:0000:ffff:0000:0000:0000:0000"));
		assertEquals(new BigInteger("000000000000ffff0000000000000000", 16),
				IpAddrUtil.getIpv6AddrAsNumber("0:0:0:ffff::"));
		assertEquals(new BigInteger("00000000ffff00000000000000000000", 16),
				IpAddrUtil.getIpv6AddrAsNumber("0000:0000:ffff:0000:0000:0000:0000:0000"));
		assertEquals(new BigInteger("00000000ffff00000000000000000000", 16),
				IpAddrUtil.getIpv6AddrAsNumber("0:0:ffff::"));
		assertEquals(new BigInteger("0000ffff000000000000000000000000", 16),
				IpAddrUtil.getIpv6AddrAsNumber("0000:ffff:0000:0000:0000:0000:0000:0000"));
		assertEquals(new BigInteger("0000ffff000000000000000000000000", 16), IpAddrUtil.getIpv6AddrAsNumber("0:ffff::"));
		assertEquals(new BigInteger("ffff0000000000000000000000000000", 16),
				IpAddrUtil.getIpv6AddrAsNumber("ffff:0000:0000:0000:0000:0000:0000:0000"));
		assertEquals(new BigInteger("ffff0000000000000000000000000000", 16), IpAddrUtil.getIpv6AddrAsNumber("ffff::"));
		assertEquals(new BigInteger("ffff000000000000000000000000ffff", 16),
				IpAddrUtil.getIpv6AddrAsNumber("ffff:0000:0000:0000:0000:0000:0000:ffff"));
		assertEquals(new BigInteger("ffff000000000000000000000000ffff", 16),
				IpAddrUtil.getIpv6AddrAsNumber("ffff::ffff"));
		assertEquals(new BigInteger("ffffffffffffffffffffffffffffffff", 16),
				IpAddrUtil.getIpv6AddrAsNumber("ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff"));
	}

	@Test
	public void testGetIpv6AsNumber_後IPv4() {
		assertEquals(new BigInteger("00000000000000000000000000000000", 16),
				IpAddrUtil.getIpv6AddrAsNumber("0000:0000:0000:0000:0000:0000:0.0.0.0"));
		assertEquals(new BigInteger("00000000000000000000000000000000", 16),
				IpAddrUtil.getIpv6AddrAsNumber("::0.0.0.0"));
		assertEquals(new BigInteger("000000000000000000000000ffffffff", 16),
				IpAddrUtil.getIpv6AddrAsNumber("0000:0000:0000:0000:0000:0000:255.255.255.255"));
		assertEquals(new BigInteger("000000000000000000000000ffffffff", 16),
				IpAddrUtil.getIpv6AddrAsNumber("::255.255.255.255"));
		assertEquals(new BigInteger("00000000000000000000ffffffffffff", 16),
				IpAddrUtil.getIpv6AddrAsNumber("0000:0000:0000:0000:0000:ffff:255.255.255.255"));
		assertEquals(new BigInteger("00000000000000000000ffffffffffff", 16),
				IpAddrUtil.getIpv6AddrAsNumber("::ffff:255.255.255.255"));
		assertEquals(new BigInteger("0000000000000000ffff0000ffffffff", 16),
				IpAddrUtil.getIpv6AddrAsNumber("0000:0000:0000:0000:ffff:0000:255.255.255.255"));
		assertEquals(new BigInteger("0000000000000000ffff0000ffffffff", 16),
				IpAddrUtil.getIpv6AddrAsNumber("::ffff:0:255.255.255.255"));
		assertEquals(new BigInteger("000000000000ffff00000000ffffffff", 16),
				IpAddrUtil.getIpv6AddrAsNumber("0000:0000:0000:ffff:0000:0000:255.255.255.255"));
		assertEquals(new BigInteger("000000000000ffff00000000ffffffff", 16),
				IpAddrUtil.getIpv6AddrAsNumber("::ffff:0:0:255.255.255.255"));
		assertEquals(new BigInteger("00000000ffff000000000000ffffffff", 16),
				IpAddrUtil.getIpv6AddrAsNumber("0000:0000:ffff:0000:0000:0000:255.255.255.255"));
		assertEquals(new BigInteger("00000000ffff000000000000ffffffff", 16),
				IpAddrUtil.getIpv6AddrAsNumber("0:0:ffff::255.255.255.255"));
		assertEquals(new BigInteger("0000ffff0000000000000000ffffffff", 16),
				IpAddrUtil.getIpv6AddrAsNumber("0000:ffff:0000:0000:0000:0000:255.255.255.255"));
		assertEquals(new BigInteger("0000ffff0000000000000000ffffffff", 16),
				IpAddrUtil.getIpv6AddrAsNumber("0:ffff::255.255.255.255"));
		assertEquals(new BigInteger("ffff00000000000000000000ffffffff", 16),
				IpAddrUtil.getIpv6AddrAsNumber("ffff:0000:0000:0000:0000:0000:255.255.255.255"));
		assertEquals(new BigInteger("ffff00000000000000000000ffffffff", 16),
				IpAddrUtil.getIpv6AddrAsNumber("ffff::255.255.255.255"));
		assertEquals(new BigInteger("ffff0000000000000000ffffffffffff", 16),
				IpAddrUtil.getIpv6AddrAsNumber("ffff:0000:0000:0000:0000:ffff:255.255.255.255"));
		assertEquals(new BigInteger("ffff0000000000000000ffffffffffff", 16),
				IpAddrUtil.getIpv6AddrAsNumber("ffff::ffff:255.255.255.255"));
		assertEquals(new BigInteger("ffffffffffffffffffffffffffffffff", 16),
				IpAddrUtil.getIpv6AddrAsNumber("ffff:ffff:ffff:ffff:ffff:ffff:255.255.255.255"));
	}

	@Test
	public void testGetIpv4AddrMask() {
		assertEquals(new BigInteger("ffffffff", 16), IpAddrUtil.getIpv4AddrMask(33));
		assertEquals(new BigInteger("ffffffff", 16), IpAddrUtil.getIpv4AddrMask(32));
		assertEquals(new BigInteger("ffffff00", 16), IpAddrUtil.getIpv4AddrMask(24));
		assertEquals(new BigInteger("ffff0000", 16), IpAddrUtil.getIpv4AddrMask(16));
		assertEquals(new BigInteger("ff000000", 16), IpAddrUtil.getIpv4AddrMask(8));
		assertEquals(new BigInteger("00000000", 16), IpAddrUtil.getIpv4AddrMask(0));
		assertEquals(new BigInteger("00000000", 16), IpAddrUtil.getIpv4AddrMask(-1));
	}

	@Test
	public void testGetIpv6AddrMask() {
		assertEquals(new BigInteger("ffffffffffffffffffffffffffffffff", 16), IpAddrUtil.getIpv6AddrMask(129));
		assertEquals(new BigInteger("ffffffffffffffffffffffffffffffff", 16), IpAddrUtil.getIpv6AddrMask(128));
		assertEquals(new BigInteger("ffffffffffffffffffffffffffff0000", 16), IpAddrUtil.getIpv6AddrMask(112));
		assertEquals(new BigInteger("ffffffffffffffffffffffff00000000", 16), IpAddrUtil.getIpv6AddrMask(96));
		assertEquals(new BigInteger("ffffffffffffffffffff000000000000", 16), IpAddrUtil.getIpv6AddrMask(80));
		assertEquals(new BigInteger("ffffffffffffffff0000000000000000", 16), IpAddrUtil.getIpv6AddrMask(64));
		assertEquals(new BigInteger("ffffffffffff00000000000000000000", 16), IpAddrUtil.getIpv6AddrMask(48));
		assertEquals(new BigInteger("ffffffff000000000000000000000000", 16), IpAddrUtil.getIpv6AddrMask(32));
		assertEquals(new BigInteger("ffff0000000000000000000000000000", 16), IpAddrUtil.getIpv6AddrMask(16));
		assertEquals(new BigInteger("00000000000000000000000000000000", 16), IpAddrUtil.getIpv6AddrMask(0));
		assertEquals(new BigInteger("00000000000000000000000000000000", 16), IpAddrUtil.getIpv6AddrMask(-1));
	}

	@Test
	public void testGetIpv4AddrFromNumber() {
		assertEquals("255.255.255.255", IpAddrUtil.getIpv4AddrFromNumber(new BigInteger("ffffffff", 16)));
		assertEquals("255.255.255.0", IpAddrUtil.getIpv4AddrFromNumber(new BigInteger("ffffff00", 16)));
		assertEquals("255.255.0.0", IpAddrUtil.getIpv4AddrFromNumber(new BigInteger("ffff0000", 16)));
		assertEquals("255.0.0.0", IpAddrUtil.getIpv4AddrFromNumber(new BigInteger("ff000000", 16)));
		assertEquals("0.0.0.0", IpAddrUtil.getIpv4AddrFromNumber(new BigInteger("00000000", 16)));
	}

	@Test
	public void testGetIpv6AddrFromNumber() {
		assertEquals("ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff",
				IpAddrUtil.getIpv6AddrFromNumber(new BigInteger("ffffffffffffffffffffffffffffffff", 16)));
		assertEquals("ffff:ffff:ffff:ffff:ffff:ffff:ffff:0000",
				IpAddrUtil.getIpv6AddrFromNumber(new BigInteger("ffffffffffffffffffffffffffff0000", 16)));
		assertEquals("ffff:ffff:ffff:ffff:ffff:ffff:0000:0000",
				IpAddrUtil.getIpv6AddrFromNumber(new BigInteger("ffffffffffffffffffffffff00000000", 16)));
		assertEquals("ffff:ffff:ffff:ffff:ffff:0000:0000:0000",
				IpAddrUtil.getIpv6AddrFromNumber(new BigInteger("ffffffffffffffffffff000000000000", 16)));
		assertEquals("ffff:ffff:ffff:ffff:0000:0000:0000:0000",
				IpAddrUtil.getIpv6AddrFromNumber(new BigInteger("ffffffffffffffff0000000000000000", 16)));
		assertEquals("ffff:ffff:ffff:0000:0000:0000:0000:0000",
				IpAddrUtil.getIpv6AddrFromNumber(new BigInteger("ffffffffffff00000000000000000000", 16)));
		assertEquals("ffff:ffff:0000:0000:0000:0000:0000:0000",
				IpAddrUtil.getIpv6AddrFromNumber(new BigInteger("ffffffff000000000000000000000000", 16)));
		assertEquals("ffff:0000:0000:0000:0000:0000:0000:0000",
				IpAddrUtil.getIpv6AddrFromNumber(new BigInteger("ffff0000000000000000000000000000", 16)));
		assertEquals("0000:0000:0000:0000:0000:0000:0000:0000",
				IpAddrUtil.getIpv6AddrFromNumber(new BigInteger("00000000000000000000000000000000", 16)));
	}

	@Test
	public void testMisc() {
		try {
			new IpAddrUtil();
		} catch (Exception ex) {
			fail("Exception must not be thrown");
		}
	}
}
