/*
 * Copyright 2012,2015 agwlvssainokuni
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

package cherry.foundation.log.trace;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * メソッド呼出のトレースログを出力する機能。
 */
public class TraceInterceptor implements MethodInterceptor {

	/** メソッド開始時のログ出力。 */
	private final Logger traceEnter = LoggerFactory.getLogger("trace.ENTER");

	/** メソッド終了時のログ出力。 */
	private final Logger traceExit = LoggerFactory.getLogger("trace.EXIT");

	/** 例外発生時のログ出力。 */
	private final Logger traceException = LoggerFactory.getLogger("trace.EXCEPTION");

	/** 配列をログ出力する際のサイズ上限。 */
	private int arrayLengthLimit;

	/** 引数・返却値をログ出力する際の文字列長上限。 */
	private int objectLengthLimit;

	/** トレースログに出力するクラス名 (インタフェース名) を抽出する条件。 */
	private String[] packagePrefix = new String[0];

	/**
	 * 配列をログ出力する際のサイズ上限 を設定する。
	 * 
	 * @param arrayLengthLimit 配列をログ出力する際のサイズ上限。
	 */
	public void setArrayLengthLimit(int arrayLengthLimit) {
		this.arrayLengthLimit = arrayLengthLimit;
	}

	/**
	 * 引数・返却値をログ出力する際の文字列長上限 を設定する。
	 * 
	 * @param objectLengthLimit 引数・返却値をログ出力する際の文字列長上限。
	 */
	public void setObjectLengthLimit(int objectLengthLimit) {
		this.objectLengthLimit = objectLengthLimit;
	}

	/**
	 * トレースログに出力するクラス名 (インタフェース名) を抽出する条件 を設定する。
	 * 
	 * @param packagePrefix トレースログに出力するクラス名 (インタフェース名) を抽出する条件。
	 */
	public void setPackagePrefix(String... packagePrefix) {
		this.packagePrefix = packagePrefix;
	}

	/**
	 * トレースログを出力する。
	 * 
	 * @param inv メソッド呼出。
	 * @return メソッド呼出の返却値。
	 */
	@Override
	public Object invoke(MethodInvocation inv) throws Throwable {
		try {
			logTraceEnter(inv);
			Object ret = inv.proceed();
			logTraceExit(inv, ret);
			return ret;
		} catch (Throwable throwable) {
			logTraceException(inv, throwable);
			throw throwable;
		}
	}

	/**
	 * メソッド開始時のトレースログを出力する。
	 * 
	 * @param inv メソッド呼出。
	 */
	private void logTraceEnter(MethodInvocation inv) {

		if (!traceEnter.isTraceEnabled()) {
			return;
		}

		StringBuilder builder = new StringBuilder();
		appendMethodName(builder, inv);
		builder.append(": (");

		Annotation[][] annon = inv.getMethod().getParameterAnnotations();
		for (int i = 0; i < inv.getArguments().length; i++) {
			if (i > 0) {
				builder.append(", ");
			}

			if (isMaskAnnotationPresent(annon[i])) {
				builder.append("<MASKED>");
				continue;
			}

			StringBuilder arg = new StringBuilder();
			appendObject(arg, inv.getArguments()[i]);
			if (objectLengthLimit > 0 && arg.length() > objectLengthLimit) {
				arg.delete(objectLengthLimit, arg.length());
				arg.append("...<truncated>...");
			}
			builder.append(arg);
		}

		builder.append(")");

		traceEnter.trace(builder.toString());
	}

	/**
	 * {@link Mask}アノテーションが存在するか判定する。
	 * 
	 * @param annon 対象アノテーション。
	 * @return {@link Mask}アノテーションの有無。
	 */
	private boolean isMaskAnnotationPresent(Annotation[] annon) {
		for (int i = 0; i < annon.length; i++) {
			if (annon[i] instanceof Mask) {
				return true;
			}
		}
		return false;
	}

	/**
	 * メソッド終了時のトレースログを出力する。
	 * 
	 * @param inv メソッド呼出。
	 * @param ret メソッド呼出の返却値。
	 */
	private void logTraceExit(MethodInvocation inv, Object ret) {

		if (!traceExit.isTraceEnabled()) {
			return;
		}

		StringBuilder builder = new StringBuilder();
		appendMethodName(builder, inv);
		builder.append(": ");
		if (inv.getMethod().getReturnType().equals(Void.TYPE)) {
			builder.append("void");
		} else if (inv.getMethod().isAnnotationPresent(Mask.class)) {
			builder.append("<MASKED>");
		} else {
			StringBuilder arg = new StringBuilder();
			appendObject(arg, ret);
			if (objectLengthLimit > 0 && arg.length() > objectLengthLimit) {
				arg.delete(objectLengthLimit, arg.length());
				arg.append("...<truncated>...");
			}
			builder.append(arg);
		}

		traceExit.trace(builder.toString());
	}

	/**
	 * 例外発生時のトレースログを出力する。
	 * 
	 * @param inv メソッド呼出。
	 * @param th 発生した例外。
	 */
	private void logTraceException(MethodInvocation inv, Throwable th) {

		if (!traceException.isTraceEnabled()) {
			return;
		}

		StringBuilder builder = new StringBuilder();
		appendMethodName(builder, inv);
		builder.append(": ");
		builder.append(th.getClass().getName());

		traceException.trace(builder.toString());
	}

	/**
	 * トレースログにメソッド名を出力する。
	 * 
	 * @param builder ログ文字列生成。
	 * @param inv メソッド呼出。
	 */
	private void appendMethodName(StringBuilder builder, MethodInvocation inv) {
		Class<?> klass = inv.getThis().getClass();
		String name = getClassName(klass, packagePrefix);
		builder.append(name == null ? klass.getName() : name);
		builder.append("#");
		builder.append(inv.getMethod().getName());
	}

	/**
	 * トレースログに出力するクラス名 (インタフェース名) を決定する。
	 * 
	 * @param klass クラスオブジェクト。
	 * @param packagePrefix 抽出条件 (パッケージ名)。
	 * @return クラス名 (インタフェース名)。
	 */
	private String getClassName(Class<?> klass, String[] packagePrefix) {
		for (String pref : packagePrefix) {
			if (klass.getName().startsWith(pref)) {
				return klass.getName();
			}
		}
		for (Class<?> iface : klass.getInterfaces()) {
			String name = getClassName(iface, packagePrefix);
			if (name != null) {
				return name;
			}
		}
		return null;
	}

	/**
	 * トレースログに引数・返却値を出力する。
	 * 
	 * @param builder ログ文字列生成。
	 * @param object 引数・返却値。
	 */
	private void appendObject(StringBuilder builder, Object object) {
		if (object == null) {
			builder.append("<null>");
		} else if (object.getClass().isArray()) {
			builder.append("[");
			int length = Array.getLength(object);
			if (arrayLengthLimit > 0 && length > arrayLengthLimit) {
				builder.append("array of ");
				builder.append(object.getClass().getComponentType().getName());
				builder.append(" size ");
				builder.append(length);
			} else {
				for (int i = 0; i < length; i++) {
					if (i > 0) {
						builder.append(", ");
					}
					appendObject(builder, Array.get(object, i));
				}
			}
			builder.append("]");
		} else {
			builder.append(object.toString());
		}
	}

}
