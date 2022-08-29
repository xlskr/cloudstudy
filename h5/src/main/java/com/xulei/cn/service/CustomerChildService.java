package com.xulei.cn.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface CustomerChildService {
    void save(Map map);

    PageInfo<Map> selectCustomerList(Map guessId);

    int changeGet(String id);

    List<Map> getMyguess(String token,String id);

    List<Map> getCustomerListByGuessIdNoPage(Map map);

    List<Map> getMyguessHistory(String token,String id);
}
