package com.github.gn5r.spring.boot.common.context;

import javax.servlet.http.HttpServletRequest;

import lombok.Setter;

/**
 * 各コントローラーメソッドに対するリクエストデータを格納するオブジェクト
 * 
 * @author gn5r
 * @since 0.5.0
 */
public class RequestData {

    @Setter
    private String url;

    @Setter
    private String subDirectory;

    @Setter
    private String contextPath;

    @Setter
    private String queryString;

    /**
     * リクエスト先URLを取得する
     * 
     * @return url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * リクエスト先の各コントローラーで記述したURL(ServletPath)を取得する
     * 
     * @return servletPath
     * @see HttpServletRequest#getServletPath()
     */
    public String getSubDirectory() {
        return this.subDirectory;
    }

    /**
     * リクエスト先の各アプリケーションのコンテキストパスを取得する
     * 
     * @return コンテキストパス
     */
    public String getContextPath() {
        return this.contextPath;
    }

    /**
     * リクエスト先クエリを取得する
     * 
     * @return クエリ
     */
    public String getQueryString() {
        return this.queryString;
    }
}