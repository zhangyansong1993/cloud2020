package com.zys.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaSentinel8401 {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaSentinel8401.class, args);
    }
}
