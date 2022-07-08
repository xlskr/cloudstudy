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
@RequestMapping(value = "/mainIdex")
public class MainIndexController extends BaseController{

    @Autowired
    private MainService mainService;



    /**
     * 服务业绩指标  达标和未达标
     * @param flag 1达标
     * @param shopsId
     * @param time
     * @param type
     * @param targetId
     * @return
     */
    @GetMapping(value = "/fwzbph/{flag}")
    public Result fwzbph(@PathVariable String flag, @RequestParam String shopsId,@RequestParam String time,@RequestParam String type,@RequestParam String targetId){
        if("1".equals(flag)){
            //达标
            return new Result(ResultCode.SUCCESS,mainService.getDb(shopsId,time,roleId,type,targetId));
        }else  if("2".equals(flag)){
            //未达标
            return new Result(ResultCode.SUCCESS,mainService.getWdb(shopsId,time,roleId,type,targetId));
        }else if("3".equals(flag)){
            //未达标
            return new Result(ResultCode.SUCCESS,mainService.getMoreDb(shopsId,time,roleId,type,targetId));
        }else{
            return new Result(ResultCode.SUCCESS,mainService.getMoreWdb(shopsId,time,roleId,type,targetId));
        }
    }

    @GetMapping(value = "/fwzbphDetail/{flag}")
    public Result fwzbphDetail(@PathVariable String flag, @RequestParam String shopsId,@RequestParam String time,@RequestParam String type,@RequestParam String targetId,@RequestParam String userId){
        if("1".equals(flag)){
            //达标
            return new Result(ResultCode.SUCCESS,mainService.getDbDeatil(shopsId,time,roleId,type,targetId,userId));
        }else{
            //未达标
            return new Result(ResultCode.SUCCESS,mainService.getWdbDeatil(shopsId,time,roleId,type,targetId,userId));
        }
    }

    /**
     * 服务指标统计
     *
     * 参数 timeStart 2022-05  2024-07
     * @param map key值 timeStart timeEnd shopsId(有值代表单个门店 ""就是所有门店) roleId
     * @return
     */
    @GetMapping(value = "/fwzbtj")
    public Result fwzbtj(@PathVariable String flag, @RequestParam Map map){
        return new Result(ResultCode.SUCCESS,mainService.fwzbtj(map));
    }


    /**
     * 服务指标详情
     *
     * 参数 timeStart 2022-05  2024-07
     * @param map key值 timeStart timeEnd shopsId(有值代表单个门店 ""就是所有门店) roleId type targetId
     * @return
     */
    @GetMapping(value = "/fwzbtjDeatil")
    public Result fwzbtjDeatil(@PathVariable String flag, @RequestParam Map map){
        return new Result(ResultCode.SUCCESS,mainService.fwzbtjDeatil(map));
    }

    //3大指标周趋势  月趋势
    @GetMapping(value = "/sdqs/{flag}")
    public Result sdqs(@PathVariable String flag, @RequestParam Map map){
        if("1".equals(flag)){
            return new Result(ResultCode.SUCCESS,mainService.getsdqsByWeek(map));
        }else{
            return new Result(ResultCode.SUCCESS,mainService.getsdqsByMonth(map));
        }
    }

    //3大指标周趋势详情  月趋势详情
    @GetMapping(value = "/sdqsDetail/{flag}")
    public Result sdqsDetail(@PathVariable String flag, @RequestParam Map map){
        if("1".equals(flag)){
            return new Result(ResultCode.SUCCESS,mainService.getSdzbWeekDetail(map));
        }else{
            return new Result(ResultCode.SUCCESS,mainService.getSdzbMonthDetail(map));
        }
    }

}
