package com.github.gn5r.spring.boot.common.controller;

import com.github.gn5r.spring.boot.common.exception.RestRuntimeException;
import com.github.gn5r.spring.boot.common.logger.CmnLogger;
import com.github.gn5r.spring.boot.common.resource.ErrorResource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.exceptions.TemplateInputException;

/**
 * 基底コントローラー
 * <p>
 * {@linkplain RestRuntimeException} を捕捉し ${server.error.path} へリダイレクトする
 * {@linkplain ExceptionHandler} が実装されている
 * </p>
 * 
 * @author gn5r
 * @since 0.4.0
 */
public abstract class AbstractController implements ErrorController {

    /**
     * エラーページパスを返却する
     * <p>application.propertiesの server.error.path に設定した値が返却される</p>
     * 
     * @return エラーページパス
     */
    @Override
    public String getErrorPath() {
        return this.SERVER_ERROR_PATH;
    }

    /**
     * エラーページパス
     * <p>application.propertiesで変更可能</p>
     */
    @Value("${server.error.path}")
    private String SERVER_ERROR_PATH;

    /**
     * {@link RestRuntimeException} を捕捉しエラーリソースを設定し /error へリダイレクトする
     * 
     * @param e     RestRuntimeException
     * @param model RedirectAttributes
     * @return エラーページへのリダイレクトURL
     */
    @ExceptionHandler(value = RestRuntimeException.class)
    public String restRuntimeExceptionHandler(RestRuntimeException e, RedirectAttributes model) {
        CmnLogger.SYS.error(e);
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

        // リダイレクト先のモデルにオブジェクトを渡す
        model.addFlashAttribute("error", res);

        return "redirect:" + SERVER_ERROR_PATH;
    }

    @ExceptionHandler(value = TemplateInputException.class)
    public String templateInputExceptionHandler(TemplateInputException e, RedirectAttributes model) {
        CmnLogger.SYS.error(e);

        // エラーレスポンスを作成
        ErrorResource res = new ErrorResource();
        res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        res.setMessage("テンプレートファイルの読み込みに失敗しました");

        // リダイレクト先のモデルにオブジェクトを渡す
        model.addFlashAttribute("error", res);

        return "redirect:" + SERVER_ERROR_PATH;
    }
}