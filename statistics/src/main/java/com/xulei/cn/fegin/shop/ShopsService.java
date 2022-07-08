package com.xulei.cn.fegin.shop;

import com.xulei.cn.entities.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="shops",path = "/shopsMenu/shops")
public interface ShopsService {

    @GetMapping(value = "/selectCheckedShops/{roleId}")
    public Result selectCheckedShops(@PathVariable(value = "roleId") String roleId);
}
