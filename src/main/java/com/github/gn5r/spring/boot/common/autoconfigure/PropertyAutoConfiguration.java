package com.github.gn5r.spring.boot.common.autoconfigure;

import com.github.gn5r.spring.boot.common.config.properties.URLConsoleProperty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * プロパティをBean登録するための設定クラス
 * 
 * @author gn5r
 */
@Configuration
public class PropertyAutoConfiguration {

    /**
     * URLロギングプロパティをBean登録する
     * 
     * @return URLロギングプロパティ
     * @see URLConsoleProperty
     */
    @Bean
    public URLConsoleProperty urlConsoleProperty() {
        return new URLConsoleProperty();
    }
}
