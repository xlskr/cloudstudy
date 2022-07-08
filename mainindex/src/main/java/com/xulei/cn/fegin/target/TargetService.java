package com.xulei.cn.fegin.target;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value="target",path = "/targetMenu")
public interface TargetService {


    @GetMapping(value = "/userTargetAnalyse/getDb")
    List<Map> getDb(@RequestParam Map map);

    @GetMapping(value = "/userTargetAnalyse/getWdb")
    List<Map> getWdb(@RequestParam Map map);

    @GetMapping(value = "/userTargetAnalyse/getMoreDb")
    List<Map> getMoreDb(@RequestParam Map map);

    @GetMapping(value = "/userTargetAnalyse/getMoreWdb")
    List<Map> getMoreWdb(@RequestParam Map map);



    @GetMapping(value = "/userTargetAnalyse/getDbDetail")
    List<Map> getDbDetail(@RequestParam Map map);

    @GetMapping(value = "/userTargetAnalyse/getWdbDetail")
    List<Map> getWdbDetail(@RequestParam Map map);

    @GetMapping(value = "/userTargetAnalyse/getFwzbtj")
    List<Map> getFwzbtj(@RequestParam Map map);

    @GetMapping(value = "/userTargetAnalyse/getFwzbtjOneShopsDeatil")
    Object getFwzbtjOneShopsDeatil(@RequestParam Map map);

    @GetMapping(value = "/userTargetAnalyse/getFwzbtjMoreShopsDeatil")
    Object getFwzbtjMoreShopsDeatil(@RequestParam Map map);

    @GetMapping(value = "/userTargetAnalyse/getsdqsByWeek")
    Map getsdqsByWeek(@RequestParam Map map);

    @GetMapping(value = "/userTargetAnalyse/getsdqsByMonth")
    Map getsdqsByMonth(@RequestParam Map map);

    @GetMapping(value = "/userTargetAnalyse/getSdzbWeekDetail")
    List<Map> getSdzbWeekDetail(@RequestParam Map map);

    @GetMapping(value = "/userTargetAnalyse/getSdzbMonthDetail")
    Map getSdzbMonthDetail(@RequestParam Map map);


}
