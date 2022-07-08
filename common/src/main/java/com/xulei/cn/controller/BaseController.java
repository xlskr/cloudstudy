package com.xulei.cn.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseController {

    protected HttpServletRequest httpServletRequest;
    protected HttpServletResponse httpServletResponse;
    protected String hotelId;
    protected String roleId;


    /**
     * 在进入控制器方法执行的内容
     * @param request
     * @param response
     */
    @ModelAttribute
    protected void setRequestAndResponse(HttpServletRequest request,HttpServletResponse response){
        this.httpServletRequest=request;
        this.httpServletResponse=response;
        if(!StringUtils.isEmpty(request.getParameter("hotelId"))){
            this.hotelId=hotelId;
        }

        if(!StringUtils.isEmpty(request.getParameter("roleId"))){
            this.roleId=roleId;
        }
    }

}
