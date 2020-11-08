package com.github.gn5r.spring.boot.common.interceptor;

import java.lang.reflect.Method;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.gn5r.spring.boot.common.annotation.NoConsoleURL;
import com.github.gn5r.spring.boot.common.context.Context;
import com.github.gn5r.spring.boot.common.context.RequestData;
import com.github.gn5r.spring.boot.common.logger.CmnLogger;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

/**
 * アクセスURLロギングインターセプター
 * 
 * @author gn5r
 * @since 0.5.0
 */
public class URLConsoleInterceptor extends HandlerInterceptorAdapter {

    /**
     * Controllerの処理より前に実行されるハンドラー
     * <p>
     * 各メソッドへのアクセスURLをロギングする。各メソッドまたはコントローラーに {@link NoConsoleURL}
     * アノテーションを付与している場合はロギングしない
     * </p>
     *
     * @param request  {@link HttpServletRequest リクエストオブジェクト}
     * @param response {@link HttpServletResponse レスポンスオブジェクト}
     * @param handler  呼び出し元オブジェクト
     * @return 通過可否(このインターセプターでは全てtrue)
     * 
     * @author gn5r
     * @since 0.5.0
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        final String url = request.getRequestURL().toString();
        RequestData requestData = new RequestData();
        requestData.setUrl(url);
        requestData.setSubDirectory(request.getServletPath());
        requestData.setContextPath(request.getContextPath());
        requestData.setQueryString(request.getQueryString());
        Context.setRequestData(requestData);
        Context.setContextPath(request.getContextPath());

        // リソースに対するアクセスの場合はURLロギングをしない
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final Method method = handlerMethod.getMethod();

        // クラスにアノテーションが付与されているかチェック
        NoConsoleURL annotation = method.getDeclaringClass().getDeclaredAnnotation(NoConsoleURL.class);
        if (Objects.isNull(annotation)) {
            // メソッドにアノテーションが付与されているかチェック
            annotation = AnnotationUtils.findAnnotation(method, NoConsoleURL.class);
            if (!Objects.isNull(annotation)) {
                return true;
            }
        } else {
            return true;
        }

        CmnLogger.SYS.info("Access to URL : [" + url + "]");

        return true;
    }
}