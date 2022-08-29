package com.xulei.cn.controller;

import com.github.pagehelper.PageInfo;
import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.ResultCode;
import com.xulei.cn.entities.system.ShopsUser;
import com.xulei.cn.entities.system.SysUser;
import com.xulei.cn.entities.system.UserAndRole;
import com.xulei.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 员工管理
 */
@RestController
@RequestMapping(value = "/sys/user")
public class SysUserController {

    @Autowired
    private UserService userService;


    @PutMapping(value = "/shopsUser/isQuit")
    public int isQuit(@RequestBody Map map){
        return userService.isQuit(map);
    };

    /**
     * 用户的新增
     * @param user
     * @return
     */
    @PostMapping
    public Result addUser(@RequestBody SysUser user){
        if(userService.addUser(user)>0){
            return new Result(ResultCode.SUCCESS,user);
        }else{
            return Result.FAIL();
        }
    }

    /**
     * 为用户赋予角色 1对1
     * @param
     * @return
     */
    @RequestMapping(value = "/assignRoles", method = RequestMethod.PUT)
    public Result assignRoles(@RequestBody UserAndRole userAndRole) {

        //3.调用service完成角色分配
        userService.assignRoles(userAndRole);
        return new Result().SUCCESS();
    }


    @PutMapping
    public Result editUser(@RequestBody SysUser user){
        if(userService.editUser(user)>0){
            return new Result(ResultCode.SUCCESS,user);
        }else{
            return Result.FAIL();
        }
    }


    //根据手机号查重---- 前段有data 就是不重复无data就是重复
    @GetMapping(value ="/{phone}")
    public Result findUserByPhone(@PathVariable String phone,@RequestParam String id){
        SysUser byMobile = userService.findByMobile(phone, id);
        return new Result(ResultCode.SUCCESS,byMobile);
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id){
        if(userService.delete(id)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

}
