package com.github.gn5r.spring.boot.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController extends AbstractController {
    
    @GetMapping({"", "/"})
    public String redirect() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}