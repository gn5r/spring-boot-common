package com.github.gn5r.spring.boot.common.context;

/**
 * 各コントローラーメソッドに対するリクエストデータを格納するオブジェクト
 * 
 * @author gn5r
 * @since 0.5.0
 */
@lombok.Data
public final class RequestData {

    private String url;
    private String subDirectory;
    private String contextPath;
    private String queryString;
}