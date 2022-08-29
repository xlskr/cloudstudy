package com.xulei.cn.service;

import com.github.pagehelper.PageInfo;
import com.xulei.cn.entities.prizesquiz.PrizesQuiz;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public interface PrizesQuizService {
    

    int add(PrizesQuiz prizesQuiz);

    int edit(PrizesQuiz prizesQuiz);

    PrizesQuiz getDeatilById(String id);

    PageInfo<PrizesQuiz> findAll(Map map);

    int delete(String id);

    HashMap getDeatilByPid(String id);

    boolean editStatus(String id);
}
