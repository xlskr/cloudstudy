package com.xulei.cn.controller;

import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.ResultCode;
import com.xulei.cn.entities.team.Team;
import com.xulei.cn.service.TeamService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping(value = "/teams")
public class TeamController extends BaseController{
    @Resource
    private TeamService teamService;

    @PostMapping
    public Result add(@Validated @RequestBody Team team){
        if(teamService.add(team)>0){
            return Result.SUCCESS();
        }else {
            return Result.FAIL();
        }
    }

    @PutMapping
    public Result update(@Validated @RequestBody Team team){
        if(teamService.update(team)>0){
            return Result.SUCCESS();
        }else {
            return Result.FAIL();
        }
    }

    @GetMapping(value="/{id}")
    public Result getDeatilById(@PathVariable String id){
        return new Result(ResultCode.SUCCESS,teamService.getDeatilById(id));
    }


    @GetMapping
    public Result getAllByPage(@RequestParam Map map){
        return new Result(ResultCode.SUCCESS,teamService.getAllByPage(map));
    }

    //删除
    @DeleteMapping(value="/{id}")
    public Result delete(@PathVariable String id){
        if(teamService.delete(id)>0){
            return Result.SUCCESS();
        }else {
            return Result.FAIL();
        }
    }
}
