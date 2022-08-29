package com.xulei.cn.controller;

import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.ResultCode;
import com.xulei.cn.entities.prizesquiz.PrizeChild;
import com.xulei.cn.service.PrizeChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value="/prizesQuiz/guess/prizeChild")
public class PrizeChildController {

    @Autowired
    private PrizeChildService prizeChildService;


    //
    @PostMapping
    public Result add(@RequestBody PrizeChild prizeChild){
        int add = prizeChildService.add(prizeChild);
        if(add >0){
            return Result.SUCCESS();
        }else{
            if(add==-2){
                return new Result(10001,"名称重复",false);
            }
            return Result.FAIL();
        }
    }

    @PutMapping
    public Result edit(@RequestBody PrizeChild PrizeChild){
        int edit = prizeChildService.edit(PrizeChild);
        if(edit >0){
            return Result.SUCCESS();
        }else{
            if(edit==-2){
                return new Result(10001,"名称重复",false);
            }
            return Result.FAIL();
        }
    }

    //根据id查看详情
    @GetMapping(value="/{id}")
    public Result getDeatilById(@PathVariable String id){
        return new Result(ResultCode.SUCCESS,prizeChildService.getDeatilById(id));
    }

    //查询全部 并且分页
    @GetMapping
    public Result findChidAll(@RequestParam Map map){
        return new Result(ResultCode.SUCCESS, prizeChildService.findAll(map));
    }

    @DeleteMapping(value="/{id}")
    public Result delete(@PathVariable String id){
        if(prizeChildService.delete(id)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

}
