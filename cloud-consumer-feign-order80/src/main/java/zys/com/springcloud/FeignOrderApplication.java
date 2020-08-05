package zys.com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients //使用openFeign 负载均衡
@EnableHystrix //开启hystrix服务降级相关功能
public class FeignOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignOrderApplication.class, args);
    }
}
