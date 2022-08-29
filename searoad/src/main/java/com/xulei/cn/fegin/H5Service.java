package com.xulei.cn.fegin;


import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(value="h5",path = "/customerPrize")
public interface H5Service {
    @PostMapping(value = "/getCustomerListByGuessId")
    PageInfo<Map> getCustomerListByGuessId(@RequestBody Map map);

    @GetMapping(value = "/changeGet")
    int changeGet(@RequestParam(value = "id") String id);

    @PostMapping(value = "/getCustomerListByGuessIdNoPage")
    List<Map> getCustomerListByGuessIdNoPage(Map map);
}
