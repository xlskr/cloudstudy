package com.xulei.cn.service;

import com.github.pagehelper.PageInfo;
import com.xulei.cn.entities.system.Menu;
import com.xulei.cn.entities.system.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
    int addRole(Role role);

    Role getRole(String id);

    PageInfo<Role> findAll(Map map);

    int editRole(Role role);

    int deleteRole(String id);

    void assginMenus(String roleId, List<String> menusIds);

    void assginTargets(String roleId, List<String> targetIds);

    void assginShops(String roleId, List<String> shopsIds);
}
