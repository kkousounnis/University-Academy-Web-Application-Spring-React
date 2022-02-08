package com.spring.boot.coodle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CoodleApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder app) {

        return app.sources(CoodleApplication.class);

    }

    public static void main(String[] args) {
        SpringApplication.run(CoodleApplication.class, args);
    }

}
