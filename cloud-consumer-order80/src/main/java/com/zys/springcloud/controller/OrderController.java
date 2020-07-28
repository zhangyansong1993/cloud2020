package com.zys.springcloud.controller;



import com.zys.springcloud.entities.CommonResult;
import com.zys.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/consumer/payment/")
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate myRestTemplate;

    @GetMapping(value = "/get/{id}")
    public CommonResult<Payment> gtPaymentById(@PathVariable("id") Long id) {
        log.info("查询id:" + id);
        return myRestTemplate.getForObject("http://localhost:8001/payment/get/" + id, CommonResult.class);

    }

    @GetMapping(value = "/create")
    public CommonResult<Payment> create(Payment payment) {
         return myRestTemplate.postForObject("http://localhost:8001/payment/create",payment,CommonResult.class);

    }
}
