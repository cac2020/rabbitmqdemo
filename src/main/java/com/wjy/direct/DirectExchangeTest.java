package com.wjy.direct;

import com.wjy.mojo.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

/**
* @Desc: 测试类
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DirectExchangeTest {
    @Autowired
    private MqSender mqSender;

    @Test
    public void hello() throws Exception {
        mqSender.send();
    }

    /**
    * @Desc:  一对多
    * 应用场景：系统通常会做集群、分布式或灾备部署
    */
    @Test
    public void oneToMany() throws Exception {
        for (int i=0;i<100;i++){
            mqSender.send(i+"");
            Thread.sleep(200);
        }
    }

    /**
     * @Desc:  多对一 请求参数为偶数
     * 应用场景：系统通常会做集群、分布式或灾备部署
     */
    @Test
    public void test_sender_many2one_1() throws Exception {
        for (int i = 0; i < 20; i+=2) {
            mqSender.send("支付订单号："+i);
            Thread.sleep(1000);
        }
    }

    /**
     * @Desc:  多对一 请求参数为奇数
     * 应用场景：系统通常会做集群、分布式或灾备部署
     */
    @Test
    public void test_sender_many2one_2() throws Exception {
        for (int i = 1; i < 20; i+=2) {
            mqSender.send("支付订单号："+i);
            Thread.sleep(1000);
        }
    }


    /**
     * @Desc:  测试发送对象
     */
    @Test
    public void test_sender() {
        Order order = new Order();
        order.setId(100001);
        order.setOrderId(String.valueOf(System.currentTimeMillis()));
        order.setAmount(new BigDecimal("1999.99"));
        order.setCreateTime(new Date());
        mqSender.sender(order);
    }

    /**
     * @Desc:  测试RPC
     * RabbitMQ支持RPC远程调用，同步返回结果。
     * 虽然RabbitMQ支持RPC接口调用，但不推荐使用
     * 原因：
     * 1）RPC默认为单线程阻塞模型，效率极低。
     * 2）需要手动实现多线程消费。
     */
    @Test
    public void test_rpc() {
        mqSender.sender("900000001");
    }
}
