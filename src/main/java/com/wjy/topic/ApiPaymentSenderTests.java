package com.wjy.topic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiPaymentSenderTests {
    @Autowired
    private ApiPaymentSender sender;

    @Test
    public void test_order() {
        sender.order("订单管理！");
    }

    @Test
    public void test_orderQuery() {
        sender.orderQuery("查询订单信息！");
    }

    @Test
    public void test_orderDetailQuery() {
        sender.orderDetailQuery("查询订单详情信息！");
    }
}
