package com.zys.springcloud.controller;


import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zys.springcloud.entities.CommonResult;
import com.zys.springcloud.entities.Payment;
import com.zys.springcloud.service.HystrixService;
import com.zys.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.security.Provider;
import java.util.List;

@RequestMapping(value = "/payment")
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String port;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private HystrixService hystrixService;

    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果：" + result);
        if (result > 0) {
            return new CommonResult(200, "插入成功" + port, result);
        } else {
            return new CommonResult(500, "插入失败" + port, null);
        }
    }

    @GetMapping(value = "/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功" + port, payment);
        } else {
            return new CommonResult(500, "查询失败：" + id + "," + port, null);
        }
    }


    /**
     * 服务相关信息
     *
     * @return
     */
    @GetMapping(value = "/discovery")
    public Object discovery() {

        List<String> service = discoveryClient.getServices();
        for (String s : service) {
            log.info(s);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    /**
     * 自定义负载均衡策略
     *
     * @return
     */
    @GetMapping(value = "/lb")
    public String getPort() {
        return port;
    }

    /**
     * hystrix 服务降级
     *
     * @return
     */
    @GetMapping(value = "/hystrix")
    public String hystrixTest() {
        return hystrixService.test();
    }


    /**
     * hystrix 服务熔断
     * @param id
     * @return
     */

    public String circuiBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        String num = IdUtil.simpleUUID();
        return num;
    }

    public String circuiBreaker_fallback(@PathVariable("id") Integer id) {

        return "id不能为负数，请输入整数";
    }
}
