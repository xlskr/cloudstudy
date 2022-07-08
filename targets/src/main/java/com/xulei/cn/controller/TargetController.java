package com.xulei.cn.controller;

import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.ResultCode;
import com.xulei.cn.entities.target.Target;
import com.xulei.cn.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 店铺菜单--店铺管理
 */
@RestController
@RequestMapping(value = "targetMenu/target")
public class TargetController extends BaseController{


    @Autowired
    private TargetService targetService;


    //
    @PostMapping
    public Result add(@RequestBody Target target){
        if(targetService.add(target)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    @PutMapping
    public Result edit(@RequestBody Target shops){
        if(targetService.edit(shops)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    //根据id查看详情
    @GetMapping(value="/{id}")
    public Result getDeatilById(@PathVariable String id){
        return new Result(ResultCode.SUCCESS,targetService.getDeatilById(id));
    }

    //查询全部 并且分页 hotelId必备
    @GetMapping
    public Result findAll(@RequestParam Map map){
        return new Result(ResultCode.SUCCESS, targetService.findAll(map));
    }



    @DeleteMapping(value="/{id}")
    public Result delete(@PathVariable String id){
        if(targetService.delete(id)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }


    /**
     .
     * 酒店下已未分配的指标
     * @param roleId
     * @return
     */
    @GetMapping(value = "/selectCheckedTargets/{roleId}")
    public Result selectCheckedTargets(@PathVariable String roleId) {
        //3.调用service完成角色分配
        List<Target> targets=targetService.selectCheckedTargets(roleId);
        return new Result(ResultCode.SUCCESS,targets);
    }

    /**
     * 酒店下还未分配的指标
     * @return
     */
    @GetMapping(value = "/selectUnCheckedTargets")
    public Result selectUnCheckedTargets() {
        //3.调用service完成角色分配
        List<Target> targets=targetService.selectUnCheckedTargets(hotelId);
        return new Result(ResultCode.SUCCESS, targets);
    }


    /**
     * 根据shopsId找出target 在首页展示的时候需要用到 还有指标设置菜单也用到了
     * @return
     */
    @GetMapping(value = "/selectCheckedTargetsByShopsId/{shopsId}")
    public Result selectCheckedTargetsByShopsId(@PathVariable String shopsId) {
        //3.调用service完成角色分配
        List<Target> targets=targetService.selectCheckedTargetsByShopsId(shopsId);
        return new Result(ResultCode.SUCCESS, targets);
    }
}
