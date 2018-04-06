package com.huangyuanlove.ourstories.service;

import com.huangyuanlove.ourstories.bean.TestBean;
import com.huangyuanlove.ourstories.entites.User;

import java.util.List;

/**
 * Created by huangyuan on 16-11-29.
 */
public interface UserService {

    /**
     * 注册用户
     */

    public int registerUser(User user);

    /**
     * 根据查找用户
     */
    public User findUserById(int id);

    /**
     * 根据邮箱查找用户
     */
    public User findUserByEmail(String email);


    /**
     * 登录
     */
    public User findUserByEmailAndPassword(String email,String password);


    /**
     * 删除
     */
    public int deleteUserById(int id);


    /**
     * 更新融云token
     */
    public int updateUerRongIMTokenById(String rongIMToken,int id);


    /**
     * 测试
     */
    public List<TestBean> test();

}
