package com.xulei.cn.service;


import com.github.pagehelper.PageInfo;
import com.xulei.cn.entities.system.Menu;
import com.xulei.cn.entities.system.ShopsUser;
import com.xulei.cn.entities.system.SysUser;
import com.xulei.cn.entities.system.UserAndRole;

import java.util.List;
import java.util.Map;

public interface UserService {
    public SysUser getSysUser(String id);


    int addUser(SysUser user);

//    int assginRole(UserAndRole userAndRole);

    int assignRoles(UserAndRole userAndRole);

    SysUser findByMobile(String mobile,String id);

    int editUser(SysUser user);

    boolean userExistsByMobilePhone(Map pd);

    int saveVerificationCode(Map pd);

    boolean checkVerificationCode(String code, String mobile);

    List<Menu> findMenusByUser(SysUser user);

    //=================下面的两个方法和门店有关
    ShopsUser getShopsUser(String id);

    PageInfo<ShopsUser> getAllShopsUser(Map map);

    int delete(String id);

    UserAndRole finUserAndRole(UserAndRole userAndRole);
}
