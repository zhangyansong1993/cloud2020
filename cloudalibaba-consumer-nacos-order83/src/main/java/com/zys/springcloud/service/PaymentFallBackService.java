package com.zys.springcloud.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentFallBackService implements PaymentService {
    @Override
    public String getPayment(Integer id) {
        return "PaymentFallBackService";
    }
}
