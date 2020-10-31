package com.github.gn5r.spring.boot.common.logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 共通ロガー
 *
 * @author gn5r
 * @since 0.1.0-RELEASE
 */
public enum CmnLogger {

	SYS(), APP();

	/** ロガー */
	private final Logger log = LoggerFactory.getLogger(name());

	// 除外クラス名
	private final Pattern excludes = Pattern.compile("java.lang.*|.*CmnLogger");

	/**
	 * infoレベルのログ
	 *
	 * @param message メッセージ
	 */
	public final void info(Object message) {
		log.info(setStackTrace(message));
	}

	/**
	 * infoレベルのログ
	 *
	 * @param messages メッセージ配列
	 */
	public final void info(Object... messages) {
		log.info(setStackTrace(messages));
	}

	/**
	 * errroレベルのログ
	 * 
	 * @param message メッセージ
	 */
	public final void error(Object message) {
		log.error(setStackTrace(message));
	}

	/**
	 * errorレベルのログ
	 * 
	 * @param t 例外クラス
	 */
	public final void error(Throwable t) {
		log.error(setStackTrace(t.getMessage()));
	}

	/**
	 * オブジェクト配列をカンマで連結した文字列を返却する
	 *
	 * @param objects オブジェクト配列
	 * @return メッセージテキスト
	 */
	private final String concatString(Object... objects) {
		StringBuffer buffer = new StringBuffer();

		for (Object msg : objects) {
			buffer.append(msg.toString() + ",");
		}

		// 末尾にあるカンマを削除
		return buffer.toString().replaceAll(",$", "");
	}

	/**
	 * 先頭の {@link StackTraceElement} を返却する
	 * 
	 * @return {@link StackTraceElement}
	 */
	private final StackTraceElement getFiStackTraceElement() {
		final StackTraceElement[] elements = Thread.currentThread().getStackTrace();

		for (StackTraceElement e : elements) {
			// 除外クラスにマッチするか検証
			final Matcher m = excludes.matcher(e.getClassName());

			// マッチした場合は次のループへ
			if (m.find()) {
				continue;
			}
			// 除外クラス以外のStackTraceを返却
			return e;
		}
		return null;
	}

	/**
	 * 呼び出し元のクラス名とメソッド名を付与しログ出力する
	 * 
	 * @param messages メッセージ
	 * @return メッセージテキスト
	 */
	private final String setStackTrace(Object... messages) {
		final StackTraceElement element = this.getFiStackTraceElement();

		StringBuffer buffer = new StringBuffer();
		buffer.append(element.getClassName() + element.getMethodName() + " - " + concatString(messages));

		return buffer.toString();
	}
}
