package com.xulei.cn.utils;

import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Map;

public class BeanMapUtils {

    public static <T> Map<String,Object> toMap(T t) throws InstantiationException, IllegalAccessException {
        Map<String,Object> map=new HashMap<>();
        if(t!=null) {
            BeanMap beanMap = BeanMap.create(t);
            for(Object key:beanMap.keySet()){
                map.put(key+"",beanMap.get(key));
            }
        }
        return  map;
    }




    /**
     * 泛型方法
     * @param map
     * @param t
     * @param <T>
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
   public static <T> T toBeans(Map<String,Object> map,Class<T> t) throws InstantiationException, IllegalAccessException {
       T bean=t.newInstance();
       BeanMap beanMap = BeanMap.create(bean);
       beanMap.putAll(map);
       return  bean;
   }

}
