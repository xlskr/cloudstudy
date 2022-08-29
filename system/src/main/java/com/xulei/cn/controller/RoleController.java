package com.xulei.cn.controller;

import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.ResultCode;
import com.xulei.cn.entities.system.Role;
import com.xulei.cn.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "sys/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    //角色的新增
    @PostMapping
    public Result addRole(@RequestBody Role role){
        if(roleService.addRole(role)>0){
            return new Result(ResultCode.SUCCESS,role);
        }else{
            return Result.FAIL();
        }
    }

    //根据id查看详情
    @GetMapping(value="/{id}")
    public Result getRole(@PathVariable String id){
        return new Result(ResultCode.SUCCESS,roleService.getRole(id));
    }

    //查询全部 并且分页
    @GetMapping
    public Result findAll(@RequestParam Map map){
        return new Result(ResultCode.SUCCESS, roleService.findAll(map));
    }

    //角色的修改和删除
    @PutMapping
    public Result editRole(@RequestBody Role role){
        if(roleService.editRole(role)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    @DeleteMapping(value="/{id}")
    public Result deleteRole(@PathVariable String id){
        if(roleService.deleteRole(id)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    //为角色赋予菜单
    @RequestMapping(value = "/assginMenusAndMenuApi", method = RequestMethod.PUT)
    public Result assginMenus(@RequestBody Map map) {
        String roleId=map.get("id").toString();
        List<String> menusIds = (List<String>) map.get("menusIds");
        List<String> menusApiIds = (List<String>) map.get("menusApiIds");
        //3.调用service完成角色分配
        roleService.assginMenus(roleId,menusIds);
        roleService.assginMenusApi(roleId,menusApiIds);
        return new Result().SUCCESS();
    }


    //为角色配置api
    @RequestMapping(value = "/assginTargets", method = RequestMethod.PUT)
    public Result assginTargets(@RequestBody Map map) {
        String roleId=map.get("id").toString();
        List<String> targetIds = (List<String>) map.get("targetIds");
        //3.调用service完成角色分配
        roleService.assginTargets(roleId,targetIds);
        return new Result().SUCCESS();
    }


    //为角色分配店铺(数据)
    @RequestMapping(value = "/assginShops", method = RequestMethod.PUT)
    public Result assginShops(@RequestBody Map map) {
        String roleId=map.get("id").toString();
        List<String> shopsIds = (List<String>) map.get("shopsIds");
        //3.调用service完成角色分配
        roleService.assginShops(roleId,shopsIds);
        return new Result().SUCCESS();
    }

}
