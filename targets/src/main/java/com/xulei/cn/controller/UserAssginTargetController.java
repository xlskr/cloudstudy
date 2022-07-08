package com.xulei.cn.controller;

import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.ResultCode;
import com.xulei.cn.entities.target.Target;
import com.xulei.cn.entities.target.UserTarget;
import com.xulei.cn.service.TargetService;
import com.xulei.cn.service.UserTargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "targetMenu/userAssginTarget")
public class UserAssginTargetController {


    @Autowired
    private UserTargetService userTargetService;


    //
    @PostMapping
    public Result add(@RequestBody UserTarget userTarget){
        if(userTargetService.add(userTarget)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    @PutMapping
    public Result edit(@RequestBody UserTarget userTarget){
        if(userTargetService.edit(userTarget)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    //根据id查看详情
    @GetMapping(value="/{id}")
    public Result getDeatilById(@PathVariable String id){
        return new Result(ResultCode.SUCCESS,userTargetService.getDeatilById(id));
    }

    //查询全部 并且分页 hotelId必备
    @GetMapping
    public Result findAll(@RequestParam Map map){
        return new Result(ResultCode.SUCCESS, userTargetService.findAll(map));
    }



    @DeleteMapping(value="/{id}")
    public Result delete(@PathVariable String id){
        if(userTargetService.delete(id)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }
}
