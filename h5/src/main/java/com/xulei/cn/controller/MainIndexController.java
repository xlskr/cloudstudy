package com.xulei.cn.controller;


import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.alibaba.nacos.common.util.Md5Utils;
import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.ResultCode;
import com.xulei.cn.fegin.SearoadService;
import com.xulei.cn.service.CustomerService;
import com.xulei.cn.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class MainIndexController extends WxController{

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SearoadService searoadService;

    private final static String SALT="UTF-8";

    /**
     * H5授权
     * 权限控制
     */
    @GetMapping ("/h5Auth")
    public  Result login(@RequestParam String code) {

        if (!StringUtils.isEmpty(code)) {
            String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
            Map<String, Object> reqParams = new HashMap();
            reqParams.put("appid", Const.APP_ID);
            reqParams.put("secret", Const.APP_SECRET);
            reqParams.put("code", code);
            reqParams.put("grant_type", "authorization_code");
            String responseResult = HttpUtil.get(requestUrl, reqParams);
            if (StringUtils.isEmpty(responseResult)) {
                return new Result(10004, "授权失败", false);
            }
            JSONObject jsonObject = new JSONObject(responseResult);
            //获取access_token
            String access_token = jsonObject.getStr("access_token");
            if(StringUtils.isEmpty(access_token))
                  return new Result(10004, "授权失败", false);
            String token= Md5Utils.getMD5(access_token,SALT);
            final Map<String,Object> map=new HashMap<>();
            map.put("token",token);
            map.put("access_token",access_token);
            map.put("openid",jsonObject.getStr("openid"));
            map.put("lang","zh_CN");
            //异步保存到redis中
            new Thread(()->{
                //保存用户
                customerService.saveCustomer(map);
            }).start();
            //获取用户信息
            return new Result(ResultCode.SUCCESS,token);
        } else {
            return Result.FAIL();
        }
    }

    //获取新首页
    @GetMapping ("/getSyPidData/{pid}")
    public  Result getSyPidData(@PathVariable String pid) {
        Map<String, Object> map = new HashMap<>();
        LinkedHashMap prizeQzData = (LinkedHashMap) searoadService.getDeatilById(pid).getData();
        //竞猜主数据
        map.put("prizeQzData",prizeQzData);
        //将id作为pid找出适应的子类id
        HashMap prizeChidData=searoadService.getDeatilByPid(pid);
        if(prizeChidData!=null){
            map.put("id",prizeChidData.get("id"));
        }
        //竞猜列表
        return new Result(ResultCode.SUCCESS,map);
    }

    //获取竞猜页数据
    @GetMapping ("/getSyData/{id}")
    public  Result getSyData(@PathVariable String id,@RequestParam String token) {
        //根据token获取用户
        Map<String, Object> map = customerService.findIsGet(token,id);
        if(map==null)
           return new Result(ResultCode.UNNOTOKEN);
        LinkedHashMap prizeQzData = (LinkedHashMap) searoadService.getDeatilById(id).getData();
        //竞猜主数据
        map.put("prizeQzData",prizeQzData);
        //竞猜列表
        Map map1=new HashMap<>();
        map1.put("pqId",prizeQzData.get("id").toString());
        Result guessListData = searoadService.findAll(map1);
        List<LinkedHashMap> list= (List<LinkedHashMap>) guessListData.getData();
        map.put("guessList", list);
        final Map map2=new HashMap<>();
        if(map.get("customerId")!=null&&(Boolean) map.get("isPost")){
            map2.put("customerId",map.get("customerId"));
            map.remove("customerId");
        }
        list.forEach((guessInfo)->{
            map2.put("guessId",guessInfo.get("id"));
            guessInfo.put("child",searoadService.findChidAll(map2).getData());
        });
        return new Result(ResultCode.SUCCESS,map);
    }
}
