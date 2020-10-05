package com.gn5r.spring.boot.common.annotations;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Before;
import org.junit.Test;

public class ByteSizeTest {

    private Validator validator;

    @Before
    public void setUp() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void byteSizeTest() {
        TestClass test = new TestClass();
        test.setText("あいう");
        test.setId(111);
        test.setName("");
        Set<ConstraintViolation<Object>> violations = validator.validate(test);
        final int cnt = violations.size();
        System.out.println("エラーカウント:" + cnt);
        violations.stream().forEach(e->System.out.println(e.getMessage()));
    }

    @lombok.Data
    private class TestClass {
        @ByteSize(max = 10)
        private String text;

        @Required
        private Integer id;

        @Required
        private String name;
    }
}