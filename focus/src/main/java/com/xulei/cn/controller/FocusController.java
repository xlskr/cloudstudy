package com.xulei.cn.controller;

import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.ResultCode;
import com.xulei.cn.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 重点关注
 */
@RestController
@RequestMapping(value = "/focusMenu")
public class FocusController extends BaseController{

    @Autowired
    private MainService mainService;


    /**
     *
     * @param flag   1达标 2未达标
     * @param shopsId
     * @param time
     * @param type
     * @param targetId
     * @param userId
     * @return
     */
    @GetMapping(value = "/yjdbOrWdbList/{flag}")
    public Result yjdbOrWdbList(@PathVariable String flag, @RequestParam String shopsId,@RequestParam String time,@RequestParam String type,@RequestParam String targetId,@RequestParam String userId){
        if("1".equals(flag)){
            //达标
            return new Result(ResultCode.SUCCESS,mainService.getDbDeatil(shopsId,time,roleId,type,targetId,userId));
        }else{
            //未达标
            return new Result(ResultCode.SUCCESS,mainService.getWdbDeatil(shopsId,time,roleId,type,targetId,userId));
        }
    }


    /**
     *
     * @param
     * @param map key值 time shopsId(有值代表单个门店 ""就是所有门店) roleId  type,targetId
     * @return
     */
    @GetMapping(value = "/sdqsDetail")
    public Result sdqsDetail( @RequestParam Map map){
        Map<String,Object> map1=new HashMap<>();
        map1.put("data",mainService.fwzbtjDeatil(map));
        map1.put("list",mainService.getSdzbMonthDetail(map));
        return new Result(ResultCode.SUCCESS,map1);
    }


}
