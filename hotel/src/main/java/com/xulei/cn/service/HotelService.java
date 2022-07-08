package com.xulei.cn.service;

import com.github.pagehelper.PageInfo;
import com.xulei.cn.entities.hotel.Hotel;
import com.xulei.cn.entities.system.Role;

import java.util.Map;

public interface HotelService {
    int addHotel(Hotel hotel);

    int editHotel(Hotel hotel);

    Hotel getDeatilById(String id);

    PageInfo<Hotel> findAll(Map map);

    int delete(String id);
}
