package com.xulei.cn.service;

import com.github.pagehelper.PageInfo;
import com.xulei.cn.entities.prizesquiz.GuessInfo;
import com.xulei.cn.entities.prizesquiz.PrizesQuiz;

import java.util.List;
import java.util.Map;

public interface GuessPrizeService {
    int add(GuessInfo guessInfo);

    int edit(GuessInfo guessInfo);

    GuessInfo getDeatilById(String id);

    List<GuessInfo> findAll(Map map);

    int delete(String id);

    int selectResult(String childId);

    Object selectCustomerList(Map guessId);

    int changeGet(String id);
}
