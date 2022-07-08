package com.xulei.cn.service;

import com.github.pagehelper.PageInfo;
import com.xulei.cn.entities.shops.Shops;
import com.xulei.cn.entities.shops.UserAndShops;

import java.util.List;
import java.util.Map;

public interface ShopsService {
    int add(Shops shops);

    int edit(Shops shops);

    Shops getDeatilById(String id);

    PageInfo<Shops> findAll(Map map);

    int delete(String id);

    //给门店赋予用户
    int assginShopsUser(UserAndShops userAndShops);

    List<Shops> selectCheckedShops(String roleId);

    List<Shops> selectUnCheckedShops(String hotelId);
}
