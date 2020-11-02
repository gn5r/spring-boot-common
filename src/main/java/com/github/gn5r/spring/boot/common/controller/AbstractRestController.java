package com.github.gn5r.spring.boot.common.controller;

import com.github.gn5r.spring.boot.common.exception.RestRuntimeException;
import com.github.gn5r.spring.boot.common.resource.ErrorResource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 基底RESTコントローラー
 * <p>
 * {@linkplain RestRuntimeException} を捕捉しエラーレスポンスを返却する
 * {@linkplain ExceptionHandler} が実装されている
 * </p>
 *
 * @author gn5r
 * @since 0.1.0-RELEASE
 */
public abstract class AbstractRestController {

	/**
	 * {@link RestRuntimeException} を捕捉しエラーリソースを返却する
	 *
	 * @param e RestRuntimeException
	 * @return エラーリソース
	 *
	 * @see RestRuntimeException
	 * @see ErrorResource
	 */
	@ExceptionHandler(value = RestRuntimeException.class)
	public final ResponseEntity<?> restRuntimeExceptionHandler(RestRuntimeException e) {

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