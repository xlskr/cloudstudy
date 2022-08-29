package com.xulei.cn.utils;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WxUtil {


    private static String getAccesstoken(){
        String testUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET"
                .replace("APPID",Const.APP_ID).replace("APPSECRET",Const.APP_SECRET);
        JSONObject jsonObject1 = new JSONObject(HttpUtil.get(testUrl));
        return jsonObject1.getStr("access_token");
    }

    /**
     *
     * 微信h5页面分享
     *
     */
    private static String getTicket(){

        String ticket = "";
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+getAccesstoken()+"&type=jsapi";

        try {
            String resultString =HttpUtil.get(url);
            if (null != resultString && !"".equals(resultString)) {
                System.out.println(resultString);
                JSONObject json = new JSONObject(resultString);
                ticket = json.getStr("ticket");
            }else{
                System.out.println("返回值为空，请检查请求报文或者请求地址是否正确");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }

    public static Map<String, String> sign( String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";
        String jsapi_ticket = getTicket();
//注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString().replace("-","");
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }


}
