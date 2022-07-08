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
    public Object mdyjtj(Map map) {
        getShopsId(map);
        return targetService.mdyjtj(map);
    }

    @Override
    public Object mdyjmbtj(Map map) {
        getShopsId(map);
        return targetService.mdyjmbtj(map);
    }

    @Override
    public Object ygyjtj(Map map) {
        getShopsId(map);
        return targetService.ygyjtj(map);
    }

    @Override
    public Object ygyjmbtj(Map map) {
        getShopsId(map);
        return targetService.ygyjmbtj(map);
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
