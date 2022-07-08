package com.xulei.cn.controller;

import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.ResultCode;
import com.xulei.cn.entities.hotel.Hotel;
import com.xulei.cn.entities.shops.Shops;
import com.xulei.cn.entities.target.Target;
import com.xulei.cn.service.ShopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 店铺菜单--店铺管理
 */
@RestController
@RequestMapping(value = "/shopsMenu/shops")
public class ShopsController extends BaseController{


    @Autowired
    private ShopsService shopsService;


    //
    @PostMapping
    public Result add(@RequestBody Shops shops){
        shops.setHotelId(hotelId);
        if(shopsService.add(shops)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    @PutMapping
    public Result edit(@RequestBody Shops shops){
        if(shopsService.edit(shops)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    //根据id查看详情
    @GetMapping(value="/{id}")
    public Result getDeatilById(@PathVariable String id){
        return new Result(ResultCode.SUCCESS,shopsService.getDeatilById(id));
    }

    //查询全部 并且分页 hotelId必备
    @GetMapping
    public Result findAll(@RequestParam Map map){
        return new Result(ResultCode.SUCCESS, shopsService.findAll(map));
    }



    @DeleteMapping(value="/{id}")
    public Result delete(@PathVariable String id){
        if(shopsService.delete(id)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }


    /**
     * 该api所有角色全部放开
     * @param roleId
     * @return
     */
    @GetMapping(value = "/selectCheckedShops/{roleId}")
    public Result selectCheckedShops(@PathVariable String roleId) {
        //3.调用service完成角色分配
        List<Shops> targets=shopsService.selectCheckedShops(roleId);
        return new Result(ResultCode.SUCCESS,targets);
    }

    /**
     * 该api所有角色全部放开
     * @return
     */
    @GetMapping(value = "/selectUnCheckedShops")
    public Result selectUnCheckedShops() {
        //3.调用service完成角色分配
        List<Shops> targets=shopsService.selectUnCheckedShops(hotelId);
        return new Result(ResultCode.SUCCESS, targets);
    }
}
