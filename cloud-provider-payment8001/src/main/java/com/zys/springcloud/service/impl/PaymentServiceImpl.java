package com.zys.springcloud.service.impl;

import com.zys.springcloud.Dao.PaymentDao;
import com.zys.springcloud.entities.Payment;
import com.zys.springcloud.service.PaymentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(@Param("id") Long id) {
        return paymentDao.getPaymentById(id);
    }


}
