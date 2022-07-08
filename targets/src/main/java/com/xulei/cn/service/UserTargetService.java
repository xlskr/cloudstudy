package com.xulei.cn.service;

import com.github.pagehelper.PageInfo;
import com.xulei.cn.entities.shops.Shops;
import com.xulei.cn.entities.shops.UserAndShops;
import com.xulei.cn.entities.target.UserTarget;
import com.xulei.cn.entities.target.UserTargetP;

import java.util.List;
import java.util.Map;

public interface UserTargetService {

    int add(UserTarget userTarget);

    int edit(UserTarget userTarget);

    UserTarget getDeatilById(String id);

    List<Map> findAll(Map map);

    int delete(String id);
}
