package com.xulei.cn.service;

import com.github.pagehelper.PageInfo;
import com.xulei.cn.entities.shops.Shops;
import com.xulei.cn.entities.target.Target;

import java.util.List;
import java.util.Map;

public interface TargetService {
    int add(Target target);

    int edit(Target shops);

    Target getDeatilById(String id);

    PageInfo findAll(Map map);

    int delete(String id);

    List<Target> selectCheckedTargets(String roleId);

    List<Target> selectUnCheckedTargets(String hotelId);

    List<Target> selectCheckedTargetsByShopsId(String hotelId);
}
