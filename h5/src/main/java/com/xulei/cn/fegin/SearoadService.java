package com.xulei.cn.fegin;

import com.xulei.cn.entities.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@FeignClient(value="searoad",path = "/prizesQuiz")
public interface SearoadService {

    @GetMapping(value="/{id}")
    public Result getDeatilById(@PathVariable(value = "id")  String id);

    @GetMapping(value="/guess")
    public Result findAll(@RequestParam Map map);

    @GetMapping(value="/guess/prizeChild")
    public Result findChidAll(@RequestParam Map map);

    @GetMapping(value="/getDeatilByPid/{id}")
    public HashMap getDeatilByPid(@PathVariable(value = "id")  String id);
}
