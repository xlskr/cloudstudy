package com.xulei.cn.controller;

import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.ResultCode;
import com.xulei.cn.entities.system.Menu;
import com.xulei.cn.service.MenuService;
import com.xulei.cn.utils.MenuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "sys/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    //菜单的新增
    @PostMapping
    public Result addMenu(@RequestBody Menu menu){
        if(menuService.addMenu(menu)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    //根据id查看详情
    @GetMapping(value="/{id}")
    public Result getMenu(@PathVariable String id){
        return new Result(ResultCode.SUCCESS,menuService.getMenu(id));
    }

    //查询全部 并且不分页(有hotelId就要加上hotelId)
    @GetMapping
    public Result findAll(@RequestParam Map map){
        List<Menu> all = menuService.findAll(map);
        return new Result(ResultCode.SUCCESS, MenuUtils.getChildren("0",all));
    }

    @GetMapping(value = "/findMenusByHotelId")
    public Result findAllByHotelId(@RequestParam Map map){
        List<Menu> all = menuService.findAll(map);
        return new Result(ResultCode.SUCCESS, MenuUtils.getChildren("0",all));
    }

    //根据角色查询已勾选的菜单
    @GetMapping(value = "/selectCheckedMenus/{roleId}")
    public Result selectCheckedMenus(@PathVariable String roleId) {
        //3.调用service完成角色分配
        List<Menu> menus=menuService.selectCheckedMenus(roleId);
        return new Result(ResultCode.SUCCESS, MenuUtils.getChildren("0",menus));
    }

    //修改    先不做
    @PutMapping
    public Result editMenu(@RequestBody Menu menu){
        if(menuService.editMenu(menu)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    @DeleteMapping(value="/{id}")
    public Result deleteMenu(@PathVariable String id){
        if(menuService.deleteMenu(id)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }
}
