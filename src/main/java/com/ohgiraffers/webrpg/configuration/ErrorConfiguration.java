package com.ohgiraffers.webrpg.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

@Configuration
public class ErrorConfiguration {


    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();

        Properties props = new Properties();

        //props.setProperty("java.lang.NullPointerException", "error/nullPointer");

        exceptionResolver.setExceptionMappings(props);
        //exceptionResolver.setDefaultErrorView("error/default");
        //exceptionResolver.setExceptionAttribute("exceptionMessage");

        return exceptionResolver;
    }
}
