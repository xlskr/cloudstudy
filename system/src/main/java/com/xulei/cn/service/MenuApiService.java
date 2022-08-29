package com.xulei.cn.service;

import com.github.pagehelper.PageInfo;
import com.xulei.cn.entities.system.MenuApi;
import com.xulei.cn.entities.system.MenuApis;

import java.util.List;
import java.util.Map;

public interface MenuApiService {
    int addMenuApi(MenuApi menu);

    MenuApi getMenuApi(String id);

    PageInfo<MenuApi> findAll(Map map);

    int editMenuApi(MenuApi menu);

    int deleteMenuApi(String id);

    List<MenuApis> selectAllMenuApis(String roleId);
}
