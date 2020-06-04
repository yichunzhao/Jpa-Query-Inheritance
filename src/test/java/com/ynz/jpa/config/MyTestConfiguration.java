package com.ynz.jpa.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @TestConfiguration is a specific @Configuration and can be used to define additional beans or customizations for
 * a test.
 */

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
