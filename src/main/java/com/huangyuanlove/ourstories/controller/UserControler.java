package com.huangyuanlove.ourstories.controller;

import com.huangyuanlove.ourstories.bean.RequestResultBean;
import com.huangyuanlove.ourstories.entites.User;
import com.huangyuanlove.ourstories.rongim.io.rong.RongCloud;
import com.huangyuanlove.ourstories.rongim.io.rong.models.TokenReslut;
import com.huangyuanlove.ourstories.service.UserService;
import com.huangyuanlove.ourstories.utils.Config;
import com.huangyuanlove.ourstories.utils.MD5;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.UUID;

/**
 * Created by huangyuan on 16-11-23.
 */

@Controller
public class UserControler {

    private UserService userService;

    private static Logger logger = Logger.getLogger(UserControler.class);

    @Autowired
    public UserControler(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping("regist")
    public
    @ResponseBody
    RequestResultBean register(User userBean) {
        RequestResultBean<User> requestResultBean = new RequestResultBean<User>();

        if(StringUtils.isEmpty(userBean.getEmail())){
            requestResultBean.setStatus(Config.ERROR);
            requestResultBean.setErrmsg("邮箱为空");
            return requestResultBean;
        }

        if(StringUtils.isEmpty(userBean.getPassword())){
            requestResultBean.setStatus(Config.ERROR);
            requestResultBean.setErrmsg("密码为空");
            return requestResultBean;
        }

        userBean.setPassword(MD5.getMD5(userBean.getPassword()));

        if(StringUtils.isEmpty(userBean.getName())){
            userBean.setName(UUID.randomUUID().toString());
        }

        if (userService.findUserByEmail(userBean.getEmail()) != null) {
            requestResultBean.setStatus(Config.ERROR);
            requestResultBean.setErrmsg("邮箱已被注册");
            return requestResultBean;
        }

        userBean.setRongIMToken(UUID.randomUUID().toString());

        //先在数据库中插入记录，获取id，然后去注册融云
        int userid = userService.registerUser(userBean);



        String appKey = "8brlm7uf8p0w3";
        String appSecret = "JMf88ciJ3Z";

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);

        TokenReslut userGetTokenResult = null;
        try {
            userGetTokenResult = rongCloud.user.getToken(String.valueOf(userid), userBean.getName(), "http://www.rongcloud.cn/images/logo.png");
            if (userGetTokenResult.getCode() == 200) {
                userBean.setRongIMToken(userGetTokenResult.getToken());
                userService.updateUerRongIMTokenById(userBean.getRongIMToken(),userid);
                requestResultBean.setStatus(Config.SUCCESS);
                requestResultBean.setData(userBean);
                requestResultBean.setErrmsg("");

            } else {
                userService.deleteUserById(userid);
                requestResultBean.setStatus(userGetTokenResult.getCode());
                requestResultBean.setData(null);
                requestResultBean.setErrmsg(userGetTokenResult.getErrorMessage());
                logger.error("融云请求错误：" + userGetTokenResult.getErrorMessage());
            }
        } catch (Exception e) {
            userService.deleteUserById(userid);
            requestResultBean.setStatus(Config.ERROR);
            requestResultBean.setData(null);
            requestResultBean.setErrmsg(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            logger.error("服务器错误：" + e.getMessage() + "\n" + e.getStackTrace());

        }

        return requestResultBean;
    }

    @RequestMapping("login")
    public
    @ResponseBody
    RequestResultBean login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        RequestResultBean<User> requestResultBean = new RequestResultBean<User>();
            password = MD5.getMD5(password);
            if(StringUtils.isEmpty(password)){
                requestResultBean.setStatus(Config.ERROR);
                requestResultBean.setErrmsg("系统内部错误");
                logger.error("login 密码加密错误");
                return requestResultBean;
            }
            User userBean = userService.findUserByEmailAndPassword(email, password);
            if (userBean != null) {
                requestResultBean.setStatus(Config.SUCCESS);
                requestResultBean.setData(userBean);
            } else {
                requestResultBean.setStatus(Config.ERROR);
                requestResultBean.setErrmsg("帐号或密码不正确");
            }
        return requestResultBean;
    }


}
