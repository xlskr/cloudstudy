package com.xulei.cn.entities.prizesquiz;

import lombok.Data;

import java.util.Date;

@Data
public class GuessInfo {
    private String id;
    private String pqId;
    private String name;
    private Integer num;
    //奖品名称
    private String awardsName;
    //竞猜id
    private String childId;
    private String childName;
    private Date createTime;
}
