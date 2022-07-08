package com.xulei.cn.service;



import com.github.pagehelper.PageInfo;
import com.xulei.cn.entities.system.ShopsUser;

import java.util.Map;

public interface UserService {
    int add(ShopsUser shops);

    int edit(ShopsUser shops);

    ShopsUser getDeatilById(String id);
    
    PageInfo<ShopsUser> findAll(Map map);

    int delete(String id);
}
