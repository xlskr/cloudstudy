package com.xulei.cn.fegin;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value="system",path = "/sys")
public interface SystemService {

    @RequestMapping(value="/isPermitted",method = RequestMethod.GET)
    public boolean isPermitted(@RequestParam(value = "requestURI",required = false) String requestURI, @RequestHeader(value = "Authorization",required = false) String Authorization);

}
