package com.xulei.cn.controller;

import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.ResultCode;
import com.xulei.cn.utils.WxUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class WxController {


    @RequestMapping(value="/wxShare", method = RequestMethod.GET)
    public Result wxShare(@RequestParam String url) {
        return new Result(ResultCode.SUCCESS,WxUtil.sign(url));
    }
}
