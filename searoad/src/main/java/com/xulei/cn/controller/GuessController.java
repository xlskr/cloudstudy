package com.xulei.cn.controller;

import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.ResultCode;
import com.xulei.cn.entities.prizesquiz.GuessInfo;
import com.xulei.cn.service.GuessPrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value="/prizesQuiz/guess")
public class GuessController {

    @Autowired
    private GuessPrizeService guessPrizeService;

    //
    @PostMapping
    public Result add(@RequestBody GuessInfo guessInfo){
        if(guessPrizeService.add(guessInfo)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    @PutMapping
    public Result edit(@RequestBody GuessInfo guessInfo){
        if(guessPrizeService.edit(guessInfo)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    //根据id查看详情
    @GetMapping(value="/{id}")
    public Result getDeatilById(@PathVariable String id){
        return new Result(ResultCode.SUCCESS,guessPrizeService.getDeatilById(id));
    }

    //查询全部 并且分页
    @GetMapping
    public Result findAll(@RequestParam Map map){
        return new Result(ResultCode.SUCCESS, guessPrizeService.findAll(map));
    }

    @DeleteMapping(value="/{id}")
    public Result delete(@PathVariable String id){
        if(guessPrizeService.delete(id)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    //结果设置
    @GetMapping(value="/selectResult/{childId}")
    public Result selectResult(@PathVariable String childId){
        if(guessPrizeService.selectResult(childId)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    //获取用户列表
    @PostMapping(value="/customerList")
    public Result selectCustomerList(@RequestBody Map guessId){
       return new Result(ResultCode.SUCCESS,guessPrizeService.selectCustomerList(guessId));
    }

    //更改用户中奖
    @GetMapping(value="/customer/changeGet")
    public Result changeGet(@RequestParam String ids) {
        if (guessPrizeService.changeGet(ids) > 0) {
            return Result.SUCCESS();
        } else {
            return Result.FAIL();
        }
    }

}
