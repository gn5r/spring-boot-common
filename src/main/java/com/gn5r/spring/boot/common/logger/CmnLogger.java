/**
 *
 */
package com.gn5r.spring.boot.common.logger;

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

	/**
	 * infoレベルのログ
	 *
	 * @param message メッセージ
	 */
	public final void info(Object message) {
		log.info(message.toString());
	}

	/**
	 * infoレベルのログ
	 *
	 * @param messages メッセージ配列
	 */
	public final void info(Object... messages) {
		log.info(concatString(messages));
	}

	/**
	 * errroレベルのログ
	 * @param message メッセージ
	 */
	public final void error(Object message) {
		log.error(message.toString());
	}

	/**
	 * errorレベルのログ
	 * @param t 例外クラス
	 */
	public final void error(Throwable t) {
		log.error(t.getMessage());
	}

	/**
	 * オブジェクト配列を改行で連結した文字列を返却する
	 *
	 * @param objects オブジェクト配列
	 * @return メッセージ
	 */
	private String concatString(Object[] objects) {
		StringBuffer buffer = new StringBuffer();

		for (Object msg : objects) {
			buffer.append(msg.toString() + "\n");
		}

		return buffer.toString();
	}
}
