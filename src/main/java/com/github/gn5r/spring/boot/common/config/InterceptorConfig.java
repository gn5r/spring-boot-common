package com.github.gn5r.spring.boot.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class InterceptorConfig extends AbstractInterceptorConfig {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(urlConsoleInterceptor()).addPathPatterns("/**");
    }
}