package com.huangyuanlove.ourstories.controller;

import com.huangyuanlove.ourstories.bean.RequestResultBean;
import com.huangyuanlove.ourstories.entites.User;
import com.huangyuanlove.ourstories.rongim.io.rong.RongCloud;
import com.huangyuanlove.ourstories.rongim.io.rong.models.TokenReslut;
import com.huangyuanlove.ourstories.service.UserService;
import com.huangyuanlove.ourstories.utils.Config;
import com.huangyuanlove.ourstories.utils.MD5;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.NoSuchAlgorithmException;

/**
 * Created by huangyuan on 16-11-23.
 */

@Controller
public class UserControler {

    @Autowired
    UserService userService;

    private static Logger logger = Logger.getLogger(UserControler.class);


    @RequestMapping("regist")
    public
    @ResponseBody
    RequestResultBean register(User userBean) {
        RequestResultBean requestResultBean = new RequestResultBean();

        if (userService.findUserByEmail(userBean.getEmail()) != null) {
            requestResultBean.setStatus(Config.ERROR);
            requestResultBean.setErrmsg("邮箱已被注册");
            return requestResultBean;
        }

        String appKey = "8brlm7uf8p0w3";
        String appSecret = "JMf88ciJ3Z";

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);

        TokenReslut userGetTokenResult = null;
        try {
            userGetTokenResult = rongCloud.user.getToken("userId1", "username", "http://www.rongcloud.cn/images/logo.png");
            if (userGetTokenResult.getCode() == 200) {


                requestResultBean.setStatus(Config.SUCCESS);
                requestResultBean.setData(userBean);
                requestResultBean.setErrmsg("");
                userService.registerUser(userBean);

            } else {
                requestResultBean.setStatus(userGetTokenResult.getCode());
                requestResultBean.setData(null);
                requestResultBean.setErrmsg(userGetTokenResult.getErrorMessage());
                logger.error("融云请求错误：" + userGetTokenResult.getErrorMessage());
            }
        } catch (Exception e) {
            requestResultBean.setStatus(1000);
            requestResultBean.setData(null);
            requestResultBean.setErrmsg(e.getMessage() + "\n" + e.getStackTrace());
            logger.error("服务器错误：" + e.getMessage() + "\n" + e.getStackTrace());

        }

        return requestResultBean;
    }

    @RequestMapping("login")
    public
    @ResponseBody
    RequestResultBean login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        RequestResultBean requestResultBean = new RequestResultBean();

        try {
            password = MD5.MD5(password);
            User userBean = userService.findUserByEmailAndPassword(email, password);
            if (userBean != null) {
                requestResultBean.setStatus(Config.SUCCESS);
                requestResultBean.setData(userBean);
            } else {
                requestResultBean.setStatus(Config.ERROR);
                requestResultBean.setErrmsg("帐号或密码不正确");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("用户登录密码加密错误：" +"email="+email +",password"+password);
            logger.error("用户登录密码加密错误：" + e.getStackTrace());
            requestResultBean.setStatus(Config.ERROR);
            requestResultBean.setErrmsg("密码加密出错");
        }


        return requestResultBean;
    }


}
