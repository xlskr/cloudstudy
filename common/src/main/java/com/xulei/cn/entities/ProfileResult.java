package com.xulei.cn.entities;

import com.xulei.cn.entities.system.Menu;
import com.xulei.cn.entities.system.MenuLevel;
import com.xulei.cn.entities.system.SysUser;
import com.xulei.cn.utils.MenuUtils;
import lombok.Data;
import org.crazycake.shiro.AuthCachePrincipal;

import java.io.Serializable;
import java.util.*;
@Data
public class ProfileResult implements Serializable, AuthCachePrincipal {
    private String phone;
    private String username;
    private String hotelId;
    private String userId;
    private String roleId;

    private List<MenuLevel> menus;
//    private Map<String,Object> roles = new HashMap<>();

    /**
     *
     * @param user
     */
    public ProfileResult(SysUser user, List<Menu> list) {
        this.phone = user.getPhone();
        this.username = user.getName();
        this.hotelId = user.getHotelId();
        this.userId=user.getId();
        this.menus=MenuUtils.getChildren("0",list);
    }




    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
