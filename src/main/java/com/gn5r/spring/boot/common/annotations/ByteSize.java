package com.gn5r.spring.boot.common.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.gn5r.spring.boot.common.validation.ByteSizeValidation;

/**
 * バイト数バリデーションアノテーション
 * <p>
 * 文字列のバイト数をチェックする
 * </p>
 *
 * @author gn5r
 * @since 0.2.0-RELEASE
 */
@Documented
@Constraint(validatedBy = { ByteSizeValidation.class })
@Target({ ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ByteSize.List.class)
public @interface ByteSize {

    String message() default "{com.gn5r.spring.boot.common.validation.ByteSize.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int min() default 0;

    int max() default Integer.MAX_VALUE;

    @Documented
    @Target({ ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface List {
        ByteSize[] value();
    }
}