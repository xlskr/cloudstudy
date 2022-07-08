package com.xulei.cn.controller;

import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.ResultCode;
import com.xulei.cn.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 首页调用(客户端)
 */
@RestController
@RequestMapping(value = "/shopsStatisMenu")
public class ShopsStatisController extends BaseController {

    @Autowired
    private MainService mainService;


    /**
     *  门店业绩统计
     * @param map key:timeStart timeEnd roleId
     * @return
     */
    @GetMapping(value = "/mdyjtj")
    public Result mdyjtj(@RequestParam Map map){
            //达标
            return new Result(ResultCode.SUCCESS,mainService.mdyjtj(map));
    }

    /**
     *  门店业绩统计
     * @param
     * @param map key:timeStart timeEnd shoopsId  roleId
     * @return
     */
    @GetMapping(value = "/mdyjmbtj")
    public Result mdyjmbtj(@RequestParam Map map){
        //达标
        return new Result(ResultCode.SUCCESS,mainService.mdyjmbtj(map));
    }


    /**
     *  员工业绩统计
     * @param
     * @param map key:timeStart timeEnd shoopsId type targetId(非必传)  roleId
     * @return
     */
    @GetMapping(value = "/ygyjtj")
    public Result ygyjtj(@RequestParam Map map){
        //达标
        return new Result(ResultCode.SUCCESS,mainService.ygyjtj(map));
    }

    /**
     *  员工业绩完成情况统计
     * @param
     * @param map key:time shoopsId roleId type targetId  userId(非必传) roleId
     * @return
     */
    @GetMapping(value = "/ygyjmbtj")
    public Result ygyjmbtj(@RequestParam Map map){
        //达标
        return new Result(ResultCode.SUCCESS,mainService.ygyjmbtj(map));
    }


}
