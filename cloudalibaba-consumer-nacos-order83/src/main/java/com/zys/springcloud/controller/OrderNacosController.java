package com.zys.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zys.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderNacosController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/payment/consumer/nacos/{id}")
//    @SentinelResource(value = "fallback")
//    @SentinelResource(value = "fallback", fallback = "handlerFallback")
//    @SentinelResource(value = "fallback", blockHandler = "handlerFallback")
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler")
    public String fallback(@PathVariable("id") Integer id) {
        if (id == 4) {
            throw new NullPointerException("查询错误，空指针异常");
        }
        return restTemplate.getForObject("http://nacos-payment-provider/payment/nacos/" + id, String.class);
    }

    public String handlerFallback(@PathVariable("id") Integer id) {
        return "程序错误后反回handlerFallback";
    }

    public String blockHandler(@PathVariable("id") Integer id, BlockException e) {
        return "程序错误后反回blockHandler:" + e.getMessage();
    }

    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "consumer/getPayment/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return paymentService.getPayment(id);
    }

}


