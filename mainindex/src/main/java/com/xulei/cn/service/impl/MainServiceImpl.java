package com.xulei.cn.service.impl;

import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.shops.Shops;
import com.xulei.cn.fegin.shop.ShopsService;
import com.xulei.cn.fegin.target.TargetService;
import com.xulei.cn.service.MainService;
import com.xulei.cn.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private ShopsService shopsService;

    @Autowired
    private TargetService targetService;



    @Override
    public Object getDb(String shopsId, String time, String roleId, String type, String targetId) {
        List<String> shopsIds=new ArrayList<>();
        paramModified(shopsId, roleId, shopsIds);
        Map map=new HashMap();
        map.put("shopsIds",shopsIds);
        map.put("timeStart",time+"-01");
        map.put("timeEnd", TimeUtils.isLastDayOfMonth(time));
        map.put("type", type);
        map.put("targetId", targetId);
        return targetService.getDb(map);
    }

    @Override
    public Object getWdb(String shopsId, String time, String roleId, String type, String targetId) {
        List<String> shopsIds=new ArrayList<>();
        paramModified(shopsId, roleId, shopsIds);
        Map map=new HashMap();
        map.put("shopsIds",shopsIds);
        map.put("timeStart",time+"-01");
        map.put("timeEnd", TimeUtils.isLastDayOfMonth(time));
        map.put("type", type);
        map.put("targetId", targetId);
        return targetService.getWdb(map);
    }

    @Override
    public Object getMoreDb(String shopsId, String time, String roleId, String type, String targetId) {
        List<String> shopsIds=new ArrayList<>();
        paramModified(shopsId, roleId, shopsIds);
        Map map=new HashMap();
        map.put("shopsIds",shopsIds);
        map.put("timeStart",time+"-01");
        map.put("timeEnd", TimeUtils.isLastDayOfMonth(time));
        map.put("type", type);
        map.put("targetId", targetId);
        return targetService.getMoreDb(map);
    }

    @Override
    public Object getMoreWdb(String shopsId, String time, String roleId, String type, String targetId) {
        List<String> shopsIds=new ArrayList<>();
        paramModified(shopsId, roleId, shopsIds);
        Map map=new HashMap();
        map.put("shopsIds",shopsIds);
        map.put("timeStart",time+"-01");
        map.put("timeEnd", TimeUtils.isLastDayOfMonth(time));
        map.put("type", type);
        map.put("targetId", targetId);
        return targetService.getMoreWdb(map);
    }


    @Override
    public Object getDbDeatil(String shopsId, String time, String roleId, String type, String targetId, String userId) {
        List<String> shopsIds=new ArrayList<>();
        paramModified(shopsId, roleId, shopsIds);
        Map map=new HashMap();
        map.put("shopsIds",shopsIds);
        map.put("timeStart",time+"-01");
        map.put("timeEnd", TimeUtils.isLastDayOfMonth(time));
        map.put("type", type);
        map.put("targetId", targetId);
        map.put("userId", userId);
        return targetService.getDbDetail(map);
    }

    @Override
    public Object getWdbDeatil(String shopsId, String time, String roleId, String type, String targetId, String userId) {
        List<String> shopsIds=new ArrayList<>();
        paramModified(shopsId, roleId, shopsIds);
        Map map=new HashMap();
        map.put("shopsIds",shopsIds);
        map.put("timeStart",time+"-01");
        map.put("timeEnd", TimeUtils.isLastDayOfMonth(time));
        map.put("type", type);
        map.put("targetId", targetId);
        map.put("userId", userId);
        return targetService.getWdbDetail(map);
    }

    /**
     * timeStart timeEnd shopsId(有值代表单个门店 ""就是所有门店) roleId
     * 参数 timeStart 2022-05  2022-07
     * @param map
     * @return
     */
    @Override
    public Object fwzbtj(Map map) {
        //
        map.put("timeStart",map.get("timeStart").toString()+"-01");
        map.put("timeEnd",TimeUtils.isLastDayOfMonth(map.get("timeEnd").toString()));
        //服务指标统计
        getShopsId(map);
        return targetService.getFwzbtj(map);
    }

    /**
     * timeStart timeEnd shopsId(有值代表单个门店 ""就是所有门店) roleId  type
     * 参数 timeStart 2022-05  2022-07
     * @param map
     * @return
     */
    @Override
    public Object fwzbtjDeatil(Map map) {
        map.put("timeStart",map.get("timeStart").toString()+"-01");
        map.put("timeEnd",TimeUtils.isLastDayOfMonth(map.get("timeEnd").toString()));
        Object shopsId = map.get("shopsId");
        String roleId=map.get("roleId").toString();
        List<String> shopsIds=new ArrayList<>();
        if(shopsId!=null&&!StringUtils.isEmpty(shopsId.toString())){
            //获取单个门店详情
            shopsIds.add(shopsId.toString());
            map.put("shopsIds",shopsIds);
            return targetService.getFwzbtjOneShopsDeatil(map);
        }else{
            Result result = shopsService.selectCheckedShops(roleId);
            List<Shops> shopsList= (List<Shops>) result.getData();
            //根据shopsId求出每个人的指标
            shopsList.forEach((shops)->shopsIds.add(shops.getId()));
            map.put("shopsIds",shopsIds);
            return targetService.getFwzbtjMoreShopsDeatil(map);
        }
    }

    @Override
    public Object getsdqsByWeek(Map map) {
        getShopsId(map);
        return targetService.getsdqsByWeek(map);
    }

    @Override
    public Object getsdqsByMonth(Map map) {
        getShopsId(map);
        return targetService.getsdqsByMonth(map);
    }

    @Override
    public Object getSdzbWeekDetail(Map map) {
        getShopsId(map);
        return targetService.getSdzbWeekDetail(map);
    }

    @Override
    public Object getSdzbMonthDetail(Map map) {
        getShopsId(map);
        return targetService.getSdzbMonthDetail(map);
    }


    private void getShopsId(Map map) {
        String shopsId=map.get("shopsId")==null?"":map.get("shopsId").toString();
        String roleId=map.get("roleId").toString();
        List<String> shopsIds=new ArrayList<>();
        paramModified(shopsId, roleId, shopsIds);
        map.put("shopsIds",shopsIds);
    }

    private void paramModified(String shopsId, String roleId, List<String> shopsIds) {
        if(StringUtils.isEmpty(shopsId)){
            Result result = shopsService.selectCheckedShops(roleId);
            List<Shops> shopsList= (List<Shops>) result.getData();
            //根据shopsId求出每个人的指标
            shopsList.forEach((shops)->shopsIds.add(shops.getId()));
        }else {
            shopsIds.add(shopsId);
        }
    }


}
