package com.huangyuanlove.ourstories.test;

import com.huangyuanlove.ourstories.rongim.io.rong.RongCloud;
import com.huangyuanlove.ourstories.rongim.io.rong.models.TokenReslut;

import java.io.Reader;

/**
 * Created by huangyuan on 16-11-22.
 */
public class TestRongIM {


    public static void main(String args[]) {

        String appKey = "8brlm7uf8p0w3";
        String appSecret = "JMf88ciJ3Z";

        Reader reader = null ;
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);


        System.out.println("************************User********************");
        // 获取 Token 方法
        TokenReslut userGetTokenResult = null;
        try {
            userGetTokenResult = rongCloud.user.getToken("userId1", "username", "http://www.rongcloud.cn/images/logo.png");
        System.out.println("getToken:  " + userGetTokenResult.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
