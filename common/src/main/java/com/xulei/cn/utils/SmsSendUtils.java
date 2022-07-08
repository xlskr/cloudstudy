package com.xulei.cn.utils;

import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

public class SmsSendUtils {

    @Value("${sms.trigger.url}")
    private String serviceUrl;

    @Value("${sms.trigger.account}")
    private String account;

    @Value("${sms.trigger.password}")
    private String password;



    public String triggerSmsSend(String mobile, String content) {
        String str = null;
        try {
            if (!StringUtils.isEmpty(mobile) && !StringUtils.isEmpty(content)) {
                ArrayList<BasicNameValuePair> data = new ArrayList<BasicNameValuePair>();//用来存放post请求的参数，前面一个键，后面一个值
                data.add(new BasicNameValuePair("account",account));//把昵称放进去
                data.add(new BasicNameValuePair("password", MD5.md5(password)));
                data.add(new BasicNameValuePair("mobile",mobile));//把昵称放进去
                data.add(new BasicNameValuePair("content",content));//把昵称放进去
                str=new HttpUtils().doPost(serviceUrl, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return str;
    }
}
