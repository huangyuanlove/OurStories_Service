package com.huangyuanlove.ourstories.serviceImpl;

import com.huangyuanlove.ourstories.bean.TestBean;
import com.huangyuanlove.ourstories.entites.User;
import com.huangyuanlove.ourstories.mapper.UserMapper;
import com.huangyuanlove.ourstories.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by huangyuan on 16-11-29.
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public int registerUser(User user) {
        userMapper.registerUser(user);
        return user.getId();
    }

    public User findUserById(int id) {
        return userMapper.findUserById(id);
    }

    public User findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    public User findUserByEmailAndPassword(String email, String password) {
        return userMapper.findUserByEmailAndPWD(email,password);
    }

    public List<TestBean> test() {
        return userMapper.test();
    }
}
