package com.xulei.cn.entities.prizesquiz;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PrizesQuiz implements Serializable{

    private String name;
    private String id;
    private String city;
    private String lc;
    private String pic;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    private String describe;
    private Date createTime;
    private String pid;

    //8月3号需求整改新增三个字段
    private String awardsName;
    private Integer num;
    //抽奖状态
    private Integer status;


}
