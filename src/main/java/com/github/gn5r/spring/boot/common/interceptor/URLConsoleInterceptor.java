package com.github.gn5r.spring.boot.common.interceptor;

import java.lang.reflect.Method;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.github.gn5r.spring.boot.common.annotation.NoURLConsole;
import com.github.gn5r.spring.boot.common.config.properties.URLConsoleProperty;
import com.github.gn5r.spring.boot.common.context.Context;
import com.github.gn5r.spring.boot.common.context.RequestData;
import com.github.gn5r.spring.boot.common.logger.CmnLogger;

/**
 * アクセスURLロギングインターセプター
 *
 * @author gn5r
 * @since 0.5.0
 * @see NoURLConsole
 * @see URLConsoleProperty
 */
public class URLConsoleInterceptor implements AsyncHandlerInterceptor {

	@Autowired
	private URLConsoleProperty property;

	/**
	 * Controllerの処理より前に実行されるハンドラー
	 * <p>
	 * 各メソッドへのアクセスURLをロギングする。各メソッドまたはコントローラーに {@link NoURLConsole}
	 * アノテーションを付与している場合はロギングしない
	 * </p>
	 *
	 * <p>またはapplication.properties/ymlに {@link URLConsoleProperty} を記述してアプリケーションレベルでロギングするか指定することも可能
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

		// プロパティでfalseに指定している場合はロギングしない
		if (!property.isEnable()) {
			return true;
		}

		final HandlerMethod handlerMethod = (HandlerMethod) handler;
		final Method method = handlerMethod.getMethod();

		// クラスにアノテーションが付与されているかチェック
		NoURLConsole annotation = method.getDeclaringClass().getDeclaredAnnotation(NoURLConsole.class);
		if (Objects.isNull(annotation)) {
			// メソッドにアノテーションが付与されているかチェック
			annotation = AnnotationUtils.findAnnotation(method, NoURLConsole.class);
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