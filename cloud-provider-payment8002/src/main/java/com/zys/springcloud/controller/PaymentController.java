package com.zys.springcloud.controller;


import com.zys.springcloud.entities.CommonResult;
import com.zys.springcloud.entities.Payment;
import com.zys.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/payment")
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @PostMapping(value = "/create")
    private CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果：" + result);
        if (result > 0) {
            return new CommonResult(200, "插入成功"+port, result);
        } else {
            return new CommonResult(500, "插入失败"+port, null);
        }
    }

    @GetMapping(value = "/get/{id}")
    private CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果" +payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功"+port, payment);
        } else {
            return new CommonResult(500, "查询失败：" + id+","+port, null);
        }
    }

    @GetMapping(value = "lb")
    public String getPort(){
        return port;
    }
}
