package com.xulei.cn.service;

import com.github.pagehelper.PageInfo;
import com.xulei.cn.entities.prizesquiz.PrizeChild;

import java.util.List;
import java.util.Map;

public interface PrizeChildService {
    int add(PrizeChild prizeChild);

    int edit(PrizeChild prizeChild);

    PrizeChild getDeatilById(String id);

    List<PrizeChild> findAll(Map map);

    int delete(String id);

    int checkRepatName(PrizeChild prizeChild);
}
