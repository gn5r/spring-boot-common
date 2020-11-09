package com.github.gn5r.spring.boot.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class SampleRestController {

    @GetMapping("hello")
    public ResponseEntity<?> helloWorld() {
        return new ResponseEntity<String>("Hello World", HttpStatus.OK);
    }
}