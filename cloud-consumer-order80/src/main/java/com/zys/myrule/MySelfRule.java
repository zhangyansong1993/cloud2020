package com.zys.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Ribbon随机负载，默认轮询
 */
@Configuration
public class MySelfRule {
    //@Bean
    public IRule myRule() {
        return new RandomRule();//随机

    }
}
