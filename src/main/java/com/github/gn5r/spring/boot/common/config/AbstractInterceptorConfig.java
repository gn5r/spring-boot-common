package com.github.gn5r.spring.boot.common.config;

import com.github.gn5r.spring.boot.common.interceptor.AuthenticationInterceptor;
import com.github.gn5r.spring.boot.common.interceptor.URLConsoleInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 基底インターセプター設定クラス
 *
 * @author gn5r
 * @since 0.5.0
 */
public abstract class AbstractInterceptorConfig implements WebMvcConfigurer {

    /**
     * サンプル認証インターセプターをBean登録する
     * 
     * @return {@link AuthenticationInterceptor サンプル認証インターセプター}
     * @author gn5r
     * @since 0.5.0
     */
    @Bean
    public AuthenticationInterceptor authenticationIntercepto() {
        return new AuthenticationInterceptor();
    }

    /**
     * アクセスURLをロギングするインターセプターをBean登録する
     * 
     * @return {@link URLConsoleInterceptor アクセスURLロギングインターセプター}
     * @author gn5r
     * @since 0.5.0
     */
    @Bean
    public URLConsoleInterceptor urlConsoleInterceptor() {
        return new URLConsoleInterceptor();
    }

    /**
     * インターセプター登録メソッド
     * <p>
     * 継承先アプリケーション設定クラスにてOverrideして処理を記述
     * </p>
     * 
     * @param registry {@link InterceptorRegistry}
     * @author gn5r
     * @since 0.5.0
     */
    @Override
    public abstract void addInterceptors(InterceptorRegistry registry);
}