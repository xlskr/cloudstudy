package com.xulei.cn.service;

import com.xulei.cn.entities.system.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService {
    int addMenu(Menu menu);

    Menu getMenu(String id);

    List<Menu> findAll(Map map);

    int editMenu(Menu menu);

    int deleteMenu(String id);

    List<Menu> selectCheckedMenus(String roleId);
}
