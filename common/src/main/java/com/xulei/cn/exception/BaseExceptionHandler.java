package com.xulei.cn.exception;


import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.ResultCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(HttpServletRequest request, HttpServletResponse response, Exception e){
            //检查到异常会处理这个方法
            e.printStackTrace();
            return new Result(ResultCode.EXCEPTION);
    }
}
