package com.fransica.cardscheme.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class BeanConfig {

    @Bean
    public WebClient getwebClient(){
        return WebClient.builder().build();
    }
}
