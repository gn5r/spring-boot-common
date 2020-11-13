package com.github.gn5r.spring.boot.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * URLロギングスキップアノテーション
 * 
 * @author gn5r
 * @since 0.5.0
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NoURLConsole {

}