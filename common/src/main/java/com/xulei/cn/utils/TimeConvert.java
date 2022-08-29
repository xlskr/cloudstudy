package com.xulei.cn.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TimeConvert {

    public List<Map> convertData(List<Map> yData, List<String> listx) {
        final List<Map> list=new ArrayList<>();
        for(int i=0;i< listx.size();i++){
            final String time=listx.get(i);
            yData.forEach((map)->{
                if(time.equals(map.get("createTime").toString())){
                    list.add(map);
                }
            });
            if(list.size()!=(i+1)){
                Map map=new HashMap();
                insertMapValue(map,time);
                list.add(map);
            }
        }
        return list;
    }

    public abstract void insertMapValue(Map map,String time);
}
