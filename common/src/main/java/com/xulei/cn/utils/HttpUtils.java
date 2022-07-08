package com.xulei.cn.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class HttpUtils {

    public HttpUtils() {

    }

    public String doPost(String url, ArrayList<BasicNameValuePair> data) {

        try {
            //UrlEncodedFormEntity这个类是用来把输入数据编码成合适的内容
            //两个键值对，被UrlEncodedFormEntity实例编码后变为如下内容：param1=value1¶m2=value2
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(data,
                    "UTF-8");//首先将参数设置为utf-8的形式，
            String result = "";//向服务器请求之后返回的数据结果
            HttpClient httpClient = new DefaultHttpClient();//申明一个网络访问客户端
            HttpPost post = new HttpPost(url);//post方式
            post.setEntity(entity);//带上参数
            HttpResponse httpResponse = httpClient.execute(post);//响应结果
            if (httpResponse.getStatusLine().getStatusCode() == 200) {//如果是200  表示成功
                result = EntityUtils.toString(httpResponse.getEntity());//把结果取出来  是一个STRING类型的
            }

            return result;
        } catch (Exception e) {
            return null;
        }
    }

    //执行浏览器调用
    public String sendPost(String url, Map<String,Object> data,Map<String,Object> headrs){
        String response = null;
        try {
            CloseableHttpClient httpclient = null;
            CloseableHttpResponse httpresponse = null;
            try {
                httpclient = HttpClients.createDefault();
                HttpPost httppost = new HttpPost(url);
                //StringEntity可以用来灵活设定参数格式形式，
                // 而UrlEncodeFormEntity则适合于传统表单格式的参数形式，
                // 至于到底用什么形式，也要看请求目标服务的数据接收形式喽
                String body = JSONObject.toJSONString(data);
                StringEntity stringentity = new StringEntity(body,"UTF-8");
                Set<Map.Entry<String, Object>> entries = headrs.entrySet();
                Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
                while(iterator.hasNext()){
                    Map.Entry<String, Object> next = iterator.next();
                    httppost.setHeader(next.getKey(),next.getValue().toString());
                }
                httppost.setEntity(stringentity);
                httpresponse = httpclient.execute(httppost);
                response = EntityUtils
                        .toString(httpresponse.getEntity());
            } finally {
                if (httpclient != null) {
                    httpclient.close();
                }
                if (httpresponse != null) {
                    httpresponse.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;

    }

    public String sendPost(String url, String data,Map<String,Object> headrs){
        String response = null;
        try {
            CloseableHttpClient httpclient = null;
            CloseableHttpResponse httpresponse = null;
            try {
                httpclient = HttpClients.createDefault();
                HttpPost httppost = new HttpPost(url);
                //StringEntity可以用来灵活设定参数格式形式，
                // 而UrlEncodeFormEntity则适合于传统表单格式的参数形式，
                // 至于到底用什么形式，也要看请求目标服务的数据接收形式喽
                System.out.println(data+"=========================");
                StringEntity stringentity = new StringEntity(data,"UTF-8");
                Set<Map.Entry<String, Object>> entries = headrs.entrySet();
                Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
                while(iterator.hasNext()){
                    Map.Entry<String, Object> next = iterator.next();
                    httppost.setHeader(next.getKey(),next.getValue().toString());
                }
                httppost.setEntity(stringentity);
                httpresponse = httpclient.execute(httppost);
                response = EntityUtils
                        .toString(httpresponse.getEntity());
            } finally {
                if (httpclient != null) {
                    httpclient.close();
                }
                if (httpresponse != null) {
                    httpresponse.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public String sendGet(String strUrl, Map<String, Object> data){
        String responseParams = "";
        BufferedReader bufferedReader = null;
        try {
            String strRequestUrl = strUrl + "?" + toString(data);
            URL url = new URL(strRequestUrl);
            URLConnection urlConnection = url.openConnection();    
            // 设置通用的请求属性
            urlConnection.setRequestProperty("accept", "*/*");
            urlConnection.setRequestProperty("connection", "Keep-Alive");
            urlConnection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            urlConnection.connect();
            Map<String, List<String>> map = urlConnection.getHeaderFields();    // 获取所有响应头字段
            // 使用BufferedReader输入流来读取URL的响应
            bufferedReader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            String strLine;
            while ((strLine = bufferedReader.readLine()) != null) {
                responseParams += strLine;
            }
        } catch (Exception e) {
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return responseParams;
    }

    private String toString(Map<String, Object> data) {
        //得到了node集合
        Set<Map.Entry<String, Object>> entries = data.entrySet();
        //遍历
        Iterator<Map.Entry<String, Object>> iterator = entries.iterator();

        StringBuilder s=new StringBuilder();

        while(iterator.hasNext()){
            Map.Entry<String, Object> next = iterator.next();
                s=s.append(next.getKey()).append("=").append(next.getValue()).append("&");
        }
        int start=s.lastIndexOf("&");
        s.delete(start,start+1);
        return s.toString();
    }
}

