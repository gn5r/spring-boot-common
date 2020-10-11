package com.gn5r.spring.boot.common.validator;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.gn5r.spring.boot.common.validator.annotations.ByteSize;

/**
 * {@link ByteSize}用バリデーションクラス
 *
 * @author gn5r
 * @since 0.2.0-RELEASE
 */
public class ByteSizeValidation implements ConstraintValidator<ByteSize, CharSequence> {

    private String message;
    private int min;
    private int max;

    @Override
    public void initialize(ByteSize annotation) {
        this.message = annotation.message();
        this.min = annotation.min();
        this.max = annotation.max();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (Objects.isNull(value))
            return true;

        try {
            final int size = String.valueOf(value).getBytes("UTF-8").length;

            if (size >= this.min && size <= this.max) {
                return true;
            }
        } catch (UnsupportedEncodingException e) {
            return false;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        return false;
    }
}