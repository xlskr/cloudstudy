package com.xulei.cn.fegin.target;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value="target",path = "/targetMenu")
public interface TargetService {

    @GetMapping(value = "/userTargetAnalyse/mdyjtj")
    List<Map> mdyjtj(@RequestParam Map map);

    @GetMapping(value = "/userTargetAnalyse/mdyjmbtj")
    List<Map> mdyjmbtj(@RequestParam Map map);

    @GetMapping(value = "/userTargetAnalyse/ygyjtj")
    List<Map> ygyjtj(@RequestParam Map map);

    @GetMapping(value = "/userTargetAnalyse/ygyjmbtj")
    List<Map> ygyjmbtj(@RequestParam Map map);



}
