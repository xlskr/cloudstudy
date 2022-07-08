package com.xulei.cn.controller;


import com.xulei.cn.entities.Result;
import com.xulei.cn.entities.ResultCode;
import com.xulei.cn.entities.hotel.Hotel;
import com.xulei.cn.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value="/hotelMenu/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;


    //
    @PostMapping
    public Result addHotel(@RequestBody Hotel hotel){
        if(hotelService.addHotel(hotel)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    @PutMapping
    public Result editHotel(@RequestBody Hotel hotel){
        if(hotelService.editHotel(hotel)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    //根据id查看详情
    @GetMapping(value="/{id}")
    public Result getDeatilById(@PathVariable String id){
        return new Result(ResultCode.SUCCESS,hotelService.getDeatilById(id));
    }

    //查询全部 并且分页
    @GetMapping
    public Result findAll(@RequestParam Map map){
        return new Result(ResultCode.SUCCESS, hotelService.findAll(map));
    }



    @DeleteMapping(value="/{id}")
    public Result delete(@PathVariable String id){
        if(hotelService.delete(id)>0){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }
}
