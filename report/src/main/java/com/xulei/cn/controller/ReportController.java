package com.xulei.cn.controller;

import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.ResultCode;
import com.xulei.cn.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 首页调用(客户端)
 */
@RestController
@RequestMapping(value = "/ReportMenu")
public class ReportController extends BaseController {

    @Autowired
    private MainService mainService;




    /**
     *  门店业绩统计
     * @param
     * @param map key:timeStart timeEnd shoopsId targetId type
     * @return
     */
    @GetMapping(value = "/mdyjmbtj")
    public Result mdyjmbtj(@RequestParam Map map){
        //达标
        return new Result(ResultCode.SUCCESS,mainService.mdyjmbtj(map));
    }


    /**
     *  员工业绩
     * @param
     * @param map key:timeStart timeEnd shoopsId roleId
     * @return
     */
    @GetMapping(value = "/ygyjmbtj")
    public Result ygyjmbtj(@RequestParam Map map){
        //达标
        return new Result(ResultCode.SUCCESS,mainService.ygyjmbtj(map));
    }


}
