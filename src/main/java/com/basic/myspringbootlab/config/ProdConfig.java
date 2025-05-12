package com.basic.myspringbootlab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("Prod")
public class ProdConfig {
    @Bean
    public MyEnvironment myEnvironment() {
        return MyEnvironment.builder()
                .mode("운영환경")
                .build();

    }
}
