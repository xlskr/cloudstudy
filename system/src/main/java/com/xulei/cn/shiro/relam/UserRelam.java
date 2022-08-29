package com.xulei.cn.shiro.relam;

import com.xulei.cn.entities.ProfileResult;
import com.xulei.cn.entities.system.Menu;
import com.xulei.cn.entities.system.SysUser;
import com.xulei.cn.entities.system.UserAndRole;
import com.xulei.cn.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRelam extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String mobile = upToken.getUsername();
        String password = new String( upToken.getPassword());
        //2.根据手机号查询用户
        SysUser user = userService.findByMobile(mobile,"");
        //3.判断用户是否存在，用户密码是否和输入密码一致
        if(user != null) {
            List<Menu> menuList=userService.findMenusByUser(user);
            ProfileResult result = new ProfileResult(user,menuList);
            UserAndRole userAndRole=new UserAndRole();
            userAndRole.setUserId(user.getId());
            userAndRole=userService.finUserAndRole(userAndRole);
            result.setRoleId(userAndRole.getRoleId());
            //构造方法：安全数据，密码，realm域名
            //根据角色ID找出MENUAPI

            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(result,"123456",this.getName());
            return info;
        }
        //返回null，会抛出异常，标识用户名和密码不匹配
        return null;
    }
}
