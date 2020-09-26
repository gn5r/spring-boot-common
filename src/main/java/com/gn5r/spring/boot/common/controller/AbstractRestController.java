package com.gn5r.spring.boot.common.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.gn5r.spring.boot.common.exception.RestRuntimeException;
import com.gn5r.spring.boot.common.resource.ErrorResource;

/**
 * 基底コントローラー
 *
 * @author gn5r
 * @since 0.1.0-RELEASE
 */
public abstract class AbstractRestController {

	/**
	 * {@link RestRuntimeException} を捕捉しエラーリソースを返却する
	 *
	 * @param req WebRequest
	 * @param e   RestRuntimeException
	 * @return エラーリソース
	 *
	 * @see WebRequest
	 * @see RestRuntimeException
	 * @see ErrorResource
	 */
	@ExceptionHandler(value = RestRuntimeException.class)
	public final ResponseEntity<?> restRuntimeExceptionHandler(WebRequest req, RestRuntimeException e) {

		// エラーレスポンスを作成
		ErrorResource res = new ErrorResource();
		res.setStatus(e.getStatus());
		res.setMessage(e.getMessage());

		// バリデーションエラーがあれば返却
		if (e.getFieldErrors() != null) {
			// 念のため空ではないかチェック
			if (!e.getFieldErrors().isEmpty()) {
				res.setFieldErrors(e.getFieldErrors());
			}
		}

		return new ResponseEntity<ErrorResource>(res, e.getStatus());
	}
}