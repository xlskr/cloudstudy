package com.xulei.cn.service.impl;


import com.github.pagehelper.PageInfo;
import com.xulei.cn.dao.DAO;
import com.xulei.cn.entities.system.Menu;
import com.xulei.cn.entities.system.ShopsUser;
import com.xulei.cn.entities.system.SysUser;
import com.xulei.cn.entities.system.UserAndRole;
import com.xulei.cn.service.BaseService;
import com.xulei.cn.service.UserService;
import com.xulei.cn.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private DAO daoSupport;

    @Autowired
    private IdWorker idWorker;

    public SysUser getSysUser(String id) {
        return (SysUser) daoSupport.findForObject("SysUserMapper.getSysUser",id);
    }

    @Override
    public int addUser(SysUser user) {

        SysUser userDb= this.findByMobile(user.getPhone(), user.getId());
        if(userDb!=null)
            return -1;
        user.setId(idWorker.nextId()+"");
        user.setCreateTime(new Date());
        return daoSupport.save("SysUserMapper.addUser",user);
    }

    /**
     * 为用户赋予角色
     * @param userAndRole
     * @return
     */
    @Override
    public int assignRoles(UserAndRole userAndRole) {
        daoSupport.delete("SysUserMapper.deleteUserAndRole",userAndRole);
        userAndRole.setId(idWorker.nextId()+"");
        return daoSupport.save("SysUserMapper.assginRole",userAndRole);
    }

    /**
     * 根据手机号查找用户
     * @param mobile
     * @return
     */
    @Override
    public SysUser findByMobile(String mobile,String id) {
        SysUser user=new SysUser();
        user.setPhone(mobile);
        user.setId(id);
        return (SysUser) daoSupport.findForObject("SysUserMapper.findByUser",user);
    }

    @Override
    public int editUser(SysUser user) {
        SysUser userDb= this.getSysUser(user.getId());
        if(userDb==null)
            return -1;

        //根据用户id查询数据库
//        SysUser userDbs= (SysUser) daoSupport.findForObject("SysUserMapper.getUserById",user.getId());
        userDb.setName(user.getName());
        userDb.setPhone(user.getPhone());
        userDb.setRemark(user.getRemark());
        userDb.setAiNum(user.getAiNum());
        userDb.setSex(user.getSex());
        userDb.setUserNum(user.getUserNum());
        //
        return daoSupport.update("SysUserMapper.update",userDb);
    }

    @Override
    public boolean userExistsByMobilePhone(Map pd) {
        return null != daoSupport.findForObject("SysUserMapper.userExistsByMobilePhone", pd) ? true : false;
    }

    @Override
    public int saveVerificationCode(Map pd) {
        return daoSupport.save("SysUserMapper.saveVerificationCode", pd);
    }

    @Override
    public boolean checkVerificationCode(String code, String mobile) {
        Map<String,Object> map=new HashMap<>();
        map.put("mobilePhone",mobile);
        map.put("verificationCode",code);
        return (Integer)daoSupport.findForObject("SysUserMapper.checkVerificationCode", map)==0;
    }

    @Override
    public List<Menu> findMenusByUser(SysUser user) {
        return (List<Menu>) daoSupport.findForList("SysUserMapper.findMenusByUser",user);
    }


    @Override
    public int delete(String id) {
        return daoSupport.delete("SysUserMapper.delete",id);
    }

    @Override
    public UserAndRole finUserAndRole(UserAndRole userAndRole) {
        return (UserAndRole) daoSupport.findForObject("SysUserMapper.finUserAndRole",userAndRole);
    }

    @Override
    public int isQuit(Map map) {
        return daoSupport.update("SysUserMapper.isQuit",map);
    }

}
