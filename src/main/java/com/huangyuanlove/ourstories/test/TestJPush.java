package com.huangyuanlove.ourstories.test;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

import java.util.HashMap;

/**
 * Created by huangyuan on 16-11-22.
 */
public class TestJPush {

    private static final String appKey = "ea4aa603ab7edb99a90a9466";
    private static final String masterSecret = "7f377da838f06d06d6160ece";


    public static void main(String args[]) {
        JPushClient client = new JPushClient(masterSecret, appKey);
        try {


            PushPayload pushPayload = buildPushObject_messageWithExtras();
            PushResult result = client.sendPush(pushPayload);
            System.out.print(result.toString());

        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }


    public static PushPayload buildPushObject_messageWithExtras() {
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("key1","value1");
        map.put("key2","value2");
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder().setAll(true).build())
                .setNotification(Notification.android("内容","标题",map))
                .build();
    }
}
