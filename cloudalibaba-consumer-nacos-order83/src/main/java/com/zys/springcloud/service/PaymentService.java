package com.zys.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallBackService.class)
public interface PaymentService {
    @GetMapping(value = "/payment/nacos/{id}")
    String getPayment(@PathVariable("id") Integer id);
}
