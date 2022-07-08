package com.xulei.cn.service.impl;

import com.github.pagehelper.PageInfo;
import com.xulei.cn.dao.DAO;
import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.system.ShopsUser;
import com.xulei.cn.entities.shops.UserAndShops;
import com.xulei.cn.entities.system.SysUser;
import com.xulei.cn.entities.system.UserAndRole;
import com.xulei.cn.fegin.system.SystemService;
import com.xulei.cn.service.ShopsService;
import com.xulei.cn.service.UserService;
import com.xulei.cn.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private DAO daoSupport;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SystemService systemService;

    @Autowired
    private ShopsService shopsService;

    @Override
    public int add(ShopsUser shops) {
        SysUser sysUser=new SysUser();
        sysUser.setName(shops.getName());
        sysUser.setUserNum(shops.getUserNum());
        sysUser.setSex(shops.getSex());
        sysUser.setAiNum(shops.getAiNum());
        sysUser.setPhone(shops.getPhone());
        sysUser.setRemark(shops.getRemark());
        Result result = systemService.addUser(sysUser);
        if(result.isSuccess()){
            UserAndRole userAndRole=new UserAndRole();
            userAndRole.setUserId(((SysUser)result.getData()).getId());
            userAndRole.setRoleId(shops.getRoleId());
            systemService.assignRoles(userAndRole);
            //给用户赋予门店
            UserAndShops userAndShops=new UserAndShops();
            userAndShops.setUserId(((SysUser)result.getData()).getId());
            userAndShops.setShopsId(shops.getShopsId());
            shopsService.assginShopsUser(userAndShops);
            return 1;
        }
        return 0;
    }

    @Override
    public int edit(ShopsUser shopsUser) {
        SysUser sysUser=new SysUser();
        sysUser.setName(shopsUser.getName());
        sysUser.setUserNum(shopsUser.getUserNum());
        sysUser.setSex(shopsUser.getSex());
        sysUser.setAiNum(shopsUser.getAiNum());
        sysUser.setPhone(shopsUser.getPhone());
        sysUser.setRemark(shopsUser.getRemark());
        sysUser.setId(shopsUser.getId());
        Result result = systemService.editUser(sysUser);
        if(result.isSuccess()){
            UserAndRole userAndRole=new UserAndRole();
            userAndRole.setUserId(((SysUser)result.getData()).getId());
            userAndRole.setRoleId(shopsUser.getRoleId());
            systemService.assignRoles(userAndRole);
            //给门店添加用户
            UserAndShops userAndShops=new UserAndShops();
            userAndShops.setUserId(((SysUser)result.getData()).getId());
            userAndShops.setShopsId(shopsUser.getShopsId());
            shopsService.assginShopsUser(userAndShops);
            return 1;
        }
        return 0;
    }

    @Override
    public ShopsUser getDeatilById(String id) {
        return systemService.getDetailById(id);
    }

    @Override
    public PageInfo<ShopsUser> findAll(Map map) {
        return systemService.getAllShopsUser(map);
    }

    @Override
    public int delete(String id) {
        return 0;
    }
}
