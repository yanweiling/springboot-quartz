package com.ywl.study.service;

import com.ywl.study.dao.OrderMapper;
import com.ywl.study.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;

    public void createOrder(){
        Random random = new Random();
        int count = random.nextInt(5);
        for (int i=0;i<count ;i++){
            Order order = new Order();
            order.setAmount(BigDecimal.TEN);
            order.setStatus(1);
            order.setReceiveName("xxxx");
            order.setReceiveAddress("XXXXX");
            order.setReceiveMobile("13811112222");
            order.setCreateTime(new Date());
            order.setCreateUser("test");
            order.setUpdateTime(new Date());
            order.setUpdateUser("test");
            orderMapper.insertSelective(order);
        }
    }
}
