package com.wjy.ack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqAckTests {
    @Autowired
    private Producer producer;

    /**
    * @Desc: 测试之前需在application.yml开启消息确认的配置
    */
    @Test
    public void send() {
        producer.send();
    }
}
