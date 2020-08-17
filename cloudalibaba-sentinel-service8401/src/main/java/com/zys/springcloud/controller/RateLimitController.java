package com.zys.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zys.springcloud.config.Custom;
import com.zys.springcloud.entities.CommonResult;
import com.zys.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称限流",new Payment(2020l,"serial001"));
    }

    public CommonResult handleException(BlockException e){
        return new CommonResult(500,e.getClass().getCanonicalName()+"服务不可用");
    }

    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200,"按URL限流",new Payment(2020l,"serial002"));
    }

    @GetMapping("/custom")
    @SentinelResource(value = "custom",blockHandlerClass = Custom.class,blockHandler = "custom2")
    public CommonResult Custom(){
        return new CommonResult(200,"按自定义",new Payment(2020l,"serial003"));
    }
}
