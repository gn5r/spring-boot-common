package com.github.gn5r.spring.boot.common.config.properties;

import com.github.gn5r.spring.boot.common.annotation.NoURLConsole;
import com.github.gn5r.spring.boot.common.interceptor.URLConsoleInterceptor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * URLロギングプロパティ
 * <p>{@link URLConsoleInterceptor} 内でロギングするかの判断に使用。コントローラー/メソッド毎に {@link NoURLConsole} で個別指定することも可能</p>
 * 
 * @author gn5r
 * @since 0.6.0
 * 
 * @see URLConsoleInterceptor
 * @see NoURLConsole
 */
@Component
@ConfigurationProperties(prefix = "application.interceptor.url.console")
public class URLConsoleProperty {

    /**
     * URLロギングの有効/無効
     */
    private boolean enable = true;

    public final void setEnable(final boolean enable) {
        this.enable = enable;
    }

    public final boolean isEnable() {
        return this.enable;
    }
}