package com.zys.springcloud.controller;


import com.zys.springcloud.entities.CommonResult;
import com.zys.springcloud.entities.Payment;
import com.zys.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RequestMapping("/consumer/payment/")
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate myRestTemplate;

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/get/{id}")
    public CommonResult<Payment> gtPaymentById(@PathVariable("id") Long id) {
        log.info("查询id:" + id);
        return myRestTemplate.getForObject("http://CLOUD-PAYMENT-SERVICE/payment/get/" + id, CommonResult.class);

    }

    @GetMapping(value = "/create")
    public CommonResult<Payment> create(Payment payment) {
        return myRestTemplate.postForObject("http://CLOUD-PAYMENT-SERVICE/payment/create", payment, CommonResult.class);

    }


    @GetMapping(value = "/getEntity/{id}")
    public CommonResult<Payment> getPaymentById2(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> forEntity = myRestTemplate.getForEntity("http://CLOUD-PAYMENT-SERVICE/payment/get/" + id, CommonResult.class);
        if (forEntity.getStatusCode().is2xxSuccessful()) {
            return forEntity.getBody();
        } else {
            return new CommonResult<>(500, "操作失败", null);
        }

    }

    @GetMapping(value = "lb")
    public String get() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instance(instances);
        URI uri = serviceInstance.getUri();
        return myRestTemplate.getForObject(uri+"/payment/lb", String.class);
    }

}
