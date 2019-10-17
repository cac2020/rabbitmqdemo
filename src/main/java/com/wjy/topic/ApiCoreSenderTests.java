package com.wjy.topic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiCoreSenderTests {
    @Autowired
    private ApiCoreSender sender;

    @Test
    public void test_user() {
        sender.user("用户管理！");
    }

    @Test
    public void test_userQuery() {
        sender.userQuery("查询用户信息！");
    }
}
