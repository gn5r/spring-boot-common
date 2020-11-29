package com.github.gn5r.spring.boot.common;

import com.github.gn5r.spring.boot.common.config.FQCNBeanNameGenerator;
import com.github.gn5r.spring.boot.common.logger.CmnLogger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(nameGenerator = FQCNBeanNameGenerator.class)
public class SpringBootCommonApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringBootCommonApplication.class);
    }

    public static void main(String[] args) {
        CmnLogger.SYS.info("default encoding:" + System.getProperty("file.encoding"));
        SpringApplication.run(SpringBootCommonApplication.class, args);
    }
}