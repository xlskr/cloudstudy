package com.xulei.cn.service.impl;

import com.github.pagehelper.PageInfo;
import com.xulei.cn.dao.DAO;
import com.xulei.cn.entities.system.*;
import com.xulei.cn.service.BaseService;
import com.xulei.cn.service.RoleService;
import com.xulei.cn.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl extends BaseService implements RoleService {

    @Autowired
    private DAO daoSupport;

    @Autowired
    private IdWorker idWorker;

    @Override
    public int addRole(Role role) {
        role.setId(idWorker.nextId()+"");
        //去重以后做
        role.setCreateTime(new Date());
        return daoSupport.save("RoleMapper.addRole",role);
    }

    @Override
    public Role getRole(String id) {
        return (Role) daoSupport.findForObject("RoleMapper.getRole",id);
    }

    @Override
    public PageInfo<Role> findAll(Map map) {
        Map map1 = this.getPageParms(map);
        List<Role> roles = (List<Role>) daoSupport.findForList("RoleMapper.findAll", map);
        PageInfo<Role> pageInfo = new PageInfo<Role>(roles);
        return pageInfo;
    }

    @Override
    public int editRole(Role role) {
        Role roleDb = this.getRole(role.getId());
        if(roleDb==null)
            return -1;
        roleDb.setName(role.getName());
        roleDb.setDescription(role.getDescription());
        return daoSupport.update("RoleMapper.editRole",roleDb);
    }

    @Override
    public int deleteRole(String id) {
        return daoSupport.delete("RoleMapper.deleteRole",id);
    }

    @Override
    public void assginMenus(String roleId, List<String> menusIds) {
        //删除原来的赋值
        daoSupport.delete("RoleMapper.deleteRoleAndMenu",roleId);
        List<RoleAndMenu> roleAndMenus=new ArrayList<>();

        for(int i=0;i< menusIds.size();i++) {
            RoleAndMenu roleAndMenu = new RoleAndMenu();
            roleAndMenu.setRoleId(roleId);
            roleAndMenu.setMenuId(menusIds.get(i));
            roleAndMenu.setId(idWorker.nextId() + "");
            roleAndMenus.add(roleAndMenu);
        }

        daoSupport.save("RoleMapper.assginMenus",roleAndMenus);
    }

    @Override
    public void assginTargets(String roleId, List<String> targetIds) {
        List<RoleAndTarget> roleAndTargets=new ArrayList<>();

        for(int i=0;i< targetIds.size();i++) {
            RoleAndTarget roleAndMenu = new RoleAndTarget();
            roleAndMenu.setRoleId(roleId);
            roleAndMenu.setTargetId(targetIds.get(i));
            roleAndMenu.setId(idWorker.nextId() + "");
            roleAndTargets.add(roleAndMenu);
        }
        daoSupport.save("RoleMapper.assginTargets",roleAndTargets);
    }

    @Override
    public void assginShops(String roleId, List<String> shopsIds) {
        List<RoleAndData> roleAndData=new ArrayList<>();

        for(int i=0;i< shopsIds.size();i++) {
            RoleAndData roleAndMenu = new RoleAndData();
            roleAndMenu.setRoleId(roleId);
            roleAndMenu.setRoleId(shopsIds.get(i));
            roleAndMenu.setId(idWorker.nextId() + "");
            roleAndData.add(roleAndMenu);
        }

        daoSupport.save("RoleMapper.assginShops",roleAndData);

    }

}
