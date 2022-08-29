package com.xulei.cn.controller;


import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.ResultCode;
import com.xulei.cn.entities.system.Menu;
import com.xulei.cn.entities.system.MenuApi;
import com.xulei.cn.entities.system.MenuApis;
import com.xulei.cn.service.MenuApiService;
import com.xulei.cn.utils.MenuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "sys/menuApi")
public class MenuApiController {

    //根据菜单id获取api列表
    @Autowired
    private MenuApiService menuApiService;

    //菜单的新增
    @PostMapping
    public Result addMenuApi(@RequestBody MenuApi menu){
        if(menuApiService.addMenuApi(menu)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    //根据id查看详情
    @GetMapping(value="/{id}")
    public Result getMenuApi(@PathVariable String id){
        return new Result(ResultCode.SUCCESS,menuApiService.getMenuApi(id));
    }

    //查询全部 并且不分页(有hotelId就要加上hotelId)
    @GetMapping
    public Result findAll(@RequestParam Map map){
        return new Result(ResultCode.SUCCESS, menuApiService.findAll(map));
    }


    //修改    先不做
    @PutMapping
    public Result editMenu(@RequestBody MenuApi menu){
        if(menuApiService.editMenuApi(menu)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }


    @DeleteMapping(value="/{id}")
    public Result deleteMenu(@PathVariable String id){
        if(menuApiService.deleteMenuApi(id)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    //根据角色查出已勾选的API框框
    @GetMapping(value = "/selectAllMenuApis/{roleId}")
    public Result selectAllMenuApis(@PathVariable String roleId) {
        //3.调用service完成角色分配
        List<MenuApis> menus=menuApiService.selectAllMenuApis(roleId);
        return new Result(ResultCode.SUCCESS, menus);
    }

}
