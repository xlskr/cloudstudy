package com.xulei.cn.fegin;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

@FeignClient(value="system",path = "/sys")
public interface SystemService {

    @RequestMapping(value="/isPermitted",method = RequestMethod.GET)
    public boolean isPermitted(@RequestParam(value = "requestURI",required = true) String requestURI,
                               @RequestHeader(value = "Authorization",required = false) String Authorization,
                               @RequestParam(value = "method",required = true) HttpMethod method);

}
