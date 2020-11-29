package com.github.gn5r.spring.boot.common.controller;

import javax.servlet.http.HttpServletRequest;

import com.github.gn5r.spring.boot.common.exception.RestRuntimeException;
import com.github.gn5r.spring.boot.common.logger.CmnLogger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.Data;

@Controller
public class SampleController extends AbstractController {

    @GetMapping({ "", "/" })
    public String redirect() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index() {
        TestUser test = new TestUser();
        test.setUsername("gn5r");
        test.setNote("ノート");

        CmnLogger.SYS.info(test);
        return "index";
    }

    @Override
    public String restRuntimeExceptionHandler(RestRuntimeException e, HttpServletRequest request,
            RedirectAttributes model) {
        return null;
    }

    @Data
    private class TestUser {
        private String username;
        private String note;
    }
}