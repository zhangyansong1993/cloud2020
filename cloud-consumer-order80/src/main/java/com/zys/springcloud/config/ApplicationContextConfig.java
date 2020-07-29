package com.zys.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    //@LoadBalanced
    @Bean
    public RestTemplate myRestTemplate() {
        return new RestTemplate();
    }
}
