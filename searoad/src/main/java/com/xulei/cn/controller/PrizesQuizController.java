package com.xulei.cn.controller;


import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.ResultCode;
import com.xulei.cn.entities.prizesquiz.PrizesQuiz;
import com.xulei.cn.service.PrizesQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 有奖竞猜父类
 */
@RestController
@RequestMapping(value="/prizesQuiz")
public class PrizesQuizController extends BaseController{

    @Autowired
    private PrizesQuizService prizesQuizService;


    //
    @PostMapping
    public Result add(@RequestBody PrizesQuiz prizesQuiz){
        if(prizesQuizService.add(prizesQuiz)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    @PutMapping
    public Result edit(@RequestBody PrizesQuiz prizesQuiz){
        if(prizesQuizService.edit(prizesQuiz)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    //根据id查看详情
    @GetMapping(value="/{id}")
    public Result getDeatilById(@PathVariable String id){
        return new Result(ResultCode.SUCCESS,prizesQuizService.getDeatilById(id));
    }


    @GetMapping(value="/getDeatilByPid/{id}")
    public HashMap getDeatilByPid(@PathVariable(value = "id")  String id){
        return prizesQuizService.getDeatilByPid(id);
    };

    //查询全部 并且分页
    @GetMapping
    public Result findAll(@RequestParam Map map){
        return new Result(ResultCode.SUCCESS, prizesQuizService.findAll(map));
    }

    @DeleteMapping(value="/{id}")
    public Result delete(@PathVariable String id){
        if(prizesQuizService.delete(id)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    //更改抽奖状态
    @PutMapping(value = "/{id}")
    public Result editStatus(@PathVariable String id){
        if(prizesQuizService.editStatus(id)){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }
}
