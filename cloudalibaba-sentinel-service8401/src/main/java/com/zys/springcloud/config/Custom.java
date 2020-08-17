package com.zys.springcloud.config;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zys.springcloud.entities.CommonResult;
import com.zys.springcloud.entities.Payment;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Custom {

    public static CommonResult custom(BlockException e){
        return new CommonResult(500,"自定义异常处理---->1");
    }
    public static CommonResult custom2(BlockException e){
        return new CommonResult(500,"自定义异常处理---->2");
    }
}
