package zys.com.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zys.springcloud.entities.CommonResult;
import com.zys.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zys.com.springcloud.service.PaymentFeignService;

@RequestMapping("/consumer/payment/")
@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "defaultFallback")
public class FeignOrderController {
    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    //    @HystrixCommand(fallbackMethod = "timeout",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
//    })
 //   @HystrixCommand
    @GetMapping(value = "/hystrix")
    public String hystrixTest() {

        return paymentFeignService.hystrixTest();
    }

    public String timeout() {

        return "消费端超时了也没毛病";
    }

    public String defaultFallback() {
        return "这是全局的配置";
    }
}
