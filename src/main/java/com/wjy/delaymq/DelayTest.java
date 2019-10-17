package com.wjy.delaymq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DelayTest {

    @Autowired
    private Sender sender;

    @Test
    public void delaySendTest() {
        System.err.println("发送延迟消息...");
        QueueMessage message = new QueueMessage("app.queue.hello", "测试延时消息...");
        //20代表延时消息队列;
        message.setType(20);
        //设置延时时间,单位为毫秒;
        message.setSeconds(6);
        sender.send(message);
        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
