package com.ynz.jpa.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@TestConfiguration
public class MyTestConfiguration {

    @Value("${test.server}")
    private String localHost;

    @Bean
    @Scope("prototype")
    public StringBuilder uriBuilder() {
        return new StringBuilder(localHost);
    }

}
