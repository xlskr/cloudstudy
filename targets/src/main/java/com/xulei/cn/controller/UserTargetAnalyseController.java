package com.xulei.cn.controller;

import com.xulei.cn.service.UserTargetAnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "targetMenu/userTargetAnalyse")
public class UserTargetAnalyseController{

    @Autowired
    private UserTargetAnalyseService userTargetAnalyseService;

    @GetMapping(value = "/getDb")
    List<Map> getDb(@RequestParam Map map){
        return userTargetAnalyseService.getDb(map);
    }

    @GetMapping(value = "/getWdb")
    List<Map> getWdb(@RequestParam Map map){
        return userTargetAnalyseService.getWdb(map);
    }

    @GetMapping(value = "/getMoreDb")
    List<Map> getMoreDb(@RequestParam Map map){
        return userTargetAnalyseService.getMoreDb(map);
    }

    @GetMapping(value = "/getMoreWdb")
    List<Map> getMoreWdb(@RequestParam Map map){
        return userTargetAnalyseService.getMoreWdb(map);
    }

    @GetMapping(value = "/getDbDetail")
    List<Map> getDbDetail(@RequestParam Map map){
        return userTargetAnalyseService.getDbDetail(map);
    }

    @GetMapping(value = "/getWdbDetail")
    List<Map> getWdbDetail(@RequestParam Map map){
        return userTargetAnalyseService.getWdbDetail(map);
    }

    @GetMapping(value = "/getFwzbtj")
    List<Map> getFwzbtj(@RequestParam Map map){
        return userTargetAnalyseService.getFwzbtj(map);
    }

    @GetMapping(value = "/getFwzbtjOneShopsDeatil")
    Object getFwzbtjOneShopsDeatil(@RequestParam Map map){
        return userTargetAnalyseService.getFwzbtjOneShopsDeatil(map);
    }

    @GetMapping(value = "/getFwzbtjMoreShopsDeatil")
    Object getFwzbtjMoreShopsDeatil(@RequestParam Map map){
        return userTargetAnalyseService.getFwzbtjMoreShopsDeatil(map);
    }

    @GetMapping(value = "/getsdqsByWeek")
    Map getsdqsByWeek(@RequestParam Map map){
        return userTargetAnalyseService.getsdqsByWeek(map);
    }

    @GetMapping(value = "/getsdqsByMonth")
    Map getsdqsByMonth(@RequestParam Map map){
        return userTargetAnalyseService.getsdqsByMonth(map);

    }

    @GetMapping(value = "/getSdzbWeekDetail")
    List<Map> getSdzbWeekDetail(@RequestParam Map map){
        return userTargetAnalyseService.getSdzbWeekDetail(map);
    }

    @GetMapping(value = "/getSdzbMonthDetail")
    Map getSdzbMonthDetail(@RequestParam Map map){
        return userTargetAnalyseService.getSdzbMonthDetail(map);
    }

    @GetMapping(value = "/userTargetAnalyse/mdyjtj")
    List<Map> mdyjtj(@RequestParam Map map){
        return userTargetAnalyseService.mdyjtj(map);
    }

    @GetMapping(value = "/userTargetAnalyse/mdyjmbtj")
    List<Map> mdyjmbtj(@RequestParam Map map){
        return userTargetAnalyseService.mdyjmbtj(map);
    }

    @GetMapping(value = "/userTargetAnalyse/ygyjtj")
    List<Map> ygyjtj(@RequestParam Map map){
        return userTargetAnalyseService.ygyjtj(map);
    }

    @GetMapping(value = "/userTargetAnalyse/ygyjmbtj")
    List<Map> ygyjmbtj(@RequestParam Map map){
        return userTargetAnalyseService.ygyjmbtj(map);
    }
}
