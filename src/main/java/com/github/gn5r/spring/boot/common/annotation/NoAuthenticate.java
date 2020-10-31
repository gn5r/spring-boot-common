package com.github.gn5r.spring.boot.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 認証スキップ判定アノテーション
 * 
 * @author gn5r
 * @since 0.3.0-RELEASE
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NoAuthenticate {

}