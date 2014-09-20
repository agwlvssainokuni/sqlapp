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

package cherry.spring.common.custom.format;

import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import cherry.spring.common.lib.ipaddr.IpAddrUtil;
import cherry.spring.common.log.Log;
import cherry.spring.common.log.LogFactory;

public class IpAddrFormatAnnotationFormatterFactory implements
		AnnotationFormatterFactory<IpAddrFormat> {

	private Log log = LogFactory.getLog(getClass());

	private Set<Class<?>> fieldTypes = createFieldTypes();

	private Set<Class<?>> createFieldTypes() {
		return new HashSet<Class<?>>(Arrays.asList(InetAddress.class,
				Inet4Address.class, Inet6Address.class, BigInteger.class,
				String.class));
	}

	@Override
	public Set<Class<?>> getFieldTypes() {
		return fieldTypes;
	}

	@Override
	public Printer<?> getPrinter(IpAddrFormat annotation, Class<?> fieldType) {
		if (fieldType == InetAddress.class) {
			return new InetAddressFormatter(annotation.v6compress());
		} else if (fieldType == Inet4Address.class) {
			return new Inet4AddressFormatter();
		} else if (fieldType == Inet6Address.class) {
			return new Inet6AddressFormatter(annotation.v6compress());
		} else if (fieldType == BigInteger.class) {
			return new BigIntegerFormatter(annotation.value(),
					annotation.v6compress());
		} else if (fieldType == String.class) {
			return new StringFormatter(annotation.value(),
					annotation.v6compress());
		} else {
			throw new IllegalStateException();
		}
	}

	@Override
	public Parser<?> getParser(IpAddrFormat annotation, Class<?> fieldType) {
		if (fieldType == InetAddress.class) {
			return new InetAddressFormatter(annotation.v6compress());
		} else if (fieldType == Inet4Address.class) {
			return new Inet4AddressFormatter();
		} else if (fieldType == Inet6Address.class) {
			return new Inet6AddressFormatter(annotation.v6compress());
		} else if (fieldType == BigInteger.class) {
			return new BigIntegerFormatter(annotation.value(),
					annotation.v6compress());
		} else if (fieldType == String.class) {
			return new StringFormatter(annotation.value(),
					annotation.v6compress());
		} else {
			throw new IllegalStateException();
		}
	}

	class InetAddressFormatter implements Printer<InetAddress>,
			Parser<InetAddress> {

		private boolean v6compress;

		InetAddressFormatter(boolean v6compress) {
			this.v6compress = v6compress;
		}

		@Override
		public String print(InetAddress object, Locale locale) {
			String addr = object.getHostAddress();
			if (v6compress && object instanceof Inet6Address) {
				addr = IpAddrUtil.compressIpv6Addr(addr);
			}
			return addr;
		}

		@Override
		public InetAddress parse(String text, Locale locale)
				throws ParseException {
			if (IpAddrUtil.isIpv4Addr(text) || IpAddrUtil.isIpv6Addr(text)) {
				try {
					return InetAddress.getByName(text);
				} catch (UnknownHostException ex) {
					log.debug(ex, "Invalid InetAddress format: {0}", text);
				}
			}
			throw new ParseException("Invlaid InetAddress format: " + text, 0);
		}
	}

	class Inet4AddressFormatter implements Printer<Inet4Address>,
			Parser<Inet4Address> {

		@Override
		public String print(Inet4Address object, Locale locale) {
			return object.getHostAddress();
		}

		@Override
		public Inet4Address parse(String text, Locale locale)
				throws ParseException {
			if (IpAddrUtil.isIpv4Addr(text)) {
				try {
					return (Inet4Address) InetAddress.getByName(text);
				} catch (UnknownHostException ex) {
					log.debug(ex, "Invalid Inet4Address format: {0}", text);
				}
			}
			throw new ParseException("Invlaid Inet4Address format: " + text, 0);
		}
	}

	class Inet6AddressFormatter implements Printer<Inet6Address>,
			Parser<Inet6Address> {

		private boolean v6compress;

		Inet6AddressFormatter(boolean v6compress) {
			this.v6compress = v6compress;
		}

		@Override
		public String print(Inet6Address object, Locale locale) {
			String addr = object.getHostAddress();
			if (v6compress) {
				addr = IpAddrUtil.compressIpv6Addr(addr);
			}
			return addr;
		}

		@Override
		public Inet6Address parse(String text, Locale locale)
				throws ParseException {
			if (IpAddrUtil.isIpv6Addr(text)) {
				try {
					return (Inet6Address) InetAddress.getByName(text);
				} catch (UnknownHostException ex) {
					log.debug(ex, "Invalid Inet6Address format: {0}", text);
				}
			}
			throw new ParseException("Invlaid Inet6Address format: " + text, 0);
		}
	}

	class BigIntegerFormatter implements Printer<BigInteger>,
			Parser<BigInteger> {

		private IpAddrFormat.Version version;

		private boolean v6compress;

		BigIntegerFormatter(IpAddrFormat.Version version, boolean v6compress) {
			this.version = version;
			this.v6compress = v6compress;
		}

		@Override
		public String print(BigInteger object, Locale locale) {
			if (version == IpAddrFormat.Version.V4) {
				return IpAddrUtil.getIpv4AddrFromNumber(object);
			} else if (version == IpAddrFormat.Version.V6) {
				String addr = IpAddrUtil.getIpv6AddrFromNumber(object);
				if (v6compress) {
					addr = IpAddrUtil.compressIpv6Addr(addr);
				}
				return addr;
			} else {
				if (object.toByteArray().length == 4) {
					return IpAddrUtil.getIpv4AddrFromNumber(object);
				} else {
					String addr = IpAddrUtil.getIpv6AddrFromNumber(object);
					if (v6compress) {
						addr = IpAddrUtil.compressIpv6Addr(addr);
					}
					return addr;
				}
			}
		}

		@Override
		public BigInteger parse(String text, Locale locale)
				throws ParseException {
			if (version == IpAddrFormat.Version.V4) {
				if (IpAddrUtil.isIpv4Addr(text)) {
					return IpAddrUtil.getIpv4AddrAsNumber(text);
				}
				throw new ParseException("Invlaid IPv4 format: " + text, 0);
			} else if (version == IpAddrFormat.Version.V6) {
				if (IpAddrUtil.isIpv6Addr(text)) {
					return IpAddrUtil.getIpv6AddrAsNumber(text);
				}
				throw new ParseException("Invlaid IPv6 format: " + text, 0);
			} else {
				if (IpAddrUtil.isIpv4Addr(text)) {
					return IpAddrUtil.getIpv4AddrAsNumber(text);
				}
				if (IpAddrUtil.isIpv6Addr(text)) {
					return IpAddrUtil.getIpv6AddrAsNumber(text);
				}
				throw new ParseException("Invalid IPv4/IPv6 format: " + text, 0);
			}
		}
	}

	class StringFormatter implements Printer<String>, Parser<String> {

		private IpAddrFormat.Version version;

		private boolean v6compress;

		StringFormatter(IpAddrFormat.Version version, boolean v6compress) {
			this.version = version;
			this.v6compress = v6compress;
		}

		@Override
		public String print(String object, Locale locale) {
			if (IpAddrUtil.isIpv4Addr(object)) {
				BigInteger addr = IpAddrUtil.getIpv4AddrAsNumber(object);
				return IpAddrUtil.getIpv4AddrFromNumber(addr);
			} else if (IpAddrUtil.isIpv6Addr(object)) {
				if (v6compress) {
					return IpAddrUtil.compressIpv6Addr(object);
				} else {
					return IpAddrUtil.decompressIpv6Addr(object);
				}
			} else {
				throw new IllegalArgumentException("Invalid IPv4/IPv6 format: "
						+ object);
			}
		}

		@Override
		public String parse(String text, Locale locale) throws ParseException {
			if (version == IpAddrFormat.Version.V4) {
				if (IpAddrUtil.isIpv4Addr(text)) {
					BigInteger addr = IpAddrUtil.getIpv4AddrAsNumber(text);
					return IpAddrUtil.getIpv4AddrFromNumber(addr);
				}
				throw new ParseException("Invlaid IPv4 format: " + text, 0);
			} else if (version == IpAddrFormat.Version.V6) {
				if (IpAddrUtil.isIpv6Addr(text)) {
					if (v6compress) {
						return IpAddrUtil.compressIpv6Addr(text);
					} else {
						return IpAddrUtil.decompressIpv6Addr(text);
					}
				}
				throw new ParseException("Invlaid IPv6 format: " + text, 0);
			} else {
				if (IpAddrUtil.isIpv4Addr(text)) {
					BigInteger addr = IpAddrUtil.getIpv4AddrAsNumber(text);
					return IpAddrUtil.getIpv4AddrFromNumber(addr);
				}
				if (IpAddrUtil.isIpv6Addr(text)) {
					if (v6compress) {
						return IpAddrUtil.compressIpv6Addr(text);
					} else {
						return IpAddrUtil.decompressIpv6Addr(text);
					}
				}
				throw new ParseException("Invalid IPv4/IPv6 format: " + text, 0);
			}
		}
	}

}
