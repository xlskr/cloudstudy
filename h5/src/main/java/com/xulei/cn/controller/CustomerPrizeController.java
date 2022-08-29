package com.xulei.cn.controller;

import com.github.pagehelper.PageInfo;
import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.ResultCode;
import com.xulei.cn.entities.wechath5.CustomerVo;
import com.xulei.cn.service.CustomerChildService;
import com.xulei.cn.service.CustomerService;
import com.xulei.cn.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/customerPrize")
public class CustomerPrizeController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerChildService customerChildService;

    @Autowired
    private RedisUtil redisUtil;

    //提交
    @PostMapping
    public Result add(@RequestBody CustomerVo customerVo){
        if(redisUtil.get(customerVo.getToken())==null){
            return new Result(ResultCode.UNNOTOKEN);
        }
        if(customerService.submit(customerVo)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    @GetMapping(value = "/getMyguess")
    public Result getMyguess(@RequestParam(value = "token") String token,@RequestParam(value = "id") String id){
        if(redisUtil.get(token)==null){
            return new Result(ResultCode.UNNOTOKEN);
        }
        List<Map> myguess = customerChildService.getMyguess(token, id);
        System.out.println("Json语句"+myguess);
        return new Result(ResultCode.SUCCESS, myguess);
    }

    //获取历史竞猜列表
    @GetMapping(value = "/getMyguessHistory")
    public Result getMyguessHistory(@RequestParam(value = "token") String token,@RequestParam(value = "id") String id){
        if(redisUtil.get(token)==null){
            return new Result(ResultCode.UNNOTOKEN);
        }
        return new Result(ResultCode.SUCCESS,customerChildService.getMyguessHistory(token,id));
    }

    @PostMapping(value = "/getCustomerListByGuessId")
    public PageInfo<Map> getCustomerListByGuessId(@RequestBody Map map){
        return customerChildService.selectCustomerList(map);
    };

    @PostMapping(value = "/getCustomerListByGuessIdNoPage")
    List<Map> getCustomerListByGuessIdNoPage(@RequestBody Map map){
        return customerChildService.getCustomerListByGuessIdNoPage(map);
    };

    @GetMapping(value = "/changeGet")
    public int changeGet(@RequestParam(value = "id") String id){
        return customerChildService.changeGet(id);
    }




}
