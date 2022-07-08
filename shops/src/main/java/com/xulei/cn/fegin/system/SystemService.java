package com.xulei.cn.fegin.system;

import com.github.pagehelper.PageInfo;
import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.system.Role;
import com.xulei.cn.entities.system.ShopsUser;
import com.xulei.cn.entities.system.SysUser;
import com.xulei.cn.entities.system.UserAndRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@FeignClient(value="system",path = "/sys")
public interface SystemService {

    @PostMapping(value = "/user")
    public Result addUser(@RequestBody SysUser user);

    @RequestMapping(value = "/user/assignRoles", method = RequestMethod.PUT)
    public Result assignRoles(@RequestBody UserAndRole userAndRole) ;

    @PutMapping(value = "/user")
    public Result editUser(@RequestBody SysUser user);

    @GetMapping(value = "/user/shopsUser/id/{id}")
    public ShopsUser getDetailById(@PathVariable(value = "id") String id);

    @GetMapping(value = "/user/shopsUser/list")
    public PageInfo<ShopsUser> getAllShopsUser(@RequestParam Map map);
}
