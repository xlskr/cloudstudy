package com.xulei.cn.controller;

import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.ResultCode;
import com.xulei.cn.entities.system.ShopsUser;
import com.xulei.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 店铺菜单-----员工管理
 */
@RestController
@RequestMapping(value = "/shopsMenu/user")
public class UserController {

    @Autowired
    private UserService userService;

    //
    @PostMapping
    public Result add(@RequestBody ShopsUser shopsUser){
        if(userService.add(shopsUser)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    @PutMapping
    public Result editHotel(@RequestBody ShopsUser shopsUser){
        if(userService.edit(shopsUser)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    //根据id查看详情
    @GetMapping(value="/{id}")
    public Result getDeatilById(@PathVariable String id){
        return new Result(ResultCode.SUCCESS,userService.getDeatilById(id));
    }

    //查询全部 并且分页
    @GetMapping
    public Result findAll(@RequestParam Map map){
        return new Result(ResultCode.SUCCESS, userService.findAll(map));
    }



    @DeleteMapping(value="/{id}")
    public Result delete(@PathVariable String id){
        if(userService.delete(id)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }
}
