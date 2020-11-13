package com.github.gn5r.spring.boot.common.interceptor;

import java.lang.reflect.Method;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.github.gn5r.spring.boot.common.annotation.NoAuthenticate;
import com.github.gn5r.spring.boot.common.logger.CmnLogger;

/**
 * 認証インターセプターサンプルクラス
 *
 *
 * @author gn5r
 * @since 0.3.0
 */
@Configuration
public class AuthenticationInterceptor implements AsyncHandlerInterceptor {

    /**
     * Controllerの処理より前に実行されるハンドラー
     * <p>
     * ここではサンプルとしてコントローラーまたは各メソッドに {@linkplain NoAuthenticate} アノテーションが付与されているかチェック
     *
     * @param request  {@link HttpServletRequest リクエストオブジェクト}
     * @param response {@link HttpServletResponse レスポンスオブジェクト}
     * @param handler  呼び出し元オブジェクト
     * @return 認証通過可否
     * 
     * @author gn5r
     * @since 0.3.0
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // リソースに対するアクセスの場合は何もしない
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final Method method = handlerMethod.getMethod();

        // Controllerクラスにアノテーションが付与されているかチェック
        NoAuthenticate annotation = method.getDeclaringClass().getDeclaredAnnotation(NoAuthenticate.class);
        if (Objects.isNull(annotation)) {
            // メソッドにアノテーションが付与されているかチェック
            annotation = AnnotationUtils.findAnnotation(method, NoAuthenticate.class);
            if (!Objects.isNull(annotation)) {
                CmnLogger.SYS.info("メソッド単位で認証をスキップします");
                return true;
            }
        } else {
            CmnLogger.SYS.info("コントローラー単位で認証をスキップします");
            return true;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}