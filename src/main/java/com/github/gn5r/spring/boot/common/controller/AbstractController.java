package com.github.gn5r.spring.boot.common.controller;

import javax.servlet.http.HttpServletRequest;

import com.github.gn5r.spring.boot.common.context.Context;
import com.github.gn5r.spring.boot.common.exception.RestRuntimeException;
import com.github.gn5r.spring.boot.common.interceptor.URLConsoleInterceptor;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 基底コントローラー
 * <p>
 * {@linkplain RestRuntimeException} を捕捉した {@link ExceptionHandler} を記述する
 * </p>
 * 
 * @author gn5r
 * @since 0.4.0
 */
public abstract class AbstractController {

    /**
     * <p>
     * {@link RestRuntimeException} を捕捉した処理を記述する
     * </p>
     * <p>
     * リクエスト先URLは {@link HttpServletRequest#getServletPath()} で取得可能
     * </p>
     * <p>
     * {@link URLConsoleInterceptor} を有効にしている場合は {@link Context#getRequestData()}
     * から取得することも可能
     * </p>
     * 
     * @param e       RestRuntimeException
     * @param request HttpServletRequest
     * @param model   RedirectAttributes
     * @return エラー発生時に遷移させるページ
     * 
     * @see RestRuntimeException
     * @see RedirectAttributes
     */
    @ExceptionHandler(value = RestRuntimeException.class)
    public abstract String restRuntimeExceptionHandler(RestRuntimeException e, HttpServletRequest request,
            RedirectAttributes model);
}