package com.spring.boot.coodle.config;

import static javax.management.Query.gt;
import static org.hibernate.criterion.Restrictions.gt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("cors.allowedOrings")
    private String allowedOrigins;

    public void addCorsMappings(CorsRegistry registry) {
        final long MAX_AGE_SECS = 3600;

        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .maxAge(MAX_AGE_SECS);
    }
   
}
