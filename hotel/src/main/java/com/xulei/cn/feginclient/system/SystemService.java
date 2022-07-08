package com.xulei.cn.feginclient.system;

import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.system.Role;
import com.xulei.cn.entities.system.SysUser;
import com.xulei.cn.entities.system.UserAndRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value="system",path = "/sys")
public interface SystemService {

    @PostMapping(value = "/user")
    public Result addUser(@RequestBody SysUser user);

    @PutMapping
    public Result editUser(@RequestBody SysUser user);

    @PutMapping(value = "/user/assignRoles")
    public Result assignRoles(@RequestBody UserAndRole userAndRole);

    @PostMapping(value = "/role")
    public Result addRole(@RequestBody Role role);
}
