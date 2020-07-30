package com.zys.springcloud.controller;



import com.zys.springcloud.entities.CommonResult;
import com.zys.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        return myRestTemplate.getForObject("http://CLOUD-PAYMENT-SERVICE/payment/get/" + id, CommonResult.class);

    }

    @GetMapping(value = "/create")
    public CommonResult<Payment> create(Payment payment) {
         return myRestTemplate.postForObject("http://CLOUD-PAYMENT-SERVICE/payment/create",payment,CommonResult.class);

    }



    @GetMapping(value = "/getEntity/{id}")
    private CommonResult<Payment>  getPaymentById2(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> forEntity = myRestTemplate.getForEntity("http://CLOUD-PAYMENT-SERVICE/payment/get/" + id, CommonResult.class);
        if(forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        }else {
            return new CommonResult<>(500,"操作失败",null);
        }

    }

}
