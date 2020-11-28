package com.github.gn5r.spring.boot.common.context;

import com.github.gn5r.spring.boot.common.interceptor.URLConsoleInterceptor;

/**
 * 共通情報格納クラス
 * 
 * @author gn5r
 * @since 0.5.0
 */
public class Context {

    /**
     * リクエストデータ
     */
    private static RequestData requestData;

    /**
     * コンテキストパス
     */
    private static String contextPath;

    /**
     * 各コントローラーのメソッドに対するリクエストデータをセットする
     * <p>
     * {@link URLConsoleInterceptor} にてセット
     * </p>
     * 
     * @param requestData リクエストデータ
     * @see {@link RequestData}
     */
    public static void setRequestData(RequestData requestData) {
        Context.requestData = requestData;
    }

    /**
     * 各コントローラーのメソッドに対するリクエストデータを取得する
     * 
     * @return {@link RequestData リクエストデータ}
     * @see {@link RequestData}
     */
    public static RequestData getRequestData() {
        return Context.requestData;
    }

    /**
     * Spring Bootアプリケーションのコンテキストパスをセットする
     * 
     * @param contextPath コンテキストパス
     */
    public static void setContextPath(String contextPath) {
        Context.contextPath = contextPath;
    }

    /**
     * Spring Bootアプリケーションのコンテキストパスを取得する
     * 
     * @return コンテキストパス
     */
    public static String getContextPath() {
        return Context.contextPath;
    }
}