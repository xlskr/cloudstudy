package com.xulei.cn.service;

import com.github.pagehelper.PageHelper;

import java.util.Map;

public class BaseService {



    //分页参数控制
    public Map getPageParms(Map map){
        if(!map.containsKey("pageSize")){
            map.put("pageSize",10);
        }
        if(!map.containsKey("pageNum")){
            map.put("pageNum",1);
        }
        Integer pageNum= Integer.valueOf(map.get("pageNum").toString());
        Integer pageSize= Integer.valueOf(map.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        return map;
    }
}
