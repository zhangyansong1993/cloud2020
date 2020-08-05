package zys.com.springcloud.service;

import com.zys.springcloud.entities.CommonResult;
import org.springframework.stereotype.Service;

@Service
public class PaymentFeignServiceImpl implements PaymentFeignService {
    @Override
    public CommonResult getPaymentById(Long id) {
        return null;
    }

    @Override
    public String hystrixTest() {
        return "调用hystrixTest方法出错";
    }
}
