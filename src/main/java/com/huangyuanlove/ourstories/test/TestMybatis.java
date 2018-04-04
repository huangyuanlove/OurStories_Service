package com.huangyuanlove.ourstories.test;


import com.huangyuanlove.ourstories.entites.User;
import com.huangyuanlove.ourstories.mapper.UserMapper;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by huangyuan on 16-11-27.
 */
public class TestMybatis {
    private static Logger logger = Logger.getLogger(TestMybatis.class);

    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
        User user = userMapper.findUserById(8);
        logger.info(user);
        System.out.println(user);
    }

    public static void main(String args[])
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
        User user = userMapper.findUserById(8);
        System.out.println(user);
    }


}
